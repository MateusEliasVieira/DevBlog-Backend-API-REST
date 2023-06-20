package com.mateusdev.blog.domain.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String email;
	private String senha;
	private String sobre;
	private byte[] fotoUser;

	@OneToMany(mappedBy = "usuario")
	private List<Postagem> postagens;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getSobre() {
		return sobre;
	}

	public void setSobre(String sobre) {
		this.sobre = sobre;
	}

	public byte[] getFotoUser() {
		return fotoUser;
	}

	public void setFotoUser(byte[] fotoUser) {
		this.fotoUser = fotoUser;
	}

	public List<Postagem> getPostagens() {
		return postagens;
	}

	public void setPostagens(List<Postagem> postagens) {
		this.postagens = postagens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(fotoUser);
		result = prime * result + Objects.hash(email, id, nome, postagens, senha, sobre);
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
		return Objects.equals(email, other.email) && Arrays.equals(fotoUser, other.fotoUser) && id == other.id
				&& Objects.equals(nome, other.nome) && Objects.equals(postagens, other.postagens)
				&& Objects.equals(senha, other.senha) && Objects.equals(sobre, other.sobre);
	}

}
