package br.com.caixaeletronico.api.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caixaeletronico.api.model.Conta;
import br.com.caixaeletronico.api.model.Transacao;
import br.com.caixaeletronico.api.repository.TransacaoRepository;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository transacaoRepository;

	/**
	 * Registrando o depósito na tabela transação
	 * 
	 * @param conta - Conta a ser realizado o depósito
	 * @param valor - Valor a ser depositado
	 * @return Objeto transação
	 */
	public Transacao registrarDeposito(Conta conta, BigDecimal valor) {
		Transacao depositar = new Transacao();
		depositar.setConta(conta);
		depositar.setValor(valor);
		depositar.setDataTransacao(new Date());
		transacaoRepository.save(depositar);
		return depositar;
	}

	/**
	 * Registrando o saque na tabela transação
	 * 
	 * @param conta - Conta a ser realizado o saque
	 * @param valor - Valor a ser sacado
	 * @return Objeto transação
	 */
	public Transacao registrarSaque(Conta conta, BigDecimal valor) {
		Transacao sacar = new Transacao();
		sacar.setConta(conta);
		sacar.setValor(valor);
		sacar.setDataTransacao(new Date());
		transacaoRepository.save(sacar);
		return sacar;
	}

	/**
	 * Buscando Por Conta
	 * 
	 * @param conta - Conta a ser pesquisada
	 * @return Lista de transações
	 */
	public List<Transacao> buscarContas(Conta conta) {
		return transacaoRepository.findByConta(conta);
	}
}
