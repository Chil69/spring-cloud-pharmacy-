package com.farmacia.distribuidor.service;

import com.farmacia.distribuidor.client.FarmaciaClient;
import com.farmacia.distribuidor.model.LoteMedicamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DistribuidorService {

    private final List<LoteMedicamento> inventario = new ArrayList<>();

    @Autowired
    private FarmaciaClient farmaciaClient;

    public LoteMedicamento recibirLote(LoteMedicamento lote) {
        lote.setEstado("EN_DISTRIBUIDOR");
        lote.setOrigen("LABORATORIO");
        lote.setDestino("FARMACIA");
        inventario.add(lote);
        return lote;
    }

    public List<LoteMedicamento> obtenerInventario() { return inventario; }

    public LoteMedicamento despacharAFarmacia(String loteId) {
        Optional<LoteMedicamento> loteOpt = inventario.stream()
            .filter(l -> l.getLoteId().equals(loteId)).findFirst();
        if (loteOpt.isPresent()) {
            LoteMedicamento lote = loteOpt.get();
            lote.setEstado("DESPACHADO");
            LoteMedicamento respuesta = farmaciaClient.recibirLote(lote);
            lote.setEstado("ENVIADO_A_FARMACIA");
            return respuesta;
        }
        throw new RuntimeException("Lote no encontrado: " + loteId);
    }
}
