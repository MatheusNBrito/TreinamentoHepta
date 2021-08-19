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

import br.hepta.treinamento.entity.Usuario;
import br.hepta.treinamento.form.UsuarioForm;
import br.hepta.treinamento.persistence.UsuarioDAO;

@Path("/lotericas")
public class UsuarioRest {

	private UsuarioDAO dao;

	public UsuarioRest() {
		dao = new UsuarioDAO();
	}

	// Adicionar novo usuario

	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response UsuarioCreate(UsuarioForm Usuario) {
		try {
			Integer id = dao.inserir(Usuario.toEntity());
			URI uri = new URI("/treinamento/rs/lotericas/" + id);
			Response resposta = Response.created(uri).build();
			return resposta;

		} catch (Exception e) {
			Response resposta = Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar novo  usuario")
					.build();
			return resposta;
		}

	}
/*
	// Adicionar novo usuario

		//@Consumes({ MediaType.APPLICATION_JSON })
		@Consumes({ MediaType.MULTIPART_FORM_DATA })
		@Produces(MediaType.APPLICATION_JSON)
		@POST
		public Response UsuarioCreate(@FormDataParam("usuario") FormDataBodyPart usuario, @FormDataParam("file") File imagem) {
			Response entity = null;
		//public Response UsuarioCreate(UsuarioForm Usuario) {
			try {
				Integer id = dao.inserir(Usuario.toEntity());
				URI uri = new URI("/treinamento/rs/lotericas/" + id);
				Response resposta = Response.created(uri).build();
				return resposta;

			} catch (Exception e) {
				Response resposta = Response.status(Status.INTERNAL_SERVER_ERROR)
						.entity("Erro ao adicionar novo  usuario")
						.build();
				return resposta;
			}

		}
		->
	*/

	// Listar Usuarios

	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response UsuarioRead() {
		List<Usuario> usuarios = new ArrayList<>();
		try {
			usuarios = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao" + " buscar Usuario").build();
		}

		
		return Response.status(Status.OK).entity(usuarios).build();
	}



	// Pegar um usuario

	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response UsuariooFind(@PathParam("id") Integer id) {
		Usuario usuario = new Usuario();
		try {
			usuario = dao.findUsuario(id);

		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar atualizar  Usuario").build();
		}

		return Response.status(Status.OK).entity(usuario).build();
	}
//		
	// Editar um usuario

	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	@PUT
	public Response UsuarioUpdate(UsuarioForm Usuario) {
		Usuario usuario_velho = new Usuario();
		try {
			usuario_velho = dao.findUsuario(Usuario.getId());
			usuario_velho = dao.editar(Usuario);

		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity
					("Erro ao atualizar  Usuario").build();
		}

		return Response.status(Status.OK).build();
	}
//		
	// Deletar usuario

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response UsuarioDelete(@PathParam("id") Integer id) {
		Response var = null;
		try {
			Boolean delete = dao.deletar(id);
			var = delete ? Response.status(200).entity("Usuario deletado com" + " Sucesso").build()
					: Response.notModified().build();
			// dao.deletar(user_id);

			return var;
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Nao foi " + "possivel realizar operacao").build();
		}
	}

}
