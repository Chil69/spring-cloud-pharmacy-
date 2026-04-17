package com.farmacia.farmacia.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @Column(name = "venta_id")
    private String ventaId;

    @Column(name = "lote_id", nullable = false)
    private String loteId;

    @Column(nullable = false)
    private String medicamento;

    @Column(name = "cantidad_vendida")
    private int cantidadVendida;

    @Column(name = "fecha_venta")
    private LocalDateTime fechaVenta;

    private double total;

    private String cliente;

    public Venta() {}

    public String getVentaId() { return ventaId; }
    public void setVentaId(String ventaId) { this.ventaId = ventaId; }
    public String getLoteId() { return loteId; }
    public void setLoteId(String loteId) { this.loteId = loteId; }
    public String getMedicamento() { return medicamento; }
    public void setMedicamento(String medicamento) { this.medicamento = medicamento; }
    public int getCantidadVendida() { return cantidadVendida; }
    public void setCantidadVendida(int c) { this.cantidadVendida = c; }
    public LocalDateTime getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(LocalDateTime f) { this.fechaVenta = f; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
}
