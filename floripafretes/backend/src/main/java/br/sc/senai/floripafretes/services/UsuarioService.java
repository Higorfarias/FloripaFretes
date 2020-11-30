package br.sc.senai.floripafretes.services;

import java.awt.image.BufferedImage;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.sc.senai.floripafretes.dto.UsuarioDTO;
import br.sc.senai.floripafretes.dto.UsuarioNewDTO;
import br.sc.senai.floripafretes.entities.Usuario;
import br.sc.senai.floripafretes.entities.enums.Perfil;
import br.sc.senai.floripafretes.exception.AuthorizationException;
import br.sc.senai.floripafretes.exception.ResourceNotFoundException;
import br.sc.senai.floripafretes.repositories.UsuarioRepository;
import br.sc.senai.floripafretes.security.UserSS;

@Service
public class UsuarioService {

	@Autowired
	private BCryptPasswordEncoder bCrypt;

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private S3Service s3Service;

	public Usuario create(Usuario usuario) {
		return usuarioRepo.save(usuario);
	}

	public List<Usuario> findAll() {
		return usuarioRepo.findAll();
	}

	public Usuario findById(Integer id) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		return usuarioRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
	}

	public Usuario update(Usuario usuario) {
		Usuario entity = usuarioRepo.findById(usuario.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		entity.setNome(usuario.getNome());
		entity.setEmail(usuario.getEmail());
		entity.setSenha(bCrypt.encode(usuario.getSenha()));
		entity.setCelular(usuario.getCelular());

		return usuarioRepo.save(entity);
	}

	public void delete(Integer id) {
		Usuario entity = usuarioRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		usuarioRepo.delete(entity);
	}

	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

	public Usuario fromDTO(UsuarioNewDTO objDto) {
		Usuario cli = new Usuario(null, objDto.getNome(), objDto.getEmail(), bCrypt.encode(objDto.getSenha()), null);
		return cli;

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