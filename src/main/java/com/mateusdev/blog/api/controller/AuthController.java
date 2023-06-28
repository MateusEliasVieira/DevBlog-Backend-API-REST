package com.mateusdev.blog.api.controller;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mateusdev.blog.api.model.LoginInput;
import com.mateusdev.blog.config.security.TokenServiceImpl;
import com.mateusdev.blog.domain.model.Usuario;
import com.mateusdev.blog.domain.service.UsuarioService;

@RestController
@RequestMapping("/login")
public class AuthController {
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenServiceImpl tokenServiceImpl;
	
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/logar")
	@ResponseBody
	public ResponseEntity<String> logar(@RequestBody LoginInput loginInput) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(loginInput.getUser(), loginInput.getSenha());
		Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		
		if(authentication != null) {
			Optional<Usuario> usuario = usuarioService.findByUser(authentication.getName());
			String token = tokenServiceImpl.gerarTokenJWT(usuario.get());
			System.out.println("Token gerado = "+token);
			
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put("token", token);
	        
	        String json = jsonObject.toString();
	        System.out.println(json);
			
			return new ResponseEntity<String>(json,HttpStatus.ACCEPTED);
		}
		
	    return new ResponseEntity<String>("Login inválido",HttpStatus.UNAUTHORIZED);
	}
}
