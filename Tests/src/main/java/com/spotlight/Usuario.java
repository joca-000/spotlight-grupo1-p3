package com.spotlight;
import java.io.Serializable;
import java.util.UUID;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String nome;
	private String email;
	private String senha;
	private String tipo;

	public Usuario(String nome, String email, String senha, String tipo) {
		this.id = UUID.randomUUID().toString();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.tipo = tipo;
	}

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

	@Override
	public String toString() {
		return "ID: " + id + ", Nome: " + nome + ", Email: " + email + ", Tipo: " + tipo;
	}
}