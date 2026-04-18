package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.*;
import repository.*;

public class PainelAgendamento extends JPanel {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> cbCliente;
    private JComboBox<String> cbQuadra;
    private JTextField txtData;
    private JComboBox<String> cbHoraInicio;
    private JComboBox<String> cbHoraFim;
    private JTextField txtValorTotal;

    private final String[] HORARIOS_FUNCIONAMENTO = {
        "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", 
        "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", 
        "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"
    };

    public PainelAgendamento() {
        this.setLayout(null);
        this.setSize(908, 490);
        this.setBackground(Color.LIGHT_GRAY);
        
        JPanel panel_1 = new JPanel(); 
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(223, 223, 223));
        panel_1.setBounds(25, 22, 859, 198);
        this.add(panel_1);
        
        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(20, 11, 46, 14);
        panel_1.add(lblCliente);
        
        cbCliente = new JComboBox<>();
        cbCliente.setBounds(86, 7, 210, 22); // Diminuí um pouco para caber o botão "+"
        panel_1.add(cbCliente);

        // BOTÃO ATALHO: NOVO CLIENTE
        JButton btnNovoCliente = new JButton("+");
        btnNovoCliente.setToolTipText("Cadastrar novo cliente rapidamente");
        btnNovoCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarClienteRapido();
            }
        });
        btnNovoCliente.setBounds(301, 7, 45, 22);
        panel_1.add(btnNovoCliente);
        
        JLabel lblData = new JLabel("Data:");
        lblData.setBounds(20, 36, 46, 14);
        panel_1.add(lblData);
        
        txtData = new JTextField("dd/mm");
        txtData.setBounds(86, 33, 260, 20);
        panel_1.add(txtData);
        
        JLabel lblHoraFim = new JLabel("Hora Fim:");
        lblHoraFim.setBounds(20, 61, 56, 14);
        panel_1.add(lblHoraFim);
        
        cbHoraFim = new JComboBox<>(HORARIOS_FUNCIONAMENTO);
        cbHoraFim.setBounds(86, 58, 260, 22);
        panel_1.add(cbHoraFim);
        
        JLabel lblQuadra = new JLabel("Quadras:");
        lblQuadra.setBounds(469, 11, 66, 14);
        panel_1.add(lblQuadra);
        
        cbQuadra = new JComboBox<>();
        cbQuadra.setBounds(535, 7, 260, 22);
        panel_1.add(cbQuadra);
        
        JLabel lblHoraInicio = new JLabel("Hora Início:");
        lblHoraInicio.setBounds(469, 36, 66, 14);
        panel_1.add(lblHoraInicio);
        
        cbHoraInicio = new JComboBox<>(HORARIOS_FUNCIONAMENTO);
        cbHoraInicio.setBounds(535, 33, 260, 22);
        panel_1.add(cbHoraInicio);
        
        JLabel lblValorTotal = new JLabel("Valor Total:");
        lblValorTotal.setBounds(469, 61, 65, 14);
        panel_1.add(lblValorTotal);
        
        txtValorTotal = new JTextField();
        txtValorTotal.setEditable(false);
        txtValorTotal.setBounds(535, 58, 260, 20);
        panel_1.add(txtValorTotal);
        
        JButton btnAgendar = new JButton("Agendar");
        btnAgendar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                executarAgendamento();
            }
        });
        btnAgendar.setBounds(479, 164, 104, 23);
        panel_1.add(btnAgendar);

        preencherComboBoxes();
    }

    // MÉTODO PARA CADASTRO RÁPIDO VIA POPUP
    private void cadastrarClienteRapido() {
        try {
            String nome = JOptionPane.showInputDialog(this, "Nome do Cliente:");
            if (nome == null || nome.isEmpty()) return;

            String cpf = JOptionPane.showInputDialog(this, "CPF (11 dígitos):");
            if (cpf == null || cpf.isEmpty()) return;

            String telefone = JOptionPane.showInputDialog(this, "Telefone:");
            if (telefone == null || telefone.isEmpty()) return;

            // Cria e salva usando a lógica que já temos
            Cliente novo = new Cliente(nome, cpf, telefone);
            new ClienteRepository().salvar(novo);

            JOptionPane.showMessageDialog(this, "Cliente cadastrado e pronto para agendar!");
            
            // Atualiza a lista e seleciona o novo cliente automaticamente
            preencherComboBoxes();
            cbCliente.setSelectedItem(novo.getNome() + " - " + novo.getCpf());

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente rápido.");
        }
    }

    public void preencherComboBoxes() {
        cbCliente.removeAllItems();
        for (Cliente c : new ClienteRepository().listarTodos()) {
            cbCliente.addItem(c.getNome() + " - " + c.getCpf());
        }

        cbQuadra.removeAllItems();
        for (Quadra q : new QuadraRepository().listarTodas()) {
            cbQuadra.addItem(q.getId() + " - " + q.getNome());
        }
    }

    private void executarAgendamento() {
        try {
            String clienteSel = (String) cbCliente.getSelectedItem();
            String quadraSel = (String) cbQuadra.getSelectedItem();
            
            if (clienteSel == null || quadraSel == null) {
                JOptionPane.showMessageDialog(this, "Selecione um cliente e uma quadra!");
                return;
            }

            String cpf = clienteSel.split(" - ")[1];
            int idQuadra = Integer.parseInt(quadraSel.split(" - ")[0]);

            Cliente cliente = new ClienteRepository().listarTodos().stream()
                    .filter(c -> c.getCpf().equals(cpf)).findFirst().orElse(null);
            
            Quadra quadra = new QuadraRepository().listarTodas().stream()
                    .filter(q -> q.getId() == idQuadra).findFirst().orElse(null);

            String dataDigitada = txtData.getText();
            int anoAtual = LocalDate.now().getYear();
            String dataFinal = dataDigitada + "/" + anoAtual;

            int hInicio = Integer.parseInt(((String) cbHoraInicio.getSelectedItem()).split(":")[0]);
            int hFim = Integer.parseInt(((String) cbHoraFim.getSelectedItem()).split(":")[0]);
            
            Reserva novaReserva = new Reserva(0, cliente, quadra, dataFinal, hInicio, hFim);
            
            txtValorTotal.setText(String.format("R$ %.2f", novaReserva.calcularValorTotal()));
            new ReservaRepository().salvar(novaReserva);
            
            JOptionPane.showMessageDialog(this, "Agendamento realizado para " + dataFinal + "!");

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Regra de Negócio", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro nos dados. Use dd/mm para data.");
        }
    }
}