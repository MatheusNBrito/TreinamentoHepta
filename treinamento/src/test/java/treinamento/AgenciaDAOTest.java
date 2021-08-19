package treinamento;

import org.junit.jupiter.api.Test;
import br.hepta.treinamento.entity.Agencia;
import br.hepta.treinamento.entity.Usuario;
import br.hepta.treinamento.persistence.AgenciaDAO;
import br.hepta.treinamento.persistence.UsuarioDAO;

class AgenciaDAOTest {

	@Test
	void testAgenciainserir() throws Exception {

		Agencia agenciaTest = new Agencia();
		
		agenciaTest.setNome("Agencia Luz de SOl");
		agenciaTest.setAdress("UOF Lote &70");
		agenciaTest.setTelefone(33333333);

		AgenciaDAO users = new AgenciaDAO();
		users.inserir(agenciaTest);

	}
	
	@Test
	void testAgencialistar() throws Exception {
		AgenciaDAO users = new AgenciaDAO();
		users.listarAgencia();
	}
	
	@Test
	void testAgenciaeditar() throws Exception {

		Agencia agenciaTest = new Agencia();
		
		agenciaTest.setNome("Luz do Sol");
		agenciaTest.setAdress("UOF Lote &70");
		agenciaTest.setTelefone(33333333);
		agenciaTest.setId(5);

		AgenciaDAO users = new AgenciaDAO();
		users.editar(agenciaTest);
	}
	
	@Test
	void testAgenciadeletar() throws Exception {

		AgenciaDAO users = new AgenciaDAO();
		users.deletar(4);
	}

}
