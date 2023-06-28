package com.mateusdev.blog.domain.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/*Agora, o mapeamento @OneToMany está correto, 
 * fazendo referência à propriedade categoria 
 * da classe Postagem. O parâmetro mappedBy 
 * especifica o nome do atributo na classe 
 * Postagem que faz o mapeamento inverso. 
 * A coluna categoria_id será criada na tabela 
 * da entidade Postagem para representar a chave estrangeira.*/

@Entity
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private int id;
	@Column(nullable = false)
	private String categoria;

	@OneToMany(mappedBy = "categoria")
	private List<Postagem> postagens;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<Postagem> getPostagens() {
		return postagens;
	}

	public void setPostagens(List<Postagem> postagens) {
		this.postagens = postagens;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, id, postagens);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(categoria, other.categoria) && id == other.id
				&& Objects.equals(postagens, other.postagens);
	}

}
