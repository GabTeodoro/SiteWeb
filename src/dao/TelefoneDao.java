package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import model.Telefone;

public class TelefoneDao {

	private Connection connection;
	
	public TelefoneDao() {
		
		connection = SingleConnection.getConnection();

	}
	
	public void CadastrarTelefone(Telefone telefone) {
		
		try {
			
			String sql = "INSERT INTO telefone (tipo, numero, login_dono) VALUES (?,?,?)";
			PreparedStatement inserir = connection.prepareStatement(sql);
			
			inserir.setString(1, telefone.getTipo());
			inserir.setString(2, telefone.getNumero());
			inserir.setString(3, telefone.getLogin_dono());
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
	
	public List<Telefone> listarTelefone(String login_dono){
		
		List<Telefone> telefones = new ArrayList<Telefone>();
		
		try {
			
			String sql = "SELECT * FROM telefone WHERE login_dono = '" + login_dono + "' ORDER BY id ASC";
			PreparedStatement listar = connection.prepareStatement(sql);
			ResultSet resultSet = listar.executeQuery();
			
			while (resultSet.next()) {
				
				Telefone telefone = new Telefone();
				telefone.setId(resultSet.getLong("id"));
				telefone.setTipo(resultSet.getString("tipo"));
				telefone.setNumero(resultSet.getString("numero"));
				telefone.setLogin_dono(resultSet.getString("login_dono"));
				telefones.add(telefone);
				
			}
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return telefones;
	}
	
	public void DeletarTelefone(String id) {
		
		try {
			
			String sql = "DELETE FROM telefone WHERE id = '" + id + "'";
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
