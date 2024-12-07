import java.util.*;

class Cliente extends Usuario {
    private List<Pedido> historicoPedidos;

    public Cliente(String login, String senha) {
        super(login, senha, "Cliente");
        this.historicoPedidos = new ArrayList<>();
    }

    public void adicionarPedido(Pedido pedido) {
        historicoPedidos.add(pedido);
    }

    public void verHistorico() {
        if (historicoPedidos.isEmpty()) {
            System.out.println("Nenhum pedido realizado ainda.");
            return;
        }

        System.out.println("Histórico de Pedidos:");
        for (Pedido pedido : historicoPedidos) {
            System.out.println(pedido);
        }
    }

    @Override
    public void mostrarMenu() {
        System.out.println("Bem-vindo ao menu do Cliente!");
    }
}
