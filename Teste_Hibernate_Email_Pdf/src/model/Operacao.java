package model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tbl_operacao")
public class Operacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="qntd_pacotes")
	private int qntdPacotes;
	
	@Column(name="data")
	private String dataOperacao;
	
	@Column(name="valor_dia")
	private Double valorDia;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getQntdPacotes() {
		return qntdPacotes;
	}
	public void setQntdPacotes(int qntdPacotes) {
		this.qntdPacotes = qntdPacotes;
	}
	public String getDataOperacao() {
		return dataOperacao;
	}
	public void setDataOperacao(String dataOperacao) {
		this.dataOperacao = dataOperacao;
	}
	public Double getValorDia() {
		return valorDia;
	}
	public void setValorDia(Double valorDia) {
		this.valorDia = valorDia;
	}
	@Override
	public String toString() {
		return "Operacao [id=" + id + ", qntdPacotes=" + qntdPacotes + ", dataOperacao=" + dataOperacao + ", valorDia="
				+ valorDia + "]";
	}
	
	
	

}
