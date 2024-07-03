package br.com.itilh.bdpedidos.sistemapedidos.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.itilh.bdpedidos.sistemapedidos.model.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, BigInteger> {
    List<Produto> findByDescricao(String descricao);
    
    List<Produto> findByDescricaoContainingIgnoreCase(String descricao);

    @Query("from Produto p order by p.id desc")
    List<Produto> findAllOrderById();
}
