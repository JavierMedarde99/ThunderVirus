package com.example.demo.dto;





import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegistroDTO {

	
	private String username;
	private String nombreCompleto;
	private String email;
	private String password;
	private MultipartFile file;
	private String fecha_nac;
	private String claveRe;
	private boolean sub;
	
}
