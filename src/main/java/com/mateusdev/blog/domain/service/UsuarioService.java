package com.mateusdev.blog.domain.service;

import java.util.List;
import java.util.Optional;

import com.mateusdev.blog.domain.model.Usuario;


public interface UsuarioService {

	public String save(Usuario usuario);
	public Optional<Usuario> findByUser(String user);
	public List<Usuario> listUsers();
}
