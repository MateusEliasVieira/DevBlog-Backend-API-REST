package com.mateusdev.blog.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateusdev.blog.api.model.PostagemInput;
import com.mateusdev.blog.api.model.PostagemOutput;
import com.mateusdev.blog.domain.model.Postagem;
import com.mateusdev.blog.domain.service.PostagemService;

@RestController
@RequestMapping("/posts")
public class PostagemController {

	private ModelMapper modelMapper;
	
	@Autowired
	private PostagemService postagemService;
	
	public PostagemController() {
		modelMapper = new ModelMapper();
	}
	
	@PostMapping("/new")
	public ResponseEntity<PostagemOutput> inserirPost(@RequestBody PostagemInput postagemInput) {
		Postagem postagem = modelMapper.map(postagemInput, Postagem.class);
		postagem = postagemService.save(postagem,postagemInput.getCategoria());
		PostagemOutput postagemOutput = modelMapper.map(postagem, PostagemOutput.class);
		return new ResponseEntity<PostagemOutput>(postagemOutput, HttpStatus.CREATED);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<PostagemOutput>> listarPostagens() {
		List<Postagem> listPostagem = postagemService.findAll();
		List<PostagemOutput> listPostagemOutput = new ArrayList<PostagemOutput>();
		listPostagem.forEach(post -> {
			PostagemOutput postagemOutput = modelMapper.map(post, PostagemOutput.class);
			postagemOutput.setAutor(post.getUsuario().getNome());
			listPostagemOutput.add(postagemOutput);
		});
		//List<PostagemOutput> listPostagemOutput = modelMapper.map(postagemService.findAll(), new TypeToken<List<PostagemOutput>>() {}.getType());
		//String autor = SecurityContextHolder.getContext().getAuthentication().getName();
		return new ResponseEntity<List<PostagemOutput>>(listPostagemOutput, HttpStatus.OK);
	}
	
}
