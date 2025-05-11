package spotlight;

public class Usuario{
	private String id;
	private String nome;
	private String email;
	private String senha;
	private String tipo; // admin, gerente, funcionario
	
	//construtor
	public Usuario(String id, String nome, String email, String senha, String tipo) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.tipo = tipo;
	}
	
	//getters e setters
	public String getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	public String getSenha() {
		return senha;
	}
	public String getTipo() {
		return tipo;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	//metodo para verificar senha
	public boolean verificarSenha(String senha) {
		return this.senha.equals(senha);
	}
}

