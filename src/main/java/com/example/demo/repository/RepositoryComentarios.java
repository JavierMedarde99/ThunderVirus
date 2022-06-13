package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.example.demo.entity.Comentarios;



public interface RepositoryComentarios extends JpaRepository<Comentarios, Long>{
	@Query(value= "SELECT * FROM comentarios WHERE id_jugador=1 ",nativeQuery=true)
	List<Comentarios> javiComent();
}
