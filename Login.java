import java.util.ArrayList;
import java.util.List;

public class Login {
    private List<Usuario> usuarios;
    private Usuario usuarioAutenticado;

    public Login() {
        this.usuarios = new ArrayList<>();
        this.usuarioAutenticado = null;
    }

    public void adicionarUsuario(Usuario usuario) {
        if (usuario != null) {
            usuarios.add(usuario);
        }
    }


    public boolean autenticar(String nome, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome) && usuario.verificarSenha(senha)) {
                this.usuarioAutenticado = usuario;
                return true;
            }
        }
        this.usuarioAutenticado = null;
        return false;
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void logout() {
        this.usuarioAutenticado = null;
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }
}