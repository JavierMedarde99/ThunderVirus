package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@Table(name="usuarios")
@AllArgsConstructor
public class Usuarios {
	
	 public Usuarios(){}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String username;
	private String password;
	private String email;
	private String file;
	private String nombreCompleto;
	private String fecha_nac;
	private Timestamp fecha_ini_usu;
	private Timestamp fecha_fin_usu;
	private boolean sub;
	
	//la parte del usuario subcriptor
	private double dinero;
	private String tarjetaCredito;
	private double tarifa;
	private Timestamp fecha_ini_sub;
	private Timestamp fecha_fin_sub;
	
	//registro
	public Usuarios(String usuario, String clave,String file, String email, String nombreCompleto, String fecha_nac,Timestamp fecha_ini_usu) {
		super();
		this.username = usuario;
		this.password = clave;
		this.file=file;
		this.email = email;
		this.nombreCompleto = nombreCompleto;
		this.fecha_nac = fecha_nac;
		this.fecha_ini_usu = fecha_ini_usu;
	}
	
	
	
	
	
}
