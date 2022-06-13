package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Jugadores;


public interface RepositoryJugadores extends JpaRepository<Jugadores, Long>{

	@Query(value= "SELECT * FROM jugadores WHERE nombre =:nombre",nativeQuery=true)
	Jugadores dataJugadores(@Param("nombre")String nombre); 
	
	@Query(value= "SELECT juegos FROM jugadores WHERE nombre =:nombre",nativeQuery=true)
	 String dataJuegos(@Param("nombre")String nombre); 
	
}
