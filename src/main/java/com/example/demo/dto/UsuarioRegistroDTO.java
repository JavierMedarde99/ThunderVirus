package com.example.demo.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegistroDTO {

	
	private String usuario;
	private String nombreCompleto;
	private String email;
	private String clave;
	private String foto;
	private String fecha_nac;
	private String claveRe;
	private boolean sub;
	
}
