import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGrafica {
    private Sistema sistema;
    private JFrame frame;
    private JTextField campoNome;
    private JTextField campoLogin;
    private JPasswordField campoSenha;

    public InterfaceGrafica() {
        sistema = new Sistema();
        criarInterface();
    }

    private void criarInterface() {
        frame = new JFrame("Sistema de Cadastro e Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        // Componentes da interface
        JLabel labelNome = new JLabel("Nome:");
        campoNome = new JTextField();
        JLabel labelLogin = new JLabel("Login:");
        campoLogin = new JTextField();
        JLabel labelSenha = new JLabel("Senha:");
        campoSenha = new JPasswordField();

        JButton botaoCadastrar = new JButton("Cadastrar");
        JButton botaoLogin = new JButton("Login");

        // Adicionando componentes ao frame
        frame.add(labelNome);
        frame.add(campoNome);
        frame.add(labelLogin);
        frame.add(campoLogin);
        frame.add(labelSenha);
        frame.add(campoSenha);
        frame.add(new JLabel("")); // Espaço vazio
        frame.add(botaoCadastrar);
        frame.add(new JLabel("")); // Espaço vazio
        frame.add(botaoLogin);

        // Ações dos botões
        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = campoNome.getText();
                String login = campoLogin.getText();
                String senha = new String(campoSenha.getPassword());
                if (!nome.isEmpty() && !login.isEmpty() && !senha.isEmpty()) {
                    sistema.cadastrarUsuario(nome, login, senha);
                    JOptionPane.showMessageDialog(frame, "Usuário cadastrado com sucesso!");
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(frame, "Preencha todos os campos!");
                }
            }
        });

        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = campoLogin.getText();
                String senha = new String(campoSenha.getPassword());
                if (sistema.fazerLogin(login, senha)) {
                    JOptionPane.showMessageDialog(frame, "Login realizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Login ou senha incorretos!");
                }
                limparCampos();
            }
        });

        frame.setVisible(true);
    }

    private void limparCampos() {
        campoNome.setText("");
        campoLogin.setText("");
        campoSenha.setText("");
    }

    public static void main(String[] args) {
        new InterfaceGrafica();
    }
}