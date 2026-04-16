package com.farmacia.farmacia.model;

import java.time.LocalDate;

public class LoteMedicamento {
    private String loteId;
    private String medicamento;
    private String categoria;
    private int cantidad;
    private double precioUnitario;
    private String origen;
    private String destino;
    private LocalDate fechaProduccion;
    private LocalDate fechaVencimiento;
    private String estado;

    public LoteMedicamento() {}

    public String getLoteId() { return loteId; }
    public void setLoteId(String loteId) { this.loteId = loteId; }
    public String getMedicamento() { return medicamento; }
    public void setMedicamento(String medicamento) { this.medicamento = medicamento; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }
    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }
    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }
    public LocalDate getFechaProduccion() { return fechaProduccion; }
    public void setFechaProduccion(LocalDate fp) { this.fechaProduccion = fp; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fv) { this.fechaVencimiento = fv; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
