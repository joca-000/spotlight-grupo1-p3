import java.util.Scanner;

public class Main {
    private Login loginSystem;
    private Cadastro cadastroSystem;
    private Sistema sistema;
    private Scanner scanner;

    public Main() {
        this.loginSystem = new Login();
        this.cadastroSystem = new Cadastro(loginSystem);
        this.sistema = new Sistema(this); // Passa a referência de Main
        this.scanner = new Scanner(System.in);
        sistema.adicionarTarefa("Montar cenário", "João Silva");
        sistema.adicionarTarefa("Ensaio de cena", "Maria Oliveira");
    }


    public static void limparTela() {
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
    }

    private void exibirMenuPrincipal() {
        limparTela();
        System.out.println("\n=== Sistema de Gestão de Teatro ===");
        System.out.println("1. Logar");
        System.out.println("2. Cadastrar");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void exibirLogin() {
        limparTela();
        System.out.println("\n=== Tela de Login ===");
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        if (loginSystem.autenticar(nome, senha)) {
            System.out.println("Login bem-sucedido! Bem-vindo, " + loginSystem.getUsuarioAutenticado().getNome());
        } else {
            System.out.println("Falha na autenticação. Verifique nome e senha.");
        }
    }

    private void exibirCadastro() {
        limparTela();
        System.out.println("\n=== Tela de Cadastro ===");
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();
        System.out.print("Digite o tipo (alta_administracao/backstage/criativo/adm_geral/adm_criativo): ");
        String tipo = scanner.nextLine();

        if (cadastroSystem.cadastrarUsuario(nome, email, senha, tipo)) {
            System.out.println("Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Falha no cadastro. Verifique os dados e tente novamente.");
        }
    }

    private void exibirMenuLogado() {
        boolean logado = true;
        while (logado) {
            limparTela();
            Usuario usuario = loginSystem.getUsuarioAutenticado();
            String tipoUsuario = usuario.getTipo();

            System.out.println("\n=== Menu do Usuário ===");
            System.out.println("ID: " + usuario.getId());


            System.out.println("1. Ver Perfil");


            if (tipoUsuario.equals("alta_administracao")) {
                System.out.println("2. Adicionar Teatro");
                System.out.println("3. Listar Teatros");
                System.out.println("4. Gerenciar Teatro");
                System.out.println("5. Deletar Teatro");
                System.out.println("6. Logout");
            } else if (tipoUsuario.equals("backstage") || tipoUsuario.equals("criativo")) {
                System.out.println("2. Concluir Tarefa");
                System.out.println("3. Logout");
            } else if (tipoUsuario.equals("adm_geral") || tipoUsuario.equals("adm_criativo")) {
                System.out.println("2. Terminar Tarefa");
                System.out.println("3. Logout");
            } else {
                System.out.println("2. Logout");
            }

            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (tipoUsuario) {
                case "alta_administracao":
                    switch (opcao) {
                        case "1":
                            exibirPerfil();
                            break;
                        case "2":
                            sistema.addTeatro(tipoUsuario);
                            break;
                        case "3":
                            sistema.listarTeatro(tipoUsuario);
                            break;
                        case "4":
                            sistema.gerenciarTeatro(tipoUsuario);
                            break;
                        case "5":
                            sistema.delTeatro(tipoUsuario);
                            break;
                        case "6":
                            loginSystem.logout();
                            System.out.println("Logout realizado com sucesso.");
                            logado = false;
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                    break;

                case "backstage":
                case "criativo":
                    switch (opcao) {
                        case "1":
                            exibirPerfil();
                            break;
                        case "2":
                            sistema.concluirTask(tipoUsuario, usuario.getNome());
                            break;
                        case "3":
                            loginSystem.logout();
                            System.out.println("Logout realizado com sucesso.");
                            logado = false;
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                    break;

                case "adm_geral":
                case "adm_criativo":
                    switch (opcao) {
                        case "1":
                            exibirPerfil();
                            break;
                        case "2":
                            sistema.terminarTask(tipoUsuario);
                            break;
                        case "3":
                            loginSystem.logout();
                            System.out.println("Logout realizado com sucesso.");
                            logado = false;
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                    break;

                default:
                    switch (opcao) {
                        case "1":
                            exibirPerfil();
                            break;
                        case "2":
                            loginSystem.logout();
                            System.out.println("Logout realizado com sucesso.");
                            logado = false;
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
            }
        }
    }

    private void exibirPerfil() {
        limparTela();
        Usuario usuario = loginSystem.getUsuarioAutenticado();
        System.out.println("\n=== Perfil do Usuário ===");
        System.out.println("ID: " + usuario.getId());
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Email: " + usuario.getEmail());
        System.out.println("Tipo: " + usuario.getTipo());
        System.out.println("\nPressione Enter para voltar...");
        scanner.nextLine();
    }

    public void iniciarSistema() {
        boolean rodando = true;
        while (rodando) {
            exibirMenuPrincipal();
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    exibirLogin();
                    if (loginSystem.getUsuarioAutenticado() != null) {
                        exibirMenuLogado();
                    }
                    break;
                case "2":
                    exibirCadastro();
                    break;
                case "3":
                    limparTela();
                    System.out.println("Saindo do sistema. Até logo!");
                    rodando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Main sistema = new Main();
        sistema.iniciarSistema();
    }
}