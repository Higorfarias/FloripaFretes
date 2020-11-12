package br.sc.senai.floripafretes.resources;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.sc.senai.floripafretes.entities.Usuario;
import br.sc.senai.floripafretes.repositories.UsuarioRepository;

@RestController
@RequestMapping("/")
public class UsuarioResource {
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	//Inserindo novo Usuario
	@PostMapping("/usuarios") 
	public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
		try {
			Usuario newUser = usuarioRepo.save(usuario);
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	//Buscando todos usuarios
	@GetMapping("/usuarios")
	public ResponseEntity <Iterable<Usuario>> listUsuarios() {
		try {
			Iterable<Usuario> usuarios = usuarioRepo.findAll();
			if (((Collection<?>) usuarios).size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

}
