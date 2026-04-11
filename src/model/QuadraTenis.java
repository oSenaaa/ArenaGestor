package model;

// Classe que herda de Quadra e representa especificamente uma quadra de Tênis.
public class QuadraTenis extends Quadra {

    // Construtor que inicializa a quadra de tênis repassando os dados para a classe mãe.
    public QuadraTenis(int id, String nome, double valorHora) {
        super(id, nome, valorHora);
    }

    // Implementação polimórfica do cálculo de preço, aplicando uma taxa fixa adicional para manutenção da rede/saibro.
    @Override
    public double calcularPrecoFinal(int horas) {
        double taxaManutencao = 20.0;
        return (this.valorHora * horas) + taxaManutencao;
    }
}