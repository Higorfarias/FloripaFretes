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

import br.sc.senai.floripafretes.entities.Frete;
import br.sc.senai.floripafretes.repositories.FreteRepository;

@RestController
@RequestMapping("/")
public class FreteResource {
	
	@Autowired
	private FreteRepository freteRepo;
	
	@PostMapping("/fretes")
	public ResponseEntity<Frete> addFrete(@RequestBody Frete frete) {
		
		try {
			Frete newFrete = freteRepo.save(frete);
			return new ResponseEntity<>(newFrete, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/fretes")
	public ResponseEntity <Iterable<Frete>> listUsuarios() {
		
		try {
			Iterable<Frete> fretes = freteRepo.findAll();
			if (((Collection<?>) fretes).size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(fretes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

}
