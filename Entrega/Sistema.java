import java.util.*;

class Sistema {
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();
    private Map<String, Double> precos = new HashMap<>();
    private Usuario usuarioLogado;

    public void inicializarSistema() {
        // Configura preços iniciais
        precos.put("peso1", 3.0); // Menos de 1Kg
        precos.put("peso2", 5.0); // Entre 1Kg e 3Kg
        precos.put("peso3", 9.0); // Entre 3Kg e 8Kg
        precos.put("peso4", 12.0); // Entre 8Kg e 12Kg
        precos.put("minuto", 0.30);
        precos.put("km", 0.50);

        // Adiciona usuários iniciais
        usuarios.add(new Administrador("admin", "1234"));
        usuarios.add(new Cliente("cliente1", "senha1"));
        usuarios.add(new Entregador("entregador1", "senha1", "João Silva", "123.456.789-00", "11-99999-9999", "ABC-1234"));
    }

    public void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Bem-vindo ao Sistema de Entregas!");
            System.out.println("1. Login");
            System.out.println("2. Solicitar Cadastro (Cliente)");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 :{ realizarLogin(scanner); break; }
                case 2 :{ solicitarCadastroCliente(scanner); break; }
                case 3 :{
                    System.out.println("Saindo...");
                    return;
                }
                default :{ System.out.println("Opção inválida!"); break; }
            }
        }
    }

    private void solicitarCadastroCliente(Scanner scanner) {
        System.out.println("Cadastro de Cliente");
        System.out.print("Informe o login: ");
        String login = scanner.nextLine();
        System.out.print("Informe a senha: ");
        String senha = scanner.nextLine();

        // Verifica se o login já existe
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login)) {
                System.out.println("Erro: Login já está em uso.");
                return;
            }
        }

        // Cria um novo cliente e adiciona à lista de usuários
        Cliente novoCliente = new Cliente(login, senha);
        usuarios.add(novoCliente);
        System.out.println("Cadastro realizado com sucesso! Agora você pode fazer login.");
    }


    private void realizarLogin(Scanner scanner) {
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                usuarioLogado = usuario;
                System.out.println("Login realizado com sucesso!");
                if (usuario instanceof Cliente) {
                    menuCliente((Cliente) usuario);
                } else if (usuario instanceof Administrador) {
                    menuAdministrador((Administrador) usuario);
                } else if (usuario instanceof Entregador) {
                    menuEntregador((Entregador) usuario);
                }
                return;
            }
        }
        System.out.println("Credenciais inválidas!");
    }

    private void menuCliente(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu Cliente");
            System.out.println("1. Fazer Pedido");
            System.out.println("2. Ver Histórico de Pedidos");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> fazerPedido(cliente, scanner);
                case 2 -> cliente.verHistorico();
                case 3 -> {
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void fazerPedido(Cliente cliente, Scanner scanner) {
        System.out.print("Peso da encomenda (Kg): ");
        double peso = scanner.nextDouble();
        scanner.nextLine();
        if (peso > 12) {
            System.out.println("Não é possível transportar encomendas acima de 12Kg.");
            return;
        }
        System.out.print("CEP de origem: ");
        String cepOrigem = scanner.nextLine();
        System.out.print("CEP de destino: ");
        String cepDestino = scanner.nextLine();
        System.out.print("Distância em Km: ");
        double distancia = scanner.nextDouble();
        System.out.print("Tempo estimado (em minutos): ");
        double tempo = scanner.nextDouble();

        //TODO -> salvar o valor total de frete do metodo calcularPreco
        if (preco == -1) {
            System.out.println("Erro ao calcular o preço.");
            return;
        }

        //TODO -> Criar um pedido Novo
        //TODO -> Adicionar o pedido a lista de pedidos
        //TODO -> Adicionar o pedido a lista de pedidos do cliente
        System.out.println("Pedido criado com sucesso! Valor total: R$ " + preco);
    }

    private double calcularPreco(double peso, double distancia, double tempo) {
        double precoPeso = 0;
        if (peso < 1) {
            precoPeso = precos.get("peso1");
        } else if (peso <= 3) {
            precoPeso = precos.get("peso2");
        } else if (peso <= 8) {
            precoPeso = precos.get("peso3");
        } else if (peso <= 12) {
            precoPeso = precos.get("peso4");
        } else {
            return -1;
        }

        double precoDistancia = distancia * precos.get("km");
        double precoTempo = tempo * precos.get("minuto");

        return precoPeso + precoDistancia + precoTempo;
    }

    private void menuAdministrador(Administrador administrador) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu Administrador");
            System.out.println("1. Ver Pedidos");
            System.out.println("2. Alterar Preços");
            System.out.println("3. Cadastrar Novo Entregador");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> administrador.verPedidos(pedidos);
                case 2 -> alterarPrecos(scanner);
                case 3 -> cadastrarEntregador(scanner);
                case 4 -> {
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarEntregador(Scanner scanner) {
        System.out.println("Cadastro de Entregador");
        System.out.print("Informe o login: ");
        String login = scanner.nextLine();
        System.out.print("Informe a senha: ");
        String senha = scanner.nextLine();

        // Verifica se o login já existe
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login)) {
                System.out.println("Erro: Login já está em uso.");
                return;
            }
        }

        System.out.print("Nome completo: ");
        String nomeCompleto = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Placa da moto: ");
        String placaMoto = scanner.nextLine();

        Entregador novoEntregador = new Entregador(login, senha, nomeCompleto, cpf, telefone, placaMoto);
        usuarios.add(novoEntregador);
        System.out.println("Entregador cadastrado com sucesso!");
    }


    private void alterarPrecos(Scanner scanner) {
        System.out.println("Alterar preços:");
        for (String chave : precos.keySet()) {
            System.out.print(chave + " (atual: R$ " + precos.get(chave) + "): ");
            double novoPreco = scanner.nextDouble();
            precos.put(chave, novoPreco);
        }
        System.out.println("Preços alterados com sucesso!");
    }

    private void menuEntregador(Entregador entregador) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu Entregador");
            System.out.println("1. Ver Pedidos em Aberto");
            System.out.println("2. Ver Minhas Entregas");
            System.out.println("3. Atualizar Dados do Cadastro");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> entregador.verPedidosEmAberto(pedidos);
                case 2 -> entregador.verMinhasEntregas();
                case 3 -> atualizarDadosEntregador(entregador, scanner);
                case 4 -> {
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void atualizarDadosEntregador(Entregador entregador, Scanner scanner) {
        System.out.println("Atualização de Dados do Entregador");

        System.out.print("Nome completo atual: " + entregador.getNomeCompleto() + ". Novo nome (ou pressione Enter para manter): ");
        String nomeCompleto = scanner.nextLine();
        if (!nomeCompleto.isEmpty()) {
            entregador.setNomeCompleto(nomeCompleto);
        }

        System.out.print("CPF atual: " + entregador.getCpf() + ". Novo CPF (ou pressione Enter para manter): ");
        String cpf = scanner.nextLine();
        if (!cpf.isEmpty()) {
            entregador.setCpf(cpf);
        }

        System.out.print("Telefone atual: " + entregador.getTelefone() + ". Novo telefone (ou pressione Enter para manter): ");
        String telefone = scanner.nextLine();
        if (!telefone.isEmpty()) {
            entregador.setTelefone(telefone);
        }

        //TODO -> Imprime uma mensagem oferecendo para atualizar a placa da moto
        //TODO -> guarda o valor informado pelo usuário como placaMoto
        if (!placaMoto.isEmpty()) {
            //TODO -> atualiza a placa da moto
        }

        System.out.println("Dados atualizados com sucesso!");
    }
}