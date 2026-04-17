package com.farmacia.distribuidor.controller;

import com.farmacia.distribuidor.model.LoteMedicamento;
import com.farmacia.distribuidor.service.DistribuidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/distribuidor")
public class DistribuidorController {

    @Autowired
    private DistribuidorService distribuidorService;

    @PostMapping("/recibir")
    public ResponseEntity<LoteMedicamento> recibirLote(@RequestBody LoteMedicamento lote) {
        return ResponseEntity.ok(distribuidorService.recibirLote(lote));
    }

    @GetMapping("/inventario")
    public ResponseEntity<List<LoteMedicamento>> verInventario() {
        return ResponseEntity.ok(distribuidorService.obtenerInventario());
    }

    @PostMapping("/despachar/{loteId}")
    public ResponseEntity<LoteMedicamento> despacharAFarmacia(@PathVariable String loteId) {
        return ResponseEntity.ok(distribuidorService.despacharAFarmacia(loteId));
    }
}
