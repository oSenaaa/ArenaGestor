package view;
 
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.*;
import repository.QuadraRepository;
 
public class PainelQuadras extends JPanel {
 
    private static final long serialVersionUID = 1L;
    private JTextField txtNome, txtValor, txtID, txtBusca;
    private JComboBox<String> cbTipo;
    private JTable tabelaQuadras;
    private DefaultTableModel modeloTabela;
    private TableRowSorter<DefaultTableModel> sorterQuadras;
 
    public PainelQuadras() {
        this.setLayout(new BorderLayout(0, 20));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.setBackground(new Color(240, 240, 240));
 
        JPanel panelForm = new JPanel(null);
        panelForm.setBackground(Color.WHITE);
        panelForm.setPreferredSize(new Dimension(0, 200));
        this.add(panelForm, BorderLayout.NORTH);
 
        JLabel lblN = new JLabel("Nome:"); lblN.setBounds(40, 25, 60, 14); panelForm.add(lblN);
        txtNome = new JTextField(); txtNome.setBounds(110, 22, 700, 25); panelForm.add(txtNome);
 
        JLabel lblT = new JLabel("Tipo:"); lblT.setBounds(40, 65, 60, 14); panelForm.add(lblT);
        cbTipo = new JComboBox<>(new String[]{"Futsal", "Tênis", "Society", "Campo", "Vôlei"});
        cbTipo.setBounds(110, 62, 700, 25); panelForm.add(cbTipo);
 
        JLabel lblV = new JLabel("Valor/Hora:"); lblV.setBounds(20, 105, 80, 14); panelForm.add(lblV);
        txtValor = new JTextField("0.00"); txtValor.setBounds(110, 102, 700, 25); panelForm.add(txtValor);
 
        txtID = new JTextField(); txtID.setVisible(false); panelForm.add(txtID);
 
        // ==========================================
        //  BARRA DE PESQUISA DE QUADRAS
        // ==========================================
        JLabel lblBusca = new JLabel("Pesquisar:");
        lblBusca.setBounds(40, 155, 70, 14);
        panelForm.add(lblBusca);
        
        txtBusca = new JTextField();
        txtBusca.setBounds(110, 150, 280, 25);
        panelForm.add(txtBusca);
 
        txtBusca.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filtrar(); }
            public void removeUpdate(DocumentEvent e) { filtrar(); }
            public void changedUpdate(DocumentEvent e) { filtrar(); }
            private void filtrar() {
                String texto = txtBusca.getText();
                if (texto.trim().length() == 0) {
                    sorterQuadras.setRowFilter(null);
                } else {
                    sorterQuadras.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
                }
            }
        });
        // ==========================================
 
        JButton btnCad = new JButton("Cadastrar");
        btnCad.setBackground(new Color(41, 128, 185)); btnCad.setForeground(Color.WHITE);
        btnCad.setOpaque(true); btnCad.setBorderPainted(false);
        btnCad.setBounds(410, 150, 120, 30);
        btnCad.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnCad.addActionListener(e -> cadastrar());
        panelForm.add(btnCad);
 
        JButton btnExc = new JButton("Excluir");
        btnExc.setBackground(new Color(231, 76, 60)); btnExc.setForeground(Color.WHITE);
        btnExc.setOpaque(true); btnExc.setBorderPainted(false);
        btnExc.setBounds(540, 150, 100, 30);
        btnExc.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnExc.addActionListener(e -> excluir());
        panelForm.add(btnExc);
 
        JScrollPane scroll = new JScrollPane();
        modeloTabela = new DefaultTableModel(new Object[][] {}, new String[] {"ID", "Nome", "Tipo", "Valor/Hora"}) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabelaQuadras = new JTable(modeloTabela);
        tabelaQuadras.setRowHeight(25);
        
        // Ativando o Sorter
        sorterQuadras = new TableRowSorter<>(modeloTabela);
        tabelaQuadras.setRowSorter(sorterQuadras);
        
        tabelaQuadras.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int viewRow = tabelaQuadras.getSelectedRow();
                if(viewRow >= 0) {
                    // Conversão de índice para não errar a linha quando houver filtro ativo
                    int modelRow = tabelaQuadras.convertRowIndexToModel(viewRow);
                    txtID.setText(modeloTabela.getValueAt(modelRow, 0).toString());
                    txtNome.setText(modeloTabela.getValueAt(modelRow, 1).toString());
                    cbTipo.setSelectedItem(modeloTabela.getValueAt(modelRow, 2).toString());
                    txtValor.setText(modeloTabela.getValueAt(modelRow, 3).toString().replace("R$ ", "").replace(",", "."));
                }
            }
        });
        scroll.setViewportView(tabelaQuadras);
        this.add(scroll, BorderLayout.CENTER);
 
        atualizarTabela();
    }
 
    private void cadastrar() {
        try {
            double v = Double.parseDouble(txtValor.getText().replace(",", "."));
            String tipoSelecionado = cbTipo.getSelectedItem().toString();
            Quadra q = null;
            
            int novoId = modeloTabela.getRowCount() > 0 ? Integer.parseInt(modeloTabela.getValueAt(modeloTabela.getRowCount() - 1, 0).toString()) + 1 : 1;
 
            if (tipoSelecionado.equals("Futsal")) q = new QuadraFutsal(novoId, txtNome.getText(), v);
            else if (tipoSelecionado.equals("Tênis")) q = new QuadraTenis(novoId, txtNome.getText(), v);
            else if (tipoSelecionado.equals("Society")) q = new QuadraSociety(novoId, txtNome.getText(), v);
            else if (tipoSelecionado.equals("Campo")) q = new QuadraCampo(novoId, txtNome.getText(), v);
            else q = new QuadraFutsal(novoId, txtNome.getText(), v);
 
            new QuadraRepository().salvar(q);
            atualizarTabela();
            limpar();
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Valor inválido."); }
    }
 
    private void excluir() {
        if(!txtID.getText().isEmpty()) {
            new QuadraRepository().excluir(Integer.parseInt(txtID.getText()));
            atualizarTabela(); limpar();
        }
    }
 
    private void limpar() {
        txtNome.setText(""); txtValor.setText("0.00"); txtID.setText(""); txtBusca.setText("");
    }
 
    public void atualizarTabela() {
        modeloTabela.setRowCount(0);
        new QuadraRepository().listarTodas().forEach(q -> modeloTabela.addRow(new Object[]{q.getId(), q.getNome(), q.getClass().getSimpleName().replace("Quadra", ""), String.format("R$ %.2f", q.getValorHora())}));
    }
}