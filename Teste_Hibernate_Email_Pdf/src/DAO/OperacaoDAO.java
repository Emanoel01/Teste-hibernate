package DAO;

import javax.persistence.EntityManager;

import conection.ConectionFactory;
import model.Operacao;

public class OperacaoDAO {
	
	public EntityManager emCliente = new ConectionFactory().getConection();
	
	
	public Operacao saveOperacao(Operacao operacao) {
		
		
		try {
			emCliente.getTransaction().begin();
			emCliente.persist(operacao);
			emCliente.getTransaction().commit();
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			emCliente.getTransaction().rollback();
			
		}finally {
			emCliente.close();
		}
		
		
		return operacao;
		
	}

}
