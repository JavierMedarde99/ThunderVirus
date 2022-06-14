package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.example.demo.entity.Comentarios;



public interface RepositoryComentarios extends JpaRepository<Comentarios, Long>{
	@Query(value= "SELECT * FROM comentarios WHERE id_jugador=1 ",nativeQuery=true)
	List<Comentarios> javiComent();
	
	@Query(value= "SELECT * FROM comentarios WHERE id_jugador=2 ",nativeQuery=true)
	List<Comentarios> antonioComent();
	

	@Query(value= "SELECT * FROM comentarios WHERE id_jugador=3 ",nativeQuery=true)
	List<Comentarios> ivanComent();
	
	@Query(value= "SELECT * FROM comentarios WHERE id_jugador=4 ",nativeQuery=true)
	List<Comentarios> adrianComent();
	
	@Query(value= "SELECT * FROM comentarios WHERE id_jugador=5 ",nativeQuery=true)
	List<Comentarios> angieComent();
	
	@Query(value= "SELECT * FROM comentarios WHERE id_jugador=6 ",nativeQuery=true)
	List<Comentarios> tonoComent();
}
