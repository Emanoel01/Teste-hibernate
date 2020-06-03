package DAO;

import javax.persistence.EntityManager;

import conection.ConectionFactory;
import model.ClienteOperacao;

public class ClienteOperacaoDAO {

	public EntityManager emCliente = new ConectionFactory().getConection();
	
	public Boolean saveClienteOperacao(ClienteOperacao clienteOperacao) {
		
		Boolean salvar = false;
		
		try {
			emCliente.getTransaction().begin();
			emCliente.persist(clienteOperacao);
			emCliente.getTransaction().commit();
			salvar = true;
			
		}catch (Exception e) {
			
			System.out.println(e.getMessage());
			emCliente.getTransaction().rollback();
			
			
		}finally {
			
			emCliente.close();
		}
		
		return salvar;
		
	}
	
	
	
	
	
}
