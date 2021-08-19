package br.hepta.treinamento.form;

import java.util.Date;

import br.hepta.treinamento.entity.Usuario;

public class UsuarioForm {

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

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUser_data() {
		return user_data;
	}

	public void setUser_data(Date user_data) {
		this.user_data = user_data;
	}

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
		return sobrenome;
	}

	public void setNomecompleto(String nomecompleto) {
		this.sobrenome = nomecompleto;
	}

	public String getMail() {
		return email;
	}

	public void setMail(String mail) {
		this.email = mail;
	}

	public Integer getFK_agencia() {
		return fk_agencia;
	}

	public void setFK_agencia(Integer fK_agencia) {
		fk_agencia = fK_agencia;
	}

	public Usuario toEntity() {
		Usuario user = new Usuario();

		user.setNome(nome);
		user.setPassword(senha);
		user.setEmail(email);
		user.setFullname(sobrenome);
		user.setFK_agencia(fk_agencia);
		user.setUser_data(user_data);

		return user;
	}
}
