package DAO;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import conection.ConectionFactory;
import model.Cliente;

public class ClienteDAO {
	
	  public EntityManager emCliente = new ConectionFactory().getConection();
	
	public Cliente saveCliente(Cliente cliente) {
		
		try {
			
			emCliente.getTransaction().begin();
			emCliente.persist(cliente);
			emCliente.getTransaction().commit();
			
			
			
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
			emCliente.getTransaction().rollback();
			
		}finally {
			emCliente.close();
		}
		
		return cliente;
	}
	
	
	@SuppressWarnings("unchecked")
	public HashMap<Integer, Cliente> getAllClientes(){
		
		HashMap<Integer, Cliente> allClientes = new HashMap<>();
		List<Cliente> clienteList = null;
		
		try {
			
			 clienteList = emCliente.createQuery("from Cliente c").getResultList();
			 
			 for(Cliente c:clienteList) {
				 allClientes.put(c.getId(), c);
			 }
			
		}catch (Exception e) {
			
			System.out.println(e.getMessage());
			emCliente.getTransaction().rollback();
			
		}finally {
			emCliente.close();
		}
		
		return allClientes;
		
	}
	
	public Cliente getClienteById(int id) {
		
		Cliente cliente = null;
		
		try {
			 cliente   = emCliente.find(Cliente.class, id);	
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			emCliente.getTransaction().rollback();
			
			
		}finally {
			emCliente.close();
		}
		
		return cliente;
		
	}
	

}
