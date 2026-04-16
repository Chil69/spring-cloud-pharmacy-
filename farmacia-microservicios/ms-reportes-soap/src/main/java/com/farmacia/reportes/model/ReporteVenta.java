package com.farmacia.reportes.model;

import java.time.LocalDateTime;

public class ReporteVenta {
    private String ventaId;
    private String medicamento;
    private int cantidad;
    private double total;
    private String cliente;
    private LocalDateTime fechaRegistro;

    public ReporteVenta() {}

    public ReporteVenta(String ventaId, String medicamento, int cantidad, double total,
                        String cliente, LocalDateTime fechaRegistro) {
        this.ventaId = ventaId; this.medicamento = medicamento; this.cantidad = cantidad;
        this.total = total; this.cliente = cliente; this.fechaRegistro = fechaRegistro;
    }

    public String getVentaId() { return ventaId; }
    public void setVentaId(String ventaId) { this.ventaId = ventaId; }
    public String getMedicamento() { return medicamento; }
    public void setMedicamento(String medicamento) { this.medicamento = medicamento; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}
