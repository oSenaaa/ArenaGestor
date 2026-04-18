package repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class QuadraRepository {
    
    private static final String CAMINHO_ARQUIVO = "quadras.txt";

    public void salvar(Quadra quadra) {
        // Se o ID for 0, gera um ID automático baseado na quantidade de quadras
        if (quadra.getId() == 0) {
            quadra.setId(listarTodas().size() + 1);
        }
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO, true))) {
            String tipo = quadra.getClass().getSimpleName();
            String linha = quadra.getId() + ";" + quadra.getNome() + ";" + quadra.getValorHora() + ";" + tipo;
            bw.write(linha);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar quadra: " + e.getMessage());
        }
    }

    public List<Quadra> listarTodas() {
        List<Quadra> quadras = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 4) {
                    int id = Integer.parseInt(dados[0]);
                    String nome = dados[1];
                    double valor = Double.parseDouble(dados[2]);
                    String tipo = dados[3];

                    // Recria o objeto específico baseado no texto lido
                    if (tipo.equals("QuadraFutsal")) quadras.add(new QuadraFutsal(id, nome, valor));
                    else if (tipo.equals("QuadraTenis")) quadras.add(new QuadraTenis(id, nome, valor));
                    else if (tipo.equals("QuadraSociety")) quadras.add(new QuadraSociety(id, nome, valor));
                    else if (tipo.equals("QuadraCampo")) quadras.add(new QuadraCampo(id, nome, valor));
                }
            }
        } catch (IOException e) {} // Ignora se o arquivo não existir ainda
        return quadras;
    }

    public void excluir(int idBusca) {
        List<Quadra> quadras = listarTodas();
        quadras.removeIf(q -> q.getId() == idBusca);
        reescreverArquivo(quadras);
    }

    public void editar(Quadra quadraEditada) {
        List<Quadra> quadras = listarTodas();
        for (int i = 0; i < quadras.size(); i++) {
            if (quadras.get(i).getId() == quadraEditada.getId()) {
                quadras.set(i, quadraEditada);
                break;
            }
        }
        reescreverArquivo(quadras);
    }

    private void reescreverArquivo(List<Quadra> quadras) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO, false))) {
            for (Quadra q : quadras) {
                String tipo = q.getClass().getSimpleName();
                bw.write(q.getId() + ";" + q.getNome() + ";" + q.getValorHora() + ";" + tipo);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao reescrever quadras: " + e.getMessage());
        }
    }
}