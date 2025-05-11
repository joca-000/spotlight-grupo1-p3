import java.util.UUID;

public class Cadastro {
    private Login loginSystem;

    public Cadastro(Login loginSystem) {
        this.loginSystem = loginSystem;
    }

    private String gerarId() {
        return UUID.randomUUID().toString();
    }

    public boolean cadastrarUsuario(String nome, String email, String senha, String tipo) {
        if (!validarDados(nome, email, senha, tipo)) {
            return false;
        }

        String id = gerarId();
        Usuario novoUsuario = new Usuario(nome, email, senha, tipo);
        novoUsuario.setId(id);

        loginSystem.adicionarUsuario(novoUsuario);
        System.out.println("Seu ID gerado é: " + id);
        return true;
    }

    private boolean validarDados(String nome, String email, String senha, String tipo) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Erro: Nome não pode ser vazio.");
            return false;
        }
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Erro: Email não pode ser vazio.");
            return false;
        }
        if (senha == null || senha.trim().isEmpty()) {
            System.out.println("Erro: Senha não pode ser vazia.");
            return false;
        }
        if (tipo == null || tipo.trim().isEmpty()) {
            System.out.println("Erro: Tipo não pode ser vazio.");
            return false;
        }


        if (!tipo.equalsIgnoreCase("alta_administracao") &&
                !tipo.equalsIgnoreCase("backstage") &&
                !tipo.equalsIgnoreCase("criativo") &&
                !tipo.equalsIgnoreCase("adm_geral") &&
                !tipo.equalsIgnoreCase("adm_criativo")) {
            System.out.println("Erro: Tipo deve ser 'alta_administracao', 'backstage', 'criativo', 'adm_geral' ou 'adm_criativo'.");
            return false;
        }

        if (nomeJaExiste(nome)) {
            System.out.println("Erro: Nome de usuário já está em uso.");
            return false;
        }

        if (!email.contains("@") || !email.contains(".")) {
            System.out.println("Erro: Email inválido.");
            return false;
        }

        return true;
    }

    private boolean nomeJaExiste(String nome) {
        for (Usuario usuario : loginSystem.getUsuarios()) {
            if (usuario.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }
}