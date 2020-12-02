package br.sc.senai.floripafretes.utils;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.sc.senai.floripafretes.entities.Frete;
import br.sc.senai.floripafretes.entities.Usuario;
import br.sc.senai.floripafretes.repositories.FreteRepository;
import br.sc.senai.floripafretes.repositories.UsuarioRepository;

@Component
public class DummyData {
	
	@Autowired
	private FreteRepository freteRepo;
	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@PostConstruct
	public void saveUser() {
		
		Frete fr1 = new Frete(null, "Frete saco Grande", "Frete em geral", null, null, null);
		
		Usuario user1 = new Usuario(null, "higor", "higor@gmail.com", pe.encode("123"), null);
		usuarioRepo.saveAll(Arrays.asList(user1));
		freteRepo.saveAll(Arrays.asList(fr1));
		
		
		
	}
}
