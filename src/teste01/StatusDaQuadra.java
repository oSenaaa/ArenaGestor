package teste01;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class StatusDaQuadra extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatusDaQuadra frame = new StatusDaQuadra();
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
	public StatusDaQuadra() {
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
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(30, 21, 199, 70);
		panel_1.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(30, 106, 199, 70);
		panel_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(266, 21, 199, 70);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(266, 106, 199, 70);
		panel_1.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(517, 21, 199, 70);
		panel_1.add(textField_4);

	}

}
