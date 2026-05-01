package model;

// Classe que herda de Quadra e representa especificamente uma quadra de Futsal.
public class QuadraFutsal extends Quadra {

	 // Construtor que inicializa a quadra de futsal repassando os dados para a classe mãe.
    public QuadraFutsal(int id, String nome, double valorHora) {
        super(id, nome, valorHora);
    }

    // Implementação polimórfica do cálculo de preço, retornando o valor padrão sem taxas extras.
    @Override
    public double calcularPrecoFinal(int horas) {
        return this.valorHora * horas;
    }
}