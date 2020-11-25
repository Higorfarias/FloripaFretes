package br.sc.senai.floripafretes.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.sc.senai.floripafretes.security.UserSS;

public class UserService {

	public static UserSS authenticated() {

		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
