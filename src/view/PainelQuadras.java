package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;

// Painel para gerenciar o CRUD (Cadastro) das Quadras
public class PainelQuadras extends JPanel {

    private static final long serialVersionUID = 1L;

    // Construtor: Monta o formulário de cadastro e os botões de ação
    public PainelQuadras() {
        this.setLayout(null);
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
        btnCadastrar.setBounds(539, 164, 104, 23);
        panel_1.add(btnCadastrar);
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(661, 164, 89, 23);
        panel_1.add(btnEditar);
        
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(760, 164, 89, 23);
        panel_1.add(btnExcluir);
    }
}