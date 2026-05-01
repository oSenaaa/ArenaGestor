package view;
 
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import model.*;
import repository.*;
 
public class PainelAgendamento extends JPanel {
 
    private static final long serialVersionUID = 1L;
    private JComboBox<String> cbCliente, cbQuadra, cbHoraInicio, cbHoraFim;
    private JTextField txtData, txtValorTotal;
    private JTable tabelaReservas;
    private DefaultTableModel modeloTabela;
 
    public PainelAgendamento() {
        this.setLayout(new BorderLayout(0, 20));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.setBackground(new Color(240, 240, 240));
 
        JPanel panelEntrada = new JPanel(null);
        panelEntrada.setBackground(Color.WHITE);
        panelEntrada.setPreferredSize(new Dimension(0, 180));
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Novo Agendamento"));
        this.add(panelEntrada, BorderLayout.NORTH);
 
        // --- LINHA 1: CLIENTE E QUADRA ---
        JLabel lblC = new JLabel("Cliente:"); lblC.setBounds(20, 30, 60, 20); panelEntrada.add(lblC);
        cbCliente = new JComboBox<>(); cbCliente.setBounds(85, 29, 210, 25); panelEntrada.add(cbCliente);
        JButton btnNovo = new JButton("+"); btnNovo.setBounds(300, 29, 45, 25);
        btnNovo.addActionListener(e -> cadastrarClienteRapido());
        panelEntrada.add(btnNovo);
 
        JLabel lblQ = new JLabel("Quadra:"); lblQ.setBounds(450, 30, 60, 20); panelEntrada.add(lblQ);
        cbQuadra = new JComboBox<>(); cbQuadra.setBounds(510, 29, 310, 25); panelEntrada.add(cbQuadra);
 
        // --- LINHA 2: DATA E HORA INÍCIO ---
        JLabel lblD = new JLabel("Data:"); lblD.setBounds(20, 65, 60, 20); panelEntrada.add(lblD);
        txtData = new JTextField("dd/mm"); txtData.setBounds(85, 64, 260, 25); panelEntrada.add(txtData);
 
        JLabel lblInicio = new JLabel("Início:"); lblInicio.setBounds(450, 65, 60, 20); panelEntrada.add(lblInicio);
        String[] horas = {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};
        cbHoraInicio = new JComboBox<>(horas); cbHoraInicio.setBounds(510, 64, 310, 25); panelEntrada.add(cbHoraInicio);
        // --- LINHA 3: HORA FIM E VALOR TOTAL ---
        JLabel lblFim = new JLabel("Fim:"); lblFim.setBounds(20, 100, 60, 20); panelEntrada.add(lblFim);
        cbHoraFim = new JComboBox<>(horas); cbHoraFim.setBounds(85, 99, 260, 25); panelEntrada.add(cbHoraFim);
 
        JLabel lblValor = new JLabel("Total:"); lblValor.setBounds(450, 100, 60, 20); panelEntrada.add(lblValor);
        txtValorTotal = new JTextField("R$ 0,00"); txtValorTotal.setEditable(false);
        txtValorTotal.setBounds(510, 99, 310, 25); panelEntrada.add(txtValorTotal);
 
        // --- BOTÕES ---
        JButton btnAge = new JButton("Agendar");
        btnAge.setBackground(new Color(41, 128, 185)); btnAge.setForeground(Color.WHITE);
        btnAge.setOpaque(true); btnAge.setBorderPainted(false);
        btnAge.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAge.setBounds(450, 140, 110, 30);
        btnAge.addActionListener(e -> executarAgendamento());
        panelEntrada.add(btnAge);
 
        JButton btnCan = new JButton("Cancelar Reserva");
        btnCan.setBackground(new Color(231, 76, 60)); btnCan.setForeground(Color.WHITE);
        btnCan.setOpaque(true); btnCan.setBorderPainted(false);
        btnCan.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnCan.setBounds(570, 140, 150, 30);
        btnCan.addActionListener(e -> cancelar());
        panelEntrada.add(btnCan);
        // Adicionei o botão limpar para ficar igual ao seu print
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnLimpar.setBounds(730, 140, 90, 30);
        panelEntrada.add(btnLimpar);
 
        // --- TABELA ---
        JScrollPane scroll = new JScrollPane();
        modeloTabela = new DefaultTableModel(new Object[][] {}, new String[] {"ID", "Cliente", "Quadra", "Data", "Início", "Fim", "Total"}) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabelaReservas = new JTable(modeloTabela);
        tabelaReservas.setRowHeight(25);
        scroll.setViewportView(tabelaReservas);
        this.add(scroll, BorderLayout.CENTER);
 
        // Inicia a inteligência do cálculo em tempo real!
        configurarEventosDeCalculo();
        preencherComboBoxes();
        atualizarTabela();
    }
 
    // -----------------------------------------------------
    //  NOVA LÓGICA: CÁLCULO DINÂMICO
    // -----------------------------------------------------
    private void configurarEventosDeCalculo() {
        // Cria um ouvinte que chama o cálculo toda vez que algo for alterado
        ActionListener atualizador = e -> calcularValorTotal();
        cbQuadra.addActionListener(atualizador);
        cbHoraInicio.addActionListener(atualizador);
        cbHoraFim.addActionListener(atualizador);
    }
 
    private void calcularValorTotal() {
        try {
            // Verifica se as caixas não estão vazias
            if (cbQuadra.getSelectedItem() == null || cbHoraInicio.getSelectedItem() == null || cbHoraFim.getSelectedItem() == null) {
                txtValorTotal.setText("R$ 0,00");
                return;
            }
 
            int hi = Integer.parseInt(cbHoraInicio.getSelectedItem().toString().split(":")[0]);
            int hf = Integer.parseInt(cbHoraFim.getSelectedItem().toString().split(":")[0]);
 
            // Se a hora fim for antes da hora início, dá erro
            if (hf <= hi) {
                txtValorTotal.setText("Horário Inválido");
                return;
            }
 
            // Pega a quadra para saber o valor dela
            int idQuadra = Integer.parseInt(cbQuadra.getSelectedItem().toString().split(" - ")[0]);
            Quadra q = new QuadraRepository().listarTodas().stream().filter(qua -> qua.getId() == idQuadra).findFirst().orElse(null);
 
            if (q != null) {
                // Cálculo mágico (Polimorfismo aplicado!)
                double total = (hf - hi) * q.getValorHora();
                txtValorTotal.setText(String.format("R$ %.2f", total));
            }
 
        } catch (Exception ex) {
            txtValorTotal.setText("R$ 0,00");
        }
    }

 
    public void preencherComboBoxes() {
        cbCliente.removeAllItems();
        new ClienteRepository().listarTodos().forEach(c -> cbCliente.addItem(c.getNome() + " - " + c.getCpf()));
        cbQuadra.removeAllItems();
        new QuadraRepository().listarTodas().forEach(q -> cbQuadra.addItem(q.getId() + " - " + q.getNome()));
    }
 
    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        new ReservaRepository().listarDadosTabela().forEach(r -> modeloTabela.addRow(new Object[]{r[0], r[1], r[2], r[3], r[4]+":00", r[5]+":00", "R$ "+r[6]}));
    }
 
    private void cancelar() {
        int r = tabelaReservas.getSelectedRow();
        if(r >= 0) {
            new ReservaRepository().cancelarReserva(Integer.parseInt(modeloTabela.getValueAt(r, 0).toString()));
            atualizarTabela();
        }
    }
 
    private void executarAgendamento() {
        try {
            if (txtValorTotal.getText().equals("Horário Inválido")) {
                JOptionPane.showMessageDialog(this, "Ajuste os horários antes de agendar!");
                return;
            }
            Cliente c = new ClienteRepository().listarTodos().stream().filter(cli -> cli.getCpf().equals(cbCliente.getSelectedItem().toString().split(" - ")[1])).findFirst().get();
            Quadra q = new QuadraRepository().listarTodas().stream().filter(qua -> qua.getId() == Integer.parseInt(cbQuadra.getSelectedItem().toString().split(" - ")[0])).findFirst().get();
            String data = txtData.getText() + "/" + LocalDate.now().getYear();
            int hi = Integer.parseInt(cbHoraInicio.getSelectedItem().toString().split(":")[0]);
            int hf = Integer.parseInt(cbHoraFim.getSelectedItem().toString().split(":")[0]);
            // Pega o ID da última reserva na tabela e soma + 1. Se estiver vazia, o ID é 1.
            int novoId = 1;
            if (tabelaReservas.getRowCount() > 0) {
                novoId = Integer.parseInt(modeloTabela.getValueAt(tabelaReservas.getRowCount() - 1, 0).toString()) + 1;
            }
            new ReservaRepository().salvar(new Reserva(novoId, c, q, data, hi, hf));
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Agendamento realizado com sucesso!");
        } catch (Exception ex) { 
            JOptionPane.showMessageDialog(this, "Erro!"); 
        }
    }
 
    private void cadastrarClienteRapido() {
        String n = JOptionPane.showInputDialog("Nome:");
        String cp = JOptionPane.showInputDialog("CPF:");
        if (n != null && cp != null) {
            new ClienteRepository().salvar(new Cliente(n, cp, ""));
            preencherComboBoxes();
        }
    }
}