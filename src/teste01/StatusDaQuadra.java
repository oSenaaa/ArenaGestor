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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StatusDaQuadra extends JPanel {

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
	    // 1. O próprio StatusDaQuadra já é o painel, então configuramos ele direto
	    this.setLayout(null);
	    this.setBackground(Color.LIGHT_GRAY); // Aquele fundo cinza que você tinha no 'panel'
	    
	    // 2. O painel interno mais claro que segura os TextFields
	    JPanel panel_1 = new JPanel(); // Lembre-se de usar JPanel e não Panel
	    panel_1.setLayout(null);
	    panel_1.setBackground(new Color(223, 223, 223));
	    panel_1.setBounds(21, 22, 863, 222); 
	    this.add(panel_1);
	    
	    // 3. Os seus TextFields
	    JTextField textField = new JTextField();
	    textField.setColumns(10);
	    textField.setBounds(30, 21, 199, 70);
	    panel_1.add(textField);
	    
	    JTextField textField_1 = new JTextField();
	    textField_1.setColumns(10);
	    textField_1.setBounds(30, 120, 199, 70);
	    panel_1.add(textField_1);
	    
	    JTextField textField_2 = new JTextField();
	    textField_2.setColumns(10);
	    textField_2.setBounds(269, 21, 199, 70);
	    panel_1.add(textField_2);
	    
	    JTextField textField_3 = new JTextField();
	    textField_3.setColumns(10);
	    textField_3.setBounds(269, 120, 199, 70);
	    panel_1.add(textField_3);
	}
}