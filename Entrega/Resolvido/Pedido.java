import java.time.*;

class Pedido {
    private Cliente cliente;
    private Entregador entregador;
    private double peso;
    private String cepOrigem;
    private String cepDestino;
    private double distancia;
    private double tempoEstimado;
    private double preco;
    private String status;
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioEntrega;

    public Pedido(Cliente cliente, double peso, String cepOrigem, String cepDestino, double distancia, double tempoEstimado, double preco) {
        this.cliente = cliente;
        this.peso = peso;
        this.cepOrigem = cepOrigem;
        this.cepDestino = cepDestino;
        this.distancia = distancia;
        this.tempoEstimado = tempoEstimado;
        this.preco = preco;
        this.status = "Em Aberto";
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPreco() {
        return preco;
    }

    public void setHorarioInicio(LocalDateTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public void setHorarioEntrega(LocalDateTime horarioEntrega) {
        this.horarioEntrega = horarioEntrega;
    }

    @Override
    public String toString() {
        return String.format("Pedido: [Peso: %.2f Kg, Origem: %s, Destino: %s, Distância: %.2f Km, Preço: R$ %.2f, Status: %s]", 
                peso, cepOrigem, cepDestino, distancia, preco, status);
    }
}