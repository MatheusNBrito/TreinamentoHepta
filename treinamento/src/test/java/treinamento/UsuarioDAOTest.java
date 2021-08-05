package treinamento;

import org.junit.jupiter.api.Test;

import br.hepta.treinamento.entity.Usuario;
import br.hepta.treinamento.persistence.UsuarioDAO;

class UsuarioDAOTest {

	@Test
	void testUsuariosinserir() throws Exception {

		Usuario usuarioTest = new Usuario();

		usuarioTest.setNome("Fernanda");
		usuarioTest.setPassword("segredo123");
		usuarioTest.setFullname("Fernanda Silva");
		usuarioTest.setEmail("fefa@gmail");
		usuarioTest.setFK_agencia(1);

		UsuarioDAO users = new UsuarioDAO();
		users.inserir(usuarioTest);

	}

	@Test
	void testUsuarioslistar() throws Exception {
		UsuarioDAO users = new UsuarioDAO();
		users.listar();
	}

	@Test
	void testUsuarioseditar() throws Exception {

		Usuario usuarioTest = new Usuario();

		usuarioTest.setNome("Pedro");
		usuarioTest.setPassword("senha00");
		usuarioTest.setFullname("Pedro Marques");
		usuarioTest.setEmail("pedroM@gmail");
		usuarioTest.setFK_agencia(1);
		usuarioTest.setId(18);
		
		UsuarioDAO users = new UsuarioDAO();
		users.editar(usuarioTest);
	}
	
	@Test
	void testUsuarioseditarAgencia() throws Exception {

		Usuario usuarioTest = new Usuario();;
		usuarioTest.setFK_agencia(5);
		usuarioTest.setId(2);
		
		UsuarioDAO users = new UsuarioDAO();
		users.editarFKAgencia(usuarioTest);
	}
	

	@Test
	void testUsuariosdeletar() throws Exception {

		UsuarioDAO users = new UsuarioDAO();
		users.deletar(20);
	}

}
