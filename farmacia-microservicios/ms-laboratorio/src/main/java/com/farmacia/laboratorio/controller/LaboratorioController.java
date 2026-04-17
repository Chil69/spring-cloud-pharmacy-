package com.farmacia.laboratorio.controller;

import com.farmacia.laboratorio.model.LoteMedicamento;
import com.farmacia.laboratorio.service.LaboratorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/laboratorio")
public class LaboratorioController {

    @Autowired
    private LaboratorioService laboratorioService;

    @PostMapping("/producir")
    public ResponseEntity<LoteMedicamento> producirLote(@RequestBody LoteMedicamento lote) {
        return ResponseEntity.ok(laboratorioService.producirLote(lote));
    }

    @GetMapping("/lotes")
    public ResponseEntity<List<LoteMedicamento>> listarLotes() {
        return ResponseEntity.ok(laboratorioService.obtenerLotes());
    }

    @PostMapping("/enviar/{loteId}")
    public ResponseEntity<LoteMedicamento> enviarADistribuidor(@PathVariable String loteId) {
        return ResponseEntity.ok(laboratorioService.enviarADistribuidor(loteId));
    }
}
