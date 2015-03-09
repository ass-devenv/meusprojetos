package managedBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 






import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile; 



public class RecebeArquivos {
     
	public String upload(FileUploadEvent event) {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();  
		ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();  
		String pathRel = servletContext.getRealPath("/upfiles");	
		
		try{
			File drproject = new File(pathRel);
			
			InputStream inputStream = event.getFile().getInputstream();
			
			OutputStream out = new FileOutputStream(new File(drproject, event.getFile().getFileName()));
			
			int read = 0;
			
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				
				out.write(bytes, 0, read);
			}

			inputStream.close();
			out.flush();
			out.close();
		
		}catch(IOException e){
			System.out.print("Erro...: " + e.getMessage());
		}

		FacesMessage message = new FacesMessage("Upload do arquivo ", event.getFile().getFileName() + " foi adicionado com sucesso no diretório.");
		FacesContext.getCurrentInstance().addMessage(null, message);
		
		return "success_upload";
	}
}