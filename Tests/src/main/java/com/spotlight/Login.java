package com.spotlight;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Login implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Usuario> usuarios;
    private Usuario usuarioAutenticado;

    public Login() {
        this.usuarios = new ArrayList<>();
        this.usuarioAutenticado = null;
        carregarUsuarios(); // Carregar usuários ao iniciar
    }

    public boolean autenticar(String nome, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome) && usuario.getSenha().equals(senha)) {
                usuarioAutenticado = usuario;
                return true;
            }
        }
        return false;
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void logout() {
        usuarioAutenticado = null;
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario buscarUsuarioPorNome(String nome) {
        return usuarios.stream()
                .filter(u -> u.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    // Método para carregar usuários de um arquivo
    @SuppressWarnings("unchecked")
    public void carregarUsuarios() {
        try (FileInputStream fileIn = new FileInputStream("usuarios.dat");
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            usuarios = (List<Usuario>) objectIn.readObject();
        } catch (FileNotFoundException e) {
            // Arquivo não existe, inicia com lista vazia
            usuarios = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
            usuarios = new ArrayList<>();
        }
    }

    // Método para salvar usuários em um arquivo
    public void salvarUsuarios() {
        try (FileOutputStream fileOut = new FileOutputStream("usuarios.dat");
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(usuarios);
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }
}