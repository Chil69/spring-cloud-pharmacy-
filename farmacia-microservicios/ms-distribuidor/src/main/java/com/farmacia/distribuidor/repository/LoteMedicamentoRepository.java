package com.farmacia.distribuidor.repository;

import com.farmacia.distribuidor.model.LoteMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteMedicamentoRepository extends JpaRepository<LoteMedicamento, String> {
}
