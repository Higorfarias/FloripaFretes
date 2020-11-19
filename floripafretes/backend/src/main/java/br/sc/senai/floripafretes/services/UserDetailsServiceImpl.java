package br.sc.senai.floripafretes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.sc.senai.floripafretes.entities.Usuario;
import br.sc.senai.floripafretes.repositories.UsuarioRepository;
import br.sc.senai.floripafretes.security.UserSS;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario user1 = usuarioRepo.findByEmail(email);
		if (user1 == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserSS(user1.getId(), user1.getEmail(), user1.getSenha(), user1.getPerfis());
	}

	
}
