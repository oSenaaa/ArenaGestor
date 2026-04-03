package teste01;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class Cliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTelefone;
	private JTextField txtCPF;
	private JTextField txtNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente();
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
	public Cliente() {
	    // 1. CONFIGURAÇÕES DA JANELA PRINCIPAL
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 960, 620);
	    
	    // 2. CRIA A "SALA" (contentPane)
	    contentPane = new JPanel();
	    contentPane.setBackground(new Color(223, 223, 223));
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(null);

	    // 3. CRIA OS "MÓVEIS" (CardLayout e painelTelas)
	    // Precisamos declarar essas variáveis como 'final' para que os botões lá embaixo consigam acessá-las.
	    final CardLayout cardLayout = new CardLayout();
	    final JPanel painelTelas = new JPanel(cardLayout);
	    
	    // 4. CONFIGURA O PAINEL DE TELAS E ADICIONA NO CONTENTPANE
	    painelTelas.setBounds(10, 65, 908, 490); 
	    contentPane.add(painelTelas);

	    // 5. ADICIONA AS TELAS NO BARALHO
	    // Você vai descomentar (tirar as barras //) conforme for convertendo as outras telas para JPanel
	    painelTelas.add(new StatusDaQuadra(), "telaStatusDaQuadra");
	    painelTelas.add(new Quadras(), "telaQuadras"); 
	    painelTelas.add(new Agendamento(), "telaAgendamentos");
	    
	    // IMPORTANTE: Aqui você precisa adicionar o painel que era originalmente o conteúdo da sua tela de Clientes
	    // Eu peguei o código do 'Panel panel = new Panel();' que estava solto no final e criei uma classe anônima para ele entrar no CardLayout.
	    // Para ficar organizado no futuro, o ideal é criar um arquivo 'PainelClientes.java' separado, igual as outras telas.
	    JPanel painelConteudoClientes = new JPanel();
	    painelConteudoClientes.setBackground(new Color(192, 192, 192));
	    painelConteudoClientes.setLayout(null);
	    
	    JPanel panel_1 = new JPanel(); // Mudei de Panel para JPanel
	    panel_1.setBackground(new Color(223, 223, 223));
	    panel_1.setBounds(25, 22, 859, 198);
	    painelConteudoClientes.add(panel_1);
	    panel_1.setLayout(null);
	    
	    JLabel lblNewLabel_1 = new JLabel("Nome:");
	    lblNewLabel_1.setBounds(20, 11, 46, 14);
	    panel_1.add(lblNewLabel_1);
	    
	    JLabel lblNewLabel_1_1 = new JLabel("CPF:");
	    lblNewLabel_1_1.setBounds(20, 36, 46, 14);
	    panel_1.add(lblNewLabel_1_1);
	    
	    JLabel lblNewLabel_1_2 = new JLabel("Telefone:");
	    lblNewLabel_1_2.setBounds(20, 61, 56, 14);
	    panel_1.add(lblNewLabel_1_2);
	    
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
	    
	    JLabel lblNewLabel = new JLabel("Buscar:");
	    lblNewLabel.setBounds(25, 255, 70, 14);
	    painelConteudoClientes.add(lblNewLabel);
	    
	    JTextArea textArea = new JTextArea();
	    textArea.setBounds(105, 250, 157, 22);
	    painelConteudoClientes.add(textArea);

	    // Adiciona o painel de clientes ao CardLayout como a tela principal
	    painelTelas.add(painelConteudoClientes, "telaClientes");


	    // 6. ADICIONA OS COMPONENTES FIXOS DO TOPO (Labels e Botões de navegação)
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
	    btnNewButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            cardLayout.show(painelTelas, "telaClientes");
	        }
	    });
	    btnNewButton.setBounds(10, 36, 99, 23);
	    contentPane.add(btnNewButton);
	    
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
	            cardLayout.show(painelTelas, "telaAgendamentos"); // Ajustei o nome aqui
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