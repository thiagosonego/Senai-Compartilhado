import java.util.*;

public class EleicoesTerminal {

    static class Usuario {
        String codigo;
        boolean votou;

        Usuario(String codigo) {
            this.codigo = codigo;
            this.votou = false;
        }

        void resetarVoto() {
            this.votou = false;
        }
    }

    static class Eleicao {
        Map<String, Integer> opcoes;
        boolean aberta;

        Eleicao() {
            this.opcoes = new HashMap<>();
            this.aberta = false;
        }

        void adicionarOpcao(String opcao) {
            System.out.println("Adicionando opção: " + opcao);
            opcoes.put(opcao, 0);
        }

        void iniciar() {
            aberta = true;
            System.out.println("Votação iniciada.");
        }

        void encerrar() {
            aberta = false;
            System.out.println("Votação encerrada.");
        }

        void votar(String opcao) {
            System.out.println("Registrando voto para a opção: " + opcao);
            opcoes.put(opcao, opcoes.get(opcao) + 1);
        }

        String resultadoFinal() {
            System.out.println("Calculando resultado final...");
            int maiorVoto = 0;
            String opcaoEleita = "";

            for (Map.Entry<String, Integer> entry : opcoes.entrySet()) {
                if (entry.getValue() > maiorVoto) {
                    maiorVoto = entry.getValue();
                    opcaoEleita = entry.getKey();
                }
            }

            return "Resultado final:\n" + exibirResultados() + "\nOpção eleita: " + opcaoEleita;
        }

        String exibirResultados() {
            System.out.println("Exibindo resultados parciais...");
            StringBuilder resultados = new StringBuilder();
            for (Map.Entry<String, Integer> entry : opcoes.entrySet()) {
                resultados.append(entry.getKey()).append(": ").append(entry.getValue()).append(" votos\n");
            }
            return resultados.toString();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Eleicao eleicao = null;
        List<Usuario> usuarios = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            usuarios.add(new Usuario("U" + i));
            System.out.println("Usuário pré-cadastrado: U" + i);
        }

        while (true) {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Acessar como Administrador");
            System.out.println("2. Acessar como Funcionário");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                System.out.print("Digite a senha de administrador: ");
                String senha = scanner.nextLine();
                if (!senha.equals("admin123")) {
                    System.out.println("Senha incorreta!");
                    continue;
                }

                while (true) {
                    System.out.println("\n=== Menu Administrador ===");
                    System.out.println("1. Cadastrar opções de voto");
                    System.out.println("2. Iniciar votação");
                    System.out.println("3. Encerrar votação e ver resultado final");
                    System.out.println("4. Voltar ao menu principal");
                    System.out.print("Escolha uma opção: ");
                    int opcaoAdmin = scanner.nextInt();
                    scanner.nextLine();

                    if (opcaoAdmin == 1) {
                        if (eleicao == null || !eleicao.aberta) {
                            eleicao = new Eleicao();
                            for (Usuario usuario : usuarios) {
                                usuario.resetarVoto();
                            }
                            System.out.println("Digite as opções de voto (digite 'fim' para terminar):");
                            while (true) {
                                String opcaoVoto = scanner.nextLine();
                                if (opcaoVoto.equalsIgnoreCase("fim")) break;
                                eleicao.adicionarOpcao(opcaoVoto);
                            }
                        } else {
                            System.out.println("A votação já está aberta. Não é possível cadastrar novas opções.");
                        }
                    } else if (opcaoAdmin == 2) {
                        if (eleicao != null && !eleicao.aberta) {
                            eleicao.iniciar();
                        } else {
                            System.out.println("Não há uma eleição cadastrada ou já está em andamento.");
                        }
                    } else if (opcaoAdmin == 3) {
                        if (eleicao != null && eleicao.aberta) {
                            eleicao.encerrar();
                            System.out.println(eleicao.resultadoFinal());
                        } else {
                            System.out.println("Nenhuma votação em andamento para encerrar.");
                        }
                    } else if (opcaoAdmin == 4) {
                        break;
                    } else {
                        System.out.println("Opção inválida.");
                    }
                }
            } else if (opcao == 2) {
                System.out.print("Digite seu código de acesso: ");
                String codigo = scanner.nextLine();
                Usuario usuario = usuarios.stream()
                        .filter(u -> u.codigo.equals(codigo))
                        .findFirst()
                        .orElse(null);

                if (usuario == null) {
                    System.out.println("Código inválido.");
                } else if (usuario.votou) {
                    System.out.println("Você já votou.");
                } else if (eleicao == null || !eleicao.aberta) {
                    System.out.println("Nenhuma votação está aberta no momento.");
                } else {
                    System.out.println("Opções de voto:");
                    List<String> opcoes = new ArrayList<>(eleicao.opcoes.keySet());
                    for (int i = 0; i < opcoes.size(); i++) {
                        System.out.println((i + 1) + ". " + opcoes.get(i));
                    }

                    System.out.print("Escolha uma opção: ");
                    int escolha = scanner.nextInt();
                    scanner.nextLine();

                    if (escolha < 1 || escolha > opcoes.size()) {
                        System.out.println("Opção inválida.");
                    } else {
                        eleicao.votar(opcoes.get(escolha - 1));
                        usuario.votou = true;
                        System.out.println("Voto registrado com sucesso!");
                    }
                }
            } else if (opcao == 3) {
                System.out.println("Encerrando aplicação. Até logo!");
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}
