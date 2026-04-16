package com.farmacia.reportes.service;

import com.farmacia.reportes.model.ReporteVenta;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ReportesService {

    private final List<ReporteVenta> ventas = new ArrayList<>();

    public ReporteVenta registrarVenta(String ventaId, String medicamento, int cantidad,
                                       double total, String cliente) {
        ReporteVenta reporte = new ReporteVenta(ventaId, medicamento, cantidad, total,
                                                 cliente, LocalDateTime.now());
        ventas.add(reporte);
        return reporte;
    }

    public Map<String, Object> generarResumen() {
        Map<String, Object> resumen = new HashMap<>();
        resumen.put("totalVentas", ventas.size());
        resumen.put("ingresoTotal", ventas.stream().mapToDouble(ReporteVenta::getTotal).sum());
        resumen.put("totalUnidades", ventas.stream().mapToInt(ReporteVenta::getCantidad).sum());

        Map<String, Long> ventasPorMedicamento = new HashMap<>();
        ventas.forEach(v -> ventasPorMedicamento.merge(v.getMedicamento(), (long) v.getCantidad(), Long::sum));
        resumen.put("ventasPorMedicamento", ventasPorMedicamento);
        resumen.put("ultimasVentas", ventas.size() > 5 ? ventas.subList(ventas.size() - 5, ventas.size()) : ventas);

        return resumen;
    }

    public List<ReporteVenta> obtenerTodasLasVentas() { return ventas; }

    public String generarReporteXml() {
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<ReporteVentas xmlns=\"http://farmacia.com/reportes\">\n");
        xml.append("  <totalVentas>").append(ventas.size()).append("</totalVentas>\n");
        xml.append("  <ingresoTotal>").append(ventas.stream().mapToDouble(ReporteVenta::getTotal).sum()).append("</ingresoTotal>\n");
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
        xml.append("</ReporteVentas>");
        return xml.toString();
    }
}
