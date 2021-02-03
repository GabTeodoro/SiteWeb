package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import model.Produto;

public class ProdutoDao {

	private Connection connection;

	public ProdutoDao() {

		connection = SingleConnection.getConnection();

	}

	public void CadastrarProduto(Produto produto) {

		try {

			String sql = "INSERT INTO produto (nome, quantidade, valor) VALUES (?,?,?)";
			PreparedStatement inserir = connection.prepareStatement(sql);

			inserir.setString(1, produto.getNome());
			inserir.setInt(2, produto.getQuantidade());
			inserir.setDouble(3, produto.getValor());
			inserir.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public List<Produto> listarProduto() {

		List<Produto> produtos = new ArrayList<Produto>();

		try {

			String sql = "SELECT * FROM produto ORDER BY id ASC";
			PreparedStatement listar = connection.prepareStatement(sql);
			ResultSet resultSet = listar.executeQuery();

			if (resultSet.next()) {

				Produto produto = new Produto();
				produto.setCodigo(resultSet.getLong("codigo"));
				produto.setNome(resultSet.getString("nome"));
				produto.setQuantidade(resultSet.getInt("quantidade"));
				produto.setValor(resultSet.getDouble("valor"));

				produtos.add(produto);

			}

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return produtos;
	}

	public Produto buscarProduto(String nome) {

		try {

			String sql = "SELECT * FROM produto WHERE nome = '" + nome + "'";
			PreparedStatement buscar = connection.prepareStatement(sql);
			ResultSet resultSet = buscar.executeQuery();

			if (resultSet.next()) {

				Produto produto = new Produto();
				produto.setCodigo(resultSet.getLong("codigo"));
				produto.setNome(resultSet.getString("nome"));
				produto.setQuantidade(resultSet.getInt("quantidade"));
				produto.setValor(resultSet.getDouble("valor"));

				return produto;
			}

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return null;
	}

	public Produto editarProduto(Produto produto) {

		try {

			String sql = "UPDATE produto SET nome = ?, quantidade = ?, valor = ? WHERE codigo = '" + produto.getCodigo()
					+ "'";
			PreparedStatement editar = connection.prepareStatement(sql);

			editar.setString(1, produto.getNome());
			editar.setInt(2, produto.getQuantidade());
			editar.setDouble(3, produto.getValor());

			editar.executeUpdate();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return null;
	}

	public void deletarProduto(String nome) {

		try {
			
			String sql = "DELETE FROM produto WHERE nome = '" + nome + "'";
			PreparedStatement deletar = connection.prepareStatement(sql);
			deletar.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
