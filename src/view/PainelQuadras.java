package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.*;
import repository.QuadraRepository;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Painel para gerenciar o CRUD (Cadastro) das Quadras
public class PainelQuadras extends JPanel {

    private static final long serialVersionUID = 1L;

    // Construtor: Monta o formulário de cadastro e os botões de ação
    public PainelQuadras() {
        this.setLayout(null);
        this.setSize(908, 490);
        this.setBackground(Color.LIGHT_GRAY);
        
        JPanel panel_1 = new JPanel(); 
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(223, 223, 223));
        panel_1.setBounds(25, 22, 859, 198);
        this.add(panel_1);
        
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 11, 46, 14);
        panel_1.add(lblNome);
        
        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(20, 36, 46, 14);
        panel_1.add(lblTipo);
        
        JLabel lblValorHora = new JLabel("Valor / Hora:");
        lblValorHora.setBounds(20, 61, 70, 14);
        panel_1.add(lblValorHora);
        
        JTextField txtNome = new JTextField(); 
        txtNome.setBounds(100, 8, 486, 20);
        panel_1.add(txtNome);

        JTextField txtTipo = new JTextField(); 
        txtTipo.setBounds(100, 33, 486, 20);
        panel_1.add(txtTipo);
        
        JTextField txtValor = new JTextField(); 
        txtValor.setBounds(100, 58, 486, 20);
        panel_1.add(txtValor);
        
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = txtNome.getText();
                    String tipo = txtTipo.getText().toLowerCase();
                    double valor = Double.parseDouble(txtValor.getText().replace(",", "."));
                    
                    Quadra novaQuadra;
                    // Define qual classe instanciar baseada no que o usuário digitou
                    if (tipo.contains("futsal")) novaQuadra = new QuadraFutsal(0, nome, valor);
                    else if (tipo.contains("tênis") || tipo.contains("tenis")) novaQuadra = new QuadraTenis(0, nome, valor);
                    else if (tipo.contains("society")) novaQuadra = new QuadraSociety(0, nome, valor);
                    else if (tipo.contains("campo")) novaQuadra = new QuadraCampo(0, nome, valor);
                    else {
                        JOptionPane.showMessageDialog(null, "Tipo inválido! Digite Futsal, Tênis, Society ou Campo.");
                        return;
                    }
                    
                    new QuadraRepository().salvar(novaQuadra);
                    JOptionPane.showMessageDialog(null, "Quadra cadastrada com sucesso!");
                    txtNome.setText(""); txtTipo.setText(""); txtValor.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar. Verifique se o valor numérico está correto.");
                }
            }
        });
        btnCadastrar.setBounds(539, 164, 104, 23);
        panel_1.add(btnCadastrar);
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Solicita o ID provisoriamente via pop-up
                String idStr = JOptionPane.showInputDialog("Digite o ID da Quadra que deseja editar:");
                if (idStr != null && !idStr.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idStr);
                        String nome = txtNome.getText();
                        double valor = Double.parseDouble(txtValor.getText().replace(",", "."));
                        
                        // Assumindo Futsal como padrão só para atualizar os dados básicos na edição provisória
                        Quadra quadraEditada = new QuadraFutsal(id, nome, valor); 
                        new QuadraRepository().editar(quadraEditada);
                        JOptionPane.showMessageDialog(null, "Quadra editada com sucesso!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao editar. Verifique os dados.");
                    }
                }
            }
        });
        btnEditar.setBounds(661, 164, 89, 23);
        panel_1.add(btnEditar);
        
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idStr = JOptionPane.showInputDialog("Digite o ID da Quadra para excluir:");
                if (idStr != null && !idStr.isEmpty()) {
                    int id = Integer.parseInt(idStr);
                    new QuadraRepository().excluir(id);
                    JOptionPane.showMessageDialog(null, "Quadra excluída!");
                }
            }
        });
        btnExcluir.setBounds(760, 164, 89, 23);
        panel_1.add(btnExcluir);
    }
}