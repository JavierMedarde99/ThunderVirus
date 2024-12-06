package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
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
	@Column(name = "nombre_completo")
	private String nombreCompleto;
	@Column(name = "fecha_nac")
	private String fechaNac;
	@Column(name = "fecha_ini_usu")
	private Timestamp fechaIniUsu;
	@Column(name = "fecha_fin_usu")
	private Timestamp fechaFinUsu;
	private boolean sub;
	
	//la parte del usuario subcriptor
	private double dinero;
	private String tarjetaCredito ="";
	private double tarifa;
	@Column(name = "fecha_ini_sub")
	private Timestamp fechaIniSub;
	@Column(name = "fecha_fin_sub")
	private Timestamp fechaFinSub;
	
	//registro
	public Usuarios(String usuario, String clave,String file, String email, String nombreCompleto, String fechaNac,Timestamp fechaIniUsu) {
		super();
		this.username = usuario;
		this.password = clave;
		this.file=file;
		this.email = email;
		this.nombreCompleto = nombreCompleto;
		this.fechaNac = fechaNac;
		this.fechaIniUsu = fechaIniUsu;
	}
	
	
	
	
	
}
