package br.hepta.treinamento.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AGENCIA")

public class Agencia {

	public static final long serialVersionUID = 1l;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idag")
	private Integer idag;

	@Column(name = "nome")
	private String nome;
	
	@Column(name = "adress")
	private String adress;

	@Column(name = "telefone")
	private Integer telefone;

	public Integer getIdag() {
		return idag;
	}

	public void setId(Integer idag) {
		this.idag = idag;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

}
