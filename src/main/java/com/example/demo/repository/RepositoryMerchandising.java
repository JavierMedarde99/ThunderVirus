package com.example.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Merchandising;



@Repository
public interface RepositoryMerchandising extends JpaRepository<Merchandising, Long>{
	
	@Query(value= "SELECT * FROM merchandising WHERE nombre='tazas'",nativeQuery=true)
	List<Merchandising> tazas();
	
	@Query(value= "SELECT * FROM merchandising WHERE nombre='camisetas'",nativeQuery=true)
	List<Merchandising> camisetas();
}
