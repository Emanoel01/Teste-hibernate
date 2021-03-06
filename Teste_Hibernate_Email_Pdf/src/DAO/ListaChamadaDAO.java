package DAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import org.hibernate.type.EmbeddedComponentType;

import conection.ConectionFactory;
import model.ListaChamada;

public class ListaChamadaDAO {
	
	public EntityManager emCliente = new ConectionFactory().getConection();
	
	public Boolean saveListaChamada(List<ListaChamada> listListachamada) {
		
		boolean retorno = false;
		
		try {
			
			for(ListaChamada listaChamada : listListachamada) {
				emCliente.getTransaction().begin();
				emCliente.persist(listaChamada);
				emCliente.getTransaction().commit();
				
				retorno  = true;
			}

			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			emCliente.getTransaction().rollback();
		}finally {
			emCliente.close();
		}
		
		return retorno;
	}
	
	
	@SuppressWarnings( "unchecked")
	public List<String> getDatas(){
		
		List<ListaChamada> listaChamada = null;
		List<String> listaDatas = new ArrayList<>();
		
		try {
			
			 
			listaChamada = emCliente.createQuery("from ListaChamada lc group by lc.dia").getResultList();
			
			
			
			for(ListaChamada chamada : listaChamada) {
				String dia;
				String mes;
				String data;
				
				
				//DIA
				if(chamada.getDia()<10) {
					dia = "0"+chamada.getDia();
				}else {
					dia = String.valueOf(chamada.getDia());
				}
				
				//MES
				if(chamada.getMes()<10) {
					mes = "0"+chamada.getMes();
				}else {
					mes = String.valueOf(chamada.getMes());
				}
				
				//ANO
				data = dia + "/" + mes + "/" + chamada.getAno();
				
				
				listaDatas.add(data);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			emCliente.getTransaction().rollback();
		}finally {
			//emCliente.close();
		}
		
		return listaDatas;
		
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<ListaChamada> getAll(){
		
		List<ListaChamada> listaChamadaList = null;
		
		try {
			
			listaChamadaList = emCliente.createQuery("from ListaChamada lc").getResultList();
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage()); 
			emCliente.getTransaction().rollback();
			
		}finally {
			emCliente.close();
		}
		
		return listaChamadaList;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<ListaChamada> getListaByData(String data){
		List<ListaChamada> listChamada = null;
		
		try {
			
			if(!data.equals("data")) {
				
				String[] arrayDatas = data.split("/");
				
				int dia = Integer.parseInt(arrayDatas[0]);
				int mes = Integer.parseInt(arrayDatas[1]);
				int ano = Integer.parseInt(arrayDatas[2]);
				
				listChamada = emCliente.createQuery("from ListaChamada lc where lc.dia = "+dia+ " and lc.mes = "+mes+" and lc.ano = " + ano).getResultList();
				
			}else{
				listChamada = emCliente.createQuery("from ListaChamada lc").getResultList();
			}
			
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			emCliente.getTransaction().rollback();
			
		}finally {
			emCliente.close();
		}
		
		
		return listChamada;
	}
	

	
	


}
