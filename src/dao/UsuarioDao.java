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

			String sql = "INSERT INTO usuario (nome, login, senha) VALUES (?,?,?)";
			PreparedStatement cadastrar = connection.prepareStatement(sql);

			cadastrar.setString(1, usuario.getNome());
			cadastrar.setString(2, usuario.getLogin());
			cadastrar.setString(3, usuario.getSenha());

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

			String sql = "UPDATE usuario SET nome = ?, login = ?, senha = ? WHERE id = '" + usuario.getId() + "'";
			PreparedStatement atualizar = connection.prepareStatement(sql);

			atualizar.setString(1, usuario.getNome());
			atualizar.setString(2, usuario.getLogin());
			atualizar.setString(3, usuario.getSenha());
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
