package br.com.stoom.avaliacao.service;

import java.util.List;

import javax.validation.Valid;

import br.com.stoom.avaliacao.entidade.Endereco;

public interface EnderecoService {
	
	List<Endereco> buscarEnderecos();

	void cadastrarEndereco(@Valid Endereco estudante);

	Endereco buscarEndereco(int id);

	void atualizarEndereco(@Valid Endereco estudante);

	boolean deletarEndereco(int id);
}
