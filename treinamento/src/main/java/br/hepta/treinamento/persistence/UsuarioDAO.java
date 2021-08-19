package br.hepta.treinamento.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.hepta.treinamento.entity.Usuario;
import br.hepta.treinamento.form.UsuarioForm;

//inserir

public class UsuarioDAO {

	public Integer inserir(Usuario user) throws Exception {

		Connection connection = ConexaoUtil.conexao();
		Integer idCheck = 0;
		try {

			String sql = " INSERT INTO usuarios (username, fullname, password, "
					+ " email, FK_agencia, user_data, imagem ) VALUES (?, ?, ?, ?, ?, ?, ?) ";

			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, user.getNome());
			statement.setString(3, user.getPassword());
			statement.setString(2, user.getFullname());
			statement.setString(4, user.getEmail());
			statement.setInt(5, user.getFK_agencia());
			statement.setDate(6, new java.sql.Date(user.getUser_data().getTime()));
			statement.setBytes(7, user.getImagem());

			statement.executeUpdate();
			ResultSet result = statement.getGeneratedKeys();
			while (result.next()) {
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
				
				usuario.setId(result.getInt(1));
				usuario.setNome(result.getString(2));
				usuario.setFullname(result.getString(4));
				usuario.setEmail(result.getString(5));
				usuario.setPassword(result.getString(3));
				usuario.setFK_agencia(result.getInt(6));
				usuario.setUser_data(result.getDate(7));
				usuario.setImagem(result.getBytes(8));
				
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

			String sql = " SELECT * from usuarios  WHERE user_id = ? ";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				usuario.setId(result.getInt(1));
				usuario.setNome(result.getString(2));
				usuario.setFullname(result.getString(4));
				usuario.setEmail(result.getString(5));
				usuario.setPassword(result.getString(3));
				usuario.setFK_agencia(result.getInt(6));
				usuario.setUser_data(result.getDate(7));
				usuario.setImagem(result.getBytes(8));
				
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

	public Usuario editar(UsuarioForm user) throws Exception {
		Connection connection = ConexaoUtil.conexao();

		try {

			String sql = " UPDATE usuarios SET password=?, fullname=?, " + "email=? , "
					+ " username=? , FK_agencia=? , user_data=? , imagem=? WHERE user_id=? ";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getSenha());
			statement.setString(2, user.getNomecompleto());
			statement.setString(3, user.getMail());
			statement.setString(4, user.getNome());
			statement.setInt(5, user.getFK_agencia());
			statement.setDate(6, new java.sql.Date(user.getUser_data().getTime()));
			statement.setBytes(7, user.getImagem());
			statement.setInt(8, user.getId());

			int rows = statement.executeUpdate();

			if (rows > 0) {
				System.out.println("Dados modificados com sucesso.");
			}
			connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		connection.close();
		return null;
	}

	// Deletar usuario

	public boolean deletar(int id) throws Exception {
		Connection connection = ConexaoUtil.conexao();
		boolean var = false;
		try {

			String sql = " DELETE FROM  usuarios WHERE user_id=? ";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);

			int rows = statement.executeUpdate();

			if (rows > 0) {
				var = true;
				System.out.println("Usuario deletado com sucesso.");
			}

			connection.close();

		} catch (SQLException ex) {
			var = false;
			ex.printStackTrace();
		}
		return var;
	}
}
