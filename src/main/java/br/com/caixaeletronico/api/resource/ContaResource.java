package br.com.caixaeletronico.api.resource;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caixaeletronico.api.event.RecursoCriadoEvent;
import br.com.caixaeletronico.api.model.Conta;
import br.com.caixaeletronico.api.model.Transacao;
import br.com.caixaeletronico.api.repository.ContaRepository;
import br.com.caixaeletronico.api.service.ContaService;
import br.com.caixaeletronico.api.service.PessoaService;

/**
 * @author Antonini Matias
 *
 */
@RestController
@RequestMapping("/conta")
public class ContaResource {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private ContaService contaService;
	
	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Conta> listarContas() {
		return contaRepository.findAll();
	}

	/**
	 * Path para abertura de uma conta
	 * 
	 * @param conta    - Informar um objeto json com os dados da conta
	 * @param response - resposta para o usuário
	 * @return conta   - Retorna a conta que foi cadastrada
	 */
	@PostMapping
	public ResponseEntity<Conta> abrirConta(@Valid @RequestBody Conta conta, HttpServletResponse response) {
		Conta contaSalva = contaRepository.save(conta);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, contaSalva.getIdConta()));
		contaSalva.setPessoa(pessoaService.buscarPessoaPeloCodigo(contaSalva.getPessoa().getIdPessoa()));
		return ResponseEntity.status(HttpStatus.CREATED).body(contaSalva);
	}

	/**
	 * Path para gerar o extrato de uma conta
	 * 
	 * @param codigo - Código da conta que deseja gerar o extrato
	 * @return transacao - retorna uma lista de transações feita em uma conta
	 */
	@GetMapping("/{codigo}/gerarExtrato")
	public ResponseEntity<?> gerarExtrato(@PathVariable Long codigo) {
		List<Transacao> transacoes = contaService.gerarExtrato(codigo);
		return new ResponseEntity<>(transacoes, HttpStatus.OK);
	}

	/**
	 * Path para Ativar/Bloquear uma conta
	 * 
	 * @param codigo - Código da conta que desejar ativar/bloquear
	 * @param ativo  - true (ativar) or false (bloquear) uma conta
	 */
	@PutMapping("/{codigo}/ativar")
	public void bloquearConta(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		contaService.bloquearConta(codigo, ativo);
	}

	/**
	 * Path para consultar o saldo de uma conta
	 * 
	 * @param codigo - Código da conta que desejar consultar saldo
	 * @return saldo - Retorna o saldo da conta
	 */
	@GetMapping("/{codigo}/saldo")
	public BigDecimal consultarSaldo(@PathVariable Long codigo) {
		BigDecimal saldo = contaService.consultarSaldo(codigo);
		return saldo;
	}

	/**
	 * Path para realizar um depósito em uma conta
	 * 
	 * @param codigo - Código da conta que desejar realizar depósito
	 * @param valor  - Valor a ser depositado
	 * @return conta - Retorna a conta que foi feito o depósito
	 */
	@PutMapping("/{codigo}/depositar")
	public ResponseEntity<Conta> depositar(@PathVariable Long codigo, @RequestBody BigDecimal valor) {
		Conta conta = contaService.depositar(codigo, valor);
		return conta != null ? ResponseEntity.ok(conta) : ResponseEntity.notFound().build();
	}

	/**
	 * Path para realizar um saque em uma conta
	 * 
	 * @param codigo - Código da conta que desejar realizar o saque
	 * @param valor  - Valor a ser sacado
	 */
	@PutMapping("/{codigo}/sacar")
	public ResponseEntity<Conta> sacar(@PathVariable Long codigo, @RequestBody BigDecimal valor) {
		Conta conta = contaService.sacar(codigo, valor);
		return conta != null ? ResponseEntity.ok(conta) : ResponseEntity.notFound().build();
	}
}
