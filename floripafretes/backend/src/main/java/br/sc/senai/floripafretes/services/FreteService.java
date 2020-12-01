package br.sc.senai.floripafretes.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.multipart.MultipartFile;

import br.sc.senai.floripafretes.entities.Frete;
import br.sc.senai.floripafretes.entities.Usuario;
import br.sc.senai.floripafretes.exception.AuthorizationException;
import br.sc.senai.floripafretes.exception.ResourceNotFoundException;
import br.sc.senai.floripafretes.repositories.FreteRepository;
import br.sc.senai.floripafretes.security.UserSS;

public class FreteService {
	
	@Autowired
	private FreteRepository freteRepo;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private S3Service s3Service;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;
	
	public Frete create(Frete frete) {
		return freteRepo.save(frete);
	}

	public List<Frete> findAll() {
		return freteRepo.findAll();
	}

	public Frete findById(Integer id) {
		return freteRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
	}

	public Frete update(Frete frete) {
		Frete entity = freteRepo.findById(frete.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setTitulo(frete.getTitulo());
		entity.setDescricao(frete.getDescricao());
		entity.setData(LocalDate.now());
		entity.setEndereco(frete.getEndereco());
		entity.setCep(frete.getCep());
		entity.setCel(frete.getCel());
		
		return freteRepo.save(entity);
	}

	public void delete(Integer id) {
		Frete entity = freteRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		freteRepo.delete(entity);
	}
	
	//Lista os fretes do usuario logado
	public Page<Frete> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Usuario usuario =  usuarioService.findById(user.getId());
		return freteRepo.findByUsuario(usuario, pageRequest);
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		
		String fileName = prefix + user.getId() + ".jpg";
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}


}
