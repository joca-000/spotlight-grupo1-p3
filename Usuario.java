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
	
	public String setId() {
		this.id = id;
	}
	public String setNome() {
		this.nome = nome;
	}
	public String setEmail() {
		this.email = email;
	}
	public String setSenha() {
		this.senha = senha;
	}
	public String setTipo() {
		this.tipo = tipo;
	}
	
	//metodo para verificar senha
	public boolean verificarSenha(String senha) {
		return this.senha.equals(senha);
	}
}
