package model;

public abstract class Pessoa {
    
    private String nome;
    private String cpf;
    private String telefone;

    public Pessoa(String nome, String cpf, String telefone) {
        this.nome = nome;
        setCpf(cpf); // Chama o método com a nova validação inteligente
        
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

   
    public void setCpf(String cpf) {
        //Limpa os caracteres especiais apenas para a validação
        String cpfLimpo = cpf.replace(".", "").replace("-", "").trim();
        
        // Verifica se sobraram exatamente 11 números
        if (cpfLimpo.length() != 11) {
            throw new IllegalArgumentException("CPF inválido! O CPF deve conter 11 números.");
        }
        
        // Se passou no teste, salva a versão formatada original
        this.cpf = cpf; 
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}