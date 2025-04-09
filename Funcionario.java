public class Funcionario {
    private String nome;
    private String endereco;
    private String dataNascimento;
    private String rg;
    private String cpf;

    public Funcionario(String nome, String endereco, String dataNascimento, String rg, String cpf) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.rg = rg;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome + " (CPF: " + cpf + ")";
    }
}