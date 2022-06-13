package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Merchandising;


@Repository
public interface RepositoryMerchandising extends JpaRepository<Merchandising, Long>{
	
	
}
