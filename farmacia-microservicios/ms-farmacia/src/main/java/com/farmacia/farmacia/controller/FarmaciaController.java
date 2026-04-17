package com.farmacia.farmacia.controller;

import com.farmacia.farmacia.model.LoteMedicamento;
import com.farmacia.farmacia.model.Venta;
import com.farmacia.farmacia.service.FarmaciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/farmacia")
public class FarmaciaController {

    @Autowired
    private FarmaciaService farmaciaService;

    @PostMapping("/recibir")
    public ResponseEntity<LoteMedicamento> recibirLote(@RequestBody LoteMedicamento lote) {
        return ResponseEntity.ok(farmaciaService.recibirLote(lote));
    }

    @GetMapping("/stock")
    public ResponseEntity<List<LoteMedicamento>> verStock() {
        return ResponseEntity.ok(farmaciaService.obtenerStock());
    }

    @PostMapping("/vender/{loteId}")
    public ResponseEntity<Venta> venderMedicamento(
            @PathVariable String loteId,
            @RequestParam int cantidad,
            @RequestParam(defaultValue = "Cliente General") String cliente) {
        return ResponseEntity.ok(farmaciaService.venderMedicamento(loteId, cantidad, cliente));
    }

    @GetMapping("/ventas")
    public ResponseEntity<List<Venta>> verVentas() {
        return ResponseEntity.ok(farmaciaService.obtenerVentas());
    }

    @GetMapping("/public/catalogo")
    public ResponseEntity<List<Map<String, String>>> verCatalogo() {
        return ResponseEntity.ok(List.of(
            Map.of("nombre", "Amoxicilina 500mg", "categoria", "Antibiotico"),
            Map.of("nombre", "Paracetamol 500mg", "categoria", "Analgesico"),
            Map.of("nombre", "Ibuprofeno 400mg", "categoria", "Antiinflamatorio"),
            Map.of("nombre", "Omeprazol 20mg", "categoria", "Gastrico"),
            Map.of("nombre", "Losartan 50mg", "categoria", "Antihipertensivo")
        ));
    }
}
