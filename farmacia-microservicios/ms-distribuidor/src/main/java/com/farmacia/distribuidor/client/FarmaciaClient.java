package com.farmacia.distribuidor.client;

import com.farmacia.distribuidor.model.LoteMedicamento;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-farmacia")
public interface FarmaciaClient {
    @PostMapping("/farmacia/recibir")
    LoteMedicamento recibirLote(@RequestBody LoteMedicamento lote);
}
