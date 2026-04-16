package com.farmacia.reportes.endpoint;

import com.farmacia.reportes.model.ReporteVenta;
import com.farmacia.reportes.service.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportes")
public class ReportesRestController {

    @Autowired
    private ReportesService reportesService;

    @PostMapping("/registrar-venta")
    public ResponseEntity<Map<String, Object>> registrarVenta(@RequestBody Map<String, Object> datos) {
        ReporteVenta reporte = reportesService.registrarVenta(
            (String) datos.get("ventaId"),
            (String) datos.get("medicamento"),
            ((Number) datos.get("cantidad")).intValue(),
            ((Number) datos.get("total")).doubleValue(),
            (String) datos.get("cliente")
        );
        return ResponseEntity.ok(Map.of("status", "OK", "ventaId", reporte.getVentaId(),
            "mensaje", "Venta registrada en reportes"));
    }

    @GetMapping("/resumen")
    public ResponseEntity<Map<String, Object>> obtenerResumen() {
        return ResponseEntity.ok(reportesService.generarResumen());
    }

    @GetMapping("/ventas")
    public ResponseEntity<List<ReporteVenta>> obtenerVentas() {
        return ResponseEntity.ok(reportesService.obtenerTodasLasVentas());
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> obtenerReporteXml() {
        return ResponseEntity.ok(reportesService.generarReporteXml());
    }
}
