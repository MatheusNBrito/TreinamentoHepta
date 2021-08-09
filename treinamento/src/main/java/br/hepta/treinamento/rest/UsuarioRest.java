package br.hepta.treinamento.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
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

//	// Adicionar novo usuario
//
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	@POST
//	public Response UsuarioCreate(UsuarioForm Usuario) {
//		try {
//			Integer id = dao.inserir(Usuario.toEntity());
//			URI uri = new URI("/treinamento/rs/lotericas/" + id);
//			Response resposta = Response.created(uri).build();
//			return resposta;
//
//		} catch (Exception e) {
//			Response resposta = Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar novo  usuario")
//					.build();
//			return resposta;
//		}
//
//	}

	// Listar Usuario

	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response UsuarioRead() {
		List<Usuario> usuarios = new ArrayList<>();
		try {
			usuarios = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar Usuario").build();
		}

		// GenericEntity<List<Usuario>> entity = new
		// GenericEntity<List<Usuario>>(Usuarios) {
		return Response.status(Status.OK).entity(usuarios).build();
	}

//		// Editar um usuario
//		
//		@Path("/{user_id}")
//		@Consumes(MediaType.APPLICATION_JSON)
//		@Produces(MediaType.APPLICATION_JSON)
//		@PUT
//		public Response UsuariooUpdate(@PathParam("user_id") Integer user_id, Usuario usuario) {
//			Usuario usuario_velho = new Usuario();
//			try {
//				usuario_velho = dao.findUsuario(user_id);
//				usuario.setId(user_id);
//				usuario_velho = dao.findUsuario(user_id);
//				
//				
//			}catch(Exception e) {
//				return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar atualizar  Usuario").build();
//			}
//			
//			return Response.status(Status.OK).build();
//		}
////		
//		// Editar uma FKAgencia
//		
//				@Path("/{user_id}")
//				@Consumes(MediaType.APPLICATION_JSON)
//				@Produces(MediaType.APPLICATION_JSON)
//				@PUT
//				public Response UsuarioUpdate(@PathParam("user_id") Integer 
//						user_id, @QueryParam ("FK_agencia")Integer FK_agencia){
//					Usuario usuarioag_velho = new Usuario();
//					try {
//						usuarioag_velho = dao.findUsuario(user_id);
//						usuarioag_velho.setFK_agencia(FK_agencia);
//						dao.editarFKAgencia(usuarioag_velho);
//						
//						
//					}catch(Exception e) {
//						return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar atualizar  Usuario").build();
//					}
//					
//					return Response.status(Status.OK).build();
//				}
////		
//		//Deletar usuario
//		
//		@Path("/{user_id}")
//		@Produces(MediaType.APPLICATION_JSON)
//		@DELETE
//		public Response FUsuarioDelete(@PathParam("user_id") Integer user_id) {
//			try{
//				dao.deletar(user_id);
//				return Response.status(200).entity("Usuario deletado com Sucesso").build();
//				
//			}catch(Exception e){	
//				return Response.status(Status.BAD_REQUEST).entity("N�o foi possivel realizar operacao").build();
//			}
//		}

}
