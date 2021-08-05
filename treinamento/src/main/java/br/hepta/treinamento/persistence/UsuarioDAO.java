package br.hepta.treinamento.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.hepta.treinamento.entity.Usuario;

public class UsuarioDAO {

	public void inserir(Usuario user) throws Exception {
		Connection connection = ConexaoUtil.conexao();

		try {

			String sql = " INSERT INTO usuarios (username, password, fullname, "
					+ " email, FK_agencia ) VALUES (?, ?, ?, ?, ?) ";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getNome());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFullname());
			statement.setString(4, user.getEmail());
			statement.setInt(5, user.getFK_agencia());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Usuario inserido com sucesso");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void editar(Usuario user) throws Exception {
		Connection connection = ConexaoUtil.conexao();

		try {

			String sql = " UPDATE usuarios SET password=?, fullname=?, email=? , "
					+ " username=? , FK_agencia=? WHERE user_id=? ";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getPassword());
			statement.setString(2, user.getFullname());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getNome());
			statement.setInt(5, user.getFK_agencia());
			statement.setInt(6, user.getId());
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

	public void editarFKAgencia(Usuario user) throws Exception {
		Connection connection = ConexaoUtil.conexao();

		try {

			String sql = " UPDATE usuarios SET FK_agencia=? WHERE user_id=? ";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, user.getFK_agencia());
			statement.setInt(2, user.getId());
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

	public List<Usuario> listar() throws Exception {
		Connection connection = ConexaoUtil.conexao();

		try {

			String sql = " SELECT * FROM usuarios ";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				int Id = result.getInt("user_id");
				String nome = result.getString(2);
				String nomecompleto = result.getString(4);
				String email = result.getString(5);
				String senha = result.getString(3);
				int fkAgencia = result.getInt(6);

				System.out.println(
						Id + ": " + nome + " - " + nomecompleto + " - " + email
						+ " - " + senha + " - " + fkAgencia);
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

			String sql = " DELETE FROM  usuarios WHERE user_id=? ";
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
