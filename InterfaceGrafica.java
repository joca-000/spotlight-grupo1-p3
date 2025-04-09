import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InterfaceGrafica {
    private Sistema sistema;
    private JFrame frame;
    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private String usuarioLogado;

    public InterfaceGrafica() {
        sistema = new Sistema();
        criarTelaLogin();
    }

    private void criarTelaLogin() {
        frame = new JFrame("SPOTLIGHT - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().setBackground(new Color(236, 239, 241));

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(new Color(236, 239, 241));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("SPOTLIGHT - Autenticação", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(new Color(38, 50, 56));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        painel.add(titulo, gbc);

        JLabel labelLogin = new JLabel("Login:");
        labelLogin.setFont(new Font("Arial", Font.PLAIN, 14));
        labelLogin.setForeground(new Color(38, 50, 56));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        painel.add(labelLogin, gbc);

        campoLogin = new JTextField(15);
        campoLogin.setFont(new Font("Arial", Font.PLAIN, 14));
        campoLogin.setBorder(BorderFactory.createLineBorder(new Color(189, 189, 189)));
        gbc.gridx = 1;
        gbc.gridy = 1;
        painel.add(campoLogin, gbc);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setFont(new Font("Arial", Font.PLAIN, 14));
        labelSenha.setForeground(new Color(38, 50, 56));
        gbc.gridx = 0;
        gbc.gridy = 2;
        painel.add(labelSenha, gbc);

        campoSenha = new JPasswordField(15);
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 14));
        campoSenha.setBorder(BorderFactory.createLineBorder(new Color(189, 189, 189)));
        gbc.gridx = 1;
        gbc.gridy = 2;
        painel.add(campoSenha, gbc);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        painelBotoes.setBackground(new Color(236, 239, 241));

        JButton botaoCadastrar = criarBotao("Cadastrar", new Color(46, 125, 50));
        JButton botaoLogin = criarBotao("Login", new Color(25, 118, 210));

        painelBotoes.add(botaoCadastrar);
        painelBotoes.add(botaoLogin);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        painel.add(painelBotoes, gbc);

        frame.add(painel, BorderLayout.CENTER);

        botaoCadastrar.addActionListener(e -> cadastrarUsuario());
        botaoLogin.addActionListener(e -> fazerLogin());

        frame.setVisible(true);
    }

    private JButton criarBotao(String texto, Color cor) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 12));
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setPreferredSize(new Dimension(100, 35));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        botao.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                botao.setBackground(cor.brighter());
            }
            public void mouseExited(MouseEvent evt) {
                botao.setBackground(cor);
            }
        });
        return botao;
    }

    private void limparCampos() {
        campoLogin.setText("");
        campoSenha.setText("");
    }

    private void cadastrarUsuario() {
        String login = campoLogin.getText().trim();
        String senha = new String(campoSenha.getPassword()).trim();
        if (login.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (sistema.cadastrarUsuario(login, senha)) {
            JOptionPane.showMessageDialog(frame, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(frame, "Este login já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fazerLogin() {
        String login = campoLogin.getText().trim();
        String senha = new String(campoSenha.getPassword()).trim();
        if (login.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (sistema.fazerLogin(login, senha)) {
            usuarioLogado = login;
            frame.dispose();
            abrirTelaOpcoes();
        } else {
            JOptionPane.showMessageDialog(frame, "Login ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirTelaOpcoes() {
        JFrame telaOpcoes = new JFrame("SPOTLIGHT - Menu");
        telaOpcoes.setSize(400, 300);
        telaOpcoes.setLocationRelativeTo(null);
        telaOpcoes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaOpcoes.setLayout(new BorderLayout(10, 10));
        telaOpcoes.getContentPane().setBackground(new Color(236, 239, 241));

        JPanel painelSuperior = criarPainelSuperior();

        JPanel painelCentral = new JPanel(new GridBagLayout());
        painelCentral.setBackground(new Color(236, 239, 241));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton botaoTeatros = criarBotao("Teatros", new Color(25, 118, 210));
        JButton botaoAgenda = criarBotao("Agenda", new Color(46, 125, 50));
        JButton botaoAdm = criarBotao("ADM", new Color(237, 108, 2));
        JButton botaoSair = criarBotao("Sair", new Color(211, 47, 47));

        gbc.gridx = 0;
        gbc.gridy = 0;
        painelCentral.add(botaoTeatros, gbc);
        gbc.gridy = 1;
        painelCentral.add(botaoAgenda, gbc);
        gbc.gridy = 2;
        painelCentral.add(botaoAdm, gbc);
        gbc.gridy = 3;
        painelCentral.add(botaoSair, gbc);

        telaOpcoes.add(painelSuperior, BorderLayout.NORTH);
        telaOpcoes.add(painelCentral, BorderLayout.CENTER);

        botaoTeatros.addActionListener(e -> { telaOpcoes.dispose(); abrirTelaTeatros(); });
        botaoAgenda.addActionListener(e -> { telaOpcoes.dispose(); abrirTelaAgenda(); });
        botaoAdm.addActionListener(e -> { telaOpcoes.dispose(); abrirTelaAdm(); });
        botaoSair.addActionListener(e -> System.exit(0));

        telaOpcoes.setVisible(true);
    }

    private JPanel criarPainelSuperior() {
        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.setBackground(new Color(236, 239, 241));
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel labelUsuario = new JLabel("Usuário: " + usuarioLogado);
        labelUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        labelUsuario.setForeground(new Color(38, 50, 56));
        painelSuperior.add(labelUsuario, BorderLayout.WEST);

        JLabel labelProjeto = new JLabel("SPOTLIGHT");
        labelProjeto.setFont(new Font("Arial", Font.BOLD, 16));
        labelProjeto.setForeground(new Color(38, 50, 56));
        painelSuperior.add(labelProjeto, BorderLayout.EAST);

        return painelSuperior;
    }

    private void abrirTelaTeatros() {
        JFrame telaTeatros = new JFrame("SPOTLIGHT - Teatros");
        telaTeatros.setSize(600, 400);
        telaTeatros.setLocationRelativeTo(null);
        telaTeatros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaTeatros.setLayout(new BorderLayout(10, 10));
        telaTeatros.getContentPane().setBackground(new Color(236, 239, 241));

        JPanel painelSuperior = criarPainelSuperior();

        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(new Color(236, 239, 241));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Teatros", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(new Color(38, 50, 56));
        painelCentral.add(titulo, BorderLayout.NORTH);

        JList<Teatro> listaTeatros = new JList<>(new DefaultListModel<>());
        DefaultListModel<Teatro> model = (DefaultListModel<Teatro>) listaTeatros.getModel();
        for (Teatro t : sistema.getTeatros()) {
            model.addElement(t);
        }
        listaTeatros.setFont(new Font("Arial", Font.PLAIN, 12));
        listaTeatros.setBackground(Color.WHITE);
        listaTeatros.setBorder(BorderFactory.createLineBorder(new Color(189, 189, 189)));
        painelCentral.add(new JScrollPane(listaTeatros), BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        painelBotoes.setBackground(new Color(236, 239, 241));

        JButton botaoCadastrar = criarBotao("Cadastrar Teatro", new Color(46, 125, 50));
        JButton botaoEditar = criarBotao("Editar Informações", new Color(237, 108, 2));
        JButton botaoDeletar = criarBotao("Deletar Teatro", new Color(211, 47, 47));
        JButton botaoVoltar = criarBotao("Voltar", new Color(25, 118, 210));

        painelBotoes.add(botaoCadastrar);
        painelBotoes.add(botaoEditar);
        painelBotoes.add(botaoDeletar);
        painelBotoes.add(botaoVoltar);

        telaTeatros.add(painelSuperior, BorderLayout.NORTH);
        telaTeatros.add(painelCentral, BorderLayout.CENTER);
        telaTeatros.add(painelBotoes, BorderLayout.SOUTH);

        botaoCadastrar.addActionListener(e -> cadastrarTeatro(model, telaTeatros));
        botaoEditar.addActionListener(e -> editarTeatro(listaTeatros, model, telaTeatros));
        botaoDeletar.addActionListener(e -> deletarTeatro(listaTeatros, model, telaTeatros));
        botaoVoltar.addActionListener(e -> { telaTeatros.dispose(); abrirTelaOpcoes(); });

        telaTeatros.setVisible(true);
    }

    private void cadastrarTeatro(DefaultListModel<Teatro> model, JFrame tela) {
        JTextField campoNome = new JTextField(15);
        JTextField campoLocalizacao = new JTextField(15);
        JTextField campoCapacidade = new JTextField(5);
        JTextArea campoDescricao = new JTextArea(5, 20);

        JPanel painelCadastro = new JPanel(new GridLayout(4, 2, 5, 5));
        painelCadastro.add(new JLabel("Nome:"));
        painelCadastro.add(campoNome);
        painelCadastro.add(new JLabel("Localização:"));
        painelCadastro.add(campoLocalizacao);
        painelCadastro.add(new JLabel("Capacidade:"));
        painelCadastro.add(campoCapacidade);
        painelCadastro.add(new JLabel("Descrição:"));
        painelCadastro.add(new JScrollPane(campoDescricao));

        int result = JOptionPane.showConfirmDialog(tela, painelCadastro, "Cadastrar Teatro", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int capacidade = Integer.parseInt(campoCapacidade.getText().trim());
                sistema.cadastrarTeatro(campoNome.getText().trim(), campoLocalizacao.getText().trim(), capacidade, campoDescricao.getText().trim());
                model.addElement(new Teatro(campoNome.getText().trim(), campoLocalizacao.getText().trim(), capacidade, campoDescricao.getText().trim()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(tela, "Capacidade deve ser um número!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editarTeatro(JList<Teatro> lista, DefaultListModel<Teatro> model, JFrame tela) {
        Teatro selecionado = lista.getSelectedValue();
        if (selecionado != null) {
            JTextField campoNome = new JTextField(selecionado.getNome(), 15);
            JTextField campoLocalizacao = new JTextField(selecionado.getLocalizacao(), 15);
            JTextField campoCapacidade = new JTextField(String.valueOf(selecionado.getCapacidade()), 5);
            JTextArea campoDescricao = new JTextArea(selecionado.getDescricao(), 5, 20);

            JPanel painelEdicao = new JPanel(new GridLayout(4, 2, 5, 5));
            painelEdicao.add(new JLabel("Nome:"));
            painelEdicao.add(campoNome);
            painelEdicao.add(new JLabel("Localização:"));
            painelEdicao.add(campoLocalizacao);
            painelEdicao.add(new JLabel("Capacidade:"));
            painelEdicao.add(campoCapacidade);
            painelEdicao.add(new JLabel("Descrição:"));
            painelEdicao.add(new JScrollPane(campoDescricao));

            int result = JOptionPane.showConfirmDialog(tela, painelEdicao, "Editar Teatro", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    int capacidade = Integer.parseInt(campoCapacidade.getText().trim());
                    sistema.deletarTeatro(selecionado);
                    sistema.cadastrarTeatro(campoNome.getText().trim(), campoLocalizacao.getText().trim(), capacidade, campoDescricao.getText().trim());
                    model.removeElement(selecionado);
                    model.addElement(new Teatro(campoNome.getText().trim(), campoLocalizacao.getText().trim(), capacidade, campoDescricao.getText().trim()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(tela, "Capacidade deve ser um número!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(tela, "Selecione um teatro para editar!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarTeatro(JList<Teatro> lista, DefaultListModel<Teatro> model, JFrame tela) {
        Teatro selecionado = lista.getSelectedValue();
        if (selecionado != null) {
            sistema.deletarTeatro(selecionado);
            model.removeElement(selecionado);
        } else {
            JOptionPane.showMessageDialog(tela, "Selecione um teatro para deletar!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirTelaAgenda() {
        JFrame telaAgenda = new JFrame("SPOTLIGHT - Agenda");
        telaAgenda.setSize(500, 400);
        telaAgenda.setLocationRelativeTo(null);
        telaAgenda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaAgenda.setLayout(new BorderLayout(10, 10));
        telaAgenda.getContentPane().setBackground(new Color(236, 239, 241));

        JPanel painelSuperior = criarPainelSuperior();

        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(new Color(236, 239, 241));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Agenda", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(new Color(38, 50, 56));
        painelCentral.add(titulo, BorderLayout.NORTH);

        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setText(
                "<html>" +
                        "<body style='font-family: Arial; color: #263238; background-color: #FFFFFF; padding: 10px;'>" +
                        "<h2>Suas Atividades</h2>" +
                        "<p>Nenhuma atividade registrada no momento.</p>" +
                        "</body>" +
                        "</html>"
        );
        editorPane.setEditable(false);
        editorPane.setBackground(Color.WHITE);
        editorPane.setBorder(BorderFactory.createLineBorder(new Color(189, 189, 189)));
        painelCentral.add(new JScrollPane(editorPane), BorderLayout.CENTER);

        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.setBackground(new Color(236, 239, 241));
        JButton botaoVoltar = criarBotao("Voltar", new Color(25, 118, 210));
        painelBotao.add(botaoVoltar);

        telaAgenda.add(painelSuperior, BorderLayout.NORTH);
        telaAgenda.add(painelCentral, BorderLayout.CENTER);
        telaAgenda.add(painelBotao, BorderLayout.SOUTH);

        botaoVoltar.addActionListener(e -> { telaAgenda.dispose(); abrirTelaOpcoes(); });

        telaAgenda.setVisible(true);
    }

    private void abrirTelaAdm() {
        JFrame telaAdm = new JFrame("SPOTLIGHT - Administração");
        telaAdm.setSize(600, 400);
        telaAdm.setLocationRelativeTo(null);
        telaAdm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaAdm.setLayout(new BorderLayout(10, 10));
        telaAdm.getContentPane().setBackground(new Color(236, 239, 241));

        JPanel painelSuperior = criarPainelSuperior();

        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(new Color(236, 239, 241));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Administração", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(new Color(38, 50, 56));
        painelCentral.add(titulo, BorderLayout.NORTH);

        JList<Funcionario> listaFuncionarios = new JList<>(new DefaultListModel<>());
        DefaultListModel<Funcionario> model = (DefaultListModel<Funcionario>) listaFuncionarios.getModel();
        for (Funcionario f : sistema.getFuncionarios()) {
            model.addElement(f);
        }
        listaFuncionarios.setFont(new Font("Arial", Font.PLAIN, 12));
        listaFuncionarios.setBackground(Color.WHITE);
        listaFuncionarios.setBorder(BorderFactory.createLineBorder(new Color(189, 189, 189)));
        painelCentral.add(new JScrollPane(listaFuncionarios), BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        painelBotoes.setBackground(new Color(236, 239, 241));

        JButton botaoCadastrar = criarBotao("Cadastrar Funcionário", new Color(46, 125, 50));
        JButton botaoVoltar = criarBotao("Voltar", new Color(25, 118, 210));

        painelBotoes.add(botaoCadastrar);
        painelBotoes.add(botaoVoltar);

        telaAdm.add(painelSuperior, BorderLayout.NORTH);
        telaAdm.add(painelCentral, BorderLayout.CENTER);
        telaAdm.add(painelBotoes, BorderLayout.SOUTH);

        botaoCadastrar.addActionListener(e -> cadastrarFuncionario(model, telaAdm));
        botaoVoltar.addActionListener(e -> { telaAdm.dispose(); abrirTelaOpcoes(); });

        telaAdm.setVisible(true);
    }

    private void cadastrarFuncionario(DefaultListModel<Funcionario> model, JFrame tela) {
        JTextField campoNome = new JTextField(15);
        JTextField campoEndereco = new JTextField(15);
        JTextField campoDataNascimento = new JTextField(10);
        JTextField campoRg = new JTextField(10);
        JTextField campoCpf = new JTextField(11);

        JPanel painelCadastro = new JPanel(new GridLayout(5, 2, 5, 5));
        painelCadastro.add(new JLabel("Nome:"));
        painelCadastro.add(campoNome);
        painelCadastro.add(new JLabel("Endereço:"));
        painelCadastro.add(campoEndereco);
        painelCadastro.add(new JLabel("Data de Nascimento (dd/mm/aaaa):"));
        painelCadastro.add(campoDataNascimento);
        painelCadastro.add(new JLabel("RG:"));
        painelCadastro.add(campoRg);
        painelCadastro.add(new JLabel("CPF:"));
        painelCadastro.add(campoCpf);

        int result = JOptionPane.showConfirmDialog(tela, painelCadastro, "Cadastrar Funcionário", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            sistema.cadastrarFuncionario(campoNome.getText().trim(), campoEndereco.getText().trim(), campoDataNascimento.getText().trim(), campoRg.getText().trim(), campoCpf.getText().trim());
            model.addElement(new Funcionario(campoNome.getText().trim(), campoEndereco.getText().trim(), campoDataNascimento.getText().trim(), campoRg.getText().trim(), campoCpf.getText().trim()));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfaceGrafica());
    }
}