package com.mateusdev.blog.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mateusdev.blog.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	//@Query("SELECT u FROM Usuario u WHERE u.user = :username")
	//Optional<Usuario> findByUsername(@Param("username") String username);
	Optional<Usuario> findByUser(String username);

}
