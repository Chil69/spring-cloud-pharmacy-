package com.farmacia.reportes.service;

import com.farmacia.reportes.model.ReporteVenta;
import com.farmacia.reportes.repository.ReporteVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ReportesService {

    @Autowired
    private ReporteVentaRepository repository;

    public ReporteVenta registrarVenta(String ventaId, String medicamento, int cantidad,
                                       double total, String cliente) {
        ReporteVenta reporte = new ReporteVenta(ventaId, medicamento, cantidad, total, cliente);
        return repository.save(reporte);
    }

    public List<ReporteVenta> obtenerTodasLasVentas() {
        return repository.findAll();
    }

    public Map<String, Object> generarResumen() {
        List<ReporteVenta> ventas = repository.findAll();
        int totalVentas = ventas.size();
        double ingresoTotal = ventas.stream().mapToDouble(ReporteVenta::getTotal).sum();
        int totalUnidades = ventas.stream().mapToInt(ReporteVenta::getCantidad).sum();

        Map<String, Object> resumen = new HashMap<>();
        resumen.put("totalVentas", totalVentas);
        resumen.put("ingresoTotal", ingresoTotal);
        resumen.put("totalUnidades", totalUnidades);
        return resumen;
    }

    public String generarReporteXml() {
        List<ReporteVenta> ventas = repository.findAll();
        Map<String, Object> resumen = generarResumen();

        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<reporte>\n");
        xml.append("  <resumen>\n");
        xml.append("    <totalVentas>").append(resumen.get("totalVentas")).append("</totalVentas>\n");
        xml.append("    <ingresoTotal>").append(resumen.get("ingresoTotal")).append("</ingresoTotal>\n");
        xml.append("    <totalUnidades>").append(resumen.get("totalUnidades")).append("</totalUnidades>\n");
        xml.append("  </resumen>\n");
        xml.append("  <ventas>\n");
        for (ReporteVenta v : ventas) {
            xml.append("    <venta>\n");
            xml.append("      <ventaId>").append(v.getVentaId()).append("</ventaId>\n");
            xml.append("      <medicamento>").append(v.getMedicamento()).append("</medicamento>\n");
            xml.append("      <cantidad>").append(v.getCantidad()).append("</cantidad>\n");
            xml.append("      <total>").append(v.getTotal()).append("</total>\n");
            xml.append("      <cliente>").append(v.getCliente()).append("</cliente>\n");
            xml.append("    </venta>\n");
        }
        xml.append("  </ventas>\n");
        xml.append("</reporte>");
        return xml.toString();
    }
}
