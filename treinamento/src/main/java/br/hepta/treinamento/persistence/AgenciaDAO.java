package br.hepta.treinamento.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.hepta.treinamento.entity.Agencia;

public class AgenciaDAO {

	public void inserir(Agencia user) throws Exception {
		Connection connection = ConexaoUtil.conexao();

		try {

			String sql = "INSERT INTO agencia (nome, adress, telefone) VALUES (?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getNome());
			statement.setString(2, user.getAdress());
			statement.setInt(3, user.getTelefone());
		

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Usuario inserido com sucesso");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void editar(Agencia user) throws Exception {
		Connection connection = ConexaoUtil.conexao();

		try {

			String sql = "UPDATE agencia SET  nome =?, adress=?, telefone=? WHERE idag=? ";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getNome());
			statement.setString(2, user.getAdress());
			statement.setInt(3, user.getTelefone());
			statement.setInt(4, user.getIdag());
			int rows = statement.executeUpdate();

			if (rows > 0) {
				System.out.println("Dados modificados com sucesso.");
			}

			connection.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		connection.close();
	}
	
	
	public List<Agencia> listar() throws Exception {
		Connection connection = ConexaoUtil.conexao();

		try {

			String sql = "SELECT * FROM agencia";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				int Id = result.getInt("idag");
				String nome = result.getString(1);
				String endereço = result.getString(2);
				Integer telefone = result.getInt(3);
		

				System.out.println(Id + ": " + nome + " - " + endereço + " - " + telefone );
				return null;
			}

			connection.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;

		}
		return null;
	}
	

	public void deletar(int id) throws Exception {
		Connection connection = ConexaoUtil.conexao();

		try {

			String sql = "DELETE FROM  agencia WHERE idag=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);

			int rows = statement.executeUpdate();

			if (rows > 0) {
				System.out.println("Usuario deletado com sucesso.");
			}

			connection.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}
	
}
