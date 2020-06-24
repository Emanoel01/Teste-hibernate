package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import conection.ConectionFactory;
import model.ClienteOperacao;

public class ClienteOperacaoDAO {

	 
	
	public Boolean saveClienteOperacao(ClienteOperacao clienteOperacao) {
		EntityManager emCliente = new ConectionFactory().getConection();
		
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
	
	public List<ClienteOperacao> getAllCO(){
		EntityManager emCliente = new ConectionFactory().getConection();
		List<ClienteOperacao> coList = null;
		
		try {
			coList = emCliente.createQuery("from ClienteOperacao co").getResultList();
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			emCliente.getTransaction().rollback();
		}finally {
			emCliente.close();
		}
		
		
		return coList;
	}
	
	@SuppressWarnings("unchecked")
	public List<ClienteOperacao> getByIdCliente(int id){
		EntityManager emCliente = new ConectionFactory().getConection();
		List<ClienteOperacao> coList = null;
		
		try {
			coList = emCliente.createQuery("from ClienteOperacao co where co.idCliente = " + id).getResultList();
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			emCliente.getTransaction().rollback();
			
		}finally {
			emCliente.close();
		}
		
		
		return coList;
	}
	
	@SuppressWarnings("unchecked")
	public List<ClienteOperacao> getByData(String data){
		EntityManager emCliente = new ConectionFactory().getConection();
		List<ClienteOperacao> coList = null;
		
		try {
			
			String sql = "Select co.id, co.id_cliente, co.id_operacao"
					+ " from tbl_cliente_operacao as co "
					+ "inner join tbl_operacao as o"
					+ " ON o.id = co.id_operacao and o.data = :data_operacao";
			
			
			Query query = emCliente.createNativeQuery(sql, ClienteOperacao.class);
			query.setParameter("data_operacao",data);
			coList = query.getResultList();
			
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			emCliente.getTransaction().rollback();
			
		}finally {
			emCliente.close();
			
		}
		
		
		return coList;
	}
	
	
	public List<ClienteOperacao> getByDataAndId(int id, String data){
		EntityManager emCliente = new ConectionFactory().getConection();
		List<ClienteOperacao> list=null;
		
		
		try {
			
			String sql = "Select co.id, co.id_cliente, co.id_operacao"
					+ " from tbl_cliente_operacao as co"
					+ " inner join tbl_operacao as o "
					+ " ON o.id = co.id_operacao "
					+ " AND co.id_cliente = :idC "
					+ " AND o.data = :dataO";
			
			Query query = emCliente.createNativeQuery(sql,ClienteOperacao.class);
			query.setParameter("idC", id);
			query.setParameter("dataO", data);
			
			list  = query.getResultList();
			
			
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			emCliente.getTransaction().rollback();
			
		}finally {
			emCliente.close();
		}
		
		
		return list;
	}
	
	
	
	
	
}
