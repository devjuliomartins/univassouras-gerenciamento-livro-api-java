package com.julio.gerenciamento_livro_api.Service;

import com.julio.gerenciamento_livro_api.model.Livro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class LivroService {

    private final List<Livro> banco = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public Livro criar(Livro livro) {
        livro.setId(sequence.getAndIncrement());
        banco.add(livro);

        return livro;
    }

    public List<Livro> listar() {
        return banco;
    }

    public Livro buscarPorId(Long id) {

        Optional<Livro> optLivro = banco.stream()
                .filter(livro -> livro.getId().equals(id))
                .findFirst();

        if (optLivro.isEmpty()) {
            throw new RuntimeException("Livro não encontrado");
        }

        return optLivro.get();
    }

    public Livro atualizar(Long id, Livro livroAtualizado) {

        Livro livro = buscarPorId(id);

        livro.setTitulo(livroAtualizado.getTitulo());
        livro.setAutor(livroAtualizado.getAutor());
        livro.setGenero(livroAtualizado.getGenero());
        livro.setAnoPublicacao(livroAtualizado.getAnoPublicacao());

        return livro;
    }

    public void excluir(Long id) {

        boolean removido = banco.removeIf(
                livro -> livro.getId().equals(id)
        );

        if (!removido) {
            throw new RuntimeException("Livro não encontrado");
        }
    }
}