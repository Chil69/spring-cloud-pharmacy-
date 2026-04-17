package com.farmacia.laboratorio.service;

import com.farmacia.laboratorio.client.DistribuidorClient;
import com.farmacia.laboratorio.model.LoteMedicamento;
import com.farmacia.laboratorio.repository.LoteMedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LaboratorioService {

    @Autowired
    private LoteMedicamentoRepository repository;

    @Autowired
    private DistribuidorClient distribuidorClient;

    public LoteMedicamento producirLote(LoteMedicamento lote) {
        lote.setOrigen("LABORATORIO");
        lote.setDestino("DISTRIBUIDOR");
        lote.setFechaProduccion(LocalDate.now());
        lote.setFechaVencimiento(LocalDate.now().plusYears(2));
        lote.setEstado("PRODUCIDO");
        return repository.save(lote);
    }

    public List<LoteMedicamento> obtenerLotes() {
        return repository.findAll();
    }

    public LoteMedicamento enviarADistribuidor(String loteId) {
        Optional<LoteMedicamento> loteOpt = repository.findById(loteId);
        if (loteOpt.isPresent()) {
            LoteMedicamento lote = loteOpt.get();
            lote.setEstado("EN_TRANSITO");
            repository.save(lote);

            LoteMedicamento respuesta = distribuidorClient.recibirLote(lote);

            lote.setEstado("ENVIADO_A_DISTRIBUIDOR");
            repository.save(lote);
            return respuesta;
        }
        throw new RuntimeException("Lote no encontrado: " + loteId);
    }
}
