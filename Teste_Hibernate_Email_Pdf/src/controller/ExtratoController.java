package controller;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.ClienteOperacaoDAO;
import model.ClienteOperacao;

public class ExtratoController {
	
	
	public void setInModel(DefaultTableModel model, List<ClienteOperacao> list) {
		
		if(model.getRowCount()>0) {
			while(model.getRowCount()>0) {
				model.removeRow(0);
			}
		}
		
		 
		 int cont =0;
		 
		 for(ClienteOperacao co : list) {
			 model.addRow(new Object[0]);
			 model.setValueAt(co.getIdCliente().getId(), cont, 0);
			 model.setValueAt(co.getIdCliente().getNome(), cont, 1);
			 model.setValueAt(co.getIdCliente().getEmail(), cont, 2);
			 model.setValueAt(co.getIdOperacao().getQntdPacotes(), cont, 3);
			 model.setValueAt(co.getIdOperacao().getValorDia(), cont, 4);
			 model.setValueAt(co.getIdOperacao().getDataOperacao(), cont, 5);
			 
			 cont++;
		 }
		
	}
	
	
	 public void getExtratoController(int id,DefaultTableModel model, JTable table,String data){
		 
		 List<ClienteOperacao> list = null;
		 ClienteOperacaoDAO dao = new ClienteOperacaoDAO();
		 
		 if(id==0 && data.equals("Datas")) {
			 list = dao.getAllCO();
			 
			 setInModel(model,list);
			
		 }else if(id>0 && data.equals("Datas")) {
			 
			 list = dao.getByIdCliente(id);
			 
			 setInModel(model,list);
			 
		 }else if(id==0 && !data.equals("Datas")) {
			 list = dao.getByData(data);
			 
			 setInModel(model,list);
			 
		 }else if(id>0 && !data.equals("Datas")) {
			 list = dao.getByDataAndId(id, data);
			 
			 setInModel(model,list);
		 }
		 
		 table.setModel(model);
		 
		 
	 }
	

}
