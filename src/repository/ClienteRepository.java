package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List; // Import necessário para a linha 39

import model.Cliente;

public class ClienteRepository {

    private static final String CAMINHO_ARQUIVO = "clientes.txt";

    public void salvar(Cliente cliente) {
        //  VALIDAÇÃO DE DUPLICIDADE
        for (Cliente c : listarTodos()) {
            if (c.getCpf().equals(cliente.getCpf())) {
                throw new IllegalArgumentException("Erro: O CPF " + cliente.getCpf() + " já está cadastrado!");
            }
        }

        // SALVAMENTO
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO, true))) {
            bw.write(cliente.getNome() + ";" + cliente.getCpf() + ";" + cliente.getTelefone());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar cliente: " + e.getMessage());
        }
    }

    public void excluir(String cpfBusca) {
        List<Cliente> clientes = listarTodos();
        clientes.removeIf(cliente -> cliente.getCpf().equals(cpfBusca));
        reescreverArquivo(clientes);
    }

    public void editar(String cpfOriginal, Cliente clienteEditado) {
        List<Cliente> clientes = listarTodos();
        boolean clienteEncontrado = false;

        for (int i = 0; i < clientes.size(); i++) {
            // Comparamos o CPF do TXT com o CPF ORIGINAL (ignorando espaços invisíveis)
            String cpfTxt = clientes.get(i).getCpf().trim();
            String cpfBusca = cpfOriginal.trim();

            if (cpfTxt.equals(cpfBusca)) {
                clientes.set(i, clienteEditado); // Substitui pelo novo
                clienteEncontrado = true;
                break; // Achou e trocou? Para a busca.
            }
        }

        if (clienteEncontrado) {
            reescreverArquivo(clientes);
            System.out.println("Cliente editado com sucesso no arquivo!");
        } else {
            System.out.println("ERRO: O cliente com CPF " + cpfOriginal + " não foi encontrado no arquivo.");
        }
    }
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 3) {
                    clientes.add(new Cliente(dados[0], dados[1], dados[2]));
                }
            }
        } catch (IOException e) {
            // Se o arquivo não existir, retorna lista vazia
        }
        return clientes;
    }

    private void reescreverArquivo(List<Cliente> clientes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO, false))) {
            for (Cliente c : clientes) {
                bw.write(c.getNome() + ";" + c.getCpf() + ";" + c.getTelefone());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao reescrever arquivo: " + e.getMessage());
        }
    }
}