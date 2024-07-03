package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.itilh.bdpedidos.sistemapedidos.model.Cliente;
import br.com.itilh.bdpedidos.sistemapedidos.repository.ClienteRepository;

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
public class ClienteController {

    private final ClienteRepository repository;

    public ClienteController(ClienteRepository repository){
        this.repository = repository;
    }

    @GetMapping("/clientes")
    public List<Cliente> getAllClientes() {
        return (List<Cliente>) repository.findAllOrderById();
    }
    
    @GetMapping("/cliente/{id}")
    public ResponseEntity<Cliente> getClientePorId(@PathVariable BigInteger id) {
        Optional<Cliente> cliente = repository.findById(id);

        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/cliente/nome/{nome}")
    public List<Cliente> getClienteByName(@PathVariable String nome) {
        return (List<Cliente>) repository.findByNomeRazaoSocialContainingIgnoreCase(nome);
    }
    
    @PostMapping("/cliente")
    public Cliente postCliente(@RequestBody Cliente cliente) throws Exception {
        try{
            return repository.save(cliente);
        } catch(Exception e){
            throw new Exception("Não foi possível efetuar o cadastro." + e.getMessage());
        }
    }

    @PutMapping("cliente/{id}")
    public Cliente putCliente(@PathVariable BigInteger id, @RequestBody Cliente cliente) throws Exception {
        try{
            return repository.save(cliente);
        } catch(Exception e){
            throw new Exception("Não foi possível efetuar a alteração." + e.getMessage());
        }
    }
    
    @DeleteMapping("/cliente/{id}")
    public String deleteClienteById(@PathVariable BigInteger id) throws Exception{
        try{ 
            repository.deleteById(id);
            return "Excluído";
        }catch (Exception ex){
            throw new Exception("Não foi possível excluir o cliente." + ex.getMessage());
        }
    }
}
