package br.com.stoom.avaliacao.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.stoom.avaliacao.entidade.Endereco;
import br.com.stoom.avaliacao.service.EnderecoServiceImpl;


@RestController
@RequestMapping(value="/endereco")
public class EnderecoRestController {
	
	@Autowired
	private EnderecoServiceImpl service;

	// CADASTRO (POST)
	@RequestMapping(value="/{streetName}/{number}/{complement}/{neighbourhood}/{city}/{state}"
			+ "/{country}/{zipcode}/{latitude}/{longitude}", method = RequestMethod.POST)
	public ResponseEntity<?> addEndereco(@PathVariable String streetName,@PathVariable int number,
			@PathVariable String complement, @PathVariable String neighbourhood, @PathVariable String city,
			 @PathVariable String state, @PathVariable String country, @PathVariable String zipcode,
			 @PathVariable int latitude, @PathVariable int longitude) {
		try {
			Endereco novo = new Endereco();
			novo.setStreetName(streetName);
			novo.setNumber(number);
			
			if(complement != null) novo.setComplement(complement);
			
			
			novo.setNeighbourhood(neighbourhood);
			novo.setCity(city);
			novo.setState(state);
			novo.setCountry(country);
			novo.setZipcode(zipcode);
			novo.setLatitude(latitude);
			novo.setLongitude(longitude);
	
			service.cadastrarEndereco(novo);
			
			return ResponseEntity.ok().body("cadatrado");
		}catch (Exception e) {
			return ResponseEntity.badRequest().body("erro no cadatrado");
		}
	}

	// ATUALIZACAO (PUT)
	@RequestMapping(value="/{id}/{streetName}/{number}/{complement}/{neighbourhood}/{city}/{state}"
			+ "/{country}/{zipcode}/{latitude}/{longitude}", method = RequestMethod.PUT)
	public ResponseEntity<?> editEndereco(@PathVariable int id, @PathVariable String streetName,@PathVariable int number,
			@PathVariable String complement, @PathVariable String neighbourhood, @PathVariable String city,
			 @PathVariable String state, @PathVariable String country, @PathVariable String zipcode,
			 @PathVariable int latitude, @PathVariable int longitude) {
		try {
			
			Endereco novo = new Endereco();
			novo.setId(id);
			novo.setStreetName(streetName);
			novo.setNumber(number);
			novo.setComplement(complement);
			novo.setNeighbourhood(neighbourhood);
			novo.setCity(city);
			novo.setState(state);
			novo.setCountry(country);
			novo.setZipcode(zipcode);
			novo.setLatitude(latitude);
			novo.setLongitude(longitude);
			
			service.atualizarEndereco(novo);
			
			return ResponseEntity.ok().body("cadatro atualizado");
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e);
		}
	}


	// LISTAGEM POR ID(GET)
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findEnderecoId(@PathVariable Integer id) {
		try {
			Endereco obj = service.buscarEndereco(id);
			return ResponseEntity.ok().body(obj);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body("nenhum resultado com esse id: "+id);
		}
	}
	
	// LISTAR TODAS
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ResponseEntity<?> findAllEndereco() {
		List<Endereco> obj = service.buscarEnderecos();
		if(obj != null) {
			return ResponseEntity.ok().body(obj);
		}else {
			return ResponseEntity.badRequest().body("nenhum resultado encontrado");
			
		}
	}

	// EXCLUSÃO POR ID (DELETE)
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delEnderecos(@PathVariable Integer id) {
		try {
			service.deletarEndereco(id);
			return ResponseEntity.ok().body("deletado");
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e);
		}
	}
}
