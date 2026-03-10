package br.com.alura.literalura.principal;

import br.com.alura.literalura.ConsumoApi;
import br.com.alura.literalura.model.*;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConverteDados;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String endereco = "https://gutendex.com/books/?search=";

    private LivroRepository repository;
    private AutorRepository autorRepository;


    public Principal(LivroRepository repository, AutorRepository autorRepository) {
        this.repository = repository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu() {
        int opcao = -1;
        while (opcao != 0) {
            String menu = """
                    ***************************************************
                    *            BIBLIOTECA LITERALURA                *
                    ***************************************************
                    
                        1 - Buscar livro pelo título
                        2 - Listar livros registrados
                        3 - Listar nossos autores
                        4 - Listar autores vivos em um determinado ano
                        5 - Listar livros por idioma
                    
                                        0 - Sair
                    
                    ***************************************************
                    Escolha uma opção: """;

            System.out.println(menu);
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> buscarLivroApi();
                case 2 -> listarLivrosRegistrados();
                case 3 -> listarAutores();
                case 4 -> listarAutoresVivosNoAno();
                case 5 -> listarLivrosPorIdioma();
                case 0 -> System.out.println("Saindo... Obrigado por usar!");
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private void buscarLivroApi() {
        System.out.print("Digite o nome do livro: ");
        String nomeDoLivro = sc.nextLine();

        String json = consumo.obterDados(endereco + nomeDoLivro.replace(" ", "%20"));
        DadosRespostaApi dados = conversor.obterDados(json, DadosRespostaApi.class);

        if (dados.resultados() != null && !dados.resultados().isEmpty()) {
            DadosLivro dadosLivro = dados.resultados().get(0);

            DadosAutor dadosAutor = dadosLivro.autores().get(0);

            Autor autor = new Autor(dadosAutor);
            autorRepository.save(autor);

            Livro livro = new Livro(dadosLivro);
            livro.setAutor(autor);

            repository.save(livro);

            System.out.println("\nLivro e Autor salvos com sucesso!");
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private void listarLivrosRegistrados() {
        List<Livro> livros = repository.findAll();
        livros.forEach(System.out::println);
    }

    private void listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }
    private void listarAutoresVivosNoAno() {
        System.out.println("Digite o ano que deseja pesquisar:");
        var ano = sc.nextInt();
        sc.nextLine();

        List<Autor> autoresVivos = autorRepository.buscarAutoresVivosNoAno(ano);

        if (autoresVivos.isEmpty()) {
            System.out.println("\nNenhum autor encontrado vivo no ano de " + ano);
        } else {
            System.out.println("\n--- AUTORES VIVOS NO ANO " + ano + " ---");
            autoresVivos.forEach(System.out::println);
            System.out.println("---------------------------------------\n");
        }
    }
    private void listarLivrosPorIdioma() {
        System.out.println("""
            Digite o idioma para busca:
            en - Inglês
            es - Espanhol
            fr - Francês
            pt - Português
            """);
        String idioma = sc.nextLine();

        List<Livro> livrosPorIdioma = repository.findByIdioma(idioma);

        if (livrosPorIdioma.isEmpty()) {
            System.out.println("\nNão existem livros nesse idioma no banco de dados.");
        } else {
            System.out.println("\n--- LIVROS NO IDIOMA " + idioma.toUpperCase() + " ---");
            livrosPorIdioma.forEach(System.out::println);
            System.out.println("-----------------------------------------\n");
        }
    }
}