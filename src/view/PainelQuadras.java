package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.*;
import repository.QuadraRepository;

public class PainelQuadras extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtNome;
    private JComboBox<String> cbTipo; // Agora é um ComboBox
    private JTextField txtValor;
    private JTextField txtID; 
    
    private JTable tabelaQuadras;
    private DefaultTableModel modeloTabela;

    public PainelQuadras() {
        this.setLayout(null);
        this.setSize(908, 490);
        this.setBackground(new Color(240, 240, 240)); // Fundo mais claro e moderno
        
        JPanel panel_1 = new JPanel(); 
        panel_1.setLayout(null);
        panel_1.setBackground(Color.WHITE); // Fundo branco para o formulário
        panel_1.setBounds(25, 22, 859, 198);
        this.add(panel_1);
        
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(40, 25, 60, 14);
        panel_1.add(lblNome);
        
        txtNome = new JTextField("Ex: Quadra 01"); 
        txtNome.setBounds(110, 22, 700, 25);
        panel_1.add(txtNome);
        
        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(40, 65, 60, 14);
        panel_1.add(lblTipo);
        
        // Substituído por ComboBox para evitar erros
        String[] tiposQuadra = {"Futsal", "Tênis", "Society", "Campo", "Vôlei"};
        cbTipo = new JComboBox<>(tiposQuadra);
        cbTipo.setBounds(110, 62, 700, 25);
        panel_1.add(cbTipo);
        
        JLabel lblValorHora = new JLabel("Valor/Hora:");
        lblValorHora.setBounds(20, 105, 80, 14);
        panel_1.add(lblValorHora);
        
        txtValor = new JTextField("0.00"); 
        txtValor.setBounds(110, 102, 700, 25);
        panel_1.add(txtValor);
        
        txtID = new JTextField();
        txtID.setVisible(false);
        panel_1.add(txtID);
        
        // --- BOTÕES COLORIDOS ---
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBackground(new Color(41, 128, 185)); // Azul
        btnCadastrar.setForeground(Color.WHITE); // Letra branca
        btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCadastrar.addActionListener(e -> {
            try {
                String nome = txtNome.getText();
                String tipoStr = cbTipo.getSelectedItem().toString().toLowerCase();
                double valor = Double.parseDouble(txtValor.getText().replace(",", "."));
                
                Quadra novaQuadra;
                if (tipoStr.contains("futsal")) novaQuadra = new QuadraFutsal(0, nome, valor);
                else if (tipoStr.contains("tênis") || tipoStr.contains("tenis")) novaQuadra = new QuadraTenis(0, nome, valor);
                else if (tipoStr.contains("society")) novaQuadra = new QuadraSociety(0, nome, valor);
                else novaQuadra = new QuadraCampo(0, nome, valor); // Padrão Campo/Vôlei

                new QuadraRepository().salvar(novaQuadra);
                atualizarTabela();
                limparCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Verifique o valor numérico.");
            }
        });
        btnCadastrar.setBounds(460, 150, 100, 30);
        panel_1.add(btnCadastrar);
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBackground(new Color(220, 220, 220)); // Cinza claro
        btnEditar.addActionListener(e -> {
            if (txtID.getText().isEmpty()) return;
            try {
                int id = Integer.parseInt(txtID.getText());
                // Usamos o padrão Futsal apenas para o transporte de dados provisório
                Quadra qEditada = new QuadraFutsal(id, txtNome.getText(), Double.parseDouble(txtValor.getText().replace(",", ".")));
                new QuadraRepository().editar(qEditada);
                atualizarTabela();
                limparCampos();
            } catch (Exception ex) { }
        });
        btnEditar.setBounds(570, 150, 80, 30);
        panel_1.add(btnEditar);
        
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBackground(new Color(231, 76, 60)); // Vermelho
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnExcluir.addActionListener(e -> {
            if (!txtID.getText().isEmpty()) {
                new QuadraRepository().excluir(Integer.parseInt(txtID.getText()));
                atualizarTabela();
                limparCampos();
            }
        });
        btnExcluir.setBounds(660, 150, 80, 30);
        panel_1.add(btnExcluir);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBackground(new Color(220, 220, 220));
        btnLimpar.addActionListener(e -> limparCampos());
        btnLimpar.setBounds(750, 150, 80, 30);
        panel_1.add(btnLimpar);

        // --- TABELA ---
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 235, 859, 230);
        this.add(scrollPane);

        modeloTabela = new DefaultTableModel(new Object[][] {}, new String[] {"ID", "Nome", "Tipo", "Valor/Hora"}) {
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tabelaQuadras = new JTable(modeloTabela);
        tabelaQuadras.setRowHeight(25); // Linhas mais altas (mais bonito)
        
        tabelaQuadras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tabelaQuadras.getSelectedRow();
                if (linha >= 0) {
                    txtID.setText(modeloTabela.getValueAt(linha, 0).toString());
                    txtNome.setText(modeloTabela.getValueAt(linha, 1).toString());
                    cbTipo.setSelectedItem(modeloTabela.getValueAt(linha, 2).toString());
                    
                    // Remove o "R$ " para colocar no campo de texto limpo
                    String valor = modeloTabela.getValueAt(linha, 3).toString().replace("R$ ", "").replace(",", ".");
                    txtValor.setText(valor);
                }
            }
        });
        scrollPane.setViewportView(tabelaQuadras);
        atualizarTabela();
    }

    private void limparCampos() {
        txtNome.setText("");
        txtValor.setText("0.00");
        txtID.setText("");
        cbTipo.setSelectedIndex(0);
    }

    public void atualizarTabela() {
        modeloTabela.setRowCount(0);
        for (Quadra q : new QuadraRepository().listarTodas()) {
            // FORMATAÇÃO DO VALOR AQUI!
            String valorFormatado = String.format("R$ %.2f", q.getValorHora());
            // Para mostrar bonito sem a palavra "Quadra" na frente (ex: Futsal em vez de QuadraFutsal)
            String tipoLimpo = q.getClass().getSimpleName().replace("Quadra", "");
            
            modeloTabela.addRow(new Object[]{q.getId(), q.getNome(), tipoLimpo, valorFormatado});
        }
    }
}