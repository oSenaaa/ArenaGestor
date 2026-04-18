package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import model.*;
import repository.*;

public class PainelAgendamento extends JPanel {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> cbCliente, cbQuadra, cbHoraInicio, cbHoraFim;
    private JTextField txtData, txtValorTotal;
    private JTable tabelaReservas;
    private DefaultTableModel modeloTabela;
    private JLabel lblStatus; // Para o rodapé "X agendamento(s) registrado(s)"

    private final String[] HORARIOS = {
        "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", 
        "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"
    };

    public PainelAgendamento() {
        this.setLayout(null);
        this.setSize(908, 490);
        this.setBackground(new Color(240, 240, 240));

        // --- CONTAINER DE ENTRADA (BOX BRANCO) ---
        JPanel panelEntrada = new JPanel(null);
        panelEntrada.setBackground(Color.WHITE);
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Novo Agendamento"));
        panelEntrada.setBounds(25, 22, 859, 180);
        this.add(panelEntrada);

        // Cliente e Botão Novo
        JLabel lblC = new JLabel("Cliente:"); lblC.setBounds(20, 30, 60, 20); panelEntrada.add(lblC);
        cbCliente = new JComboBox<>(); cbCliente.setBounds(85, 29, 210, 25); panelEntrada.add(cbCliente);
        
        JButton btnNovoCli = new JButton("+");
        btnNovoCli.setBounds(300, 29, 45, 25);
        btnNovoCli.addActionListener(e -> cadastrarClienteRapido());
        panelEntrada.add(btnNovoCli);

        // Quadra
        JLabel lblQ = new JLabel("Quadra:"); lblQ.setBounds(450, 30, 60, 20); panelEntrada.add(lblQ);
        cbQuadra = new JComboBox<>(); cbQuadra.setBounds(510, 29, 310, 25); panelEntrada.add(cbQuadra);

        // Data e Horas
        JLabel lblD = new JLabel("Data:"); lblD.setBounds(20, 65, 60, 20); panelEntrada.add(lblD);
        txtData = new JTextField("dd/mm"); txtData.setBounds(85, 64, 260, 25); panelEntrada.add(txtData);

        JLabel lblHi = new JLabel("Hora Início:"); lblHi.setBounds(415, 65, 80, 20); panelEntrada.add(lblHi);
        cbHoraInicio = new JComboBox<>(HORARIOS); cbHoraInicio.setBounds(510, 64, 310, 25); panelEntrada.add(cbHoraInicio);

        JLabel lblHf = new JLabel("Hora Fim:"); lblHf.setBounds(20, 100, 60, 20); panelEntrada.add(lblHf);
        cbHoraFim = new JComboBox<>(HORARIOS); cbHoraFim.setBounds(85, 99, 260, 25); panelEntrada.add(cbHoraFim);

        JLabel lblV = new JLabel("Valor Total:"); lblV.setBounds(415, 100, 80, 20); panelEntrada.add(lblV);
        txtValorTotal = new JTextField("R$ 0,00"); txtValorTotal.setEditable(false);
        txtValorTotal.setBounds(510, 99, 310, 25); panelEntrada.add(txtValorTotal);

        // --- BOTÕES COLORIDOS ---
        JButton btnAgendar = new JButton("Agendar");
        btnAgendar.setBackground(new Color(41, 128, 185)); btnAgendar.setForeground(Color.WHITE);
        btnAgendar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnAgendar.setBounds(530, 140, 100, 30);
        btnAgendar.addActionListener(e -> executarAgendamento());
        panelEntrada.add(btnAgendar);

        JButton btnCancelar = new JButton("Cancelar Reserva");
        btnCancelar.setBackground(new Color(231, 76, 60)); btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCancelar.setBounds(640, 140, 150, 30);
        btnCancelar.addActionListener(e -> cancelarReservaSelecionada());
        panelEntrada.add(btnCancelar);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(800, 140, 45, 30); // Compacto como no print
        panelEntrada.add(btnLimpar);

        // --- TABELA ---
        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(25, 220, 859, 210);
        this.add(scroll);

        modeloTabela = new DefaultTableModel(new Object[][] {}, 
            new String[] {"ID", "Cliente", "Quadra", "Data", "Hora Início", "Hora Fim", "Valor Total"}) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabelaReservas = new JTable(modeloTabela);
        tabelaReservas.setRowHeight(25);
        scroll.setViewportView(tabelaReservas);

        // Rodapé de Status
        lblStatus = new JLabel("0 agendamento(s) registrado(s)");
        lblStatus.setBounds(25, 435, 300, 20);
        this.add(lblStatus);

        preencherComboBoxes();
        atualizarTabela();
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        java.util.List<String[]> dados = new ReservaRepository().listarDadosTabela();
        for (String[] r : dados) {
            // Formata o valor para exibir R$
            String valor = "R$ " + r[6].replace(".", ",");
            modeloTabela.addRow(new Object[]{r[0], r[1], r[2], r[3], r[4] + ":00", r[5] + ":00", valor});
        }
        lblStatus.setText(dados.size() + " agendamento(s) registrado(s)");
    }

    private void cancelarReservaSelecionada() {
        int linha = tabelaReservas.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma reserva na tabela.");
            return;
        }
        int id = Integer.parseInt(modeloTabela.getValueAt(linha, 0).toString());
        new ReservaRepository().cancelarReserva(id);
        JOptionPane.showMessageDialog(this, "Reserva cancelada!");
        atualizarTabela();
    }

    private void executarAgendamento() {
        try {
            String cSel = (String) cbCliente.getSelectedItem();
            String qSel = (String) cbQuadra.getSelectedItem();
            if (cSel == null || qSel == null) return;

            Cliente c = new ClienteRepository().listarTodos().stream()
                .filter(cli -> cli.getCpf().equals(cSel.split(" - ")[1])).findFirst().get();
            Quadra q = new QuadraRepository().listarTodas().stream()
                .filter(qua -> qua.getId() == Integer.parseInt(qSel.split(" - ")[0])).findFirst().get();

            String dataFinal = txtData.getText() + "/" + LocalDate.now().getYear();
            int hI = Integer.parseInt(cbHoraInicio.getSelectedItem().toString().split(":")[0]);
            int hF = Integer.parseInt(cbHoraFim.getSelectedItem().toString().split(":")[0]);

            Reserva r = new Reserva(modeloTabela.getRowCount() + 1, c, q, dataFinal, hI, hF);
            new ReservaRepository().salvar(r);
            
            JOptionPane.showMessageDialog(this, "Sucesso!");
            atualizarTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void cadastrarClienteRapido() {
        String n = JOptionPane.showInputDialog("Nome:");
        String c = JOptionPane.showInputDialog("CPF:");
        String t = JOptionPane.showInputDialog("Telefone:");
        if (n != null && c != null) {
            new ClienteRepository().salvar(new Cliente(n, c, t));
            preencherComboBoxes();
        }
    }

    public void preencherComboBoxes() {
        cbCliente.removeAllItems();
        new ClienteRepository().listarTodos().forEach(c -> cbCliente.addItem(c.getNome() + " - " + c.getCpf()));
        cbQuadra.removeAllItems();
        new QuadraRepository().listarTodas().forEach(q -> cbQuadra.addItem(q.getId() + " - " + q.getNome()));
    }
}