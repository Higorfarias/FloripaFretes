package br.sc.senai.floripafretes.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import br.sc.senai.floripafretes.entities.Frete;
import br.sc.senai.floripafretes.entities.Usuario;
import br.sc.senai.floripafretes.exception.AuthorizationException;
import br.sc.senai.floripafretes.exception.ResourceNotFoundException;
import br.sc.senai.floripafretes.repositories.FreteRepository;
import br.sc.senai.floripafretes.security.UserSS;

public class FreteService {
	
	@Autowired
	private FreteRepository freteRepo;
	
	@Autowired
	private UsuarioService usuarioService;
	
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
	
	//Lista os fretes do usuario logado
	public Page<Frete> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Usuario usuario =  usuarioService.findById(user.getId());
		return freteRepo.findByUsuario(usuario, pageRequest);
	}


}
