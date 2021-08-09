package br.hepta.treinamento.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.hepta.treinamento.entity.Usuario;

//inserir

public class UsuarioDAO {
	// Criando um objeto do tipo inteiro com a função chamada 'inserir' o user e
	// vai lançar uma excessao

	public Integer inserir(Usuario user) throws Exception {

		Connection connection = ConexaoUtil.conexao();
		Integer idCheck = 0;
		try {

			String sql = " INSERT INTO usuarios (username, password, fullname, "
					+ " email, FK_agencia ) VALUES (?, ?, ?, ?, ?) ";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, user.getNome());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFullname());
			statement.setString(4, user.getEmail());
			statement.setInt(5, user.getFK_agencia());

			statement.executeUpdate();
			ResultSet result = statement.getGeneratedKeys();
			while(result.next()) {
			idCheck = result.getInt(1);
			// ternario para if simples
			System.out.println(idCheck > 0 ? "Usuario inserido com " + "sucesso" : "Usuario não cadastrado");
			}
			return idCheck;

		} catch (

		SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {

			connection.close();
		}
	}

	// listar todos usuarios

	public List<Usuario> listar() throws Exception {
		Connection connection = ConexaoUtil.conexao();

		try {
			List<Usuario> lista = new ArrayList<>();

			String sql = " SELECT * FROM usuarios ";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				Usuario usuario = new Usuario();
				// int id = result.getInt(1);

				usuario.setId(result.getInt(1));
				usuario.setNome(result.getString(2));
				usuario.setFullname(result.getString(3));
				usuario.setEmail(result.getString(4));
				usuario.setPassword(result.getString(5));
				usuario.setFK_agencia(result.getInt(6));
				System.out.println();
				lista.add(usuario);
				System.out.println(usuario.toString());
			}
			// System.out.println(lista);
			connection.close();

			return lista;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;

		}
	}

	// Encontrar usuario especifico

	public Usuario findUsuario(int id) throws Exception {
		Connection connection = ConexaoUtil.conexao();

		Usuario usuario = new Usuario();

		try {

			// jeito mais pratico com função nao void
			// ResultSet result = connection.prepareStatement("Select from usuarios where
			// user_id = ? ").setInt(1, id).executeQuery();

			String sql = " SELECT * from usuarios  WHERE user_id = ? ";
			PreparedStatement statement = connection.prepareStatement(sql);
			// equivale ao ?
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				// int Id = result.getInt("user_id");

				usuario.setId(result.getInt(1));
				usuario.setNome(result.getString(2));
				usuario.setFullname(result.getString(4));
				usuario.setEmail(result.getString(5));
				usuario.setPassword(result.getString(3));
				usuario.setFK_agencia(result.getInt(6));
				System.out.println(usuario.toString());
			}

			System.out.println("sucesso.");

			connection.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		connection.close();
		return usuario;
	}

	// Editar usuario

	public void editar(Usuario user) throws Exception {
		Connection connection = ConexaoUtil.conexao();

		try {

			String sql = " UPDATE usuarios SET password=?, fullname=?, " + "email=? , "
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

	// Editar agencia do usuario

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

	// Deletar usuario

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
