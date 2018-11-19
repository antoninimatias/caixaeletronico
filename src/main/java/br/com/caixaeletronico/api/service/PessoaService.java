package br.com.caixaeletronico.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.caixaeletronico.api.model.Pessoa;
import br.com.caixaeletronico.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	/**
	 * Método para buscar pessoa pelo código
	 * 
	 * @param codigo - Código da pessoa a ser pesquisada
	 * @return pessoa - Retorna a pessoa
	 */
	public Pessoa buscarPessoaPeloCodigo(Long codigo) {
		Pessoa pessoaSalva = pessoaRepository.findOne(codigo);
		if (pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}
}
