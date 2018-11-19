package br.com.caixaeletronico.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.caixaeletronico.api.model.Conta;

/**
 * @author Antonini Matias
 *
 */
public interface ContaRepository extends JpaRepository<Conta, Long> {

}
