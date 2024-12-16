package org.senac.aula01.respository;

import org.senac.aula01.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    
    // Método customizado para buscar produtos com base no nome e ordenação
    List<Produto> findByNomeContains(String nome, Sort sort);
}
