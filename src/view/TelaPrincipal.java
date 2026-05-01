package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.Cliente;
import repository.ClienteRepository;

public class TelaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNome, txtCPF, txtTelefone, txtBusca;
    private JTable tabelaClientes;
    private DefaultTableModel modeloTabelaClientes;
    private TableRowSorter<DefaultTableModel> sorterClientes;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaPrincipal frame = new TelaPrincipal();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(960, 620));
        setBounds(100, 100, 960, 620);
        
        contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(new Color(223, 223, 223));
        setContentPane(contentPane);

        // --- CABEÇALHO ---
        JPanel painelCabecalho = new JPanel(null);
        painelCabecalho.setPreferredSize(new Dimension(0, 70));
        contentPane.add(painelCabecalho, BorderLayout.NORTH);

        String[] menus = {"Arquivo", "Editar", "Ajuda"};
        int xMenu = 20;
        for (String m : menus) {
            JLabel lbl = new JLabel(m);
            lbl.setBounds(xMenu, 11, 50, 14);
            painelCabecalho.add(lbl);
            xMenu += 56;
        }

        final CardLayout cardLayout = new CardLayout();
        final JPanel painelTelas = new JPanel(cardLayout);
        contentPane.add(painelTelas, BorderLayout.CENTER);

        JButton btnClientes = new JButton("Clientes");
        btnClientes.setBounds(10, 36, 99, 23);
        btnClientes.addActionListener(e -> { cardLayout.show(painelTelas, "telaClientes"); atualizarTabelaClientes(); });
        painelCabecalho.add(btnClientes);

        JButton btnQuadras = new JButton("Quadras");
        btnQuadras.setBounds(119, 36, 87, 23);
        btnQuadras.addActionListener(e -> { cardLayout.show(painelTelas, "telaQuadras"); ((PainelQuadras) painelTelas.getComponent(1)).atualizarTabela(); });
        painelCabecalho.add(btnQuadras);

        JButton btnAgendamentos = new JButton("Agendamentos");
        btnAgendamentos.setBounds(216, 36, 142, 23);
        btnAgendamentos.addActionListener(e -> { cardLayout.show(painelTelas, "telaAgendamentos"); ((PainelAgendamento) painelTelas.getComponent(2)).preencherComboBoxes(); });
        painelCabecalho.add(btnAgendamentos);

        JButton btnStatus = new JButton("Status das Quadras");
        btnStatus.setBounds(368, 36, 189, 23);
        btnStatus.addActionListener(e -> cardLayout.show(painelTelas, "telaStatusDaQuadra"));
        painelCabecalho.add(btnStatus);
<<<<<<< HEAD
        
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon("C:\\Users\\AMD\\AppData\\Local\\Microsoft\\Windows\\INetCache\\IE\\YLCYURRC\\Design_sem_nome_(2)[1].png"));
        lblLogo.setBackground(Color.WHITE);
        lblLogo.setBounds(773, 11, 161, 61);
        painelCabecalho.add(lblLogo);
=======
>>>>>>> branch 'master' of https://github.com/oSenaaa/ArenaGestor.git

        // --- PAINEL CLIENTES ---
        JPanel painelCli = new JPanel(new BorderLayout(0, 20));
        painelCli.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JPanel formCli = new JPanel(null);
        formCli.setBackground(Color.WHITE);
        formCli.setPreferredSize(new Dimension(0, 200));
        painelCli.add(formCli, BorderLayout.NORTH);

        JLabel lblN = new JLabel("Nome:"); lblN.setBounds(40, 25, 60, 14); formCli.add(lblN);
        txtNome = new JTextField(); txtNome.setBounds(110, 22, 700, 25); formCli.add(txtNome);
        JLabel lblC = new JLabel("CPF:"); lblC.setBounds(40, 65, 60, 14); formCli.add(lblC);
        txtCPF = new JTextField(); txtCPF.setBounds(110, 62, 700, 25); formCli.add(txtCPF);
        JLabel lblT = new JLabel("Telefone:"); lblT.setBounds(40, 105, 80, 14); formCli.add(lblT);
        txtTelefone = new JTextField(); txtTelefone.setBounds(110, 102, 700, 25); formCli.add(txtTelefone);

        // ==========================================
        //  NOVA BARRA DE PESQUISA EM TEMPO REAL
        // ==========================================
        JLabel lblBusca = new JLabel("Pesquisar:"); 
        lblBusca.setBounds(40, 155, 70, 14); 
        formCli.add(lblBusca);
        
        txtBusca = new JTextField(); 
        txtBusca.setBounds(110, 150, 280, 25); 
        formCli.add(txtBusca);

        txtBusca.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filtrar(); }
            public void removeUpdate(DocumentEvent e) { filtrar(); }
            public void changedUpdate(DocumentEvent e) { filtrar(); }
            private void filtrar() {
                String texto = txtBusca.getText();
                if (texto.trim().length() == 0) {
                    sorterClientes.setRowFilter(null);
                } else {
                    sorterClientes.setRowFilter(RowFilter.regexFilter("(?i)" + texto)); // (?i) ignora maiúsculas/minúsculas
                }
            }
        });
        // ==========================================

        JButton btnCad = new JButton("Cadastrar");
        btnCad.setBackground(new Color(41, 128, 185)); btnCad.setForeground(Color.WHITE);
        btnCad.setOpaque(true); btnCad.setBorderPainted(false);
        btnCad.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnCad.setBounds(410, 150, 120, 30); 
        btnCad.addActionListener(e -> cadastrarCliente());
        formCli.add(btnCad);

        JButton btnExc = new JButton("Excluir");
        btnExc.setBackground(new Color(231, 76, 60)); btnExc.setForeground(Color.WHITE);
        btnExc.setOpaque(true); btnExc.setBorderPainted(false);
        btnExc.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnExc.setBounds(540, 150, 100, 30); 
        btnExc.addActionListener(e -> excluirCliente());
        formCli.add(btnExc);
        
        JButton btnEdi = new JButton("Editar");
        btnEdi.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEdi.setBounds(650, 150, 100, 30);
        formCli.add(btnEdi);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnLimpar.setBounds(760, 150, 100, 30);
        btnLimpar.addActionListener(e -> limparCampos());
        formCli.add(btnLimpar);

        JScrollPane scroll = new JScrollPane();
        modeloTabelaClientes = new DefaultTableModel(new Object[][] {}, new String[] {"Nome", "CPF", "Telefone"}) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabelaClientes = new JTable(modeloTabelaClientes);
        tabelaClientes.setRowHeight(25);
        
        // Ativando o Sorter na Tabela
        sorterClientes = new TableRowSorter<>(modeloTabelaClientes);
        tabelaClientes.setRowSorter(sorterClientes);
        
        tabelaClientes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Ao usar filtro, precisamos converter a linha clicada na tela para a linha real do modelo
                int viewRow = tabelaClientes.getSelectedRow();
                if(viewRow >= 0) {
                    int modelRow = tabelaClientes.convertRowIndexToModel(viewRow);
                    txtNome.setText(modeloTabelaClientes.getValueAt(modelRow, 0).toString());
                    txtCPF.setText(modeloTabelaClientes.getValueAt(modelRow, 1).toString());
                    txtTelefone.setText(modeloTabelaClientes.getValueAt(modelRow, 2).toString());
                    txtCPF.setEditable(false);
                }
            }
        });
        scroll.setViewportView(tabelaClientes);
        painelCli.add(scroll, BorderLayout.CENTER);

        painelTelas.add(new PainelStatusQuadra(), "telaStatusDaQuadra");
        painelTelas.add(new PainelQuadras(), "telaQuadras"); 
        painelTelas.add(new PainelAgendamento(), "telaAgendamentos");
        painelTelas.add(painelCli, "telaClientes");

        atualizarTabelaClientes();
    }

    private void cadastrarCliente() {
        try {
            new ClienteRepository().salvar(new Cliente(txtNome.getText(), txtCPF.getText(), txtTelefone.getText()));
            JOptionPane.showMessageDialog(this, "Sucesso!");
            limparCampos(); atualizarTabelaClientes();
        } catch (Exception ex) { JOptionPane.showMessageDialog(this, ex.getMessage()); }
    }

    private void excluirCliente() {
        new ClienteRepository().excluir(txtCPF.getText());
        limparCampos(); atualizarTabelaClientes();
    }

    private void limparCampos() { 
        txtNome.setText(""); txtCPF.setText(""); txtTelefone.setText(""); 
        txtBusca.setText(""); txtCPF.setEditable(true); 
    }

    private void atualizarTabelaClientes() {
        modeloTabelaClientes.setRowCount(0);
        new ClienteRepository().listarTodos().forEach(c -> modeloTabelaClientes.addRow(new Object[]{c.getNome(), c.getCpf(), c.getTelefone()}));
    }
}