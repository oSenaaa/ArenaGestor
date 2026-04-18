package view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.*;
import repository.*;

public class PainelStatusQuadra extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel painelCards;
    private JLabel lblRelogio;

    public PainelStatusQuadra() {
        this.setLayout(null);
        this.setSize(908, 490);
        this.setBackground(new Color(240, 240, 240));

        // Cabeçalho com o Relógio
        lblRelogio = new JLabel("Painel de Status — Carregando...");
        lblRelogio.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblRelogio.setBounds(25, 20, 500, 20);
        this.add(lblRelogio);

        JButton btnAtualizar = new JButton("Atualizar Agora");
        btnAtualizar.setBounds(745, 15, 140, 30);
        btnAtualizar.addActionListener(e -> atualizarDashboard());
        this.add(btnAtualizar);

        // Área onde os cards vão aparecer (Usamos JScrollPane caso tenha muitas quadras)
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 60, 860, 400);
        scrollPane.setBorder(null); // Tira a borda feia do scroll
        this.add(scrollPane);

        // Painel interno que organiza os cards num fluxo (Wrap)
        painelCards = new JPanel();
        painelCards.setBackground(new Color(240, 240, 240));
        // O FlowLayout permite que os cards fiquem lado a lado e "quebrem" a linha quando não couber
        painelCards.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15)); 
        scrollPane.setViewportView(painelCards);

        // Carrega os dados na primeira vez
        atualizarDashboard();

        // TEMPORIZADOR MÁGICO: Atualiza tudo sozinho a cada 30 segundos (30000 milissegundos)
        Timer timer = new Timer(30000, e -> atualizarDashboard());
        timer.start();
    }

    // Método que apaga os cards antigos e desenha os novos baseados na hora atual
    public void atualizarDashboard() {
        painelCards.removeAll(); // Limpa a tela

        // Pega a data e hora atuais do computador
        LocalDate dataHoje = LocalDate.now();
        LocalTime horaAgora = LocalTime.now();
        
        String dataFormatada = dataHoje.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String horaFormatada = horaAgora.format(DateTimeFormatter.ofPattern("HH:mm"));
        lblRelogio.setText("Painel de Status — " + dataFormatada + " • Agora: " + horaFormatada);

        List<Quadra> quadras = new QuadraRepository().listarTodas();
        List<String[]> reservas = new ReservaRepository().listarDadosTabela();
        List<Cliente> clientes = new ClienteRepository().listarTodos();

        for (Quadra q : quadras) {
            boolean estaOcupada = false;
            String[] reservaAtual = null;

            // Cruza a quadra com as reservas de HOJE e do HORÁRIO ATUAL
            for (String[] r : reservas) {
                int idQuadraReserva = Integer.parseInt(r[2]);
                String dataReserva = r[3];
                int horaInicio = Integer.parseInt(r[4]);
                int horaFim = Integer.parseInt(r[5]);

                if (idQuadraReserva == q.getId() && dataReserva.equals(dataFormatada)) {
                    // Se a hora atual está entre o início e o fim da reserva, está ocupada!
                    if (horaAgora.getHour() >= horaInicio && horaAgora.getHour() < horaFim) {
                        estaOcupada = true;
                        reservaAtual = r;
                        break;
                    }
                }
            }

            // Descobre o nome do cliente se estiver ocupada
            String nomeCliente = "";
            if (estaOcupada && reservaAtual != null) {
                String cpf = reservaAtual[1];
                for (Cliente c : clientes) {
                    if (c.getCpf().equals(cpf)) {
                        nomeCliente = c.getNome();
                        break;
                    }
                }
            }

            // Desenha o Card Visual
            JPanel card = criarCardQuadra(q, estaOcupada, reservaAtual, nomeCliente);
            painelCards.add(card);
        }

        // Avisa a interface gráfica para redesenhar a tela com os novos cards
        painelCards.revalidate();
        painelCards.repaint();
    }

    // "Fábrica" de Cards (Desenha a caixa verde ou vermelha)
    private JPanel criarCardQuadra(Quadra q, boolean ocupada, String[] reserva, String nomeCliente) {
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setPreferredSize(new Dimension(270, 130)); // Tamanho de cada caixinha
        card.setBackground(Color.WHITE);

        // Cores padrão (Verde ou Vermelho)
        Color corBorda = ocupada ? new Color(231, 76, 60) : new Color(46, 204, 113);
        card.setBorder(BorderFactory.createLineBorder(corBorda, 2));

        JLabel lblNome = new JLabel(q.getId() + " - " + q.getNome());
        lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNome.setBounds(15, 10, 180, 20);
        card.add(lblNome);

        // Etiqueta (LIVRE ou OCUPADA)
        JLabel lblTag = new JLabel(ocupada ? "OCUPADA" : "LIVRE", SwingConstants.CENTER);
        lblTag.setOpaque(true);
        lblTag.setBackground(corBorda);
        lblTag.setForeground(Color.WHITE);
        lblTag.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblTag.setBounds(190, 10, 70, 20);
        card.add(lblTag);

        JLabel lblTipo = new JLabel("Tipo: " + q.getClass().getSimpleName().replace("Quadra", ""));
        lblTipo.setForeground(Color.DARK_GRAY);
        lblTipo.setBounds(15, 35, 200, 20);
        card.add(lblTipo);

        if (ocupada) {
            JLabel lblCli = new JLabel("Cliente: " + nomeCliente);
            lblCli.setBounds(15, 60, 240, 20);
            card.add(lblCli);

            JLabel lblHorario = new JLabel("Horário: " + reserva[4] + ":00 - " + reserva[5] + ":00");
            lblHorario.setBounds(15, 85, 240, 20);
            card.add(lblHorario);
        } else {
            JLabel lblDisp = new JLabel("Disponível para reserva");
            lblDisp.setForeground(new Color(46, 204, 113));
            lblDisp.setFont(new Font("Tahoma", Font.ITALIC, 12));
            lblDisp.setBounds(15, 60, 200, 20);
            card.add(lblDisp);
        }

        return card;
    }
}