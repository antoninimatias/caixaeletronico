package br.com.caixaeletronico.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caixaeletronico.api.model.Conta;
import br.com.caixaeletronico.api.model.Transacao;
import br.com.caixaeletronico.api.repository.TransacaoRepository;

@RestController
@RequestMapping("/transacao")
public class TransacaoResource {

	@Autowired
	private TransacaoRepository transacaoRepository;

	/**
	 * Gerar extrato de uma conta
	 * 
	 * @param conta - Conta a ser gerado o extrato
	 * @return transacao - Retorna da lista de transações da conta
	 **/
	@GetMapping("/extrato")
	private List<Transacao> gerarExtrato(@PathVariable Conta conta) {
		return transacaoRepository.findAll();
	}
}
