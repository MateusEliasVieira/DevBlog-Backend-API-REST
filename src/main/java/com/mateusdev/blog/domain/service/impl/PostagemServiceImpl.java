package com.mateusdev.blog.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mateusdev.blog.domain.model.Categoria;
import com.mateusdev.blog.domain.model.Postagem;
import com.mateusdev.blog.domain.model.Usuario;
import com.mateusdev.blog.domain.repository.CategoriaRepository;
import com.mateusdev.blog.domain.repository.PostagemRepository;
import com.mateusdev.blog.domain.repository.UsuarioRepository;
import com.mateusdev.blog.domain.service.PostagemService;

@Service
public class PostagemServiceImpl implements PostagemService{
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public Postagem save(Postagem postagem, String categoria) {
		// Recupero a autenticação atualmente armazenada no contexto de segurança
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// Verifico se o usuário está autenticado
		if (authentication != null && authentication.isAuthenticated()) {
			
			// Busco a categoria no banco
			Optional<Categoria> categoriaOptional = categoriaRepository.findByCategoria(categoria);
			
		    // Recupero o nome de usuário do objeto de autenticação
		    String username = authentication.getName();
		    
		    // Busco esse usuario no banco
		    Optional<Usuario> usuarioOptional = usuarioRepository.findByUser(username);
		    
		    // associo o usuario autenticado com a postagem a ser salva
		    postagem.setUsuario(usuarioOptional.get());
		    
		    // Associo a  categoria a postagem
		    postagem.setCategoria(categoriaOptional.get());
		 
		} else {
		    return null;
		}
		return postagemRepository.save(postagem);
	}

	@Override
	public List<Postagem> findAll() {
		//String user = SecurityContextHolder.getContext().getAuthentication().getName();
		//Optional<Usuario> usuarioOptional = usuarioRepository.findByUser(user);
		return postagemRepository.findAll();
	}
}
