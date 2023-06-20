package com.mateusdev.blog.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mateusdev.blog.domain.model.Postagem;
import com.mateusdev.blog.domain.repository.PostagemRepository;
import com.mateusdev.blog.domain.service.PostagemService;

@Service
public class PostagemServiceImpl implements PostagemService{
	
	@Autowired
	private PostagemRepository postagemRepository;

	@Override
	public Postagem save(Postagem postagem) {
		return postagemRepository.save(postagem);
	}

	@Override
	public List<Postagem> findAll() {
		return postagemRepository.findAll();
	}
}
