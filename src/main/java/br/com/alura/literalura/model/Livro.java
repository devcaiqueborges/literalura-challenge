package br.com.alura.literalura.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private String idioma;
    Double numeroDeDownloads;

    public Livro(){}

    public Livro(DadosLivro dadosLivro){
        this.titulo = dadosLivro.titulo();
        this.idioma = dadosLivro.idiomas().get(0);
        this.numeroDeDownloads = dadosLivro.numeroDeDownloads();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDeDownloads() {
        return numeroDeDownloads;
    }

    public void setNumeroDeDownloads(Double numeroDeDownloads) {
        this.numeroDeDownloads = numeroDeDownloads;
    }
    @Override
    public String toString() {
        return String.format("""
            ----- LIVRO -----
            Título: %s
            Autor: %s
            Idioma: %s
            Número de downloads: %.1f
            -----------------
            """,
                titulo,
                (autor != null ? autor.getNome() : "Desconhecido"),
                idioma,
                numeroDeDownloads);
    }
}

