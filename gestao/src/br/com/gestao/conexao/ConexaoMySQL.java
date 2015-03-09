package br.com.gestao.conexao;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
	
	private Connection conn;
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/produtividade";
	private String user = "root";
	private String password = "";	
	
	public Connection getConn() {
		return conn;
	}
	
	public ConexaoMySQL(){
		
		if(conn==null){
			try {
				Class.forName(driver);
				conn = (Connection) DriverManager.getConnection(url, user, password);
			} catch (SQLException ex) {
				System.err.println("Erro"+ex);
				conn = null;
			}catch (ClassNotFoundException e) {
				System.out.println("Classe de conexão não encontrado!");
				 e.printStackTrace();
			}
		}
		
	}
	
	public void CloseConnection(){
		
		try {
			conn.close();
			conn = null;
		} catch (Exception e) {
			System.out.println("Conexão não fechada... ERROR" + e);
		}
	}

}
