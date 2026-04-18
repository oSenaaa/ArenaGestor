package model;

// Herda de Quadra e representa uma quadra de grama sintética (Society)
public class QuadraSociety extends Quadra {

    public QuadraSociety(int id, String nome, double valorHora) {
        super(id, nome, valorHora);
    }

    // Polimorfismo: Society tem acréscimo de taxa de iluminação noturna
    @Override
    public double calcularPrecoFinal(int horas) {
        double taxaIluminacao = 30.0;
        return (this.valorHora * horas) + taxaIluminacao;
    }
}