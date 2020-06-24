package tests;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import DAO.ClienteDAO;
import DAO.ClienteOperacaoDAO;
import DAO.ListaChamadaDAO;
import conection.ConectionFactory;
import controller.ExtratoController;
import extrato.GeradorExtrato;
import model.Cliente;
import model.ClienteOperacao;
import model.ListaChamada;

public class ClienteTeste {
	
	public static void main(String[] args) {
		
		
		Calendar c= Calendar.getInstance();
		

		int ano = c.get(Calendar.YEAR);
		int mes = c.get(Calendar.MONTH)+1;
		int dia = c.get(Calendar.DAY_OF_MONTH);
		
		String data = ano+"-"+mes+"-"+dia;
		
		int hora = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		int seg = c.get(Calendar.SECOND);
		
		String horario = hora+":"+min+":"+seg;
		
		System.out.println(data + " " + horario);
		
		//extrato.generatePFDExtrato();
	}

}
