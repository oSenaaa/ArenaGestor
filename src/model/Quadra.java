package model;

// Classe abstrata que define o esqueleto genérico de uma quadra, exigindo que as filhas implementem suas regras específicas.
public abstract class Quadra {
    
    protected int id;
    protected String nome;
    protected double valorHora;

    // Construtor para inicializar as informações básicas que toda quadra possui.
    public Quadra(int id, String nome, double valorHora) {
        this.id = id;
        this.nome = nome;
        this.valorHora = valorHora;
    }

    // Método abstrato que obriga as subclasses a definirem como o preço final é calculado (Polimorfismo).
    public abstract double calcularPrecoFinal(int horas);

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getValorHora() { return valorHora; }
    public void setValorHora(double valorHora) { this.valorHora = valorHora; }
}