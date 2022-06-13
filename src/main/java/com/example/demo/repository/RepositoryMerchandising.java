package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Merchandising;



@Repository
public interface RepositoryMerchandising extends JpaRepository<Merchandising, Long>{
	
	@Query(value= "SELECT * FROM merchandising WHERE tipo='tazas'",nativeQuery=true)
	List<Merchandising> tazas();
	
	@Query(value= "SELECT * FROM merchandising WHERE tipo='camisetas'",nativeQuery=true)
	List<Merchandising> camisetas();
}
