package br.sc.senai.floripafretes.repositories;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.sc.senai.floripafretes.entities.Frete;
import br.sc.senai.floripafretes.entities.Usuario;

@Repository
public interface FreteRepository extends JpaRepository <Frete, Integer> {
	
	@Transactional
	Page<Frete> findByUsuario(Usuario usuario, Pageable pageRequest);

}
