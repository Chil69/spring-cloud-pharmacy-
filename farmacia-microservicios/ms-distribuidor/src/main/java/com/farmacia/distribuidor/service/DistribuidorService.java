package com.farmacia.distribuidor.service;

import com.farmacia.distribuidor.client.FarmaciaClient;
import com.farmacia.distribuidor.model.LoteMedicamento;
import com.farmacia.distribuidor.repository.LoteMedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DistribuidorService {

    @Autowired
    private LoteMedicamentoRepository repository;

    @Autowired
    private FarmaciaClient farmaciaClient;

    public LoteMedicamento recibirLote(LoteMedicamento lote) {
        lote.setEstado("EN_DISTRIBUIDOR");
        lote.setOrigen("LABORATORIO");
        lote.setDestino("FARMACIA");
        return repository.save(lote);
    }

    public List<LoteMedicamento> obtenerInventario() {
        return repository.findAll();
    }

    public LoteMedicamento despacharAFarmacia(String loteId) {
        Optional<LoteMedicamento> loteOpt = repository.findById(loteId);
        if (loteOpt.isPresent()) {
            LoteMedicamento lote = loteOpt.get();
            lote.setEstado("DESPACHADO");
            repository.save(lote);

            LoteMedicamento respuesta = farmaciaClient.recibirLote(lote);

            lote.setEstado("ENVIADO_A_FARMACIA");
            repository.save(lote);
            return respuesta;
        }
        throw new RuntimeException("Lote no encontrado: " + loteId);
    }
}
