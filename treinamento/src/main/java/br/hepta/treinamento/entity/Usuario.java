package br.hepta.treinamento.entity;

import javax.persistence.Table;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
@Table(name = "USUARIO")
public class Usuario {

	public static final long serialVersionUID = 1l;

	
	
	private Integer id;
	private String nome;
	private String senha;
	private String sobrenome;
	private String email;
	private Integer fk_agencia;
	private Date user_data;
	private byte[] imagem;
	
	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	
	public Date getUser_data() {
		return user_data;
	}

	public void setUser_data(Date user_data) {
		this.user_data = user_data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return senha;
	}

	public void setPassword(String senha) {
		this.senha = senha;
	}

	public String getFullname() {
		return sobrenome;
	}

	public void setFullname(String nomecompleto) {
		this.sobrenome = nomecompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String mail) {
		this.email = mail;
	}

	public Integer getFK_agencia() {
		return fk_agencia;
	}

	public void setFK_agencia(Integer fK_agencia) {
		fk_agencia = fK_agencia;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", senha=" + senha + ", nomecompleto=" + sobrenome
				+ ", mail=" + email + ", FK_agencia=" + fk_agencia +  "]";
	}

}
