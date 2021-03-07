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

			String sql = "INSERT INTO usuario (nome, login, senha, telefone, cep, rua, numero, bairro, cidade, uf, fotoBase64, contentType, curriculoBase64, curriculoContentType)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement cadastrar = connection.prepareStatement(sql);

			cadastrar.setString(1, usuario.getNome());
			cadastrar.setString(2, usuario.getLogin());
			cadastrar.setString(3, usuario.getSenha());
			cadastrar.setString(4, usuario.getTelefone());
			cadastrar.setString(5, usuario.getCep());
			cadastrar.setString(6, usuario.getRua());
			cadastrar.setString(7, usuario.getNumero());
			cadastrar.setString(8, usuario.getBairro());
			cadastrar.setString(9, usuario.getCidade());
			cadastrar.setString(10, usuario.getUf());
			cadastrar.setString(11, usuario.getFotoBase64());
			cadastrar.setString(12, usuario.getContentType());
			cadastrar.setString(13, usuario.getCurriculoBase64());
			cadastrar.setString(14, usuario.getCurriculoContentType());

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
				usuario.setCep(resultadoBusca.getString("cep"));
				usuario.setRua(resultadoBusca.getString("rua"));
				usuario.setNumero(resultadoBusca.getString("numero"));
				usuario.setBairro(resultadoBusca.getString("bairro"));
				usuario.setCidade(resultadoBusca.getString("cidade"));
				usuario.setUf(resultadoBusca.getString("uf"));
				usuario.setFotoBase64(resultadoBusca.getString("fotoBase64"));
				usuario.setContentType(resultadoBusca.getString("contentType"));
				usuario.setCurriculoBase64(resultadoBusca.getString("curriculoBase64"));
				usuario.setCurriculoContentType(resultadoBusca.getString("curriculoContentType"));

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

	public boolean validarLoginEdit(String login, String id) {

		try {

			String sql = "SELECT COUNT(1) AS qtd FROM usuario WHERE login = '" + login + "' and id <> '" + id + "'";
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
				usuario.setCep(resultSet.getString("cep"));
				usuario.setRua(resultSet.getString("rua"));
				usuario.setNumero(resultSet.getString("numero"));
				usuario.setBairro(resultSet.getString("bairro"));
				usuario.setCidade(resultSet.getString("cidade"));
				usuario.setUf(resultSet.getString("uf"));
				usuario.setFotoBase64(resultSet.getString("fotoBase64"));
				usuario.setContentType(resultSet.getString("contentType"));
				usuario.setCurriculoBase64(resultSet.getString("curriculoBase64"));
				usuario.setCurriculoContentType(resultSet.getString("curriculoContentType"));

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

			String sql = "UPDATE usuario SET nome = ?, login = ?, senha = ?, telefone = ?, cep = ?, rua = ?, numero = ?, bairro = ?, cidade = ?, uf = ? WHERE id = '"
					+ usuario.getId() + "'";
			PreparedStatement atualizar = connection.prepareStatement(sql);

			atualizar.setString(1, usuario.getNome());
			atualizar.setString(2, usuario.getLogin());
			atualizar.setString(3, usuario.getSenha());
			atualizar.setString(4, usuario.getTelefone());
			atualizar.setString(5, usuario.getCep());
			atualizar.setString(6, usuario.getRua());
			atualizar.setString(7, usuario.getNumero());
			atualizar.setString(8, usuario.getBairro());
			atualizar.setString(9, usuario.getCidade());
			atualizar.setString(10, usuario.getUf());
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
