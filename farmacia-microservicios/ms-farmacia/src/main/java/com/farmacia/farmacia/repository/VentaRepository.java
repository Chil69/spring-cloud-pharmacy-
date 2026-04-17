package com.farmacia.farmacia.repository;

import com.farmacia.farmacia.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, String> {
}
