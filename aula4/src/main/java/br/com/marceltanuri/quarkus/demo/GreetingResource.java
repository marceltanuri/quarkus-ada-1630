package br.com.marceltanuri.quarkus.demo;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/greeting")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Olá mundo usando Quarkus!";
    }
}


// criar, alterar, pesquisar, deletar
// Create, Read, Update, Delete
// CRUD