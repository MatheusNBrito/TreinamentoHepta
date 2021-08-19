package br.hepta.treinamento.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.hepta.treinamento.entity.Agencia;
import br.hepta.treinamento.form.AgenciaForm;
import br.hepta.treinamento.persistence.AgenciaDAO;

@Path("/lotericas/agencia")
public class AgenciaRest {

	private AgenciaDAO dao;

	public AgenciaRest() {
		dao = new AgenciaDAO();
	}

	// Adicionar nova agencia

	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response AgenciaCreate(AgenciaForm Agencia) {
		try {
			Integer id = dao.inserir(Agencia.toEntity());
			URI uri = new URI("/treinamento/rs/lotericas/agencia" + id);
			Response resposta = Response.created(uri).build();
			return resposta;

		} catch (Exception e) {
			Response resposta = Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar nova  agencia")
					.build();
			return resposta;
		}

	}

	// Listar Agencia

	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response AgenciaRead() {
		List<Agencia> agencias = new ArrayList<>();
		try {
			agencias = dao.listarAgencia();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao" + " buscar Agencia").build();
		}

		// GenericEntity<List<Usuario>> entity = new
		// GenericEntity<List<Usuario>>(Usuarios) {
		return Response.status(Status.OK).entity(agencias).build();
	}

	// Pegar uma agencia

	@Path("/{idag}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response AgenciaFind(@PathParam("idag") Integer idag) {
		Agencia agencia = new Agencia();
		try {
			agencia = dao.findAgencia(idag);

		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ").build();
		}

		return Response.status(Status.OK).entity(agencia).build();
	}
//		
	// Editar uma agencia

	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	@PUT
	public Response AgenciaUpdate(AgenciaForm Agencia) {
		Agencia agencia_velha = new Agencia();
		try {
			agencia_velha = dao.findAgencia(Agencia.getIdag());
			agencia_velha = dao.editar(Agencia);

		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity
					("Erro ao atualizar  Agencia").build();
		}

		return Response.status(Status.OK).build();
	}
	
	// Deletar Agencia

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AgenciaDelete(@PathParam("id") Integer id) {
		Response var = null;
		try {
			Boolean delete = dao.deletar(id);
			var = delete ? Response.status(200).entity("Agencia deletada com" + " Sucesso").build()
					: Response.notModified().build();
			// dao.deletar(user_id);

			return var;
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Nao foi " + "possivel realizar operacao").build();
		}
	}

}
