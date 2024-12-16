package org.senac.aula01.controller;

import org.senac.aula01.model.Produto;
import org.senac.aula01.respository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")  // Permite todas as origens, ou substitua '*' por seu domínio.
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    // Método para obter os produtos com ordenação e filtro
    @GetMapping
    public List<Produto> get(
        @RequestParam(required = false, defaultValue = "nome") String order,
        @RequestParam(required = false) String filtro
    ) {
        Sort sort = Sort.by(Sort.Order.asc(order));  // Ordena em ordem crescente por padrão

        // Se a ordem for 'desc', muda a direção da ordenação
        if (order.endsWith("-desc")) {
            sort = Sort.by(Sort.Order.desc(order.replace("-desc", "")));
        }

        // Se houver um filtro de nome, busca produtos que contenham esse nome
        if (filtro != null && !filtro.isEmpty()) {
            return repository.findByNomeContains(filtro, sort);
        }

        // Caso não tenha filtro, retorna todos os produtos com a ordenação
        return repository.findAll(sort);
    }

    // Método para salvar um novo produto
    @PostMapping
    public Produto save(@RequestBody Produto produto) {
        return repository.save(produto);
    }
}
