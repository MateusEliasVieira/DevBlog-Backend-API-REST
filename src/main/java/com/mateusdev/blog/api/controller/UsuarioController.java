package com.mateusdev.blog.api.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateusdev.blog.api.model.UsuarioInput;
import com.mateusdev.blog.api.model.UsuarioOutput;
import com.mateusdev.blog.domain.model.Usuario;
import com.mateusdev.blog.domain.service.UsuarioService;

@RestController
@RequestMapping("/user")
public class UsuarioController {
	
	private ModelMapper model;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public UsuarioController() {
		model = new ModelMapper();
	}

	@PostMapping("/new")
	public ResponseEntity<UsuarioOutput> cadastrarUsuario(@RequestBody UsuarioInput usuarioInput){
		Usuario usuario = model.map(usuarioInput, Usuario.class);
		String status = usuarioService.save(usuario);
		UsuarioOutput usuarioOutput = new UsuarioOutput();
		usuarioOutput.setStatus(status);
		System.out.println("\nresultado = "+status+"\n");
		return new ResponseEntity<UsuarioOutput>(usuarioOutput,HttpStatus.OK);
	}
	
}
