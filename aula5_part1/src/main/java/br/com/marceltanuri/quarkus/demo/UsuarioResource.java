package br.com.marceltanuri.quarkus.demo;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService usuarioService;

    @GET
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GET
    @Path("/{id}")
    public Response listarUsuarioPorId(String id) {
        return usuarioService.listarUsuarioPorId(id)
                .map(usuario -> Response.ok(usuario).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/email/{email}")
    public Response buscaPorEmail(String email) {
        return usuarioService.buscaPorEmail(email)
                .map(usuario -> Response.ok(usuario).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response criarUsuario(Usuario usuarioNovo) {
        if (usuarioService.existeEmail(usuarioNovo.email())) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        var usuario = usuarioService.criarUsuario(usuarioNovo);
        return Response.status(Response.Status.CREATED).entity(usuario).build();
    }
}
