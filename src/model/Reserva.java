package model;

public class Reserva {
    
    private int id;
    private Cliente cliente; // Associação: A reserva "tem um" Cliente
    private Quadra quadra;   // Associação: A reserva "tem uma" Quadra
    private String data;
    private int horaInicio;
    private int horaFim;

    public Reserva(int id, Cliente cliente, Quadra quadra, String data, int horaInicio, int horaFim) {
        // Validação da regra de negócio básica
        if (horaFim <= horaInicio) {
            throw new IllegalArgumentException("A hora de término deve ser maior que a hora de início!");
        }
        
        this.id = id;
        this.cliente = cliente;
        this.quadra = quadra;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    // Se a quadra for Society, calcula com a taxa dela. Se for Tênis, com a taxa do Tênis.
    public double calcularValorTotal() {
        int horasAlugadas = horaFim - horaInicio;
        return quadra.calcularPrecoFinal(horasAlugadas); 
    }

    // Getters
    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Quadra getQuadra() { return quadra; }
    public String getData() { return data; }
    public int getHoraInicio() { return horaInicio; }
    public int getHoraFim() { return horaFim; }
}