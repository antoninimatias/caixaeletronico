package br.com.caixaeletronico.api.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.caixaeletronico.api.model.Conta;
import br.com.caixaeletronico.api.model.Transacao;
import br.com.caixaeletronico.api.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private TransacaoService transacaoService;

	/**
	 * Método para bloquear ou ativar uma conta
	 * 
	 * @param codigo - Código da conta a ser bloqueada
	 * @param ativo  - true (ativar) or false (bloquear)
	 */
	public void bloquearConta(Long codigo, Boolean ativo) {
		Conta contaSalva = buscarContaPeloCodigo(codigo);
		if (contaSalva != null && contaSalva.isFlagAtivo()) {
			contaSalva.setFlagAtivo(ativo);
			contaRepository.save(contaSalva);
		}
	}

	/**
	 * Método para realizar depósito
	 * 
	 * @param codigo - Código da conta
	 * @param valor  - valor a ser depositado
	 * @return conta - Conta com saldo atualizado
	 */
	public Conta depositar(Long codigo, BigDecimal valor) {
		Conta contaSalva = buscarContaPeloCodigo(codigo);

		if (contaSalva != null && contaSalva.isFlagAtivo()) {
			BigDecimal saldo = contaSalva.getSaldo();
			contaSalva.setSaldo(saldo.add(valor));
			contaSalva = contaRepository.save(contaSalva);
			transacaoService.registrarDeposito(contaSalva, valor);
			return contaSalva;
		}
		return null;
	}

	/**
	 * Método para realizar saque
	 * 
	 * @param codigo - Código da conta
	 * @param valor  - Valor a ser sacado
	 * @return conta - Conta com saldo atualizado
	 */
	public Conta sacar(Long codigo, BigDecimal valor) {
		Conta contaSalva = buscarContaPeloCodigo(codigo);

		if (contaSalva != null && contaSalva.isFlagAtivo()) {
			BigDecimal saldo = contaSalva.getSaldo();
			if (saldo.compareTo(valor) > 0) {
				contaSalva.setSaldo(saldo.subtract(valor));
				contaSalva = contaRepository.save(contaSalva);
				transacaoService.registrarSaque(contaSalva, valor);
				return contaSalva;
			}
		}
		return null;
	}

	/**
	 * Método para Consultar saldo
	 * 
	 * @param codigo - Código da conta
	 * @return saldo - Saldo da conta
	 */
	public BigDecimal consultarSaldo(Long codigo) {
		Conta contaSalva = buscarContaPeloCodigo(codigo);
		if (contaSalva != null && contaSalva.isFlagAtivo()) {
			return contaSalva.getSaldo();
		}
		return null;
	}

	/**
	 * Método para buscar conta pelo código
	 * 
	 * @param codigo - Código da conta
	 * @return conta - Conta Pesquisada
	 */
	public Conta buscarContaPeloCodigo(Long codigo) {
		Conta contaSalva = contaRepository.findOne(codigo);
		if (contaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return contaSalva;
	}

	/**
	 * Método para gerar extrato
	 * 
	 * @param codigo - Código da conta
	 * @return transacao - Lista de transações
	 */
	public List<Transacao> gerarExtrato(Long codigo) {
		Conta conta = buscarContaPeloCodigo(codigo);
		return transacaoService.buscarContas(conta);
	}
}
