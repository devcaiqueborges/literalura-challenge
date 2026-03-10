package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.anoDeNascimento <= :ano AND (a.anoDeMorte >= :ano OR a.anoDeMorte IS NULL)")
    List<Autor> buscarAutoresVivosNoAno(Integer ano);
}