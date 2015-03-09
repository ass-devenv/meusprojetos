package br.com.gestao.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.event.FlowEvent;

import br.com.gestao.DAO.PessoaDAO;
import br.com.gestao.POJO.PessoaFisica;

public class PessoaBean {

	private PessoaFisica pessoa = new PessoaFisica();
	private PessoaDAO pDao;
	private DataModel<PessoaFisica> pessoas;
	private boolean skip;
	
	public PessoaFisica getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaFisica pessoa) {
		this.pessoa = pessoa;
	}

	public PessoaDAO getpDao() {
		return pDao;
	}

	public void setpDao(PessoaDAO pDao) {
		this.pDao = pDao;
	}
	
	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }

	public DataModel<PessoaFisica> getPessoas() {
		pDao = new PessoaDAO();
		
		List<PessoaFisica> ListaPessoas = pDao.listar();
		pessoas = new ListDataModel<PessoaFisica>(ListaPessoas);

		return pessoas;
	}

	public void setPessoas(DataModel<PessoaFisica> pessoas) {
		this.pessoas = pessoas;
	}
	

	public String btnSave() {
		
		try {

			pDao = new PessoaDAO();
			pDao.Adiciona(pessoa);

			if (true) {

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

	public String btnDeletar() {

		try {
			pDao = new PessoaDAO();

			pDao.deletar(pessoa);
			return "success";

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error..." + e);
			return "failure";
		}

	}

	public String btnAlterar() {

		pDao = new PessoaDAO();
		try {

			pDao.alterar(pessoa);
			return "success";

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error" + e);
			return "failure";
		}

	}

}
