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
import javax.swing.JComboBox;

public class Agendamento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agendamento frame = new Agendamento();
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
	public Agendamento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblArquivo = new JLabel("Arquivo");
		lblArquivo.setBounds(20, 27, 46, 14);
		contentPane.add(lblArquivo);
		
		JLabel lblEditar = new JLabel("Editar");
		lblEditar.setBounds(76, 27, 46, 14);
		contentPane.add(lblEditar);
		
		JLabel lblAjuda = new JLabel("Ajuda");
		lblAjuda.setBounds(132, 27, 46, 14);
		contentPane.add(lblAjuda);
		
		JButton btnNewButton = new JButton("Clientes");
		btnNewButton.setBounds(10, 52, 99, 23);
		contentPane.add(btnNewButton);
		
		JButton btnQuadras = new JButton("Quadras");
		btnQuadras.setBounds(119, 52, 87, 23);
		contentPane.add(btnQuadras);
		
		JButton btnAgendamentos = new JButton("Agendamentos");
		btnAgendamentos.setBounds(216, 52, 142, 23);
		contentPane.add(btnAgendamentos);
		
		JButton btnStatusDaQuadra = new JButton("Status das Quadras");
		btnStatusDaQuadra.setBounds(368, 52, 189, 23);
		contentPane.add(btnStatusDaQuadra);
		
		Panel panel = new Panel();
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 81, 908, 490);
		contentPane.add(panel);
		
		Panel panel_1 = new Panel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(223, 223, 223));
		panel_1.setBounds(25, 22, 859, 198);
		panel.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Cliente:");
		lblNewLabel_1.setBounds(20, 11, 46, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Data:");
		lblNewLabel_1_1.setBounds(20, 36, 46, 14);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Hora Fim:");
		lblNewLabel_1_2.setBounds(20, 61, 56, 14);
		panel_1.add(lblNewLabel_1_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(86, 58, 260, 20);
		panel_1.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(86, 33, 260, 20);
		panel_1.add(textField_1);
		
		JButton btnAgendar = new JButton("Agendar");
		btnAgendar.setBounds(479, 164, 104, 23);
		panel_1.add(btnAgendar);
		
		JButton btnCancelarReserva = new JButton("Cancelar Reserva");
		btnCancelarReserva.setBounds(593, 164, 157, 23);
		panel_1.add(btnCancelarReserva);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(760, 164, 89, 23);
		panel_1.add(btnExcluir);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(86, 7, 260, 22);
		panel_1.add(comboBox);
		
		JLabel lblNewLabel_1_3 = new JLabel("Quadras:");
		lblNewLabel_1_3.setBounds(469, 11, 66, 14);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Hora Início:");
		lblNewLabel_1_1_1.setBounds(469, 36, 66, 14);
		panel_1.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Valor Total:");
		lblNewLabel_1_2_1.setBounds(469, 61, 65, 14);
		panel_1.add(lblNewLabel_1_2_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(535, 58, 260, 20);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(535, 33, 260, 20);
		panel_1.add(textField_3);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(535, 7, 260, 22);
		panel_1.add(comboBox_1);

	}
}
