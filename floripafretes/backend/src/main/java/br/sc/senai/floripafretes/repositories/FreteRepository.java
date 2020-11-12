package br.sc.senai.floripafretes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.sc.senai.floripafretes.entities.Frete;

@Repository
public interface FreteRepository extends JpaRepository <Frete, Integer> {

}
