package br.com.caixaeletronico.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.caixaeletronico.api.model.Conta;
import br.com.caixaeletronico.api.model.Transacao;

/**
 * @author Antonini Matias
 *
 */
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
	
	List<Transacao> findByConta(Conta conta);
}
