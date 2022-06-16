package com.example.demo.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	public static String savefile(MultipartFile multiPart, String ruta) {
		String nombreOriginal = multiPart.getOriginalFilename();
		
		try {
			File imagenfile = new File(ruta+nombreOriginal);
			multiPart.transferTo(imagenfile);
			return nombreOriginal;
		}catch (IOException e) {
			return null;
		}
	}
	
}
