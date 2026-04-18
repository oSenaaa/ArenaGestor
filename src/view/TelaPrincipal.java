package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Cliente;
import repository.ClienteRepository;

public class TelaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNome;
    private JTextField txtCPF;
    private JTextField txtTelefone;
    
    // Componentes da Tabela
    private JTable tabelaClientes;
    private DefaultTableModel modeloTabelaClientes;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaPrincipal frame = new TelaPrincipal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 960, 620);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(223, 223, 223));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        final CardLayout cardLayout = new CardLayout();
        final JPanel painelTelas = new JPanel(cardLayout);
        painelTelas.setBounds(10, 65, 908, 490); 
        contentPane.add(painelTelas);

        painelTelas.add(new PainelStatusQuadra(), "telaStatusDaQuadra");
        painelTelas.add(new PainelQuadras(), "telaQuadras"); 
        painelTelas.add(new PainelAgendamento(), "telaAgendamentos");
        
        //  PAINEL DE CLIENTES
        JPanel painelConteudoClientes = new JPanel();
        painelConteudoClientes.setBackground(new Color(240, 240, 240)); // Fundo moderno
        painelConteudoClientes.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE); // Fundo branco do formulário
        panel_1.setBounds(25, 22, 859, 198);
        panel_1.setLayout(null);
        painelConteudoClientes.add(panel_1);
        
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(40, 25, 60, 14);
        panel_1.add(lblNome);
        
        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(40, 65, 60, 14);
        panel_1.add(lblCpf);
        
        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(40, 105, 80, 14);
        panel_1.add(lblTelefone);
        
        txtNome = new JTextField();
        txtNome.setBounds(110, 22, 700, 25);
        panel_1.add(txtNome);
        
        txtCPF = new JTextField();
        txtCPF.setBounds(110, 62, 700, 25);
        panel_1.add(txtCPF);
        
        txtTelefone = new JTextField();
        txtTelefone.setBounds(110, 102, 700, 25);
        panel_1.add(txtTelefone);
        
        // --- BOTÃO CADASTRAR (AZUL) ---
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBackground(new Color(41, 128, 185)); 
        btnCadastrar.setForeground(Color.WHITE); 
        btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(txtNome.getText().isEmpty() || txtCPF.getText().isEmpty() || txtTelefone.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                    return;
                }
                try {
                    Cliente novoCliente = new Cliente(txtNome.getText(), txtCPF.getText(), txtTelefone.getText());
                    new ClienteRepository().salvar(novoCliente);
                    
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
                    limparCampos();
                    atualizarTabelaClientes();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnCadastrar.setBounds(460, 150, 100, 30);
        panel_1.add(btnCadastrar);
        
        // --- BOTÃO EDITAR (CINZA) ---
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBackground(new Color(220, 220, 220));
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Cliente clienteEditado = new Cliente(txtNome.getText(), txtCPF.getText(), txtTelefone.getText());
                    new ClienteRepository().editar(clienteEditado);
                    
                    JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
                    limparCampos();
                    atualizarTabelaClientes();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnEditar.setBounds(570, 150, 80, 30);
        panel_1.add(btnEditar);
        
        // --- BOTÃO EXCLUIR (VERMELHO) ---
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBackground(new Color(231, 76, 60)); 
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cpfDigitado = txtCPF.getText();
                if(cpfDigitado.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Selecione um cliente na tabela para excluir.");
                    return;
                }
                new ClienteRepository().excluir(cpfDigitado);
                JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
                limparCampos();
                atualizarTabelaClientes();
            }
        });
        btnExcluir.setBounds(660, 150, 80, 30);
        panel_1.add(btnExcluir);

        // --- BOTÃO LIMPAR (CINZA) ---
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBackground(new Color(220, 220, 220));
        btnLimpar.addActionListener(e -> limparCampos());
        btnLimpar.setBounds(750, 150, 80, 30);
        panel_1.add(btnLimpar);

        // --- TABELA DE CLIENTES ---
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 235, 859, 230); 
        painelConteudoClientes.add(scrollPane);
        
        modeloTabelaClientes = new DefaultTableModel(new Object[][] {}, new String[] {"Nome", "CPF", "Telefone"}) {
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        
        tabelaClientes = new JTable(modeloTabelaClientes);
        tabelaClientes.setRowHeight(25); // Linhas com a mesma altura da tela de Quadras
        
        tabelaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int linha = tabelaClientes.getSelectedRow();
                if (linha >= 0) {
                    txtNome.setText(modeloTabelaClientes.getValueAt(linha, 0).toString());
                    txtCPF.setText(modeloTabelaClientes.getValueAt(linha, 1).toString());
                    txtTelefone.setText(modeloTabelaClientes.getValueAt(linha, 2).toString());
                    txtCPF.setEditable(false); 
                }
            }
        });
        
        scrollPane.setViewportView(tabelaClientes);
        painelTelas.add(painelConteudoClientes, "telaClientes");

        // --- MENU E NAVEGAÇÃO ---
        JLabel lblArquivo = new JLabel("Arquivo");
        lblArquivo.setBounds(20, 11, 46, 14);
        contentPane.add(lblArquivo);
        
        JLabel lblEditar = new JLabel("Editar");
        lblEditar.setBounds(76, 11, 46, 14);
        contentPane.add(lblEditar);
        
        JLabel lblAjuda = new JLabel("Ajuda");
        lblAjuda.setBounds(132, 11, 46, 14);
        contentPane.add(lblAjuda);

        JButton btnClientes = new JButton("Clientes");
        btnClientes.addActionListener(e -> {
            cardLayout.show(painelTelas, "telaClientes");
            atualizarTabelaClientes(); 
        });
        btnClientes.setBounds(10, 36, 99, 23);
        contentPane.add(btnClientes);
        
        JButton btnQuadras = new JButton("Quadras");
        btnQuadras.addActionListener(e -> {
            cardLayout.show(painelTelas, "telaQuadras");
            ((PainelQuadras) painelTelas.getComponent(1)).atualizarTabela(); // Atualiza ao clicar
        });
        btnQuadras.setBounds(119, 36, 87, 23);
        contentPane.add(btnQuadras);
        
        JButton btnAgendamentos = new JButton("Agendamentos");
        btnAgendamentos.addActionListener(e -> {
            cardLayout.show(painelTelas, "telaAgendamentos"); 
            ((PainelAgendamento) painelTelas.getComponent(2)).preencherComboBoxes();
        });
        btnAgendamentos.setBounds(216, 36, 142, 23);
        contentPane.add(btnAgendamentos);
        
        JButton btnStatusDaQuadra = new JButton("Status das Quadras");
        btnStatusDaQuadra.addActionListener(e -> cardLayout.show(painelTelas, "telaStatusDaQuadra"));
        btnStatusDaQuadra.setBounds(368, 36, 189, 23);
        contentPane.add(btnStatusDaQuadra);

        atualizarTabelaClientes();
    }
    
    private void limparCampos() {
        txtNome.setText("");
        txtCPF.setText("");
        txtTelefone.setText("");
        txtCPF.setEditable(true); 
    }

    private void atualizarTabelaClientes() {
        modeloTabelaClientes.setNumRows(0); 
        ClienteRepository repo = new ClienteRepository();
        for (Cliente c : repo.listarTodos()) {
            modeloTabelaClientes.addRow(new Object[]{c.getNome(), c.getCpf(), c.getTelefone()});
        }
    }
}