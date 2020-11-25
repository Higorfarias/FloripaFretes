package br.sc.senai.floripafretes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.sc.senai.floripafretes.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Transactional
	Usuario findByEmail(String email);
	
	@Transactional
	Optional<Usuario> findById(Integer id);


}
