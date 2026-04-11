package model;

// Classe abstrata que serve de base para qualquer pessoa no sistema, impedindo que seja instanciada diretamente.
public abstract class Pessoa {
    
    // Atributos protegidos para permitir acesso direto pelas classes filhas (herança).
    protected String nome;
    protected String cpf;
    protected String telefone;

    // Construtor para inicializar os dados básicos de uma pessoa no momento de sua criação.
    public Pessoa(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}