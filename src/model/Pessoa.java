package model;

public abstract class Pessoa {
    
    protected String nome;
    protected String cpf;
    protected String telefone;

    public Pessoa(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
        
        // Se o CPF for válido, salva. Se não, "lança" um erro e para a execução.
        if (cpf != null && cpf.matches("[0-9]{11}")) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("CPF inválido! Digite exatamente 11 números, sem traços ou pontos.");
        }
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}