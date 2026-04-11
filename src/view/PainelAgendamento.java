package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;

// Painel principal onde o fluxo de vinculação Cliente x Quadra acontece
public class PainelAgendamento extends JPanel {

    private static final long serialVersionUID = 1L;

    // Construtor: Inicializa os campos de entrada (Comboboxes e TextFields)
    public PainelAgendamento() {
        this.setLayout(null);
        this.setBackground(Color.LIGHT_GRAY);
        
        JPanel panel_1 = new JPanel(); 
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(223, 223, 223));
        panel_1.setBounds(25, 22, 859, 198);
        this.add(panel_1);
        
        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(20, 11, 46, 14);
        panel_1.add(lblCliente);
        
        JComboBox<String> cbCliente = new JComboBox<>();
        cbCliente.setBounds(86, 7, 260, 22);
        panel_1.add(cbCliente);
        
        JLabel lblData = new JLabel("Data:");
        lblData.setBounds(20, 36, 46, 14);
        panel_1.add(lblData);
        
        JTextField txtData = new JTextField();
        txtData.setBounds(86, 33, 260, 20);
        panel_1.add(txtData);
        
        JLabel lblHoraFim = new JLabel("Hora Fim:");
        lblHoraFim.setBounds(20, 61, 56, 14);
        panel_1.add(lblHoraFim);
        
        JTextField txtHoraFim = new JTextField();
        txtHoraFim.setBounds(86, 58, 260, 20);
        panel_1.add(txtHoraFim);
        
        JLabel lblQuadra = new JLabel("Quadras:");
        lblQuadra.setBounds(469, 11, 66, 14);
        panel_1.add(lblQuadra);
        
        JComboBox<String> cbQuadra = new JComboBox<>();
        cbQuadra.setBounds(535, 7, 260, 22);
        panel_1.add(cbQuadra);
        
        JLabel lblHoraInicio = new JLabel("Hora Início:");
        lblHoraInicio.setBounds(469, 36, 66, 14);
        panel_1.add(lblHoraInicio);
        
        JTextField txtHoraInicio = new JTextField();
        txtHoraInicio.setBounds(535, 33, 260, 20);
        panel_1.add(txtHoraInicio);
        
        JLabel lblValorTotal = new JLabel("Valor Total:");
        lblValorTotal.setBounds(469, 61, 65, 14);
        panel_1.add(lblValorTotal);
        
        JTextField txtValorTotal = new JTextField();
        txtValorTotal.setBounds(535, 58, 260, 20);
        panel_1.add(txtValorTotal);
        
        JButton btnAgendar = new JButton("Agendar");
        btnAgendar.setBounds(479, 164, 104, 23);
        panel_1.add(btnAgendar);
        
        JButton btnCancelarReserva = new JButton("Cancelar Reserva");
        btnCancelarReserva.setBounds(593, 164, 157, 23);
        panel_1.add(btnCancelarReserva);
        
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(760, 164, 89, 23);
        panel_1.add(btnExcluir);
    }
}