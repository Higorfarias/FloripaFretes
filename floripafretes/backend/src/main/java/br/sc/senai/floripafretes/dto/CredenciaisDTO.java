package br.sc.senai.floripafretes.dto;

import java.io.Serializable;

import javax.persistence.Column;

public class CredenciaisDTO implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private String email;
		@Column(length=65)
		private String senha;
		
		public CredenciaisDTO() {
		}
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getSenha() {
			return senha;
		}
		public void setSenha(String senha) {
			this.senha = senha;
		}

}
