package spotlight;
import java.util.Scanner;

public class Login {
    private Autenticacao authService;
    private Scanner scanner;
    
    public Login(Autenticacao authService) {
        this.authService = authService;
        this.scanner = new Scanner(System.in);
    }
    
    public void exibirMenu() {
        while (true) {
            System.out.println("\n=== SPOTLIGHT - Sistema de Gestão de Teatros ===");
            System.out.println("1. Login");
            System.out.println("2. Cadastro");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            switch (opcao) {
                case 1:
                    fazerLogin();
                    break;
                case 2:
                    fazerCadastro();
                    break;
                case 3:
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private void fazerLogin() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        Usuario usuario = authService.fazerLogin(email, senha);
        
        if (usuario != null) {
            System.out.println("\nLogin bem-sucedido! Bem-vindo, " + usuario.getNome());
            // Aqui você chamaria o menu principal do sistema
        } else {
            System.out.println("\nEmail ou senha incorretos!");
        }
    }
    
    private void fazerCadastro() {
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        // Aqui você pode adicionar lógica para determinar o tipo de usuário
        String tipo = "funcionario"; // Exemplo - pode ser ajustado
        
        // Gerar ID (em um sistema real, seria mais sofisticado)
        String id = "user_" + System.currentTimeMillis();
        
        Usuario novoUsuario = new Usuario(id, nome, email, senha, tipo);
        
        if (authService.cadastroUsuario(novoUsuario)) {
            System.out.println("\nCadastro realizado com sucesso!");
        } else {
            System.out.println("\nErro: Email já cadastrado!");
        }
    }
}
