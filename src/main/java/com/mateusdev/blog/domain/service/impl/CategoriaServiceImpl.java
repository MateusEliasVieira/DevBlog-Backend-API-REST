package com.mateusdev.blog.domain.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mateusdev.blog.domain.model.Categoria;
import com.mateusdev.blog.domain.repository.CategoriaRepository;
import com.mateusdev.blog.domain.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public Optional<Categoria> findCategoriaByName(String categoria) {
		Optional<Categoria> categoriaOptional = categoriaRepository.findByCategoria(categoria);
		return categoriaOptional;
	}

}
