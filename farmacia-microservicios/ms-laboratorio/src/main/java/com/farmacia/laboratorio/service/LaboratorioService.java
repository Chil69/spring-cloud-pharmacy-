package com.farmacia.laboratorio.service;

import com.farmacia.laboratorio.client.DistribuidorClient;
import com.farmacia.laboratorio.model.LoteMedicamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LaboratorioService {

    private final List<LoteMedicamento> lotes = new ArrayList<>();

    @Autowired
    private DistribuidorClient distribuidorClient;

    public LoteMedicamento producirLote(LoteMedicamento lote) {
        lote.setOrigen("LABORATORIO");
        lote.setDestino("DISTRIBUIDOR");
        lote.setFechaProduccion(LocalDate.now());
        lote.setFechaVencimiento(LocalDate.now().plusYears(2));
        lote.setEstado("PRODUCIDO");
        lotes.add(lote);
        return lote;
    }

    public List<LoteMedicamento> obtenerLotes() { return lotes; }

    public LoteMedicamento enviarADistribuidor(String loteId) {
        Optional<LoteMedicamento> loteOpt = lotes.stream()
            .filter(l -> l.getLoteId().equals(loteId)).findFirst();
        if (loteOpt.isPresent()) {
            LoteMedicamento lote = loteOpt.get();
            lote.setEstado("EN_TRANSITO");
            LoteMedicamento respuesta = distribuidorClient.recibirLote(lote);
            lote.setEstado("ENVIADO_A_DISTRIBUIDOR");
            return respuesta;
        }
        throw new RuntimeException("Lote no encontrado: " + loteId);
    }
}
