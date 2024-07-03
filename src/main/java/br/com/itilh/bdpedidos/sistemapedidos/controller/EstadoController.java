package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.itilh.bdpedidos.sistemapedidos.model.Estado;
import br.com.itilh.bdpedidos.sistemapedidos.repository.EstadoRepository;
import br.com.itilh.bdpedidos.sistemapedidos.util.ModoBusca;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class EstadoController {
    
    private final EstadoRepository repository;
    
    public EstadoController(EstadoRepository repository){
        this.repository = repository;
    }

    @GetMapping("/estados")
    public List<Estado> getAllEstados() {
        return (List<Estado>) repository.findAllOrderById();
    }

    @GetMapping("/estado/{id}")
    public ResponseEntity<Estado> getEstadoPorId(@PathVariable BigInteger id) {
        Optional<Estado> estado = repository.findById(id);
        if(estado.isPresent()) {
            return ResponseEntity.ok(estado.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("estado/nome/{nome}")
    public List<Estado> getEstadoByNome(@PathVariable String nome, @RequestParam(required = true) ModoBusca modo) {
        if(modo.equals(ModoBusca.nome)){
            return repository.findByNome(nome);
        } else if (modo.equals(ModoBusca.start)){
            return repository.findByNomeStartingWithIgnoreCase(nome);
        } else {
            return repository.findByNomeContainingIgnoreCase(nome);
        }
    }    
    
    // @GetMapping("/estado/{id}")
    // public Estado getEstadoById(@PathVariable BigInteger id) throws Exception{
    //     return repository.findById(id).orElseThrow(
    //         () -> new Exception("ID Inválido")
    //     );
    // }
    
    @PostMapping("/estado")
    public Estado postEstado(@RequestBody Estado estado) throws Exception{
        try{
            return repository.save(estado);
        } catch (Exception e){
            throw new Exception("Erro ao salvar o Estado. " + e.getMessage());
        }
    }

    @PutMapping("estado/{id}")
    public Estado putEstadoById(@PathVariable BigInteger id, @RequestBody Estado dados) throws Exception {  
        Optional<Estado> estado = repository.findById(id);
        if(estado.isPresent())
        {
            estado.get().setNome(dados.getNome());
            return repository.save(estado.get()); 
        }
        throw new Exception("Alteração não realizada.");
    }

    @DeleteMapping("/estado/{id}")    
    public String deleteEstadoById(@PathVariable BigInteger id) throws Exception{
        Optional<Estado> estado = repository.findById(id);
        if(estado.isPresent()){
            repository.delete(estado.get());
            return "Excluído";
        }
        throw new Exception("Não excluído. ");
    }
}
