package br.sc.senai.floripafretes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.sc.senai.floripafretes.entities.Usuario;
import br.sc.senai.floripafretes.exception.ResourceNotFoundException;
import br.sc.senai.floripafretes.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	@Autowired
	private UsuarioRepository usuarioRepo;

	public Usuario create(Usuario usuario) {
		return usuarioRepo.save(usuario);
	}

	public List<Usuario> findAll() {
		return usuarioRepo.findAll();
	}

	public Usuario findById(Integer id) {
		return usuarioRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
	}

	public Usuario update(Usuario usuario) {
		Usuario entity = usuarioRepo.findById(usuario.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		entity.setNome(usuario.getNome());
		entity.setEmail(bCrypt.encode(usuario.getEmail()));
		entity.setSenha(usuario.getSenha());
		entity.setCelular(usuario.getCelular());
		
		return usuarioRepo.save(entity);
	}

	public void delete(Integer id) {
		Usuario entity = usuarioRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		usuarioRepo.delete(entity);
	}

}
