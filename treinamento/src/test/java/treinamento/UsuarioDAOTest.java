package treinamento;

import org.junit.jupiter.api.Test;

import br.hepta.treinamento.entity.Usuario;
import br.hepta.treinamento.persistence.UsuarioDAO;

class UsuarioDAOTest {

	
	//Inserir
	@Test
	void testUsuariosinserir() throws Exception {

		Usuario usuarioTest = new Usuario();

		usuarioTest.setNome("Lucas");
		usuarioTest.setPassword("123456");
		usuarioTest.setFullname("Lucas Almeida");
		usuarioTest.setEmail("lucas@gmail.com");
		usuarioTest.setFK_agencia(3);
		//usuarioTest.setUser_data();

		UsuarioDAO users = new UsuarioDAO();
		users.inserir(usuarioTest);

	}
	//Listar
	@Test
	void testUsuarioslistartodos() throws Exception {
		UsuarioDAO users = new UsuarioDAO();
		users.listar();
	}
	
	//Pegar Usuario Especifico
	@Test
	void testUsuarioslistarusuario() throws Exception {
		UsuarioDAO users = new UsuarioDAO();
		users.findUsuario(22);
	}

	//Editar Usuario	
	@Test
	void testUsuarioseditar() throws Exception {

		Usuario usuarioTest = new Usuario();

		usuarioTest.setNome("Hepta");
		usuarioTest.setPassword("xxx00");
		usuarioTest.setFullname("Hepta Tecnologia");
		usuarioTest.setEmail("hepta@gmail");
		usuarioTest.setFK_agencia(5);
		usuarioTest.setId(4);
		
		UsuarioDAO users = new UsuarioDAO();
		users.editar(usuarioTest);
	}
	
	//Editar Agencia
//	@Test
//	void testUsuarioseditarAgencia() throws Exception {
//
//		Usuario usuarioTest = new Usuario();;
//		usuarioTest.setFK_agencia(1);
//		usuarioTest.setId(22);
//		
//		UsuarioDAO users = new UsuarioDAO();
//		users.editarFKAgencia(usuarioTest);
//	}
//	
	//Deletar Usuario
	@Test
	void testUsuariosdeletar() throws Exception {

		UsuarioDAO users = new UsuarioDAO();
		users.deletar(23);
	}

}
