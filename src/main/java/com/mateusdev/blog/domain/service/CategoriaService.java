package com.mateusdev.blog.domain.service;

import java.util.Optional;

import com.mateusdev.blog.domain.model.Categoria;

public interface CategoriaService {

	public Optional<Categoria> findCategoriaByName(String categoria);
}
