package br.com.alura.literalura.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;
    private Integer anoDeNascimento;
    private Integer anoDeMorte;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros;

    public Autor(){}

    public Autor(DadosAutor dadosAutor) {
        this.nome = dadosAutor.nome();
        this.anoDeMorte = dadosAutor.anoDeNascimento();
        this.anoDeMorte = dadosAutor.anoDeMorte();
    }
    @Override
    public String toString() {
        return String.format("Autor: %s | Nascimento: %s | Falecimento: %s",
                nome,
                anoDeNascimento != null ? anoDeNascimento : "Desconhecido",
                anoDeMorte != null ? anoDeMorte : "Vivo");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public Integer getAnoDeMorte() {
        return anoDeMorte;
    }

    public void setAnoDeMorte(Integer anoDeMorte) {
        this.anoDeMorte = anoDeMorte;
    }

    public Integer getAnoDeNascimento() {
        return anoDeNascimento;
    }

    public void setAnoDeNascimento(Integer anoDeNascimento) {
        this.anoDeNascimento = anoDeNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
