import java.time.LocalDateTime;
import java.util.*;

class Entregador extends Usuario {
    private String nomeCompleto;
    private String cpf;
    private String telefone;
    private String placaMoto;
    private List<Pedido> entregas;
    private double ganhoTotal;

    public Entregador(String login, String senha, String nomeCompleto, String cpf, String telefone, String placaMoto) {
        super(login, senha, "Entregador");
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.telefone = telefone;
        this.placaMoto = placaMoto;
        this.entregas = new ArrayList<>();
        this.ganhoTotal = 0.0;
    }

    // Getters e Setters
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getPlacaMoto() {
        return placaMoto;
    }

    public void setPlacaMoto(String placaMoto) {
        this.placaMoto = placaMoto;
    }


    public void verPedidosEmAberto(List<Pedido> pedidos) {
        System.out.println("Pedidos em Aberto:");
        for (Pedido pedido : pedidos) {
            if (pedido.getStatus().equals("Em Aberto")) {
                System.out.println(pedido);
            }
        }
    }

    public void aceitarEntrega(Pedido pedido) {
        if (pedido.getStatus().equals("Em Aberto")) {
            pedido.setEntregador(this);
            pedido.setStatus("Agendada");
            entregas.add(pedido);
            System.out.println("Entrega aceita com sucesso!");
        } else {
            System.out.println("Entrega já foi aceita por outro entregador.");
        }
    }

    public void verMinhasEntregas() {
        if (entregas.isEmpty()) {
            System.out.println("Nenhuma entrega aceita ainda.");
            return;
        }

        System.out.println("Minhas Entregas:");
        for (Pedido pedido : entregas) {
            System.out.println(pedido);
        }
    }

    public void iniciarEntrega(Pedido pedido) {
        if (entregas.contains(pedido) && pedido.getStatus().equals("Agendada")) {
            pedido.setStatus("Em Andamento");
            pedido.setHorarioInicio(LocalDateTime.now());
            System.out.println("Entrega iniciada com sucesso!");
        } else {
            System.out.println("Não é possível iniciar esta entrega.");
        }
    }

    public void finalizarEntrega(Pedido pedido) {
        if (entregas.contains(pedido) && pedido.getStatus().equals("Em Andamento")) {
            pedido.setStatus("Finalizada");
            pedido.setHorarioEntrega(LocalDateTime.now());
            double ganho = pedido.getPreco() * 0.7;
            ganhoTotal += ganho;
            System.out.println("Entrega finalizada com sucesso! Você ganhou R$ " + ganho);
        } else {
            System.out.println("Não é possível finalizar esta entrega.");
        }
    }

    @Override
    public void mostrarMenu() {
        System.out.println("Bem-vindo ao menu do Entregador!");
    }
}
