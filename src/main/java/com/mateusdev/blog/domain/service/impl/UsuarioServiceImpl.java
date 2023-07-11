package com.mateusdev.blog.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mateusdev.blog.domain.model.Usuario;
import com.mateusdev.blog.domain.repository.UsuarioRepository;
import com.mateusdev.blog.domain.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public String save(Usuario usuario) {
		// salvando usuario
		if(usuarioRepository.findByEmail(usuario.getEmail()).isEmpty() &&
				usuarioRepository.findByUser(usuario.getUser()).isEmpty()) {
			usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
			usuarioRepository.save(usuario);
			return "Seu cadastrado foi realizado com sucesso!";
		}else {
			if(!usuarioRepository.findByEmail(usuario.getEmail()).isEmpty()) {
				return "Ops, já existe um usuário cadastrado com esse email!";
			}else if(!usuarioRepository.findByUser(usuario.getUser()).isEmpty()) {
				return "Ops, já existe um usuário cadastrado com esse nome de usuário!";
			}
		}
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
