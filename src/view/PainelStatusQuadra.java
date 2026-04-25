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
        // Layout principal responsivo
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(new Color(240, 240, 240));

        // --- TOPO: RELÓGIO E BOTÃO ---
        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.setBackground(new Color(240, 240, 240));
        painelTopo.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        
        lblRelogio = new JLabel("Painel de Status — Carregando...");
        lblRelogio.setFont(new Font("Tahoma", Font.BOLD, 14));
        painelTopo.add(lblRelogio, BorderLayout.WEST);

        JButton btnAtualizar = new JButton("Atualizar Agora");
        btnAtualizar.addActionListener(e -> atualizarDashboard());
        painelTopo.add(btnAtualizar, BorderLayout.EAST);
        
        this.add(painelTopo, BorderLayout.NORTH);

        // --- CENTRO: O MURAL DE CARDS ---
        painelCards = new JPanel();
        painelCards.setBackground(new Color(240, 240, 240));
        
        // FlowLayout com alinhamento à esquerda e espaçamento
        FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 20, 20);
        painelCards.setLayout(flow);

        // ScrollPane que só permite rolagem Vertical (Estilo Mural)
        JScrollPane scroll = new JScrollPane(painelCards);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(null);
        
        this.add(scroll, BorderLayout.CENTER);

        atualizarDashboard();

        // Auto-refresh a cada 30s
        Timer timer = new Timer(30000, e -> atualizarDashboard());
        timer.start();
    }

    public void atualizarDashboard() {
        painelCards.removeAll();
        
        LocalDate dataHoje = LocalDate.now();
        LocalTime horaAgora = LocalTime.now();
        String dataFormatada = dataHoje.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        lblRelogio.setText("Painel de Status — " + dataFormatada + " • " + horaAgora.format(DateTimeFormatter.ofPattern("HH:mm")));

        List<Quadra> quadras = new QuadraRepository().listarTodas();
        List<String[]> reservas = new ReservaRepository().listarDadosTabela();

        for (Quadra q : quadras) {
            boolean ocupada = false;
            String cliente = "";
            String horario = "";

            for (String[] r : reservas) {
                if (Integer.parseInt(r[2]) == q.getId() && r[3].equals(dataFormatada)) {
                    int hi = Integer.parseInt(r[4]);
                    int hf = Integer.parseInt(r[5]);
                    if (horaAgora.getHour() >= hi && horaAgora.getHour() < hf) {
                        ocupada = true;
                        cliente = r[1]; // CPF ou Nome conforme sua lógica
                        horario = hi + ":00 - " + hf + ":00";
                        break;
                    }
                }
            }
            painelCards.add(criarCard(q, ocupada, cliente, horario));
        }

        // Ajuste técnico: Força o painel a recalcular a altura para o Scroll funcionar
        int numQuadras = quadras.size();
        int linhas = (int) Math.ceil(numQuadras / 3.0); // Assume média de 3 por linha
        painelCards.setPreferredSize(new Dimension(800, linhas * 160)); 

        painelCards.revalidate();
        painelCards.repaint();
    }

    private JPanel criarCard(Quadra q, boolean ocupada, String cliente, String horario) {
        JPanel card = new JPanel(null);
        card.setPreferredSize(new Dimension(270, 130));
        card.setBackground(Color.WHITE);
        
        Color cor = ocupada ? new Color(231, 76, 60) : new Color(46, 204, 113);
        card.setBorder(BorderFactory.createLineBorder(cor, 2));

        JLabel name = new JLabel(q.getNome());
        name.setFont(new Font("Tahoma", Font.BOLD, 13));
        name.setBounds(15, 10, 150, 20);
        card.add(name);

        JLabel tag = new JLabel(ocupada ? "OCUPADA" : "LIVRE", SwingConstants.CENTER);
        tag.setOpaque(true); tag.setBackground(cor); tag.setForeground(Color.WHITE);
        tag.setFont(new Font("Tahoma", Font.BOLD, 10));
        tag.setBounds(185, 10, 70, 20);
        card.add(tag);

        if (ocupada) {
            JLabel c = new JLabel("Cliente: " + cliente); c.setBounds(15, 50, 240, 20); card.add(c);
            JLabel h = new JLabel("Hora: " + horario); h.setBounds(15, 75, 240, 20); card.add(h);
        } else {
            JLabel d = new JLabel("Disponível"); d.setForeground(cor); d.setBounds(15, 50, 240, 20); card.add(d);
        }
        return card;
    }
}