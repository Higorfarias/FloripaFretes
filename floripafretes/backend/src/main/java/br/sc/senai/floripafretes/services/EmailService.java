package br.sc.senai.floripafretes.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import br.sc.senai.floripafretes.entities.Usuario;

@Service
public interface EmailService {

	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Usuario usuario, String newPass);
	
}