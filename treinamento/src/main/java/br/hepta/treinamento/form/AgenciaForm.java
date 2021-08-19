package br.hepta.treinamento.form;

import br.hepta.treinamento.entity.Agencia;

public class AgenciaForm {

	private Integer idag;

	private String nome;

	private String adress;
	
	private Integer telefone;

	public Integer getIdag() {
		return idag;
	}

	public void setIdag(Integer idag) {
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}


	private String mail;

	public Agencia toEntity() {
		Agencia agencia = new Agencia();

		agencia.setNome(nome);
		agencia.setAdress(adress);
		agencia.setTelefone(telefone);

		return agencia;
	}
}
