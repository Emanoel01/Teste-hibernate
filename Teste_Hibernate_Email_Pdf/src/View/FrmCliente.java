package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.ClienteDAO;
import DAO.ClienteOperacaoDAO;
import DAO.ListaChamadaDAO;
import DAO.OperacaoDAO;
import Email.EnvioExtrato;
import controller.ClienteExtratoControler;
import controller.ExtratoController;
import extrato.GeradorExtrato;
import model.Cliente;
import model.ClienteOperacao;
import model.ListaChamada;
import model.Operacao;

import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.Component;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollBar;

public class FrmCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable tblClientesTotal;
	private HashMap<Integer, Cliente> clientesSelecionados;
	private int cont;
	private List<ListaChamada> listaChamadaList;
	
	static ClienteDAO clienteDao = new ClienteDAO();
	private JTable tblClientesSelecionados;
	private JTextField txtData;
	private JTable tblListChamadaOperacao;
	private JTextField txtNomeOperacao;
	private JTextField txtDataOperacao;
	private JTextField txtQntdPacotes;
	private JTextField txtDiaria;
	private List<String> listDatas;
	int contIdCliente = 0;
	
	public ListaChamadaDAO listaDao = new ListaChamadaDAO();
	private JTable tblOperacaoExtrato;
	private JTable tblClienteExtrato;
	private JTextField txtNomeExtrato;
	

	
	
	public static void setValuesOnModelTable(DefaultTableModel model, List<Cliente> clienteList) {
		for(int i=0;i<clienteList.size();i++) {
			Cliente cliente = clienteList.get(i);
			model.addRow(new Object[0]);
			model.setValueAt(cliente.getId(), i, 0);
			model.setValueAt(cliente.getNome(), i, 1);
			model.setValueAt(cliente.getEmail(), i, 2);
		}
	}
	
	/**
	 * Create the frame.
	 */
	
	
	
	@SuppressWarnings("unchecked")
	public FrmCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 764, 450);
		contentPane.add(tabbedPane);
		
		JPanel painelCliente = new JPanel();
		tabbedPane.addTab("Cadastro Cliente", null, painelCliente, null);
		painelCliente.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNome.setBounds(97, 63, 66, 28);
		painelCliente.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(173, 69, 265, 20);
		painelCliente.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CPF\r\n");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(97, 102, 66, 28);
		painelCliente.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(173, 108, 265, 20);
		painelCliente.add(textField);
		textField.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTelefone.setBounds(97, 141, 66, 28);
		painelCliente.add(lblTelefone);
		
		textField_1 = new JTextField();
		textField_1.setBounds(173, 147, 265, 20);
		painelCliente.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		lblEmail.setBounds(97, 180, 66, 28);
		painelCliente.add(lblEmail);
		
		textField_2 = new JTextField();
		textField_2.setBounds(173, 186, 265, 20);
		painelCliente.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnCadastrarCliente = new JButton("Cadastrar\r\n");
		btnCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Cliente c = new Cliente();
				
				c.setNome(txtNome.getText());
				
				
			}
		});
		btnCadastrarCliente.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnCadastrarCliente.setBounds(97, 236, 341, 37);
		painelCliente.add(btnCadastrarCliente);
		
		JLabel lblCadastroDeCliente = new JLabel("Cadastro de Cliente\r\n");
		lblCadastroDeCliente.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblCadastroDeCliente.setBounds(101, 11, 337, 41);
		painelCliente.add(lblCadastroDeCliente);
		
		JPanel painelOperacao = new JPanel();
		tabbedPane.addTab("Lista de Chamada", null, painelOperacao, null);
		painelOperacao.setLayout(null);
		
		JLabel lblListaDeChamada = new JLabel("\t\t\t\t\t\t\t\tLista de Chamada");
		lblListaDeChamada.setBounds(249, 11, 203, 47);
		lblListaDeChamada.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblListaDeChamada.setHorizontalTextPosition((int) JFrame.CENTER_ALIGNMENT);
		painelOperacao.add(lblListaDeChamada);
		
		JScrollPane spTotalCliente = new JScrollPane();
		spTotalCliente.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		spTotalCliente.setBounds(10, 104, 247, 307);
		painelOperacao.add(spTotalCliente);
		
		
		//TESTANDO
		
		tblClientesTotal = new JTable();
		spTotalCliente.setViewportView(tblClientesTotal);
		
		tblClientesTotal.setDefaultEditor(Object.class, null);
		
		HashMap<Integer,Cliente> clienteTotal = clienteDao.getAllClientes();
		
		
		DefaultTableModel model  = new DefaultTableModel() {
			public java.lang.Class<?> getColumnClass(int columnIndex) {
				switch(columnIndex) {
					case 3:
						return Boolean.class;
					case 2:
						return String.class;
					case 1: 
						return String.class;
					case 0:
						return Integer.class;

						default: return String.class;
				}
				
			}
		};
		
		model.addColumn("Id");
		model.addColumn("Nome");
		model.addColumn("email");
		model.addColumn("selecionar");
		
		tblClientesTotal.setModel(model);
		
		for(int i=0;i<clienteTotal.size();i++) {
			Cliente cliente = clienteTotal.get(i+1);
			
			model.addRow(new Object[0]);
			model.setValueAt(cliente.getId(), i, 0);
			model.setValueAt(cliente.getNome(), i, 1);
			model.setValueAt(cliente.getEmail(), i, 2);
			model.setValueAt(false, i, 3);
		}
		
		clientesSelecionados = new HashMap<>();
		
		tblClientesTotal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(e.getClickCount()==1) {
					int linha = tblClientesTotal.getSelectedRow();
					
					if(!(Boolean)model.getValueAt(linha,3)) {
						model.setValueAt(true, linha, 3);
						
						Cliente cSelecionado = clienteTotal.get(model.getValueAt(linha, 0));
						
						clientesSelecionados.put(cSelecionado.getId(), cSelecionado);
						
						
					}else {
						model.setValueAt(false, linha, 3);
						
						clientesSelecionados.remove(model.getValueAt(linha, 0));
					}
					
				}
			}
		});

		//Tabela cliente selecionados
		
		JScrollPane spClienteSelecionados = new JScrollPane();
		spClienteSelecionados.setBounds(333, 104, 247, 307);
		spClienteSelecionados.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelOperacao.add(spClienteSelecionados);
		
		tblClientesSelecionados = new JTable();
		spClienteSelecionados.setViewportView(tblClientesSelecionados);
		
		DefaultTableModel modelClienteSele = new DefaultTableModel() {
			public java.lang.Class<?> getColumnClass(int columnIndex) {
				switch(columnIndex) {
					case 0:
						return Integer.class;
					case 1:
						return String.class;
					case 2:
						return String.class;
					default:
						return String.class;
				}
			}; {};
		};
		
		modelClienteSele.addColumn("Id");
		modelClienteSele.addColumn("Nome");
		modelClienteSele.addColumn("Email");
		
		tblClientesSelecionados.setModel(modelClienteSele);
		
		JButton btnSelecionar = new JButton("->");
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cont = 0;
				if(clientesSelecionados!=null) {
					for(Cliente c : clientesSelecionados.values()) {
						modelClienteSele.addRow(new Object[0]);
						modelClienteSele.setValueAt(c.getId(), cont, 0);
						modelClienteSele.setValueAt(c.getNome(), cont, 1);
						modelClienteSele.setValueAt(c.getEmail(), cont, 2);
						cont++;
					}
				}
				
				
				System.out.println(clientesSelecionados.values());
			}
		});
		
		btnSelecionar.setFont(new Font("Arial Black", Font.BOLD, 16));
		btnSelecionar.setBounds(267, 241, 56, 40);
		painelOperacao.add(btnSelecionar);
		
		JLabel lblData = new JLabel("DATA");
		lblData.setBounds(648, 107, 73, 14);
		painelOperacao.add(lblData);
		
		txtData = new JTextField();
		txtData.setBounds(635, 132, 86, 20);
		painelOperacao.add(txtData);
		txtData.setColumns(10);
		
		JButton btnSalvarLista = new JButton("SALVAR");
		btnSalvarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ListaChamadaDAO dao = new ListaChamadaDAO();
				
				String[] arrayData = txtData.getText().split("/");
				
				int dia = Integer.parseInt(arrayData[0]);
				int mes = Integer.parseInt(arrayData[1]);
				int ano = Integer.parseInt(arrayData[2]);
				
				List<ListaChamada> listaChamadaList = new ArrayList<>();
				
				if(clientesSelecionados!=null) {
					for(Cliente cliente : clientesSelecionados.values()) {
						ListaChamada listaChamada = new ListaChamada();
						
						listaChamada.setAno(ano);
						listaChamada.setMes(mes);
						listaChamada.setDia(dia);
						listaChamada.setCliente(cliente);
						
						listaChamadaList.add(listaChamada);
					}
				}
				
				Boolean cadastrado = dao.saveListaChamada(listaChamadaList);
				
				if(cadastrado) {
					txtData.setText("");
					
					while(modelClienteSele.getRowCount()>0) {
						modelClienteSele.removeRow(0);
					}
					tabbedPane.setSelectedIndex(2);
				}
			}
		});
		btnSalvarLista.setBounds(635, 209, 89, 23);
		painelOperacao.add(btnSalvarLista);
		

		
		JPanel painelCadastroOperacao = new JPanel();
		tabbedPane.addTab("Cadastro de Opera��o", null, painelCadastroOperacao, null);
		painelCadastroOperacao.setLayout(null);
		
		
		
		
		JScrollPane sbListaChamada = new JScrollPane();
		sbListaChamada.setBounds(46, 103, 226, 308);
		painelCadastroOperacao.add(sbListaChamada);
		
		tblListChamadaOperacao = new JTable();
		sbListaChamada.setViewportView(tblListChamadaOperacao);
		tblListChamadaOperacao.setDefaultEditor(Object.class, null);
		
		DefaultTableModel modelTblList = new DefaultTableModel() {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				// TODO Auto-generated method stub
				switch(columnIndex) {
					case 0:
						return Integer.class;
					case 1:
						return String.class;
					case 2: 
						return String.class;
					default:
						return String.class;
				}
			}
		};
		
		modelTblList.addColumn("Id");
		modelTblList.addColumn("Data");
		modelTblList.addColumn("Nome");
		
		JComboBox cbDatas = new JComboBox();
		cbDatas.setBounds(109, 30, 70, 20);
		painelCadastroOperacao.add(cbDatas);
		
		DefaultComboBoxModel<String> modelCbDatas = new DefaultComboBoxModel<>();
		modelCbDatas.addElement("datas");
		
		
		
		listaChamadaList = new ArrayList<>();

		
		ListaChamadaDAO dao = new ListaChamadaDAO();
		List<String> datas = listaDao.getDatas();
		
		listaChamadaList = listaDao.getAll();
		
		
		
		ListaChamadaDAO daoDatas = new ListaChamadaDAO();
		
		
		
		for(String data : datas) {
			modelCbDatas.addElement(data);
		}
		
		
		
		
		cbDatas.setModel(modelCbDatas);
		tblListChamadaOperacao.setModel(modelTblList);
		
		int cont = 0;
		
		for(ListaChamada listaChamada : listaChamadaList) {
			
			String dia;
			String mes;
			String ano;
			
			
			if(listaChamada.getDia()<10) {
				dia = "0"+listaChamada.getDia();
			}else {
				dia = String.valueOf(listaChamada.getDia());
			}
			
			
			if(listaChamada.getMes()<10) {
				mes = "0" + listaChamada.getMes();
			}else {
				mes = String.valueOf(listaChamada.getMes());
			}
			
			String data = dia + "/" + mes + "/" + listaChamada.getAno();
			
			
			modelTblList.addRow(new Object[0]);
			modelTblList.setValueAt(listaChamada.getCliente().getId(), cont,0);
			modelTblList.setValueAt(data, cont,1);
			modelTblList.setValueAt(listaChamada.getCliente().getNome(),cont, 2);
			
			cont++;
		}
		
		cbDatas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String dataSele = modelCbDatas.getSelectedItem().toString();
				
				while(modelTblList.getRowCount()>0) {
					modelTblList.removeRow(0);
				}
				
				ListaChamadaDAO dao = new ListaChamadaDAO();
				
				List<ListaChamada> list = dao.getListaByData(dataSele);
				
				int cont = 0;
				
				for(ListaChamada listaChamada : list) {
					
					String dia;
					String mes;
					String ano;
					
					
					if(listaChamada.getDia()<10) {
						dia = "0"+listaChamada.getDia();
					}else {
						dia = String.valueOf(listaChamada.getDia());
					}
					
					
					if(listaChamada.getMes()<10) {
						mes = "0" + listaChamada.getMes();
					}else {
						mes = String.valueOf(listaChamada.getMes());
					}
					
					String data = dia + "/" + mes + "/" + listaChamada.getAno();
					
					
					modelTblList.addRow(new Object[0]);
					modelTblList.setValueAt(listaChamada.getCliente().getId(), cont,0);
					modelTblList.setValueAt(data, cont,1);
					modelTblList.setValueAt(listaChamada.getCliente().getNome(),cont, 2);
					
					cont++;
				}
				
				
				
			}
		});
		
		JLabel lblNomeCliente = new JLabel("Nome:\r\n");
		lblNomeCliente.setBounds(322, 104, 70, 14);
		painelCadastroOperacao.add(lblNomeCliente);
		
		txtNomeOperacao = new JTextField();
		txtNomeOperacao.setBounds(402, 101, 149, 20);
		painelCadastroOperacao.add(txtNomeOperacao);
		txtNomeOperacao.setColumns(10);
		
		JLabel lblDataOperacao = new JLabel("Data:");
		lblDataOperacao.setBounds(322, 139, 46, 14);
		painelCadastroOperacao.add(lblDataOperacao);
		
		txtDataOperacao = new JTextField();
		txtDataOperacao.setBounds(402, 136, 149, 20);
		painelCadastroOperacao.add(txtDataOperacao);
		txtDataOperacao.setColumns(10);
		
		JLabel lblQntdPacotes = new JLabel("qntd pacotes");
		lblQntdPacotes.setBounds(322, 170, 70, 14);
		painelCadastroOperacao.add(lblQntdPacotes);
		
		txtQntdPacotes = new JTextField();
		txtQntdPacotes.setBounds(402, 167, 86, 20);
		painelCadastroOperacao.add(txtQntdPacotes);
		txtQntdPacotes.setColumns(10);
		
		JLabel lblDiaria = new JLabel("Diaria");
		lblDiaria.setBounds(322, 195, 46, 14);
		painelCadastroOperacao.add(lblDiaria);
		
		txtDiaria = new JTextField();
		txtDiaria.setBounds(402, 198, 86, 20);
		painelCadastroOperacao.add(txtDiaria);
		txtDiaria.setColumns(10);
		
		
		tblListChamadaOperacao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				
				if(e.getClickCount()==2) {
					txtDataOperacao.setText("");
					txtNomeOperacao.setText("");
					
					int num = tblListChamadaOperacao.getSelectedRow();
					
					txtDataOperacao.setText(String.valueOf(modelTblList.getValueAt(num, 1)));
					txtNomeOperacao.setText(String.valueOf(modelTblList.getValueAt(num, 2)));
					
					
				}
				
				
			}
		});
		
		JButton btnSalvarOperacao = new JButton("Salvar Operacao\r\n");
		btnSalvarOperacao.setBounds(322, 238, 229, 23);
		painelCadastroOperacao.add(btnSalvarOperacao);
		
		
		
		
		btnSalvarOperacao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Operacao operacao = new Operacao();
				Operacao operacaoSalva;
				Cliente cliente;
				ClienteOperacao clienteOperacao = new ClienteOperacao();
				
				OperacaoDAO operacaoDAO = new OperacaoDAO();
				ClienteDAO clienteDAO = new ClienteDAO();
				ClienteOperacaoDAO clienteOperacaoDAO = new ClienteOperacaoDAO();
				
				
				operacao.setDataOperacao(txtDataOperacao.getText());
				operacao.setQntdPacotes(Integer.valueOf(txtQntdPacotes.getText()));
				operacao.setValorDia(Double.valueOf(txtDiaria.getText()));
				
				operacaoSalva = operacaoDAO.saveOperacao(operacao);
				cliente = clienteDAO.getClienteById((int) modelTblList.getValueAt(tblListChamadaOperacao.getSelectedRow(), 0));
				
				clienteOperacao.setIdCliente(cliente);
				clienteOperacao.setIdOperacao(operacaoSalva);
				
				Boolean confirmacao = clienteOperacaoDAO.saveClienteOperacao(clienteOperacao);
				
				if(confirmacao) {
					System.out.println("cadastrado");
				}else {
					System.out.println("Erro");
				}
				
			}
		});
		
		
		JPanel painelExtrato= new JPanel();
		tabbedPane.addTab("Extratos", null, painelExtrato, null);
		painelExtrato.setLayout(null);
		
		JScrollPane spListaOperacao = new JScrollPane();
		spListaOperacao.setBounds(10, 226, 533, 185);
		painelExtrato.add(spListaOperacao);
		
		tblOperacaoExtrato = new JTable();
		spListaOperacao.setViewportView(tblOperacaoExtrato);
		tblOperacaoExtrato.setDefaultEditor(Object.class, null);
		
		JScrollPane spClienteExtrato = new JScrollPane();
		spClienteExtrato.setBounds(10, 55, 533, 116);
		painelExtrato.add(spClienteExtrato);
		
		tblClienteExtrato = new JTable();
		spClienteExtrato.setViewportView(tblClienteExtrato);
		tblClienteExtrato.setDefaultEditor(Object.class, null);
		
		JLabel lblNomeExtrato = new JLabel("Nome:\r\n");
		lblNomeExtrato.setBounds(10, 11, 47, 33);
		painelExtrato.add(lblNomeExtrato);
		
		txtNomeExtrato = new JTextField();
		txtNomeExtrato.setBounds(67, 11, 147, 33);
		painelExtrato.add(txtNomeExtrato);
		txtNomeExtrato.setColumns(10);
		
		JComboBox cbDataExtrato = new JComboBox();
		cbDataExtrato.setBounds(20, 182, 95, 33);
		painelExtrato.add(cbDataExtrato);
		
		
		//Models tabelas e comboBox
		DefaultTableModel modelTblClienteExtrato = new DefaultTableModel() {
			public java.lang.Class<?> getColumnClass(int columnIndex) {
				switch(columnIndex) {
					case 0:
						return Integer.class;
					case 1:
						return String.class;
					case 2:
						return String.class;
					default:
						return String.class;
				}
			}
		};
		
		modelTblClienteExtrato.addColumn("id");
		modelTblClienteExtrato.addColumn("Nome");
		modelTblClienteExtrato.addColumn("Email");
		
		ClienteExtratoControler controllerCliente = new ClienteExtratoControler();
		controllerCliente.controllerCliente(txtNomeExtrato.getText(), tblClienteExtrato, modelTblClienteExtrato);
		
		
			txtNomeExtrato.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println("pressionou");
				controllerCliente.controllerCliente(txtNomeExtrato.getText(), tblClienteExtrato, modelTblClienteExtrato);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		
		
		DefaultTableModel modelTblExtratos = new DefaultTableModel() {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				switch (columnIndex) {
				case 0:
					return Integer.class;
				case 1:
					return String.class;
				case 2:
					return Integer.class;
				case 3:
					return Double.class;
				case 4:
					return String.class;
				default:
					return String.class;
					
				}
				
			}
		};
		
		modelTblExtratos.addColumn("Id");
		modelTblExtratos.addColumn("Nome");
		modelTblExtratos.addColumn("Email");
		modelTblExtratos.addColumn("Qntd P");
		modelTblExtratos.addColumn("Diaria");
		modelTblExtratos.addColumn("Data");
		
		
		
		
		DefaultComboBoxModel<String> modelCbDatasExtrato = new DefaultComboBoxModel<>();
		modelCbDatasExtrato.addElement("Datas");
		
		List<String> datasExtrato = null;
		
		ListaChamadaDAO daoDatasExtrato = new ListaChamadaDAO(); 
		
		datasExtrato = daoDatasExtrato.getDatas();
		
		for(String data : datasExtrato) {
			modelCbDatasExtrato.addElement(data);
		}
		
		cbDataExtrato.setModel(modelCbDatasExtrato);
		
		
		
		ExtratoController extratoController = new ExtratoController();
		
		extratoController.getExtratoController(0, modelTblExtratos,tblOperacaoExtrato , cbDataExtrato.getSelectedItem().toString());
		
		JButton btnEnviarExtrato = new JButton("Enviar por email");
		btnEnviarExtrato.setBounds(553, 292, 147, 23);
		painelExtrato.add(btnEnviarExtrato);
		
		
		
		cbDataExtrato.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				extratoController.getExtratoController(contIdCliente, modelTblExtratos,tblOperacaoExtrato , cbDataExtrato.getSelectedItem().toString());
			}
		});
		
		
		//Colocando os valores nas tabelas e no comboBox
		
		
		tblClienteExtrato.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				contIdCliente = (int) modelTblClienteExtrato.getValueAt(
					tblClienteExtrato.getSelectedRow(),
						0);
				if(e.getClickCount()==2) {
					extratoController.getExtratoController(contIdCliente, modelTblExtratos,tblOperacaoExtrato , cbDataExtrato.getSelectedItem().toString());
					
				}
			
				
			
				
			}
		});
		
		btnEnviarExtrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GeradorExtrato geradorExtrato = new GeradorExtrato();
				String caminhoExtrato = geradorExtrato.generatePFDExtrato(modelTblExtratos);
			
				EnvioExtrato envioExtrato = new EnvioExtrato();
				String emailCliente = (String) modelTblExtratos.getValueAt(0, 2);
				
				if(envioExtrato.sendExtratoByEmail(emailCliente, caminhoExtrato)) {
					JOptionPane jOptionPane = new JOptionPane();
					jOptionPane.showMessageDialog(null, "Enviado enviado com sucesso");
				}
				
				
				
			}
		});
		
		
	}
}
