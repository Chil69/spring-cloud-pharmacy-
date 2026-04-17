package com.farmacia.laboratorio.repository;

import com.farmacia.laboratorio.model.LoteMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteMedicamentoRepository extends JpaRepository<LoteMedicamento, String> {
}
