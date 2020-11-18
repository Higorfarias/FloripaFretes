package br.sc.senai.floripafretes.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.sc.senai.floripafretes.entities.Frete;
import br.sc.senai.floripafretes.exception.ResourceNotFoundException;
import br.sc.senai.floripafretes.repositories.FreteRepository;

public class FreteService {
	
	@Autowired
	private FreteRepository freteRepo;
	
	public Frete create(Frete frete) {
		return freteRepo.save(frete);
	}

	public List<Frete> findAll() {
		return freteRepo.findAll();
	}

	public Frete findById(Integer id) {
		return freteRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
	}

	public Frete update(Frete frete) {
		Frete entity = freteRepo.findById(frete.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setTitulo(frete.getTitulo());
		entity.setDescricao(frete.getDescricao());
		entity.setData(LocalDate.now());
		entity.setEndereco(frete.getEndereco());
		entity.setCep(frete.getCep());
		entity.setCel(frete.getCel());
		
		return freteRepo.save(entity);
	}

	public void delete(Integer id) {
		Frete entity = freteRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		freteRepo.delete(entity);
	}


}
