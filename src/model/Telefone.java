package model;

public class Telefone {

	private Long id;
	private String tipo;
	private String numero;
	private String login_dono;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getLogin_dono() {
		return login_dono;
	}
	public void setLogin_dono(String login_dono) {
		this.login_dono = login_dono;
	}
	
	@Override
	public String toString() {
		return "Telefone [id=" + id + ", tipo=" + tipo + ", numero=" + numero + ", login_dono=" + login_dono + "]";
	}
	
}
