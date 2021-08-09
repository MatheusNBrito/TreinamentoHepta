package br.hepta.treinamento.form;

import javax.persistence.Column;

import br.hepta.treinamento.entity.Usuario;

public class UsuarioForm {
	
	private String nome;
	
	private String senha;
	
	private String nomecompleto;
	
	private String mail;
	
	private Integer FK_agencia;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNomecompleto() {
		return nomecompleto;
	}

	public void setNomecompleto(String nomecompleto) {
		this.nomecompleto = nomecompleto;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getFK_agencia() {
		return FK_agencia;
	}

	public void setFK_agencia(Integer fK_agencia) {
		FK_agencia = fK_agencia;
	}

	public Usuario toEntity() {
		Usuario user = new Usuario();
		
		
		user.setNome(nome);
		user.setPassword(senha);
		user.setEmail(mail);
		user.setFullname(nomecompleto);
		user.setFK_agencia(FK_agencia);
		
		return user;
	}
}
