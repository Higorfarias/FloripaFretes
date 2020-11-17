package br.sc.senai.floripafretes.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L; 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private String email;
	private String senha;
	private String celular;
	private String cep;
	private String bairro;
	
	@OneToMany(mappedBy="usuario")
	private List<Frete> fretes = new ArrayList<Frete>();
	
	public Usuario () {	
	}

	public Usuario(Integer id, String nome, String email, String senha, String celular, String cep, String bairro) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.celular = celular;
		this.cep = cep;
		this.bairro = bairro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public List<Frete> getFretes() {
		return fretes;
	}

	public void setFretes(List<Frete> fretes) {
		this.fretes = fretes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
