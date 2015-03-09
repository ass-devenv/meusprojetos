package br.com.gestao.util;

import javax.servlet.http.Part;

public class FilesUpdate {

	public String Filename(Part part){
		
		for(String cd : part.getHeader("content-disposition").split(";")){
			
			if(cd.trim().startsWith("filename")){
				
				String filename = cd.substring(cd.indexOf("=") + 1 ).trim().replace("\"", "");
				
				return filename.substring(filename.lastIndexOf("/") + 1).substring(filename.lastIndexOf("\\") + 1);
				
			}
				
		}
		
		return null;
	}
}
