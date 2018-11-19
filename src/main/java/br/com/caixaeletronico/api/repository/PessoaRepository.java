package br.com.caixaeletronico.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.caixaeletronico.api.model.Pessoa;

/**
 * @author Antonini Matias
 *
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
