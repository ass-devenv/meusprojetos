package managedBean;

import java.io.File;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;


public class DocumentoService {
    
	private TreeNode root;
	private TreeNode selectedNode;
	private String pathRel;
	
	@PostConstruct	
	public void init() {    	
    	
		FacesContext facesContext = FacesContext.getCurrentInstance();  
		ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();  
		pathRel = servletContext.getRealPath("/upfiles");
		
		root = new DefaultTreeNode("Root", null);        
		TreeNode documents = new DefaultTreeNode("Meus Arquivos", root);        
		TreeNode work = new DefaultTreeNode(pathRel,documents);		
		 
		File diretorio = new File(pathRel);  
		File[] arquivos = diretorio.listFiles();  
		
		if(arquivos.length > 0){
			
			for (int i = 0; i < arquivos.length; i++) {  

				File arq = arquivos[i];
				
				work.getChildren().add(new DefaultTreeNode(arq.getName(), documents));
		        
			}
		}
                  
    }
	
	public TreeNode getRoot() {
		return root;
	}	
	
	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public void displaySelectedSingle() {
      
		if(selectedNode != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected :", selectedNode.getData().toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
      
    public String deleteNode() {     
    	
    	File arquivo = new File(selectedNode.getParent().toString()+"/"+selectedNode.getData());    	
    	
    	if(arquivo.exists()){   		
    		arquivo.delete();    	
    	}
    
    	return "up-success";
    } 
    
}