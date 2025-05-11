import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {
    private List<Teatro> teatros;
    private List<Tarefa> tarefas;
    private Scanner scanner;
    private Main main;

    public Sistema(Main main) {
        this.teatros = new ArrayList<>();
        this.tarefas = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.main = main;
    }

    public void addTeatro(String tipoUsuario) {
        if (!tipoUsuario.equals("alta_administracao")) {
            Main.limparTela();
            System.out.println("Acesso negado: Apenas funcionários da alta administração podem adicionar teatros.");
            System.out.println("\nPressione Enter para voltar...");
            scanner.nextLine();
            return;
        }
        Main.limparTela();
        System.out.print("Digite o nome do teatro: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a localização: ");
        String localizacao = scanner.nextLine();
        System.out.print("Digite a capacidade: ");
        int capacidade = Integer.parseInt(scanner.nextLine());

        Teatro teatro = new Teatro(nome, localizacao, capacidade);
        teatros.add(teatro);
        System.out.println("Teatro adicionado com sucesso!");
        System.out.println("\nPressione Enter para voltar...");
        scanner.nextLine();
    }

    public void listarTeatro(String tipoUsuario) {
        if (!tipoUsuario.equals("alta_administracao")) {
            Main.limparTela();
            System.out.println("Acesso negado: Apenas funcionários da alta administração podem listar teatros.");
            System.out.println("\nPressione Enter para voltar...");
            scanner.nextLine();
            return;
        }
        Main.limparTela();
        if (teatros.isEmpty()) {
            System.out.println("Nenhum teatro cadastrado.");
        } else {
            System.out.println("\nLista de Teatros:");
            for (Teatro teatro : teatros) {
                System.out.println(teatro);
            }
        }
        System.out.println("\nPressione Enter para voltar...");
        scanner.nextLine();
    }

    public void gerenciarTeatro(String tipoUsuario) {
        if (!tipoUsuario.equals("alta_administracao")) {
            Main.limparTela();
            System.out.println("Acesso negado: Apenas funcionários da alta administração podem gerenciar teatros.");
            System.out.println("\nPressione Enter para voltar...");
            scanner.nextLine();
            return;
        }
        Main.limparTela();
        listarTeatro(tipoUsuario);
        if (teatros.isEmpty()) return;

        System.out.print("Digite o nome do teatro a gerenciar: ");
        String nome = scanner.nextLine();
        Teatro teatro = teatros.stream()
                .filter(t -> t.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);

        if (teatro == null) {
            System.out.println("Teatro não encontrado.");
            System.out.println("\nPressione Enter para voltar...");
            scanner.nextLine();
            return;
        }

        System.out.print("Novo nome (deixe em branco para manter): ");
        String novoNome = scanner.nextLine();
        if (!novoNome.trim().isEmpty()) teatro.setNome(novoNome);

        System.out.print("Nova localização (deixe em branco para manter): ");
        String novaLocalizacao = scanner.nextLine();
        if (!novaLocalizacao.trim().isEmpty()) teatro.setLocalizacao(novaLocalizacao);

        System.out.print("Nova capacidade (0 para manter): ");
        int novaCapacidade = Integer.parseInt(scanner.nextLine());
        if (novaCapacidade > 0) teatro.setCapacidade(novaCapacidade);

        System.out.println("Teatro atualizado com sucesso!");
        System.out.println("\nPressione Enter para voltar...");
        scanner.nextLine();
    }

    public void delTeatro(String tipoUsuario) {
        if (!tipoUsuario.equals("alta_administracao")) {
            Main.limparTela();
            System.out.println("Acesso negado: Apenas funcionários da alta administração podem deletar teatros.");
            System.out.println("\nPressione Enter para voltar...");
            scanner.nextLine();
            return;
        }
        Main.limparTela();
        listarTeatro(tipoUsuario);
        if (teatros.isEmpty()) return;

        System.out.print("Digite o nome do teatro a deletar: ");
        String nome = scanner.nextLine();
        boolean removido = teatros.removeIf(t -> t.getNome().equalsIgnoreCase(nome));
        if (removido) {
            System.out.println("Teatro removido com sucesso!");
        } else {
            System.out.println("Teatro não encontrado.");
        }
        System.out.println("\nPressione Enter para voltar...");
        scanner.nextLine();
    }

    public void concluirTask(String tipoUsuario, String nomeUsuario) {
        if (!tipoUsuario.equals("backstage") && !tipoUsuario.equals("criativo")) {
            Main.limparTela();
            System.out.println("Acesso negado: Apenas funcionários backstage ou criativos podem concluir tarefas.");
            System.out.println("\nPressione Enter para voltar...");
            scanner.nextLine();
            return;
        }

        Main.limparTela();
        List<Tarefa> tarefasDoUsuario = tarefas.stream()
                .filter(t -> t.getResponsavel().equalsIgnoreCase(nomeUsuario) && !t.isConcluida())
                .toList();

        if (tarefasDoUsuario.isEmpty()) {
            System.out.println("Nenhuma tarefa pendente para você.");
            System.out.println("\nPressione Enter para voltar...");
            scanner.nextLine();
            return;
        }

        System.out.println("\nSuas Tarefas Pendentes:");
        for (int i = 0; i < tarefasDoUsuario.size(); i++) {
            System.out.println((i + 1) + ". " + tarefasDoUsuario.get(i));
        }

        System.out.print("Digite o número da tarefa a concluir: ");
        int indice = Integer.parseInt(scanner.nextLine()) - 1;

        if (indice >= 0 && indice < tarefasDoUsuario.size()) {
            tarefasDoUsuario.get(indice).concluir();
            System.out.println("Tarefa concluída com sucesso!");
        } else {
            System.out.println("Tarefa inválida.");
        }
        System.out.println("\nPressione Enter para voltar...");
        scanner.nextLine();
    }

    public void terminarTask(String tipoUsuario) {
        if (!tipoUsuario.equals("adm_geral") && !tipoUsuario.equals("adm_criativo")) {
            Main.limparTela();
            System.out.println("Acesso negado: Apenas administradores gerais ou criativos podem terminar tarefas.");
            System.out.println("\nPressione Enter para voltar...");
            scanner.nextLine();
            return;
        }

        Main.limparTela();
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
            System.out.println("\nPressione Enter para voltar...");
            scanner.nextLine();
            return;
        }

        System.out.println("\nLista de Tarefas:");
        for (int i = 0; i < tarefas.size(); i++) {
            System.out.println((i + 1) + ". " + tarefas.get(i));
        }

        System.out.print("Digite o número da tarefa a terminar: ");
        int indice = Integer.parseInt(scanner.nextLine()) - 1;

        if (indice >= 0 && indice < tarefas.size()) {
            tarefas.remove(indice);
            System.out.println("Tarefa terminada e removida com sucesso!");
        } else {
            System.out.println("Tarefa inválida.");
        }
        System.out.println("\nPressione Enter para voltar...");
        scanner.nextLine();
    }

    public void adicionarTarefa(String descricao, String responsavel) {
        Tarefa tarefa = new Tarefa(descricao, responsavel);
        tarefas.add(tarefa);
    }
}