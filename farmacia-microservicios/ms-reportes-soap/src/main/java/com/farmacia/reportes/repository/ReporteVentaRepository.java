package com.farmacia.reportes.repository;

import com.farmacia.reportes.model.ReporteVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteVentaRepository extends JpaRepository<ReporteVenta, String> {
}
