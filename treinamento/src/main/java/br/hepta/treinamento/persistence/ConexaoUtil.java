package br.hepta.treinamento.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.hepta.treinamento.persistence.ConexaoUtil;

public class ConexaoUtil {

	public static Connection conexao() {
		String jdbcURL = "jdbc:mysql://localhost:3306/treinamento";
		String usernamebd = "root";
		String passwordbd = "Mnbemf10@10";
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcURL, usernamebd, passwordbd);
			System.out.println("Conectado ao Banco de Dados");
		} catch (SQLException ex) {
			System.out.println("Nao foi possivel conectar ao Banco de Dados.");
		} catch (ClassNotFoundException ex) {
			System.out.println("Erro: Não encontrou o driver do Banco de Dados.");
		}
		return conn;
	}

	public void desconectar(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
				System.out.println("Desconectado do servidor.");
			}
		} catch (SQLException ex) {
			System.out.println("nao foi possivel desconectar do Banco de Dados.");
		}
	}
}
