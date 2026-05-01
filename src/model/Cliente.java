package model;

// Classe concreta que herda as características de Pessoa, representando um cliente do sistema.
public class Cliente extends Pessoa {

	  // Construtor que repassa os dados recebidos para a classe mãe (Pessoa) através do super().
    public Cliente(String nome, String cpf, String telefone) {
        super(nome, cpf, telefone);
    }
}