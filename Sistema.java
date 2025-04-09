import java.util.ArrayList;

public class Sistema {
    private ArrayList<Usuario> usuarios;

    public Sistema() {
        this.usuarios = new ArrayList<>();
    }

    public void cadastrarUsuario(String nome, String login, String senha) {
        Usuario novoUsuario = new Usuario(nome, login, senha);
        usuarios.add(novoUsuario);
        System.out.println("Usuário " + nome + " cadastrado com sucesso!");
    }

    public boolean fazerLogin(String login, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                System.out.println("Login realizado com sucesso! Bem-vindo, " + usuario.getNome());
                return true;
            }
        }
        System.out.println("Login ou senha incorretos.");
        return false;
    }
}