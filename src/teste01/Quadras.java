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

public class Quadras extends JFrame {

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblArquivo = new JLabel("Arquivo");
		lblArquivo.setBounds(20, 11, 46, 14);
		contentPane.add(lblArquivo);
		
		JLabel lblEditar = new JLabel("Editar");
		lblEditar.setBounds(76, 11, 46, 14);
		contentPane.add(lblEditar);
		
		JLabel lblAjuda = new JLabel("Ajuda");
		lblAjuda.setBounds(132, 11, 46, 14);
		contentPane.add(lblAjuda);
		
		JButton btnNewButton = new JButton("Clientes");
		btnNewButton.setBounds(10, 36, 99, 23);
		contentPane.add(btnNewButton);
		
		JButton btnQuadras = new JButton("Quadras");
		btnQuadras.setBounds(119, 36, 87, 23);
		contentPane.add(btnQuadras);
		
		JButton btnAgendamentos = new JButton("Agendamentos");
		btnAgendamentos.setBounds(216, 36, 142, 23);
		contentPane.add(btnAgendamentos);
		
		JButton btnStatusDaQuadra = new JButton("Status das Quadras");
		btnStatusDaQuadra.setBounds(368, 36, 189, 23);
		contentPane.add(btnStatusDaQuadra);
		
		Panel panel = new Panel();
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 65, 908, 490);
		contentPane.add(panel);
		
		Panel panel_1 = new Panel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(223, 223, 223));
		panel_1.setBounds(25, 22, 859, 198);
		panel.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(20, 11, 46, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tipo:");
		lblNewLabel_1_1.setBounds(20, 36, 46, 14);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Valor / Hora:");
		lblNewLabel_1_2.setBounds(20, 61, 70, 14);
		panel_1.add(lblNewLabel_1_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(100, 58, 486, 20);
		panel_1.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(100, 33, 486, 20);
		panel_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(100, 8, 486, 20);
		panel_1.add(textField_2);
		
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
