package com.mateusdev.blog.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mateusdev.blog.domain.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	//@Query("SELECT c FROM Categoria c WHERE c.categoria = :categoria")
	//Optional<Categoria> findByCategoria(@Param("categoria") String categoria);
	Optional<Categoria> findByCategoria(String categoria);
}
