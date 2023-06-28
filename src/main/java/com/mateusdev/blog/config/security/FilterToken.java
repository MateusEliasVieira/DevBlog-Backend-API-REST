package com.mateusdev.blog.config.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mateusdev.blog.domain.model.Usuario;
import com.mateusdev.blog.domain.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterToken extends OncePerRequestFilter { // OncePerRequestFilter = filtro uma vez por pedido

	@Autowired
	private TokenServiceImpl tokenServiceImpl;

	@Autowired
	private UsuarioRepository usuarioRepository;

	// Ao acessar qualquer rota, passará por esse filtro primeiro.
	// Qualquer requisição que for feita deverá ter o token.
	// O token se consegue quando o usuário faz o login com usuario e senha.

	// Dependendo do seu tipo de usuario é criado um token para USER ou ADMIN
	// dessa forma, quando o usuário realizar alguma requisição é possível
	// fazer verificações se ele pode ou não acessar determinada rota.

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token;
		String authentication = request.getHeader("authorization");
		
		System.out.println("\nTOKEN = "+authentication+"\n");

		if (authentication != null) {
			// No header o token vai vir assim: "Bearer shdkjahsdkjashdjahsdkjash"
			// Ao usar esta função authentication.replace("Bearer ",""); obteremos apenas o
			// token
			token = authentication.replace("Bearer ", "");

			try {

				int id = this.tokenServiceImpl.getIdDoUsuario(token); // obtenho o nome de usuário
								
				Optional<Usuario> usuario = this.usuarioRepository.findById(id); // passo o id do usuário para buscar
																					// seus dados no banco
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						usuario.get(), null, usuario.get().getAuthorities()); // definimos uma autenticação válida e
																				// passamos aqui a coleção de roles do
																				// usuario

				// Passamos a autenticação para o contexto de segurança do spring para ele saber
				// que o usuario está autenticado.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} catch (Exception e) {
				System.out.println("Erro = "+e.getMessage());
				response.setStatus(401);
			}
		}

		// Deixamos continuar o fluxo da requisição
		filterChain.doFilter(request, response);
	}
}
