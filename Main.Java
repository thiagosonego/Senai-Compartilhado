import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        menu();
    }

    public static class Reserva {
        public String Nome;
        public int NumeroAviao;

        public Reserva(String nome, int numeroAviao) {
            Nome = nome;
            NumeroAviao = numeroAviao;
        }
    }

    static final int QUANTIDADE_AVIOES = 4;
    static int[] acentos = new int[QUANTIDADE_AVIOES];
    static int[] nomeAviao = new int[QUANTIDADE_AVIOES];
    static List<Reserva> aviao = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void menu() {
        int opcao;

        System.out.println("Opcao 1: Registrar o numero de cada aviao.");
        System.out.println("Opcao 2: Registrar a quantidade de assentos disponiveis em cada aviao.");
        System.out.println("Opcao 3: Reservar passagem aerea.");
        System.out.println("Opcao 4: Realizar consulta por aviao.");
        System.out.println("Opcao 5: Realizar consulta por passageiro.");
        System.out.println("Opcao 6: Resetar.");

        opcao = scanner.nextInt();

        switch (opcao) {
            case 1 -> registrarAviao();
            case 2 -> registrarAcentos();
            case 3 -> reservar();
            case 4 -> consultaAviao();
            case 5 -> consultaPassageiro();
            case 6 -> limpar();
            default -> {
                System.out.println("Opção invalida.");
                menu();
            }
        }
    }

    public static void retornar() {
        System.out.println("\n\nAperte enter para continuar!");
        scanner.nextLine();
        scanner.nextLine();
        menu();
    }

    public static void limpar() {
        for (int a = 0; a < QUANTIDADE_AVIOES; a++) {
            acentos[a] = 0;
            nomeAviao[a] = 0;
        }
        aviao.clear();
        retornar();
    }

    private static int contaPassageiros(int nomeAviao) {
        int apont = 0;
        for (Reserva a : aviao){
            if (a.NumeroAviao == nomeAviao)
                apont++;
        }
        return apont;
    }

    public static void registrarAviao() {
        for (int a = 0; a < QUANTIDADE_AVIOES; a++) {
            System.out.printf("Informe o numero do aviao %d:\n", a+1);
            nomeAviao[a] = scanner.nextInt();
        }
        retornar();
    }

    public static void registrarAcentos() {
        for (int a = 0; a < QUANTIDADE_AVIOES; a++) {
            System.out.printf("Informe a quantidade de assentos para o aviao %d:\n", a+1);
            acentos[a] = scanner.nextInt();
        }
        retornar();
    }

    public static void reservar() {
        int a, b, posicao = -1;
        System.out.println("Informe o numero do aviao para o registro:");
        a = scanner.nextInt();

        for (b = 0; b < QUANTIDADE_AVIOES; b++) {
            if (nomeAviao[b] == a) {
                posicao = b;
                System.out.println("Aviao encontrado.");
                break;
            }
        }

        if (posicao < 0) {
            System.out.println("Este aviao nao existe!");
            retornar();
        }

        int apont = contaPassageiros(nomeAviao[posicao]);

        if (apont >= acentos[posicao]) {
            System.out.println("Nao ha assentos disponiveis para este aviao!");
            retornar();
        }

        System.out.println("Informe o nome do passageiro a reservar:");
        scanner.nextLine();
        String passageiro = scanner.nextLine();

        aviao.add(new Reserva(passageiro, nomeAviao[posicao]));
        retornar();
    }

    public static void consultaAviao() {
        int a;
        System.out.println("Informe o numero do aviao a ser consultado:");
        a = scanner.nextInt();

        boolean encontrado = false;

        for (int posicao = 0; posicao < QUANTIDADE_AVIOES; posicao++) {
            //Testa se o nome do avião é o nome digitado
                //Se for o nome certo verifica se não está vazio ou se a quantidade de passageiros não é 0

                    
                    for (Reserva av : aviao){
                        //Encontrado o avião e tem passageiros, passa por todos os registros de reserva e imprime o nome do passageiro se o nome do avião for o nome procurado
                    }
      
        }

        if (!encontrado) {
            System.out.println("Este aviao nao existe!");
        }
        retornar();
    }

    public static void consultaPassageiro() {
        System.out.println("Informe o nome a ser consultado:");
        scanner.nextLine();
        String nome = scanner.nextLine();

        boolean encontrado = false;

        for (Reserva av : aviao){
            //procura pelo nome do passageiro em todos os aviões imprime o nome e numero do avião e marca se encontrou ele
        }

        if (!encontrado) {
            System.out.println("Nao ha reservas realizadas para este passageiro!");
        }
        retornar();
    }
}
