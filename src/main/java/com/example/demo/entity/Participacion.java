package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="participacion")
@AllArgsConstructor
@NoArgsConstructor

public class Participacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "id_usu")
    private Usuarios usuarios;
	@ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;
	@Column(name = "fecha_part")
	private Timestamp fechaPart;
	private int numPart;
}
