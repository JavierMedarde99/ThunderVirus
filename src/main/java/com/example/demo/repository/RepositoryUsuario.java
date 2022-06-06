package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Usuarios;


@Repository
public interface RepositoryUsuario extends JpaRepository<Usuarios, Long>{

	@Query(value= "SELECT * FROM usuarios WHERE username =:username",nativeQuery=true)
	Optional<Usuarios> checkUsuario(@Param("username")String usuario);
	
	@Query(value= "SELECT * FROM usuarios WHERE username =:username and password =:password",nativeQuery=true)
	Optional<Usuarios> checkbdUsusario(@Param("username")String usuario,@Param("password")String clave);
	
	  Optional<Usuarios> findByUsername(String username);
}
