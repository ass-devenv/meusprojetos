package br.com.gestao.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import br.com.gestao.POJO.Empresa;
import br.com.gestao.conexao.ConexaoMySQL;

public class EmpresaDAO {
	
	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ConexaoMySQL connect = null;	
	private Statement stmt = null;
	ResultSet rs = null;	
	Empresa emp;
	
	public EmpresaDAO(){
		connect = new ConexaoMySQL();
		try {
			 stmt = (Statement) connect.getConn().createStatement(); 
		} catch (Exception e) {
			
		}
	}	
	
	public void Adiciona(Empresa emp){		
		if(connect != null){
		  connect = new ConexaoMySQL();	  
		}
	        try {        	
	        		        	
	        	stmt.execute("INSERT INTO empresa(razao_social, fantazia, cpf_cnpj, ins_estadual, lougradouro, bairro, municipio, estado, cep, telefone, email) VALUES ('" + emp.getRazao() + "','" +
	        			 emp.getFantazia() + "','" + emp.getCpf_cnpj() + "','" + 
	        			 emp.getIns_estadual() + "','" + emp.getLougradouro() + "','" + 
	        			 emp.getBairro() + "','" + emp.getMunicipio() + "','" + emp.getEstado() + "','" + emp.getCep() + "','" + 
	        			 emp.getTelefone() + "','" + emp.getEmail() +"')");	        	
	           
	        }catch (SQLException u) {
	            throw new RuntimeException(u);
	        }finally{
	        	connect.CloseConnection();
	        }
	    }

	
	 public void alterar(Empresa emp){	
		 
		 if(connect != null){
		 connect = new ConexaoMySQL();
		 }
		 
		 try {			
			 stmt.executeUpdate("UPDATE empresa SET razao_social = '"+ emp.getRazao() + "'fantazia = '" + emp.getFantazia() + 
					 			            "'cpf_cnpj = '"+ emp.getCpf_cnpj() + "'ins_estadual = '"+ emp.getIns_estadual() + 
					 			                "'lougradouro = '" + emp.getLougradouro() + "'bairro = '" + emp.getBairro() +
					 			                    "'municipio = '" + emp.getMunicipio() + "'estado = '" + emp.getEstado() + 
					 			                            "'cep = '" + emp.getCep() + "'telefone = '" + emp.getTelefone() + 
					 			            "'email = '" + emp.getEmail() + "WHERE codigo = " + emp.getCodigo());		
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error ao Alterar..." + e);
		}finally{
			connect.CloseConnection();
		}
	 }
	
	 
	 public void deletar(Empresa emp){
		 
		 if(connect != null){
		 connect = new ConexaoMySQL();
		 }
		 
		 try {
			 
			stmt.execute("DELETE FROM empresa WHERE codigo =" + emp.getCodigo());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connect.CloseConnection();
		}
	 }

	 
	 public List<Empresa> listar(){
		 
		 Empresa emp = null;
		 
		 if(connect != null){
		 
			 connect = new ConexaoMySQL();
		 }
		 
		 List<Empresa> empresas = new ArrayList<Empresa>();
		 
		 try {
			 
			 conn = connect.getConn();
			 pstm = conn.prepareStatement("SELECT * FROM empresa");			  
			 rs = pstm.executeQuery();
			
			while(rs.next()){
				
				emp = new Empresa();
				
				emp.setCodigo(rs.getInt("codigo"));
				emp.setRazao(rs.getString("razao_social"));
				emp.setFantazia(rs.getString("fantazia"));
				emp.setCpf_cnpj(rs.getString("cpf_cnpj"));
				emp.setIns_estadual(rs.getString("ins_estadual"));
				emp.setLougradouro(rs.getString("lougradouro"));
				emp.setBairro(rs.getString("bairro"));
				emp.setMunicipio(rs.getString("municipio"));
				emp.setEstado(rs.getString("estado"));
				emp.setCep(rs.getString("cep"));
				emp.setTelefone(rs.getString("telefone"));
				emp.setEmail(rs.getString("email"));
				empresas.add(emp);			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connect.CloseConnection();
		}		 
		 
		 return empresas;
	 }

}
