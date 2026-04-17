package com.farmacia.laboratorio.client;

import com.farmacia.laboratorio.model.LoteMedicamento;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-distribuidor")
public interface DistribuidorClient {
    @PostMapping("/distribuidor/recibir")
    LoteMedicamento recibirLote(@RequestBody LoteMedicamento lote);
}
