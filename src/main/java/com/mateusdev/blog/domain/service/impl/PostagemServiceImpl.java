package com.mateusdev.blog.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mateusdev.blog.domain.model.Postagem;
import com.mateusdev.blog.domain.model.Usuario;
import com.mateusdev.blog.domain.repository.PostagemRepository;
import com.mateusdev.blog.domain.repository.UsuarioRepository;
import com.mateusdev.blog.domain.service.PostagemService;

@Service
public class PostagemServiceImpl implements PostagemService{
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Postagem save(Postagem postagem) {
		// Recupero a autenticação atualmente armazenada no contexto de segurança
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// Verifico se o usuário está autenticado
		if (authentication != null && authentication.isAuthenticated()) {
			
		    // Recupero o nome de usuário do objeto de autenticação
		    String username = authentication.getName();
		    
		    // Busco esse usuario no banco
		    Optional<Usuario> usuario = usuarioRepository.findByUser(username);
		    
		    // associo o usuario autenticado com a postagem a ser salva
		    postagem.setUsuario(usuario.get());
		 
		} else {
			System.out.println("usuario nao autenticado");
		    return null;
		}
		return postagemRepository.save(postagem);
	}

	@Override
	public List<Postagem> findAll() {
		return postagemRepository.findAll();
	}
}
