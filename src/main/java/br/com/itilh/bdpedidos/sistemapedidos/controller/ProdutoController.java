package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.itilh.bdpedidos.sistemapedidos.model.Produto;
import br.com.itilh.bdpedidos.sistemapedidos.repository.ProdutoRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ProdutoController {
    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository){
        this.repository = repository;
    }

    @GetMapping("/produtos")
    public List<Produto> getAllProdutos() {
        return (List<Produto>) repository.findAllOrderById();
    }
    
    @GetMapping("produto/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable BigInteger id) {
        Optional<Produto> produto = repository.findById(id);

        if(produto.isPresent()){
            return ResponseEntity.ok(produto.get());
        } else {
            return ResponseEntity.noContent().build(); 
        }
    }
    
    @PostMapping("/produto")
    public Produto postProduto(@RequestBody Produto produto) throws Exception {
        try{
            return repository.save(produto);
        } catch(Exception e){
            throw new Exception("Não foi possível efetuar o cadastro." + e.getMessage());
        }
    }
    
    @PutMapping("produto/{id}")
    public Produto putProduto(@PathVariable BigInteger id, @RequestBody Produto produto) throws Exception{
        try{
            return repository.save(produto);
        } catch(Exception e){
            throw new Exception("Não foi possível alterar o registro." + e.getMessage());
        }
    }

    @DeleteMapping("/produto/{id}")
    public String deleteProdutoById(@PathVariable BigInteger id) throws Exception{
        try{ 
            repository.deleteById(id);
            return "Excluído";
        }catch (Exception ex){
            throw new Exception("Não foi possível excluir o produto." + ex.getMessage());
        }
    }
}
