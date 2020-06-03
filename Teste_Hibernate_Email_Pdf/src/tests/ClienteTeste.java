package tests;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import DAO.ClienteDAO;
import DAO.ListaChamadaDAO;
import conection.ConectionFactory;
import model.Cliente;
import model.ListaChamada;

public class ClienteTeste {
	
	public static void main(String[] args) {
		
		Cliente c1  =new  Cliente();
		c1.setId(4);
		
		Cliente c2 = new Cliente();
		c2.setId(5);
		
		ListaChamadaDAO dao = new ListaChamadaDAO();
		
		List<String> datas = dao.getDatas();
		
		System.out.println(datas.get(0));
		
		
		//List<ListaChamada> chamadaList = dao.getListaPorData(data);
		
		//System.out.println(chamadaList.get(0).getCliente().getNome());
	}

}
