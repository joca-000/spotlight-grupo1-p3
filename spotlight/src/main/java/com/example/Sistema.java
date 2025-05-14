package main.java.com.example;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Sistema implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Teatro> teatros;
    private List<Tarefa> tarefas;
    private List<Atividade> atividades;
    private List<Atividade> atividadesFinalizadas;
    private Scanner scanner;
    private Main main;
    private Login loginSystem;

    public Sistema(Main main) {
        this.teatros = new ArrayList<>();
        this.tarefas = new ArrayList<>();
        this.atividades = new ArrayList<>();
        this.atividadesFinalizadas = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.main = main;
        this.loginSystem = main.getLoginSystem();
        carregarDados(); // Carregar dados ao iniciar
    }

    public Login getLoginSystem() {
        return loginSystem;
    }

    public void addTeatro(String tipoUsuario) {
        if (!tipoUsuario.equals("alta_administracao")) {
            Main.limparTela();
            System.out.println("Acesso negado: Apenas funcionários da alta administração podem adicionar teatros.");
            pausar();
            return;
        }
        Main.limparTela();
        System.out.print("Digite o nome do teatro: ");
        String nome = lerStringValida();
        System.out.print("Digite a localização: ");
        String localizacao = lerStringValida();
        int capacidade = lerInteiroValido("Digite a capacidade: ");

        Teatro teatro = new Teatro(nome, localizacao, capacidade);
        teatros.add(teatro);
        System.out.println("Teatro adicionado com sucesso!");
        pausar();
    }

    public void listarTeatro(String tipoUsuario) {
        if (!tipoUsuario.equals("alta_administracao")) {
            Main.limparTela();
            System.out.println("Acesso negado: Apenas funcionários da alta administração podem listar teatros.");
            pausar();
            return;
        }
        Main.limparTela();
        if (teatros.isEmpty()) {
            System.out.println("Nenhum teatro cadastrado.");
        } else {
            System.out.println("\nLista de Teatros:");
            for (Teatro teatro : teatros) {
                System.out.println(teatro);
                if (!teatro.getAtividades().isEmpty()) {
                    System.out.println("Atividades associadas:");
                    if (tipoUsuario.equals("alta_administracao")) {
                        for (Atividade atividade : teatro.getAtividades()) {
                            System.out.println("  - Atividade: " + atividade.getNome() + ", Data: " + atividade.getData() + ", Tipo Responsável: " + atividade.getTipoResponsavel());
                        }
                    } else {
                        teatro.listarAtividades();
                    }
                } else {
                    System.out.println("Nenhuma atividade associada a este teatro.");
                }
                System.out.println();
            }
        }
        pausar();
    }

    public void gerenciarTeatro(String tipoUsuario) {
        if (!tipoUsuario.equals("alta_administracao")) {
            Main.limparTela();
            System.out.println("Acesso negado: Apenas funcionários da alta administração podem gerenciar teatros.");
            pausar();
            return;
        }
        Main.limparTela();
        listarTeatro(tipoUsuario);
        if (teatros.isEmpty()) return;

        System.out.print("Digite o nome do teatro a gerenciar: ");
        String nome = lerStringValida();
        Teatro teatro = teatros.stream()
                .filter(t -> t.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);

        if (teatro == null) {
            System.out.println("Teatro não encontrado.");
            pausar();
            return;
        }

        System.out.print("Novo nome (deixe em branco para manter): ");
        String novoNome = lerStringValida();
        if (!novoNome.trim().isEmpty()) teatro.setNome(novoNome);

        System.out.print("Nova localização (deixe em branco para manter): ");
        String novaLocalizacao = lerStringValida();
        if (!novaLocalizacao.trim().isEmpty()) teatro.setLocalizacao(novaLocalizacao);

        System.out.print("Nova capacidade (0 para manter): ");
        int novaCapacidade = lerInteiroValido("Nova capacidade: ");
        if (novaCapacidade > 0) teatro.setCapacidade(novaCapacidade);

        System.out.println("Teatro atualizado com sucesso!");
        pausar();
    }

    public void delTeatro(String tipoUsuario) {
        if (!tipoUsuario.equals("alta_administracao")) {
            Main.limparTela();
            System.out.println("Acesso negado: Apenas funcionários da alta administração podem deletar teatros.");
            pausar();
            return;
        }
        Main.limparTela();
        listarTeatro(tipoUsuario);
        if (teatros.isEmpty()) return;

        System.out.print("Digite o nome do teatro a deletar: ");
        String nome = lerStringValida();
        boolean removido = teatros.removeIf(t -> t.getNome().equalsIgnoreCase(nome));
        if (removido) {
            System.out.println("Teatro removido com sucesso!");
        } else {
            System.out.println("Teatro não encontrado.");
        }
        pausar();
    }

    public void criarAtividade(String tipoUsuario, String nomeUsuario) {
        Main.limparTela();
        if (teatros.isEmpty()) {
            System.out.println("Nenhum teatro cadastrado para associar a atividade.");
            pausar();
            return;
        }

        if (!tipoUsuario.equals("adm_geral") && !tipoUsuario.equals("adm_criativo")) {
            System.out.println("Acesso negado: Apenas adm geral ou adm criativo podem criar atividades.");
            pausar();
            return;
        }

        System.out.println("Teatros disponíveis:");
        for (int i = 0; i < teatros.size(); i++) {
            System.out.println((i + 1) + ". " + teatros.get(i).getNome());
        }
        System.out.print("Escolha o número do teatro (1-" + teatros.size() + "): ");
        int indiceTeatro = lerInteiroValido("Escolha o número do teatro: ") - 1;

        if (indiceTeatro < 0 || indiceTeatro >= teatros.size()) {
            System.out.println("Teatro inválido.");
            pausar();
            return;
        }

        Teatro teatroSelecionado = teatros.get(indiceTeatro);
        String tipoResponsavel;
        if (tipoUsuario.equals("adm_geral")) {
            tipoResponsavel = "backstage";
        } else { // adm_criativo
            tipoResponsavel = "ator";
        }

        System.out.print("Digite o nome do usuário responsável (" + tipoResponsavel + "): ");
        String responsavel = lerStringValida();

        // Validação: Verificar se o usuário existe e é do tipo correto
        Usuario usuario = loginSystem.buscarUsuarioPorNome(responsavel);
        if (usuario == null) {
            System.out.println("Erro: Usuário '" + responsavel + "' não encontrado.");
            pausar();
            return;
        }
        if (!usuario.getTipo().equalsIgnoreCase(tipoResponsavel)) {
            System.out.println("Erro: O usuário '" + responsavel + "' não é do tipo '" + tipoResponsavel + "'.");
            pausar();
            return;
        }

        System.out.print("Digite o nome da atividade: ");
        String nomeAtividade = lerStringValida();
        System.out.print("Digite a data (ex.: DD/MM/AAAA): ");
        String data = lerStringValida();

        Atividade atividade = new Atividade(nomeAtividade, data, tipoResponsavel, responsavel, nomeUsuario);
        teatroSelecionado.getAtividades().add(atividade);
        atividades.add(atividade);
        System.out.println("Atividade criada com sucesso!");
        pausar();
    }

    public void enviarAtividadeAltaAdministracao(String nomeUsuario) {
        Main.limparTela();
        if (!loginSystem.getUsuarioAutenticado().getTipo().equals("alta_administracao")) {
            System.out.println("Acesso negado: Apenas alta administração pode enviar tarefas.");
            pausar();
            return;
        }

        System.out.print("Digite o nome do usuário destinatário (adm_geral ou adm_criativo): ");
        String destinatario = lerStringValida();

        Usuario usuarioDestinatario = loginSystem.buscarUsuarioPorNome(destinatario);
        if (usuarioDestinatario == null) {
            System.out.println("Erro: Usuário '" + destinatario + "' não encontrado.");
            pausar();
            return;
        }
        if (!usuarioDestinatario.getTipo().equalsIgnoreCase("adm_geral") && !usuarioDestinatario.getTipo().equalsIgnoreCase("adm_criativo")) {
            System.out.println("Erro: O usuário '" + destinatario + "' não é adm_geral nem adm_criativo.");
            pausar();
            return;
        }

        System.out.print("Digite a descrição da tarefa: ");
        String descricaoTarefa = lerStringValida();

        Tarefa tarefa = new Tarefa(descricaoTarefa, destinatario);
        tarefas.add(tarefa);
        System.out.println("Tarefa enviada com sucesso para " + destinatario + "!");
        pausar();
    }

    public void concluirTask(String tipoUsuario, String nomeUsuario) {
        if (!tipoUsuario.equals("backstage") && !tipoUsuario.equals("ator") && !tipoUsuario.equals("adm_geral") && !tipoUsuario.equals("adm_criativo")) {
            Main.limparTela();
            System.out.println("Acesso negado: Apenas funcionários backstage, atores, adm_geral ou adm_criativo podem acessar o cronograma.");
            pausar();
            return;
        }

        while (true) {
            Main.limparTela();
            System.out.println("\n=== Seu Cronograma ===");

            List<Tarefa> tarefasDoUsuario = tarefas.stream()
                    .filter(t -> t.getResponsavel().equalsIgnoreCase(nomeUsuario) && !t.isConcluida())
                    .toList();

            List<Atividade> atividadesDoUsuario = atividades.stream()
                    .filter(a -> a.getResponsavel().equalsIgnoreCase(nomeUsuario))
                    .toList();

            if (tarefasDoUsuario.isEmpty() && atividadesDoUsuario.isEmpty()) {
                System.out.println("Nenhuma tarefa ou atividade pendente para você.");
                pausar();
                return;
            }

            if (!tarefasDoUsuario.isEmpty()) {
                System.out.println("Tarefas Pendentes:");
                for (int i = 0; i < tarefasDoUsuario.size(); i++) {
                    System.out.println("T" + (i + 1) + ". " + tarefasDoUsuario.get(i));
                }
            }

            if (!atividadesDoUsuario.isEmpty()) {
                System.out.println("\nAtividades Associadas:");
                for (int i = 0; i < atividadesDoUsuario.size(); i++) {
                    System.out.println("A" + (i + 1) + ". " + atividadesDoUsuario.get(i));
                }
            }

            System.out.println("\nOpções:");
            System.out.println("1. Entrar em uma tarefa/atividade");
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();
            if (opcao.equals("2")) {
                break;
            } else if (opcao.equals("1")) {
                System.out.print("Digite o identificador (ex.: T1 para tarefa, A1 para atividade): ");
                String id = lerStringValida().toUpperCase();

                if (id.startsWith("T")) {
                    try {
                        int indice = Integer.parseInt(id.substring(1)) - 1;
                        if (indice >= 0 && indice < tarefasDoUsuario.size()) {
                            Tarefa tarefa = tarefasDoUsuario.get(indice);
                            Main.limparTela();
                            System.out.println("Tarefa Selecionada: " + tarefa);
                            System.out.print("Deseja concluir esta tarefa? (s/n): ");
                            String confirmacao = scanner.nextLine().toLowerCase();
                            if (confirmacao.equals("s")) {
                                tarefa.concluir();
                                System.out.println("Tarefa concluída com sucesso!");
                            } else {
                                System.out.println("Tarefa não concluída.");
                            }
                            pausar();
                        } else {
                            System.out.println("Número inválido.");
                            pausar();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Identificador inválido.");
                        pausar();
                    }
                } else if (id.startsWith("A")) {
                    try {
                        int indice = Integer.parseInt(id.substring(1)) - 1;
                        if (indice >= 0 && indice < atividadesDoUsuario.size()) {
                            Atividade atividade = atividadesDoUsuario.get(indice);
                            Main.limparTela();
                            System.out.println("Atividade Selecionada: " + atividade);
                            System.out.println("\nOpções:");
                            System.out.println("1. Finalizar Atividade");
                            System.out.println("2. Rejeitar Atividade");
                            System.out.println("3. Sair da Visualização");
                            System.out.print("Escolha uma opção: ");
                            String escolha = scanner.nextLine();

                            if (escolha.equals("1")) {
                                System.out.print("Digite o feedback (o que foi feito): ");
                                String feedback = lerStringValida();
                                atividade.setFeedback(feedback);
                                atividade.setConcluida(true);
                                atividadesFinalizadas.add(atividade);
                                atividades.remove(atividade);
                                System.out.println("Atividade finalizada com sucesso! Feedback enviado ao ADM.");
                                pausar();
                            } else if (escolha.equals("2")) {
                                System.out.print("Digite o motivo da rejeição: ");
                                String feedback = lerStringValida();
                                atividade.setFeedback(feedback);
                                atividade.setRejeitada(true);
                                atividadesFinalizadas.add(atividade);
                                atividades.remove(atividade);
                                System.out.println("Atividade rejeitada! Feedback enviado ao ADM.");
                                pausar();
                            } else if (escolha.equals("3")) {
                                System.out.println("Saindo da visualização da atividade.");
                                continue;
                            } else {
                                System.out.println("Opção inválida.");
                                pausar();
                            }
                        } else {
                            System.out.println("Número inválido.");
                            pausar();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Identificador inválido.");
                        pausar();
                    }
                } else {
                    System.out.println("Identificador inválido. Use T para tarefas ou A para atividades.");
                    pausar();
                }
            } else {
                System.out.println("Opção inválida.");
                pausar();
            }
        }
    }

    public void terminarTask(String tipoUsuario) {
        if (!tipoUsuario.equals("adm_geral") && !tipoUsuario.equals("adm_criativo")) {
            Main.limparTela();
            System.out.println("Acesso negado: Apenas administradores gerais ou criativos podem terminar tarefas.");
            pausar();
            return;
        }

        Main.limparTela();
        List<Tarefa> tarefasDoUsuario = tarefas.stream()
                .filter(t -> t.getResponsavel().equalsIgnoreCase(loginSystem.getUsuarioAutenticado().getNome()) && !t.isConcluida())
                .toList();

        if (tarefasDoUsuario.isEmpty()) {
            System.out.println("Nenhuma tarefa pendente para você.");
            pausar();
            return;
        }

        System.out.println("\nLista de Tarefas:");
        for (int i = 0; i < tarefasDoUsuario.size(); i++) {
            System.out.println((i + 1) + ". " + tarefasDoUsuario.get(i));
        }
        System.out.print("Digite o número da tarefa a terminar: ");
        int indice = lerInteiroValido("Digite o número da tarefa a terminar: ") - 1;

        if (indice >= 0 && indice < tarefasDoUsuario.size()) {
            Tarefa tarefa = tarefasDoUsuario.get(indice);
            tarefa.concluir();
            System.out.println("Tarefa terminada com sucesso!");
        } else {
            System.out.println("Tarefa inválida.");
        }
        pausar();
    }

    public void adicionarTarefa(String descricao, String responsavel) {
        Tarefa tarefa = new Tarefa(descricao, responsavel);
        tarefas.add(tarefa);
    }

    public void visualizarFeedback(String tipoUsuario) {
        Main.limparTela();
        if (!tipoUsuario.equals("adm_geral") && !tipoUsuario.equals("adm_criativo")) {
            System.out.println("Acesso negado: Apenas adm geral ou adm criativo podem visualizar feedback.");
            pausar();
            return;
        }

        List<Atividade> feedbacks = atividadesFinalizadas.stream()
                .filter(a -> a.getAdmResponsavel().equalsIgnoreCase(loginSystem.getUsuarioAutenticado().getNome()) && !a.getFeedback().isEmpty())
                .toList();

        if (feedbacks.isEmpty()) {
            System.out.println("Nenhum feedback disponível.");
        } else {
            System.out.println("\nFeedbacks Recebidos:");
            for (int i = 0; i < feedbacks.size(); i++) {
                System.out.println((i + 1) + ". " + feedbacks.get(i));
            }
        }
        pausar();
    }

    private String lerStringValida() {
        String input = scanner.nextLine();
        while (input.trim().isEmpty()) {
            System.out.print("Campo não pode ser vazio. Tente novamente: ");
            input = scanner.nextLine();
        }
        return input;
    }

    private int lerInteiroValido(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    private void pausar() {
        System.out.println("\nPressione Enter para continuar...");
        scanner.nextLine();
    }

    // Método para carregar dados de arquivos
    @SuppressWarnings("unchecked")
    public void carregarDados() {
        try (FileInputStream fileIn = new FileInputStream("teatros.dat");
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            teatros = (List<Teatro>) objectIn.readObject();
        } catch (FileNotFoundException e) {
            teatros = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar teatros: " + e.getMessage());
            teatros = new ArrayList<>();
        }

        try (FileInputStream fileIn = new FileInputStream("tarefas.dat");
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            tarefas = (List<Tarefa>) objectIn.readObject();
        } catch (FileNotFoundException e) {
            tarefas = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar tarefas: " + e.getMessage());
            tarefas = new ArrayList<>();
        }

        try (FileInputStream fileIn = new FileInputStream("atividades.dat");
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            atividades = (List<Atividade>) objectIn.readObject();
        } catch (FileNotFoundException e) {
            atividades = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar atividades: " + e.getMessage());
            atividades = new ArrayList<>();
        }

        try (FileInputStream fileIn = new FileInputStream("atividadesFinalizadas.dat");
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            atividadesFinalizadas = (List<Atividade>) objectIn.readObject();
        } catch (FileNotFoundException e) {
            atividadesFinalizadas = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar atividades finalizadas: " + e.getMessage());
            atividadesFinalizadas = new ArrayList<>();
        }
    }

    // Método para salvar dados em arquivos
    public void salvarDados() {
        try (FileOutputStream fileOut = new FileOutputStream("teatros.dat");
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(teatros);
        } catch (IOException e) {
            System.out.println("Erro ao salvar teatros: " + e.getMessage());
        }

        try (FileOutputStream fileOut = new FileOutputStream("tarefas.dat");
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(tarefas);
        } catch (IOException e) {
            System.out.println("Erro ao salvar tarefas: " + e.getMessage());
        }

        try (FileOutputStream fileOut = new FileOutputStream("atividades.dat");
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(atividades);
        } catch (IOException e) {
            System.out.println("Erro ao salvar atividades: " + e.getMessage());
        }

        try (FileOutputStream fileOut = new FileOutputStream("atividadesFinalizadas.dat");
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(atividadesFinalizadas);
        } catch (IOException e) {
            System.out.println("Erro ao salvar atividades finalizadas: " + e.getMessage());
        }
    }
}