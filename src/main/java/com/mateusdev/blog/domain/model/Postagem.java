package com.mateusdev.blog.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Postagem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private int id;
	@Column(nullable = false, name = "titulo", length = 100)
	private String titulo;
	@Column(nullable = false, name = "conteudo", length = 1000)
	private String conteudo;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(nullable = false)
	private Date dataPostagem = new Date();

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	//@JoinColumn(name = "id")
	private Categoria categoria;

	@ManyToOne(fetch = FetchType.EAGER) // carrega o usuario dessa postagem 
	@JoinColumn(name = "usuario_id")
	//@JoinColumn(name = "id")
	private Usuario usuario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, conteudo, dataPostagem, id, titulo, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Postagem other = (Postagem) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(conteudo, other.conteudo)
				&& Objects.equals(dataPostagem, other.dataPostagem) && id == other.id
				&& Objects.equals(titulo, other.titulo) && Objects.equals(usuario, other.usuario);
	}

}
