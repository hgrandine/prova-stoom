package br.com.stoom.avaliacao.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stoom.avaliacao.entidade.Endereco;
import br.com.stoom.avaliacao.repository.EnderecoRepository;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	EnderecoRepository repository;

	@Override
	public void cadastrarEndereco(@Valid Endereco endereco) {
		repository.save(endereco);
	}

	@Override
	public void atualizarEndereco(@Valid Endereco endereco) {
		Optional<Endereco> atualiza = repository.findById(endereco.getId());
		
		if(atualiza.isPresent()) {
			repository.save(endereco);
		}else {
			throw new IllegalArgumentException("Endereco nao encontrado:" + endereco.getId());
		}
	}

	@Override
	public List<Endereco> buscarEnderecos() {
		return repository.findAll();
	}

	@Override
	public Endereco buscarEndereco(int id) {
		return repository.findById(id).orElseThrow();
	}
	
	@Override
	public boolean deletarEndereco(int id) {
		try {
			Endereco deleta = this.buscarEndereco(id);
			repository.delete(deleta);
			return true;
		}catch (Exception e) {
			throw new IllegalArgumentException("Endereco nao encontrado:" + id);
		}
	}

}
