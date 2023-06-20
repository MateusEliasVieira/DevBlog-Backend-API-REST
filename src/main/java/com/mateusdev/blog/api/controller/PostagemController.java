package com.mateusdev.blog.api.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mateusdev.blog.api.model.PostagemInput;
import com.mateusdev.blog.api.model.PostagemOutput;
import com.mateusdev.blog.domain.model.Postagem;
import com.mateusdev.blog.domain.service.PostagemService;

@RestController
@RequestMapping("/send")
public class PostagemController {

	private ModelMapper modelMapper;
	
	@Autowired
	private PostagemService postagemService;
	
	public PostagemController() {
		modelMapper = new ModelMapper();
	}
	
	@PostMapping("/new")
	public ResponseEntity<PostagemOutput> inserirPost(@RequestBody PostagemInput postagemInput) {
		System.out.println("\n\nEntrou aqui\n\n");
		Postagem postagem = modelMapper.map(postagemInput, Postagem.class);
		postagem = postagemService.save(postagem);
		PostagemOutput postagemOutput = modelMapper.map(postagem, PostagemOutput.class);
		return new ResponseEntity<PostagemOutput>(postagemOutput, HttpStatus.CREATED);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<PostagemOutput>> listarPostagens() {
		List<PostagemOutput> listPostagemOutput = modelMapper.map(postagemService.findAll(), new TypeToken<List<PostagemOutput>>() {}.getType());
		return new ResponseEntity<List<PostagemOutput>>(listPostagemOutput, HttpStatus.CREATED);
	}
	
}
