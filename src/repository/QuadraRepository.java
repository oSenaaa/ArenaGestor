package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Quadra;

// Importe os outros tipos se precisar ler depois

// Gerencia a leitura e gravação dos dados das Quadras no arquivo de texto.
public class QuadraRepository {
    
    private static final String CAMINHO_ARQUIVO = "quadras.txt";

    // Salva a quadra no arquivo. Salvamos o nome da classe para saber o tipo depois.
    public void salvar(Quadra quadra) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO, true))) {
            // Formato: ID;Nome;ValorHora;TipoDaQuadra
            String tipo = quadra.getClass().getSimpleName(); // Ex: Pega a palavra "QuadraSociety"
            String linha = quadra.getId() + ";" + quadra.getNome() + ";" + quadra.getValorHora() + ";" + tipo;
            bw.write(linha);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar quadra: " + e.getMessage());
        }
    }

    // Exemplo de leitura básica (retornando apenas os dados brutos para preencher tabelas)
    public List<String[]> listarDadosBrutos() {
        List<String[]> dadosDasQuadras = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 4) {
                    dadosDasQuadras.add(dados);
                }
            }
        } catch (IOException e) {
            System.out.println("Arquivo de quadras vazio ou não encontrado: " + e.getMessage());
        }
        
        return dadosDasQuadras;
    }
}