package spotlight;
import java.util.HashMap;
import java.util.Map;

public class Autenticacao {
	private Map<String, Usuario> usuarios = new HashMap<>();
	
	//cadastro de usuario
	public boolean cadastroUsuario(Usuario novoUsuario) {
		if(usuarios.containsKey(novoUsuario.getEmail())) {
			return false; //usuario ja cadastrado
		}
		usuarios.put(novoUsuario.getEmail(), novoUsuario);
        return true;
	}
	//fazer login
	public Usuario fazerLogin(String email, String senha) {
		Usuario usuario = usuarios.get(email);
		if(usuario != null && usuario.verificarSenha(senha)) {
			return usuario;
		}
		return null;//login falhou 
	}
}
