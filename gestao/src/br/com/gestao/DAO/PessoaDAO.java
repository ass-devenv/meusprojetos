package br.com.gestao.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.gestao.POJO.PessoaFisica;
import br.com.gestao.conexao.ConexaoMySQL;
import br.com.gestao.util.FormatDate;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class PessoaDAO {

	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ConexaoMySQL connect = null;
	private Statement stmt = null;
	ResultSet rs = null;

	java.sql.Date dateBanco = null;
	
	public PessoaDAO() {

		connect = new ConexaoMySQL();

		try {
			stmt = (Statement) connect.getConn().createStatement();
		} catch (Exception e) {

		}
	}

	public void Adiciona(PessoaFisica pessoa_) {
		
		if (connect != null) {
			connect = new ConexaoMySQL();
		}
		
		String dataFormat = "";
		Date dt = new Date();		
		FormatDate fdata = new FormatDate();		
		GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
		
		calendar.setTime(pessoa_.getData_nasc());
				
		dataFormat = fdata.format(calendar);
		
		try {
			
			calendar = fdata.convertFromDMY(dataFormat);
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		pessoa_.setData_nasc(calendar.getTime());
		
		
		
		try {

			stmt.execute("INSERT INTO pessoa(nome, sobrenome, estado_civil, data_nascimento, rg, cpf, logradouro, bairro, municipio, "
					+ "estado, cep, telefone, celular, email, credito) VALUES ('"
					+ pessoa_.getNome()
					+ "','"
					+ pessoa_.getSobrenome()
					+ "','"
					+ pessoa_.getEstCivil()
					+ "','"
					+ pessoa_.getData_nasc()
					+ "','"
					+ pessoa_.getRg()
					+ "','"
					+ pessoa_.getCpf()
					+ "','"
					+ pessoa_.getLogradouro()
					+ "','"
					+ pessoa_.getBairro()
					+ "','"
					+ pessoa_.getMunicipio()
					+ "','"
					+ pessoa_.getEstado()
					+ "','"
					+ pessoa_.getCep()
					+ "','"
					+ pessoa_.getTel()
					+ "','"
					+ pessoa_.getCel()
					+ "','"
					+ pessoa_.getEmail()
					+ "','"
					+ pessoa_.getCredito() + "')");

		} catch (SQLException u) {

			throw new RuntimeException(u);

		} finally {

			connect.CloseConnection();

		}
	}
	
	public void deletar(PessoaFisica pessoa_) {

		if (connect != null) {
			connect = new ConexaoMySQL();
		}

		try {

			stmt.execute("DELETE FROM empresa WHERE codigo =" + pessoa_.getId());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.CloseConnection();
		}
	}

	public void alterar(PessoaFisica pessoa_) {

		if (connect != null) {
			connect = new ConexaoMySQL();
		}

		try {
			stmt.executeUpdate("UPDATE pessoa SET nome = '" + pessoa_.getNome()
					+ "'sobrenome = '" + pessoa_.getSobrenome()
					+ "'estado_civil = '" + pessoa_.getEstCivil()
					+ "'data_nascimento = '" + pessoa_.getData_nasc()
					+ "'rg = '" + pessoa_.getRg() + "'cpf = '"
					+ pessoa_.getCpf() + "'logradouro = '"
					+ pessoa_.getLogradouro() + "'bairro = '"
					+ pessoa_.getBairro() + "'municipio = '"
					+ pessoa_.getMunicipio() + "'estado = '"
					+ pessoa_.getEstado() + "'cep = '" + pessoa_.getCep()
					+ "'telefone = '" + pessoa_.getTel() + "'celular = '"
					+ pessoa_.getCel() + "'email = '" + pessoa_.getEmail()
					+ "'credito = '" + pessoa_.getCredito() + "WHERE codigo = "
					+ pessoa_.getId());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error ao Alterar..." + e);
		} finally {
			connect.CloseConnection();
		}
	}
	
	public List<PessoaFisica> listar(){
		
		PessoaFisica pessoa_ = null;
		
		if(connect != null){
			 
			 connect = new ConexaoMySQL();
		 }
		
		List<PessoaFisica> pessoas = new ArrayList<PessoaFisica>();
		
		try{
			String sql = "select * from pessoa";
			
			conn = connect.getConn();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				pessoa_ = new PessoaFisica();
				
				pessoa_.setId(rs.getInt("id"));
				pessoa_.setNome(rs.getString("nome"));
				pessoa_.setSobrenome(rs.getString("sobrenome"));
				pessoa_.setEstCivil(rs.getString("estado_civil"));
				pessoa_.setData_nasc(rs.getDate("data_nascimento"));
				pessoa_.setRg(rs.getString("rg"));
				pessoa_.setCpf(rs.getString("cpf"));
				pessoa_.setLogradouro(rs.getString("logradouro"));
				pessoa_.setBairro(rs.getString("bairro"));
				pessoa_.setMunicipio(rs.getString("municipio"));
				pessoa_.setEstado(rs.getString("estado"));
				pessoa_.setCep(rs.getString("cep"));
				pessoa_.setTel(rs.getString("telefone"));
				pessoa_.setCel(rs.getString("celular"));
				pessoa_.setEmail(rs.getString("email"));
				pessoa_.setCredito(rs.getDouble("credito"));
				
				pessoas.add(pessoa_);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connect.CloseConnection();
		}
		
		return pessoas;
	}
}
