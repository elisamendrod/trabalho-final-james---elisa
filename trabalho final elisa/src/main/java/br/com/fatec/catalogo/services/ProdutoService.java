package br.com.fatec.catalogo.services;

import br.com.fatec.catalogo.models.ProdutoModel;
import br.com.fatec.catalogo.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoModel> listarTodos() {

        return repository.findAll();
    }
    
    public List<ProdutoModel> listarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public ProdutoModel buscarPorId(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));
    }

    public List<ProdutoModel> listarPorCategoria(Long idCategoria) {
        return repository.findByCategoriaIdCategoria(idCategoria);
    }

    public List<ProdutoModel> listarHistoricoAtualizacoes() {
        return repository.findAllByOrderByDataCadastroDesc();
    }

    
    @Transactional
    public void salvar(ProdutoModel produto) {
        if (produto.getQuantidade() == null || produto.getQuantidade() < 0) {
            throw new IllegalArgumentException("A quantidade em estoque não pode ser negativa.");
        }

        
        if (produto.getIdProduto() == 0 && repository.existsByNome(produto.getNome())) {
            throw new IllegalArgumentException("Já existe um produto com este nome.");
        }
        
        
        produto.setDataCadastro(LocalDateTime.now());

        repository.save(produto);
    }

    @Transactional
    public void excluir(long id) {
        repository.deleteById(id);
    }
}
