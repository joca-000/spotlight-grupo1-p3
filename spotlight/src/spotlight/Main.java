package spotlight;

public class Main {
	public static void main(String[] args) {
        Autenticacao authService = new Autenticacao();
        
        // Adicionar um usuário admin inicial (opcional)
        Usuario admin = new Usuario("admin_1", "Administrador", "admin@spotlight.com", "admin123", "admin");
        authService.cadastroUsuario(admin);
        
        Login loginConsole = new Login(authService);
        loginConsole.exibirMenu();
    }
}
