import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SistemaTreinamentos {

    static Scanner scanner = new Scanner(System.in);
    static Map<String, Funcao> funcoes = new HashMap<>();
    static Map<Integer, Funcionario> funcionarios = new HashMap<>();
    static List<Treinamento> treinamentos = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            exibirMenu();
        }
    }

    private static void exibirMenu() {
        System.out.println("\nMenu de Controle de Treinamentos:");
        System.out.println("1. Cadastrar Treinamento");
        System.out.println("2. Cadastrar Função e seus Treinamentos Obrigatórios");
        System.out.println("3. Alterar Treinamentos Obrigatórios de uma Função");
        System.out.println("4. Cadastrar Funcionário");
        System.out.println("5. Atualizar Treinamentos de um Funcionário");
        System.out.println("6. Buscar Funcionário por Nome ou Matrícula");
        System.out.println("0. Sair");

        System.out.print("Escolha uma opção: ");
        int opcao = Integer.parseInt(scanner.nextLine());

        switch (opcao) {
            case 1 :{ cadastrarTreinamento(); break;}
            case 2 :{ cadastrarFuncao(); break;}
            case 3 :{ alterarTreinamentosObrigatorios(); break;}
            case 4 :{ cadastrarFuncionario(); break;}
            case 5 :{ atualizarTreinamentosFuncionario(); break;}
            case 6 :{ buscarFuncionario(); break;}
            case 0 :{
                System.out.println("Encerrando o sistema.");
                System.exit(0);
            }
            default :{ System.out.println("Opção inválida! Tente novamente."); break;}
        }
    }

    private static void cadastrarTreinamento() {
        System.out.print("Digite o nome do treinamento: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o período de validade (em meses): ");
        int validade = Integer.parseInt(scanner.nextLine());
        treinamentos.add(new Treinamento(nome, validade));
        System.out.println("Treinamento cadastrado com sucesso!");
    }

    private static void cadastrarFuncao() {
        System.out.print("Digite o nome da função: ");
        String nomeFuncao = scanner.nextLine();
        Funcao funcao = new Funcao(nomeFuncao);

        System.out.println("Selecione os treinamentos obrigatórios (digite os números separados por vírgula):");
        listarTreinamentos();

        String[] indices = scanner.nextLine().split(",");
        for (String indice : indices) {
            funcao.adicionarTreinamentoObrigatorio(treinamentos.get(Integer.parseInt(indice.trim())-1));
        }

        funcoes.put(nomeFuncao, funcao);
        System.out.println("Função cadastrada com sucesso!");
    }

    private static void alterarTreinamentosObrigatorios() {
        System.out.print("Digite o nome da função: ");
        String nomeFuncao = scanner.nextLine();

        if (!funcoes.containsKey(nomeFuncao)) {
            System.out.println("Função não encontrada!");
            return;
        }

        Funcao funcao = funcoes.get(nomeFuncao);
        System.out.println("Treinamentos obrigatórios atuais:");
        funcao.listarTreinamentosObrigatorios();

        System.out.println("Selecione os novos treinamentos obrigatórios (digite os números separados por vírgula):");
        listarTreinamentos();

        String[] indices = scanner.nextLine().split(",");
        funcao.getTreinamentosObrigatorios().clear();
        for (String indice : indices) {
            funcao.adicionarTreinamentoObrigatorio(treinamentos.get(Integer.parseInt(indice.trim())-1));
        }

        System.out.println("Treinamentos obrigatórios atualizados com sucesso!");
    }

    private static void cadastrarFuncionario() {
        System.out.print("Digite o nome do funcionário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a matrícula do funcionário: ");
        int matricula = Integer.parseInt(scanner.nextLine());

        System.out.println("Selecione a função do funcionário:");
        listarFuncoes();

        String nomeFuncao = scanner.nextLine();
        if (!funcoes.containsKey(nomeFuncao)) {
            System.out.println("Função não encontrada!");
            return;
        }

        Funcao funcao = funcoes.get(nomeFuncao);
        Funcionario funcionario = new Funcionario(nome, matricula, funcao);

        System.out.print("O funcionário possui treinamentos realizados? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            cadastrarTreinamentosRealizados(funcionario);
        }

        funcionarios.put(matricula, funcionario);
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    private static void cadastrarTreinamentosRealizados(Funcionario funcionario) {
        while (true) {
            System.out.println("Selecione o treinamento realizado (ou digite 0 para sair):");
            listarTreinamentos();

            int indice = Integer.parseInt(scanner.nextLine());
            indice--;
            if (indice < 0) break;

            Treinamento treinamento = treinamentos.get(indice);
            System.out.print("Digite a data de realização (dd/MM/yyyy): ");
            LocalDate dataRealizacao = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            funcionario.adicionarTreinamentoRealizado(treinamento, dataRealizacao);
        }
    }

    private static void atualizarTreinamentosFuncionario() {
        System.out.print("Digite a matrícula do funcionário: ");
        int matricula = Integer.parseInt(scanner.nextLine());

        if (!funcionarios.containsKey(matricula)) {
            System.out.println("Funcionário não encontrado!");
            return;
        }

        Funcionario funcionario = funcionarios.get(matricula);
        cadastrarTreinamentosRealizados(funcionario);
        System.out.println("Lista de treinamentos atualizada com sucesso!");
    }

    private static void buscarFuncionario() {
        System.out.print("Buscar por (1) Nome ou (2) Matrícula: ");
        int opcao = Integer.parseInt(scanner.nextLine());

        if (opcao == 1) {
            System.out.print("Digite o nome do funcionário: ");
            String nome = scanner.nextLine();
            funcionarios.values().stream()
                .filter(f -> f.getNome().equalsIgnoreCase(nome))
                .forEach(Funcionario::exibirInformacoes);
        } else if (opcao == 2) {
            System.out.print("Digite a matrícula do funcionário: ");
            int matricula = Integer.parseInt(scanner.nextLine());
            if (funcionarios.containsKey(matricula)) {
                funcionarios.get(matricula).exibirInformacoes();
            } else {
                System.out.println("Funcionário não encontrado!");
            }
        } else {
            System.out.println("Opção inválida!");
        }
    }

    private static void listarTreinamentos() {
        for (int i = 0; i < treinamentos.size(); i++) {
            System.out.println(i+1 + ". " + treinamentos.get(i).getNome());
        }
    }

    private static void listarFuncoes() {
        funcoes.keySet().forEach(System.out::println);
    }
}

class Treinamento {
    private String nome;
    private int validade;

    public Treinamento(String nome, int validade) {
        this.nome = nome;
        this.validade = validade;
    }

    public String getNome() {
        return nome;
    }

    public int getValidade() {
        return validade;
    }
}

class Funcao {
    private String nome;
    private List<Treinamento> treinamentosObrigatorios = new ArrayList<>();

    public Funcao(String nome) {
        this.nome = nome;
    }
    
    public String getNome(){
        return nome;
    }

    public void adicionarTreinamentoObrigatorio(Treinamento treinamento) {
        treinamentosObrigatorios.add(treinamento);
    }

    public List<Treinamento> getTreinamentosObrigatorios() {
        return treinamentosObrigatorios;
    }

    public void listarTreinamentosObrigatorios() {
        if (treinamentosObrigatorios.isEmpty()) {
            System.out.println("Nenhum treinamento obrigatório cadastrado para esta função.");
        } else {
            System.out.println("Treinamentos obrigatórios:");
            treinamentosObrigatorios.forEach(t -> System.out.println("- " + t.getNome()));
        }
    }
}

class Funcionario {
    private String nome;
    private int matricula;
    private Funcao funcao;
    private List<TreinamentoRealizado> treinamentosRealizados = new ArrayList<>();

    public Funcionario(String nome, int matricula, Funcao funcao) {
        this.nome = nome;
        this.matricula = matricula;
        this.funcao = funcao;
    }

    public String getNome() {
        return nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public List<TreinamentoRealizado> getTreinamentosRealizados() {
        return treinamentosRealizados;
    }

    public void adicionarTreinamentoRealizado(Treinamento treinamento, LocalDate dataRealizacao) {
        treinamentosRealizados.add(new TreinamentoRealizado(treinamento, dataRealizacao));
    }

    public void exibirInformacoes() {
        System.out.println("\nNome: " + nome);
        System.out.println("Matrícula: " + matricula);
        System.out.println("Função: " + funcao.getNome());
        System.out.println("Treinamentos Realizados:");

        if (treinamentosRealizados.isEmpty()) {
            System.out.println("Nenhum treinamento realizado.");
        } else {
            treinamentosRealizados.forEach(t -> {
                System.out.println("- " + t.getTreinamento().getNome() + " | Realizado em: " + t.getDataRealizacao() + " | Vencimento: " + t.getDataVencimento());
            });
        }

        System.out.println("Status dos Treinamentos Obrigatórios:");
        for (Treinamento treinamento : funcao.getTreinamentosObrigatorios()) {
            TreinamentoRealizado realizado = treinamentosRealizados.stream()
                    .filter(t -> t.getTreinamento().equals(treinamento))
                    .findFirst()
                    .orElse(null);

            if (realizado == null) {
                System.out.println("- " + treinamento.getNome() + ": Não realizado");
            } else if (realizado.getDataVencimento().isBefore(LocalDate.now())) {
                System.out.println("- " + treinamento.getNome() + ": Vencido");
            } else {
                System.out.println("- " + treinamento.getNome() + ": Em dia");
            }
        }
    }
}

class TreinamentoRealizado {
    private Treinamento treinamento;
    private LocalDate dataRealizacao;

    public TreinamentoRealizado(Treinamento treinamento, LocalDate dataRealizacao) {
        this.treinamento = treinamento;
        this.dataRealizacao = dataRealizacao;
    }

    public Treinamento getTreinamento() {
        return treinamento;
    }

    public LocalDate getDataRealizacao() {
        return dataRealizacao;
    }

    public LocalDate getDataVencimento() {
        return dataRealizacao.plusMonths(treinamento.getValidade());
    }
}
