package br.com.marceltanuri.quarkus.demo;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    public record Usuario(String id, String nome, String email) {}

    private List<Usuario> usuarios = new ArrayList<>();

    @GET
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    @GET
    @Path("/{id}")
    public Usuario listarUsuarioPorId(String id) {
        return usuarios.stream().filter(u -> u.id().equals(id)).findFirst().orElse(null);
    }

    @GET
    @Path("/email/{email}")
    public Usuario buscaPorEmail(String email) {
        return usuarios.stream().filter(u -> u.email().equals(email)).findFirst().orElse(null);
    }

    @POST
    public Response criarUsuario(Usuario usuarioNovo) {

    if(usuarios.stream().anyMatch(u -> u.email().equals(usuarioNovo.email()))){
        return Response.status(Response.Status.CONFLICT).build();
    }

      var usuario = new Usuario(UUID.randomUUID().toString(), usuarioNovo.nome(), usuarioNovo.email());
      usuarios.add(usuario);
      return Response.status(Response.Status.CREATED).entity(usuario).build();
    }
}
