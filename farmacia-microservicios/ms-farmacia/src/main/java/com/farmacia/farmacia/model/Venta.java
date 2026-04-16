package com.farmacia.farmacia.model;

import java.time.LocalDateTime;

public class Venta {
    private String ventaId;
    private String loteId;
    private String medicamento;
    private int cantidadVendida;
    private LocalDateTime fechaVenta;
    private double total;
    private String cliente;

    public Venta() {}

    public Venta(String ventaId, String loteId, String medicamento, int cantidadVendida,
                 LocalDateTime fechaVenta, double total, String cliente) {
        this.ventaId = ventaId; this.loteId = loteId; this.medicamento = medicamento;
        this.cantidadVendida = cantidadVendida; this.fechaVenta = fechaVenta;
        this.total = total; this.cliente = cliente;
    }

    public String getVentaId() { return ventaId; }
    public void setVentaId(String ventaId) { this.ventaId = ventaId; }
    public String getLoteId() { return loteId; }
    public void setLoteId(String loteId) { this.loteId = loteId; }
    public String getMedicamento() { return medicamento; }
    public void setMedicamento(String medicamento) { this.medicamento = medicamento; }
    public int getCantidadVendida() { return cantidadVendida; }
    public void setCantidadVendida(int cantidadVendida) { this.cantidadVendida = cantidadVendida; }
    public LocalDateTime getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(LocalDateTime fechaVenta) { this.fechaVenta = fechaVenta; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
}
