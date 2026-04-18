package model;

// Herda de Quadra e representa um campo de futebol de grama natural
public class QuadraCampo extends Quadra {

    public QuadraCampo(int id, String nome, double valorHora) {
        super(id, nome, valorHora);
    }

    // Polimorfismo: Campo tem acréscimo de manutenção da grama natural
    @Override
    public double calcularPrecoFinal(int horas) {
        double taxaManutencaoGrama = 50.0;
        return (this.valorHora * horas) + taxaManutencaoGrama;
    }
}