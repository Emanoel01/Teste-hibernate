package model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.*;

@Entity
@Table(name="tbl_cliente_operacao")
public class ClienteOperacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@JoinColumn(name="id_cliente")
	@ManyToOne
	private Cliente idCliente;
	
	@JoinColumn(name="id_operacao")
	@ManyToOne
	private Operacao idOperacao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

	public Operacao getIdOperacao() {
		return idOperacao;
	}

	public void setIdOperacao(Operacao idOperacao) {
		this.idOperacao = idOperacao;
	}
	
	
	

}
