package com.farmacia.reportes.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Map;

@FeignClient(name = "ms-distribuidor")
public interface DistribuidorClient {
    @GetMapping("/distribuidor/inventario")
    List<Map<String, Object>> obtenerInventario();
}
