package br.com.gestao.controller;

import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.gestao.DAO.EmpresaDAO;
import br.com.gestao.POJO.Empresa;

public class EmpresaBean {	
	
	private Empresa emp = new Empresa();
	private EmpresaDAO dao;
	private DataModel<Empresa> empresas;
	
		
	public Empresa getEmp() {
		return emp;
	}

	public void setEmp(Empresa emp) {
		this.emp = emp;
	}

	public DataModel<Empresa> getEmpresas() {
		dao = new EmpresaDAO();	
		List<Empresa> empList = dao.listar();		
		empresas = new ListDataModel<Empresa>(empList); 
		return empresas;
	}

	public void setEmpresas(DataModel<Empresa> empresas) {
		this.empresas = empresas;
	}	
	
	
	//###--- BUTTONs ---###//
	public String btnNovo() throws SQLException{	
		
		return "new";
		
	}
	
	public String btnSalvar(){		
		try {
			
			dao = new EmpresaDAO();
		    dao.Adiciona(emp);		    
		    
		    if(true){
		    
		    	FacesContext context = FacesContext.getCurrentInstance();
		    
		    	FacesMessage msg = new FacesMessage("Cadastrado!");
		    
		    	context.addMessage(null, msg);
		    }
		    
		    return "success";
		    
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Houver um Erro..." + e);
		}
			return "failure";
	}
	
	public String btnDeletar(){
		dao = new EmpresaDAO();
		try {
			
			dao.deletar(emp);
			return"success";
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error..."+ e);
			return"failure";
		}
		
	}
	
	public String btnAlterar(){
		
		dao = new EmpresaDAO();
		try {
			
			dao.alterar(emp);		
			return"success";
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error" + e);
			return "failure";
		}
		
	}
	
	public void selecionar(){
		emp = (Empresa) empresas.getRowData();
	} 
				
	public String btnChamarTeleAlterar(){	
		return"successS";				
	}

	
	}
