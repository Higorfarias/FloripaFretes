package br.sc.senai.floripafretes.resources;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	// Inserindo novo usuario
	@PostMapping("/usuarios")
	public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {

		try {
			Usuario newUsuario = usuarioRepo.save(usuario);
			return new ResponseEntity<>(newUsuario, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	// Listando todos usuarios
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/usuarios")
	public ResponseEntity<Iterable<Usuario>> listUsuarios() {

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

	// Buscando usuario por id
	@GetMapping("usuarios/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
		return usuarioRepo.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	// Atualizar usuario por id
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") Integer id, @RequestBody Usuario usuario) {
		Optional<Usuario> usuarioData = usuarioRepo.findById(id);

		if (usuarioData.isPresent()) {
			Usuario updateUsuario = usuarioData.get();
			updateUsuario.setNome(usuario.getNome());
			updateUsuario.setEmail(usuario.getEmail());
			updateUsuario.setSenha(usuario.getSenha());
			updateUsuario.setCelular(usuario.getCelular());
			return new ResponseEntity<>(usuarioRepo.save(updateUsuario), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Deletar usuario por id
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("usuarios/{id}")
	public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable("id") Integer id) {

		try {
			usuarioRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}
