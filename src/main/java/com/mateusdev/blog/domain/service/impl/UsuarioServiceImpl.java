package com.mateusdev.blog.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mateusdev.blog.domain.model.Usuario;
import com.mateusdev.blog.domain.repository.UsuarioRepository;
import com.mateusdev.blog.domain.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario save(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Usuario> findByUser(String user) {
		return usuarioRepository.findByUser(user);
	}

}
