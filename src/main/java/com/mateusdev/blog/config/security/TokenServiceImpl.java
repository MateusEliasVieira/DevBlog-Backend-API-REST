package com.mateusdev.blog.config.security;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mateusdev.blog.domain.model.Usuario;

@Service
public class TokenServiceImpl {
	
	private final String EMISSOR = "mateusdev";
	private final String CHAVE_SECRETA = "ashjdkltyopwg234569shbxcajklo231";
	private final int MINUTOS_DE_UM_DIA = 60 * 24;
	private final String FUSO_HORARIO = "-03:00";

	public String gerarTokenJWT(Usuario usuario) {
		
		String token = JWT.create()
				.withIssuer(EMISSOR)
				.withSubject(usuario.getUser())
				.withClaim("id", usuario.getId())
				.withExpiresAt(LocalDateTime.now().plusMinutes(MINUTOS_DE_UM_DIA).toInstant(ZoneOffset.of(FUSO_HORARIO)))
				.sign(Algorithm.HMAC256(CHAVE_SECRETA));
		
		return token;
	}

	public int getIdDoUsuario(String token) {
		
	    // Descriptografamos o token com a chave definida	
		DecodedJWT decode =  JWT.require(Algorithm.HMAC256(CHAVE_SECRETA))
				.withIssuer(EMISSOR)
				.build()
				.verify(token);
		
		// Extraimos os dados do token
		String subject = decode.getSubject();
		String emissor = decode.getIssuer();
		Date validade = decode.getExpiresAt();
		int id = Integer.parseInt(decode.getClaim("id").toString());
				
		return id;
	}
}
