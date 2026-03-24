package br.com.marceltanuri.quarkus.demo;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UsuarioService {

    private List<Usuario> usuarios = new ArrayList<>();

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public Optional<Usuario> listarUsuarioPorId(String id) {
        return usuarios.stream().filter(u -> u.id().equals(id)).findFirst();
    }

    public Optional<Usuario> buscaPorEmail(String email) {
        return usuarios.stream().filter(u -> u.email().equals(email)).findFirst();
    }

    public boolean existeEmail(String email) {
        return usuarios.stream().anyMatch(u -> u.email().equals(email));
    }

    public Usuario criarUsuario(Usuario usuarioNovo) {
        var usuario = new Usuario(UUID.randomUUID().toString(), usuarioNovo.nome(), usuarioNovo.email());
        usuarios.add(usuario);
        return usuario;
    }
}
