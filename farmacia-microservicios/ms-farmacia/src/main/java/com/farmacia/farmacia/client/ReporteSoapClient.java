package com.farmacia.farmacia.client;

import com.farmacia.farmacia.model.LoteMedicamento;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(name = "ms-reportes-soap")
public interface ReporteSoapClient {

    @PostMapping("/reportes/registrar-venta")
    Map<String, Object> registrarVenta(@RequestBody Map<String, Object> datosVenta);

    @GetMapping("/reportes/resumen")
    Map<String, Object> obtenerResumen();
}
