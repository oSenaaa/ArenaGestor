package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

// Gerencia a leitura e gravação dos dados de Cliente no arquivo de texto.
public class ClienteRepository {
    
    // Define o nome do arquivo que será criado na raiz do projeto.
    private static final String CAMINHO_ARQUIVO = "clientes.txt";

    // Recebe um objeto Cliente e salva no final do arquivo (append).
    public void salvar(Cliente cliente) {
        // O parâmetro 'true' no FileWriter garante que os dados antigos não sejam apagados.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO, true))) {
            // Formata a linha separando os dados por ponto e vírgula.
            String linha = cliente.getNome() + ";" + cliente.getCpf() + ";" + cliente.getTelefone();
            bw.write(linha);
            bw.newLine();
        } catch (IOException e) {
            // Tratamento de exceção obrigatório para operações de arquivo.
            System.out.println("Erro ao salvar cliente: " + e.getMessage());
        }
    }

    // Lê o arquivo txt e converte cada linha de volta para um objeto Cliente.
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";"); // Quebra a linha onde tem ponto e vírgula
                
                // Valida se a linha tem exatamente Nome, CPF e Telefone
                if (dados.length == 3) {
                    Cliente clienteLido = new Cliente(dados[0], dados[1], dados[2]);
                    clientes.add(clienteLido);
                }
            }
        } catch (IOException e) {
            System.out.println("Arquivo de clientes ainda não existe ou erro de leitura: " + e.getMessage());
        }
        
        return clientes;
    }
}
