package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosAutor(
        @JsonAlias("name") String nome,
        @JsonAlias("birth_year") Integer anoDeNascimento,
        @JsonAlias("death_year") Integer anoDeMorte
) {
}
