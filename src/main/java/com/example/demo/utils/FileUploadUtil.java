package com.example.demo.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
