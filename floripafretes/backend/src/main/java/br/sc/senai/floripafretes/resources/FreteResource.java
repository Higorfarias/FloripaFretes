package br.sc.senai.floripafretes.resources;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	//Inserir novo frete
	@PostMapping("/fretes")
	public ResponseEntity<Frete> addFrete(@RequestBody Frete frete) {
		
		try {
			Frete newFrete = freteRepo.save(frete);
			return new ResponseEntity<>(newFrete, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	//Listando todos fretes
	@GetMapping("/fretes")
	public ResponseEntity <Iterable<Frete>> listFretes() {
		
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
	
	//Buscando frete por id
	@GetMapping("/fretes/{id}")
	public ResponseEntity<?> findById (@PathVariable("id") Integer id) {
		return freteRepo.findById(id).map(record -> ResponseEntity.ok()
				.body(record)).orElse(ResponseEntity.notFound().build());
	}
	
	//Atualizando frete
	@PutMapping("/fretes/{id}")
	public ResponseEntity<Frete> updateFrete(@PathVariable("id") Integer id, @RequestBody Frete frete) {
		Optional<Frete> freteData = freteRepo.findById(id);
		
		if(freteData.isPresent()) {
			Frete updateFrete = freteData.get();
			updateFrete.setTitulo(frete.getTitulo());
			updateFrete.setDescricao(frete.getDescricao());
			updateFrete.setEndereco(frete.getEndereco());
			updateFrete.setCep(frete.getCep());
			updateFrete.setCel(frete.getCel());
			return new ResponseEntity<>(freteRepo.save(updateFrete), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Deletar frete
	@DeleteMapping("fretes/{id}")
	public ResponseEntity<HttpStatus> deleteFrete(@PathVariable("id") Integer id) {
		
		try {
			freteRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@Override
	public String toString() {
		return "FreteResource [freteRepo=" + freteRepo + "]";
	}
	
	

}
