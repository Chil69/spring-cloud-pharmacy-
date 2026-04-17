package com.farmacia.farmacia.repository;

import com.farmacia.farmacia.model.LoteMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteMedicamentoRepository extends JpaRepository<LoteMedicamento, String> {
}
