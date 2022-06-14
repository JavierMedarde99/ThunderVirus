package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="comentarios")
@AllArgsConstructor
@NoArgsConstructor
public class Comentarios {

	@Id
	
	private long id;
	
	private Long id_usu;
	private Long id_jugador;
	private String comentario;
	private Timestamp fecha_coment;
}
