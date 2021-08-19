package br.hepta.treinamento.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.hepta.treinamento.entity.Agencia;
import br.hepta.treinamento.form.AgenciaForm;

public class AgenciaDAO {

	// Inserir agencia

	public Integer inserir(Agencia user) throws Exception {
		Connection connection = ConexaoUtil.conexao();
		Integer idCheck = 0;
		try {

			String sql = "INSERT INTO agencia (nome, adress, telefone) VALUES"
					+ " (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql, 
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getNome());
			statement.setString(2, user.getAdress());
			statement.setInt(3, user.getTelefone());

			statement.executeUpdate();
			ResultSet result = statement.getGeneratedKeys();
			while (result.next()) {
				idCheck = result.getInt(1);
				// ternario para if simples
				System.out.println(idCheck > 0 ? "Agencia inserida com " + 
				"sucesso" : "Usuario não cadastrado");
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
	// Editar Agencia

	public Agencia editar(AgenciaForm user) throws Exception {
		Connection connection = ConexaoUtil.conexao();

		try {

			String sql = "UPDATE agencia SET  nome =?, adress=?, telefone=? "
					+ "WHERE idag=? ";
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
		return null;
	}

	// Listar Agencia

	public List<Agencia> listarAgencia() throws Exception {
		Connection connection = ConexaoUtil.conexao();

		try {
			List<Agencia> lista = new ArrayList<>();

			String sql = " SELECT * FROM agencia ";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				Agencia agencia = new Agencia();
				// int id = result.getInt(1);

				agencia.setId(result.getInt(1));
				agencia.setNome(result.getString(4));
				agencia.setAdress(result.getString(2));
				agencia.setTelefone(result.getInt(3));

				//System.out.println();
				lista.add(agencia);
				//System.out.println(agencia.toString());
			}
			// System.out.println(lista);
			connection.close();

			return lista;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;

		}
	}

	// Deletar Agencia

	public boolean deletar(int id) throws Exception {
		Connection connection = ConexaoUtil.conexao();
		boolean var = false;
		try {

			String sql = "DELETE FROM  agencia WHERE idag=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);

			int rows = statement.executeUpdate();

			if (rows > 0) {
				var = true;
				System.out.println("Agencia deletada com sucesso.");
			}

			connection.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return var;
	}

//Encontrar agencia especifica

	public Agencia findAgencia(int id) throws Exception {
		Connection connection = ConexaoUtil.conexao();

		Agencia agencia = new Agencia();

		try {

			// jeito mais pratico com função nao void
			// ResultSet result = connection.prepareStatement("Select from usuarios where
			// user_id = ? ").setInt(1, id).executeQuery();

			String sql = " SELECT * from Agencia  WHERE idag = ? ";
			PreparedStatement statement = connection.prepareStatement(sql);
			// equivale ao ?
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				// int Id = result.getInt("user_id");

				agencia.setId(result.getInt(1));
				agencia.setNome(result.getString(4));
				agencia.setAdress(result.getString(2));
				agencia.setTelefone(result.getInt(3));
				System.out.println(agencia.toString());
			}

			System.out.println("sucesso.");

			connection.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		connection.close();
		return agencia;
	}
}