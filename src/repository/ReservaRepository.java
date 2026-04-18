package repository;

import model.Reserva;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReservaRepository {
    
    private static final String CAMINHO_ARQUIVO = "reservas.txt";

    public void salvar(Reserva reserva) {
        
        // 1. Antes de salvar, verifica se a quadra está livre (Atende ao RF004)
        if (!isDisponivel(reserva.getQuadra().getId(), reserva.getData(), reserva.getHoraInicio(), reserva.getHoraFim())) {
            throw new IllegalArgumentException("A quadra " + reserva.getQuadra().getNome() + " já está reservada neste horário!");
        }

        // 2. Se passou da validação, salva no arquivo .txt
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO, true))) {
            
            double valorTotal = reserva.calcularValorTotal(); // Puxa o cálculo polimórfico
            
            // Formato salvo: idReserva ; cpfCliente ; idQuadra ; data ; horaInicio ; horaFim ; valorTotal
            String linha = reserva.getId() + ";" + 
                           reserva.getCliente().getCpf() + ";" + 
                           reserva.getQuadra().getId() + ";" + 
                           reserva.getData() + ";" + 
                           reserva.getHoraInicio() + ";" + 
                           reserva.getHoraFim() + ";" + 
                           valorTotal;
            
            bw.write(linha);
            bw.newLine();
            
        } catch (IOException e) {
            System.out.println("Erro ao salvar reserva: " + e.getMessage());
        }
    }

    // Método que verifica choque de horários
    public boolean isDisponivel(int idQuadraDesejada, String dataDesejada, int novaHoraInicio, int novaHoraFim) {
        try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 6) {
                    int idQuadraSalva = Integer.parseInt(dados[2]);
                    String dataSalva = dados[3];
                    int horaInicioSalva = Integer.parseInt(dados[4]);
                    int horaFimSalva = Integer.parseInt(dados[5]);

                    // Verifica se a reserva é para a MESMA quadra no MESMO dia
                    if (idQuadraSalva == idQuadraDesejada && dataSalva.equals(dataDesejada)) {
                        
                        // Lógica de sobreposição de horários
                        if (novaHoraInicio < horaFimSalva && novaHoraFim > horaInicioSalva) {
                            return false; // Retorna FALSO (Deu choque de horário!)
                        }
                    }
                }
            }
        } catch (IOException e) {
            // Se der erro de leitura ou o arquivo não existir, assumimos que está tudo livre
        }
        return true; // Retorna VERDADEIRO (A quadra está livre!)
    }
    
 // Lê o arquivo de texto e retorna as linhas cruas para preencher a tabela visual
    public java.util.List<String[]> listarDadosTabela() {
        java.util.List<String[]> reservas = new java.util.ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                // Formato txt: idReserva ; cpfCliente ; idQuadra ; data ; horaInicio ; horaFim ; valorTotal
                if (dados.length >= 7) {
                    reservas.add(dados);
                }
            }
        } catch (IOException e) { }
        return reservas;
    }

    // Exclui uma reserva com base no ID (Para o botão "Cancelar Reserva")
    public void cancelarReserva(int idBusca) {
        java.util.List<String[]> reservasAtuais = listarDadosTabela();
        reservasAtuais.removeIf(dados -> Integer.parseInt(dados[0]) == idBusca);
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO, false))) {
            for (String[] r : reservasAtuais) {
                bw.write(String.join(";", r));
                bw.newLine();
            }
        } catch (IOException e) { }
    }
}