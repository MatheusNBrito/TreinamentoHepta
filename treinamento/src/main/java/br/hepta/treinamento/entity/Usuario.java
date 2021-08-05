package br.hepta.treinamento.entity;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
@Table(name = "USUARIO")
public class Usuario {

	public static final long serialVersionUID = 1l; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;
	
	@Column(name = "username")
	private String nome;
	
	@Column(name = "password")
	private String senha;
	
	@Column(name = "fullname")
	private String nomecompleto;
	
	@Column(name = "email")
	private String mail;
	
	@Column(name = "FK_agencia")
	private Integer FK_agencia;

	
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
		return nomecompleto;
	}

	public void setFullname(String nomecompleto) {
		this.nomecompleto = nomecompleto;
	}
	
	public String getEmail() {
		return mail;
	}

	public void setEmail(String mail) {
		this.mail = mail;
	}

	public Integer getFK_agencia() {
		return FK_agencia;
	}

	public void setFK_agencia(Integer fK_agencia) {
		FK_agencia = fK_agencia;
	}
	
	
}
