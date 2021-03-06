package model;

public class Usuario {

	private Long id;
	private String nome;
	private String login;
	private String senha;
	private String telefone;
	private String rua;
	private String numero;
	private String bairro;
	private String cidade;
	private String uf;
	private String cep;
	private String fotoBase64;
	private String curriculoBase64;
	private String contentType;
	private String curriculoContentType;
	private String fotoUser;
	
	public String getFotoUser() {
		
		fotoUser = "data:" + contentType + ";base64," + fotoBase64;
		
		return fotoUser;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getFotoBase64() {
		return fotoBase64;
	}
	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getCurriculoBase64() {
		return curriculoBase64;
	}
	public void setCurriculoBase64(String curriculoBase64) {
		this.curriculoBase64 = curriculoBase64;
	}
	public String getCurriculoContentType() {
		return curriculoContentType;
	}
	public void setCurriculoContentType(String curriculoContentType) {
		this.curriculoContentType = curriculoContentType;
	}
	public void setFotoUser(String fotoUser) {
		this.fotoUser = fotoUser;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", login=" + login + ", senha=" + senha + ", telefone="
				+ telefone + ", rua=" + rua + ", numero=" + numero + ", bairro=" + bairro + ", cidade=" + cidade
				+ ", uf=" + uf + ", cep=" + cep + ", fotoBase64=" + fotoBase64 + ", curriculoBase64=" + curriculoBase64
				+ ", contentType=" + contentType + ", curriculoContentType=" + curriculoContentType + ", fotoUser="
				+ fotoUser + "]";
	}

}
