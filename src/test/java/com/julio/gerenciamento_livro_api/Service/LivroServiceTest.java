
package com.julio.gerenciamento_livro_api.Service;

import com.julio.gerenciamento_livro_api.model.Livro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LivroServiceTest {

    private LivroService service;

    @BeforeEach
    void setUp() {
        service = new LivroService();
    }

    private Livro criarLivro(String titulo, String autor) {
        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setGenero("Fantasia");
        livro.setAnoPublicacao(2020);
        return livro;
    }

    @Test
    void deveCadastrarLivroComSucesso() {
        Livro livro = criarLivro("O Hobbit", "J. R. R. Tolkien");

        Livro resultado = service.criar(livro);

        assertNotNull(resultado.getId());
        assertEquals(1L, resultado.getId());
        assertEquals("O Hobbit", resultado.getTitulo());
        assertEquals(1, service.listar().size());
    }

    @Test
    void deveCadastrarLivrosComIdsDiferentes() {
        Livro primeiro = service.criar(
                criarLivro("O Hobbit", "J. R. R. Tolkien")
        );
        Livro segundo = service.criar(
                criarLivro("1984", "George Orwell")
        );

        assertNotEquals(primeiro.getId(), segundo.getId());
        assertEquals(2, service.listar().size());
    }

    @Test
    void deveListarTodosOsLivros() {
        service.criar(criarLivro("O Hobbit", "Tolkien"));
        service.criar(criarLivro("1984", "Orwell"));

        List<Livro> livros = service.listar();

        assertEquals(2, livros.size());
        assertEquals("O Hobbit", livros.get(0).getTitulo());
        assertEquals("1984", livros.get(1).getTitulo());
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremLivros() {
        List<Livro> livros = service.listar();

        assertNotNull(livros);
        assertTrue(livros.isEmpty());
    }

    @Test
    void deveBuscarLivroPorId() {
        Livro cadastrado = service.criar(
                criarLivro("O Hobbit", "Tolkien")
        );

        Livro encontrado = service.buscarPorId(cadastrado.getId());

        assertEquals(cadastrado.getId(), encontrado.getId());
        assertEquals("O Hobbit", encontrado.getTitulo());
    }

    @Test
    void deveLancarExcecaoAoBuscarLivroInexistente() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> service.buscarPorId(999L)
        );

        assertEquals("Livro não encontrado", exception.getMessage());
    }

    @Test
    void deveAtualizarLivroExistente() {
        Livro cadastrado = service.criar(
                criarLivro("Título antigo", "Autor antigo")
        );

        Livro atualizado = criarLivro(
                "Título novo", "Autor novo"
        );

        Livro resultado = service.atualizar(
                cadastrado.getId(), atualizado
        );

        assertEquals("Título novo", resultado.getTitulo());
        assertEquals("Autor novo", resultado.getAutor());
        assertEquals("Fantasia", resultado.getGenero());
        assertEquals(2020, resultado.getAnoPublicacao());
    }

    @Test
    void deveLancarExcecaoAoAtualizarLivroInexistente() {
        Livro atualizado = criarLivro("Novo título", "Novo autor");

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> service.atualizar(999L, atualizado)
        );

        assertEquals("Livro não encontrado", exception.getMessage());
    }

    @Test
    void deveExcluirLivroExistente() {
        Livro cadastrado = service.criar(
                criarLivro("O Hobbit", "Tolkien")
        );

        assertDoesNotThrow(
                () -> service.excluir(cadastrado.getId())
        );

        assertTrue(service.listar().isEmpty());
    }

    @Test
    void deveLancarExcecaoAoExcluirLivroInexistente() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> service.excluir(999L)
        );

        assertEquals("Livro não encontrado", exception.getMessage());
    }
}