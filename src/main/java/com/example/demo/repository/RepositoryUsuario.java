package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Usuarios;


@Repository
public interface RepositoryUsuario extends JpaRepository<Usuarios, Long>{

	@Query(value= "SELECT * FROM usuarios WHERE usuario =:usuario",nativeQuery=true)
	Optional<Usuarios> checkUsuario(@Param("usuario")String usuario);
	
	@Query(value= "SELECT * FROM usuarios WHERE usuario =:usuario and clave =:clave",nativeQuery=true)
	Optional<Usuarios> checkbdUsusario(@Param("usuario")String usuario,@Param("clave")String clave);
}
