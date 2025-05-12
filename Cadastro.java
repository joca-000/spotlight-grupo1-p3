public class Cadastro {
    private Login loginSystem;

    public Cadastro(Login loginSystem) {
        this.loginSystem = loginSystem;
    }

    public boolean cadastrarUsuario(String nome, String email, String senha, String tipo) {
        if (loginSystem.buscarUsuarioPorNome(nome) != null) {
            return false; // Usuário já existe
        }

        if (!tipo.equalsIgnoreCase("alta_administracao") &&
                !tipo.equalsIgnoreCase("backstage") &&
                !tipo.equalsIgnoreCase("ator") &&
                !tipo.equalsIgnoreCase("adm_geral") &&
                !tipo.equalsIgnoreCase("adm_criativo")) {
            return false; // Tipo inválido
        }

        Usuario usuario = new Usuario(nome, email, senha, tipo);
        loginSystem.adicionarUsuario(usuario);
        return true;
    }
}