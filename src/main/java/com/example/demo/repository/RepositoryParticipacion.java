package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Participacion;




public interface RepositoryParticipacion extends JpaRepository<Participacion, Long>{

	@Query(value= "SELECT * FROM participacion WHERE id_usu <>:usu AND id_evento<>:eve",nativeQuery=true)
	Optional<Participacion> checkEvent(@Param("usu")long id_usu,@Param("eve")Long id_eve);
}
