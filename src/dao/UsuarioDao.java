package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import model.Usuario;

public class UsuarioDao {

	private Connection connection;

	public UsuarioDao() {
		connection = SingleConnection.getConnection();

	}

	// Fazendo login no index.jsp.
	public boolean validarUsuario(String login, String senha) throws SQLException {

		String sql = "SELECT * FROM usuario WHERE login = '" + login + "' AND senha = '" + senha + "'";
		PreparedStatement validar = connection.prepareStatement(sql);
		ResultSet resultset = validar.executeQuery();
		connection.commit();

		if (resultset.next()) {

			return true;

		} else {

			return false;
		}
	}

	public void cadastrarUsuario(Usuario usuario) {

		try {

			String sql = "INSERT INTO usuario (nome, login, senha, telefone) VALUES (?,?,?,?)";
			PreparedStatement cadastrar = connection.prepareStatement(sql);

			cadastrar.setString(1, usuario.getNome());
			cadastrar.setString(2, usuario.getLogin());
			cadastrar.setString(3, usuario.getSenha());
			cadastrar.setString(4, usuario.getTelefone());

			cadastrar.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public Usuario buscarUsuario(String login) {

		try {

			String sql = "SELECT * FROM usuario WHERE login = '" + login + "'";
			PreparedStatement buscar = connection.prepareStatement(sql);
			ResultSet resultadoBusca = buscar.executeQuery();

			if (resultadoBusca.next()) {

				Usuario usuario = new Usuario();
				usuario.setId(resultadoBusca.getLong("id"));
				usuario.setNome(resultadoBusca.getString("nome"));
				usuario.setLogin(resultadoBusca.getString("login"));
				usuario.setSenha(resultadoBusca.getString("senha"));
				usuario.setTelefone(resultadoBusca.getString("telefone"));

				return usuario;
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return null;

	}

	// Verificando se o login já foi cadastrado.
	public boolean validarLogin(String login) {

		try {

			String sql = "SELECT COUNT(1) AS qtd FROM usuario WHERE login = '" + login + "'";
			PreparedStatement validar = connection.prepareStatement(sql);
			ResultSet resultado = validar.executeQuery();

			if (resultado.next()) {

				return resultado.getInt("qtd") <= 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return false;
	}

	public List<Usuario> listarUsuarios() {

		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {

			String sql = "SELECT * FROM usuario ORDER BY id ASC";
			PreparedStatement buscar = connection.prepareStatement(sql);
			ResultSet resultSet = buscar.executeQuery();

			while (resultSet.next()) {

				Usuario usuario = new Usuario();
				usuario.setId(resultSet.getLong("id"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setLogin(resultSet.getString("login"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setTelefone(resultSet.getString("telefone"));

				usuarios.add(usuario);
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return usuarios;
	}

	public void deletarUsuario(String login) {

		try {

			String sql = "DELETE FROM usuario WHERE login = '" + login + "'";
			PreparedStatement delete = connection.prepareStatement(sql);
			delete.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public Usuario editarUsuario(Usuario usuario) {

		try {

			String sql = "UPDATE usuario SET nome = ?, login = ?, senha = ?, telefone = ? WHERE id = '" + usuario.getId() + "'";
			PreparedStatement atualizar = connection.prepareStatement(sql);

			atualizar.setString(1, usuario.getNome());
			atualizar.setString(2, usuario.getLogin());
			atualizar.setString(3, usuario.getSenha());
			atualizar.setString(4, usuario.getTelefone());
			atualizar.executeUpdate();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return usuario;
	}
}
