package com.nivelamento.minhasfinancas.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nivelamento.minhasfinancas.model.Entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
