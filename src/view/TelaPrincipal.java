package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.Cliente;
import repository.ClienteRepository;
import javax.swing.JOptionPane;

public class TelaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtTelefone;
    private JTextField txtCPF;
    private JTextField txtNome;

    // Inicializa a aplicação
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaPrincipal frame = new TelaPrincipal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Construtor: Monta a janela e o gerenciador de telas (CardLayout)
    public TelaPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 960, 620);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(223, 223, 223));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Gerenciador para alternar entre os painéis
        final CardLayout cardLayout = new CardLayout();
        final JPanel painelTelas = new JPanel(cardLayout);
        painelTelas.setBounds(10, 65, 908, 490); 
        contentPane.add(painelTelas);

        // Adiciona as instâncias das telas ao CardLayout
        painelTelas.add(new PainelStatusQuadra(), "telaStatusDaQuadra");
        painelTelas.add(new PainelQuadras(), "telaQuadras"); 
        painelTelas.add(new PainelAgendamento(), "telaAgendamentos");
        
        // Tela de Clientes (incorporada provisoriamente)
        JPanel painelConteudoClientes = new JPanel();
        painelConteudoClientes.setBackground(new Color(192, 192, 192));
        painelConteudoClientes.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(223, 223, 223));
        panel_1.setBounds(25, 22, 859, 198);
        panel_1.setLayout(null);
        painelConteudoClientes.add(panel_1);
        
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 11, 46, 14);
        panel_1.add(lblNome);
        
        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(20, 36, 46, 14);
        panel_1.add(lblCpf);
        
        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(20, 61, 56, 14);
        panel_1.add(lblTelefone);
        
        txtTelefone = new JTextField();
        txtTelefone.setBounds(86, 58, 486, 20);
        panel_1.add(txtTelefone);
        txtTelefone.setColumns(10);
        
        txtCPF = new JTextField();
        txtCPF.setColumns(10);
        txtCPF.setBounds(86, 33, 486, 20);
        panel_1.add(txtCPF);
        
        txtNome = new JTextField();
        txtNome.setColumns(10);
        txtNome.setBounds(86, 8, 486, 20);
        panel_1.add(txtNome);
        
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // 1. Pega os textos que o usuário digitou nos campos
                String nomeDigitado = txtNome.getText();
                String cpfDigitado = txtCPF.getText();
                String telefoneDigitado = txtTelefone.getText();
                
                // 2. Validação simples para não salvar vazio
                if(nomeDigitado.isEmpty() || cpfDigitado.isEmpty() || telefoneDigitado.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos antes de cadastrar!");
                    return; // Para a execução aqui se estiver vazio
                }
                
                // 3. Tenta criar o cliente e salvar. Se o CPF for inválido, cai no 'catch'
                try {
                    // O erro pode estourar exatamente nesta linha abaixo:
                    Cliente novoCliente = new Cliente(nomeDigitado, cpfDigitado, telefoneDigitado);
                    
                    // 4. Se não deu erro acima, manda o Repository salvar no arquivo .txt
                    ClienteRepository repo = new ClienteRepository();
                    repo.salvar(novoCliente);
                    
                    // 5. Limpa os campos para o próximo cadastro e avisa o usuário
                    txtNome.setText("");
                    txtCPF.setText("");
                    txtTelefone.setText("");
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
                    
                } catch (IllegalArgumentException ex) {
                    // 6. Se deu erro (CPF inválido), o código pula direto para cá e exibe a mensagem!
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnCadastrar.setBounds(539, 164, 104, 23);
        panel_1.add(btnCadastrar);
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(661, 164, 89, 23);
        panel_1.add(btnEditar);
        
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(760, 164, 89, 23);
        panel_1.add(btnExcluir);
        
        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setBounds(25, 255, 70, 14);
        painelConteudoClientes.add(lblBuscar);
        
        JTextArea textArea = new JTextArea();
        textArea.setBounds(105, 250, 157, 22);
        painelConteudoClientes.add(textArea);

        painelTelas.add(painelConteudoClientes, "telaClientes");

        // Menu Superior
        JLabel lblArquivo = new JLabel("Arquivo");
        lblArquivo.setBounds(20, 11, 46, 14);
        contentPane.add(lblArquivo);
        
        JLabel lblEditar = new JLabel("Editar");
        lblEditar.setBounds(76, 11, 46, 14);
        contentPane.add(lblEditar);
        
        JLabel lblAjuda = new JLabel("Ajuda");
        lblAjuda.setBounds(132, 11, 46, 14);
        contentPane.add(lblAjuda);

        // Botões de Navegação (Trocam o card do layout)
        JButton btnClientes = new JButton("Clientes");
        btnClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(painelTelas, "telaClientes");
            }
        });
        btnClientes.setBounds(10, 36, 99, 23);
        contentPane.add(btnClientes);
        
        JButton btnQuadras = new JButton("Quadras");
        btnQuadras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(painelTelas, "telaQuadras");
            }
        });
        btnQuadras.setBounds(119, 36, 87, 23);
        contentPane.add(btnQuadras);
        
        JButton btnAgendamentos = new JButton("Agendamentos");
        btnAgendamentos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(painelTelas, "telaAgendamentos"); 
            }
        });
        btnAgendamentos.setBounds(216, 36, 142, 23);
        contentPane.add(btnAgendamentos);
        
        JButton btnStatusDaQuadra = new JButton("Status das Quadras");
        btnStatusDaQuadra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(painelTelas, "telaStatusDaQuadra");
            }
        });
        btnStatusDaQuadra.setBounds(368, 36, 189, 23);
        contentPane.add(btnStatusDaQuadra);
    }
}