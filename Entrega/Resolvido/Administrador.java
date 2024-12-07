
import java.util.List;

class Administrador extends Usuario {

    public Administrador(String login, String senha) {
        super(login, senha, "Administrador");
    }

    public void verPedidos(List<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado no sistema.");
            return;
        }

        System.out.println("Lista de Pedidos:");
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
    }

    @Override
    public void mostrarMenu() {
        System.out.println("Bem-vindo ao menu do Administrador!");
    }
}
