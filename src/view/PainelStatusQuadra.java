package view;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;

// Painel responsável por exibir o status atual das quadras (Livre/Ocupada)
public class PainelStatusQuadra extends JPanel {

    private static final long serialVersionUID = 1L;

    // Construtor: Inicializa e posiciona os elementos do painel
    public PainelStatusQuadra() {
        this.setLayout(null);
        this.setSize(908, 490);
        this.setBackground(Color.LIGHT_GRAY); 
        
        JPanel panel_1 = new JPanel(); 
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(223, 223, 223));
        panel_1.setBounds(21, 22, 863, 222); 
        this.add(panel_1);
        
        JTextField textField = new JTextField();
        textField.setBounds(30, 21, 199, 70);
        panel_1.add(textField);
        
        JTextField textField_1 = new JTextField();
        textField_1.setBounds(30, 120, 199, 70);
        panel_1.add(textField_1);
        
        JTextField textField_2 = new JTextField();
        textField_2.setBounds(269, 21, 199, 70);
        panel_1.add(textField_2);
        
        JTextField textField_3 = new JTextField();
        textField_3.setBounds(269, 120, 199, 70);
        panel_1.add(textField_3);
    }
}