package br.com.marceltanuri.reclameaqui;

public class Reclamacao {

    private Long id;
    private String titulo;
    private String descricao;
    private String autor;

    public Reclamacao() {
    }

    public Reclamacao(Long id, String titulo, String descricao, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
