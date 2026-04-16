package com.farmacia.farmacia.service;

import com.farmacia.farmacia.client.ReporteSoapClient;
import com.farmacia.farmacia.model.LoteMedicamento;
import com.farmacia.farmacia.model.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class FarmaciaService {

    private final List<LoteMedicamento> stock = new ArrayList<>();
    private final List<Venta> ventas = new ArrayList<>();

    @Autowired
    private ReporteSoapClient reporteSoapClient;

    public LoteMedicamento recibirLote(LoteMedicamento lote) {
        lote.setEstado("EN_FARMACIA");
        lote.setDestino("CLIENTE_FINAL");
        stock.add(lote);
        return lote;
    }

    public List<LoteMedicamento> obtenerStock() { return stock; }

    public Venta venderMedicamento(String loteId, int cantidad, String cliente) {
        Optional<LoteMedicamento> loteOpt = stock.stream()
            .filter(l -> l.getLoteId().equals(loteId)).findFirst();

        if (loteOpt.isPresent()) {
            LoteMedicamento lote = loteOpt.get();
            if (lote.getCantidad() < cantidad) {
                throw new RuntimeException("Stock insuficiente. Disponible: " + lote.getCantidad());
            }
            lote.setCantidad(lote.getCantidad() - cantidad);

            Venta venta = new Venta();
            venta.setVentaId("VNT-" + UUID.randomUUID().toString().substring(0, 8));
            venta.setLoteId(loteId);
            venta.setMedicamento(lote.getMedicamento());
            venta.setCantidadVendida(cantidad);
            venta.setFechaVenta(LocalDateTime.now());
            venta.setTotal(cantidad * lote.getPrecioUnitario());
            venta.setCliente(cliente);
            ventas.add(venta);

            // Notificar al servicio de reportes via Feign
            try {
                Map<String, Object> datosVenta = new HashMap<>();
                datosVenta.put("ventaId", venta.getVentaId());
                datosVenta.put("medicamento", venta.getMedicamento());
                datosVenta.put("cantidad", venta.getCantidadVendida());
                datosVenta.put("total", venta.getTotal());
                datosVenta.put("cliente", venta.getCliente());
                reporteSoapClient.registrarVenta(datosVenta);
            } catch (Exception e) {
                System.out.println("No se pudo notificar a reportes: " + e.getMessage());
            }

            return venta;
        }
        throw new RuntimeException("Lote no encontrado: " + loteId);
    }

    public List<Venta> obtenerVentas() { return ventas; }
}
