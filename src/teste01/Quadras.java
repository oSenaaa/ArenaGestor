package teste01;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class Quadras extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Quadras frame = new Quadras();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Quadras() {
	    // 1. A própria classe Quadras é o painel de fundo (o antigo 'panel' cinza)
	    this.setLayout(null);
	    this.setBackground(Color.LIGHT_GRAY);
	    
	    // 2. Painel interno mais claro (panel_1) que segura os campos e botões
	    JPanel panel_1 = new JPanel(); // Mudei de Panel para JPanel
	    panel_1.setLayout(null);
	    panel_1.setBackground(new Color(223, 223, 223));
	    panel_1.setBounds(25, 22, 859, 198);
	    this.add(panel_1);
	    
	    // 3. Adicionando as Labels ao panel_1
	    JLabel lblNewLabel_1 = new JLabel("Nome:");
	    lblNewLabel_1.setBounds(20, 11, 46, 14);
	    panel_1.add(lblNewLabel_1);
	    
	    JLabel lblNewLabel_1_1 = new JLabel("Tipo:");
	    lblNewLabel_1_1.setBounds(20, 36, 46, 14);
	    panel_1.add(lblNewLabel_1_1);
	    
	    JLabel lblNewLabel_1_2 = new JLabel("Valor / Hora:");
	    lblNewLabel_1_2.setBounds(20, 61, 70, 14);
	    panel_1.add(lblNewLabel_1_2);
	    
	    // 4. Adicionando os TextFields ao panel_1 (Ajustei a ordem visualmente de cima para baixo)
	    JTextField textField_2 = new JTextField(); // Referente ao Nome
	    textField_2.setColumns(10);
	    textField_2.setBounds(100, 8, 486, 20);
	    panel_1.add(textField_2);

	    JTextField textField_1 = new JTextField(); // Referente ao Tipo
	    textField_1.setColumns(10);
	    textField_1.setBounds(100, 33, 486, 20);
	    panel_1.add(textField_1);
	    
	    JTextField textField = new JTextField(); // Referente ao Valor/Hora
	    textField.setColumns(10);
	    textField.setBounds(100, 58, 486, 20);
	    panel_1.add(textField);
	    
	    // 5. Adicionando os Botões ao panel_1
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