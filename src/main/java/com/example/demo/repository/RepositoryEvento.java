package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Evento;



public interface RepositoryEvento extends JpaRepository<Evento, Long>{

}
