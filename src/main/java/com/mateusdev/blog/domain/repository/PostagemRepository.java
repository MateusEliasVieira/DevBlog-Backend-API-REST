package com.mateusdev.blog.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mateusdev.blog.domain.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Integer> {

}
