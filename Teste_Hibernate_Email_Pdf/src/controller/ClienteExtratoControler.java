package controller;

import java.util.HashMap;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.ClienteDAO;
import model.Cliente;

public class ClienteExtratoControler {
	
	
	public void controllerCliente(String nome,JTable tabela,DefaultTableModel model) {
		
		ClienteDAO dao = new ClienteDAO();
		HashMap<Integer,Cliente> clientes = null;
		List<Cliente> clientesList= null;
		
		
		if(nome.equals("")) {
			if(model.getRowCount()>0) {
				while(model.getRowCount()>0) {
					model.removeRow(0);
				}
			}
			clientes = dao.getAllClientes();
			
			int cont = 0;
			
			for(Cliente cliente : clientes.values()) {
				model.addRow(new Object[0]);
				model.setValueAt(cliente.getId(), cont, 0);
				model.setValueAt(cliente.getNome(), cont, 1);
				model.setValueAt(cliente.getEmail(), cont, 2);
				
				cont++;
			}
			
		}else {
			clientesList = dao.getClienteByName(nome);
			
			int i=0;
			
			while(model.getRowCount()>0) {
				model.removeRow(0);
			}
			
			for(Cliente c : clientesList) {
				model.addRow(new Object[0]);
				model.setValueAt(c.getId(), i, 0);
				model.setValueAt(c.getNome(), i, 1);
				model.setValueAt(c.getEmail(), i, 2);
				
				i++;
			}
		}
		
		tabela.setModel(model);
		
		
	}

}
