package com.mateusdev.blog.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mateusdev.blog.domain.model.Usuario;
import com.mateusdev.blog.domain.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioDetailsServiceImpl implements UserDetailsService {

	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	// Carrega o usuario pelo nome de usuário (por isso deve ser único la no banco)
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUser(username) // Busca o usuario
				.orElseThrow(()-> new UsernameNotFoundException("Usuário ("+username+") não encontrado!")); // Caso não exista resultado, lançamos uma exceção. (Essa exceção só pode ser lançada porque nosso usuario é do tipo Optional)
		return new User(usuario.getUser(),usuario.getSenha(),true,true,true,true,usuario.getAuthorities());
	}

}
