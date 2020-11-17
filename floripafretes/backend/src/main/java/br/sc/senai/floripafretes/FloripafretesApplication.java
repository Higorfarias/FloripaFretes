package br.sc.senai.floripafretes;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.sc.senai.floripafretes.entities.Frete;
import br.sc.senai.floripafretes.entities.Usuario;
import br.sc.senai.floripafretes.repositories.FreteRepository;
import br.sc.senai.floripafretes.repositories.UsuarioRepository;

@SpringBootApplication
public class FloripafretesApplication {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired 
	private FreteRepository freteRepository;

	public static void main(String[] args) {
		SpringApplication.run(FloripafretesApplication.class, args);
	}
	
	public void run(String... args) throws Exception {
		
		Usuario user1  = new Usuario(null, "Higor", "sahusauhsa@Gmail.com", "1234", "232323223", "24424242", "saco grande");
		Usuario user2  = new Usuario(null, "Lucas Steffens", "lucas@Gmail.com", "12234", "232323223", "24424242", "monte verde");
		
		Frete fr1 = new Frete(null, "Frete Joao paulo", "frete gratis", "joao paulo", "9329329", "3223323223" );
		Frete fr2 = new Frete(null, "Frete sc", "frete gratis", "sc", "932932assasa9", "32233sasa23223" );
		
		user1.getFretes().addAll(Arrays.asList(fr1));
		fr1.getUsuario().addAll(Arrays.asList(user1));
		
		freteRepository.saveAll(Arrays.asList(fr1, fr2));
		usuarioRepository.saveAll(Arrays.asList(user1, user2));
		

	}
	
}
