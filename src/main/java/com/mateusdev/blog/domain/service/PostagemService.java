package com.mateusdev.blog.domain.service;

import java.util.List;

import com.mateusdev.blog.domain.model.Postagem;

public interface PostagemService {
	
	public Postagem save(Postagem postagem,String categoria);
	public List<Postagem> findAll();
}
