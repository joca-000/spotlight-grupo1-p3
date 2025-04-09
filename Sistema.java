import java.util.ArrayList;

public class Sistema {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Teatro> teatros;
    private ArrayList<Funcionario> funcionarios;

    public Sistema() {
        usuarios = new ArrayList<>();
        teatros = new ArrayList<>();
        funcionarios = new ArrayList<>();
    }

    // Usuários
    public boolean cadastrarUsuario(String login, String senha) {
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login)) {
                return false;
            }
        }
        usuarios.add(new Usuario(login, senha));
        return true;
    }

    public boolean fazerLogin(String login, String senha) {
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    // Teatros
    public void cadastrarTeatro(String nome, String localizacao, int capacidade, String descricao) {
        teatros.add(new Teatro(nome, localizacao, capacidade, descricao));
    }

    public ArrayList<Teatro> getTeatros() {
        return teatros;
    }

    public void deletarTeatro(Teatro teatro) {
        teatros.remove(teatro);
    }

    // Funcionários
    public void cadastrarFuncionario(String nome, String endereco, String dataNascimento, String rg, String cpf) {
        funcionarios.add(new Funcionario(nome, endereco, dataNascimento, rg, cpf));
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }
}