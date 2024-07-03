package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.itilh.bdpedidos.sistemapedidos.model.Municipio;
import br.com.itilh.bdpedidos.sistemapedidos.repository.MunicipioRepository;

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
public class MunicipioController {

    private final MunicipioRepository repository;
    
    public MunicipioController (MunicipioRepository repository){
        this.repository = repository;
    }

    @GetMapping("/municipios")
    public List<Municipio> getAllMunicipios() {
        return (List<Municipio>) repository.findAllOrderById();
    }

    @GetMapping("/municipio/{id}")
    public ResponseEntity<Municipio> getMunicipioById(@PathVariable BigInteger id) {
        Optional<Municipio> municipio = repository.findById(id); 
        if(municipio.isPresent()){
            return ResponseEntity.ok(municipio.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("municipios/estado/{nome}")
    public List<Municipio> getMunicipioPorEstadoId(@PathVariable String nome) {
        return (List<Municipio>) repository.findByEstadoNomeIgnoreCaseContaining(nome);
    }

    @PostMapping("/municipio")
    public Municipio postMunicipio(@RequestBody Municipio municipio) throws Exception {
        try{
            return repository.save(municipio);
        } catch(Exception e ){
            throw new Exception("Não foi possível salvar seu registro." + e.getMessage());
        }     
    }

    @PutMapping("municipio/{id}")
    public Municipio putMunicipio(@PathVariable BigInteger id, @RequestBody Municipio municipio) throws Exception {
        try{
            return repository.save(municipio);
        } catch(Exception e ){
            throw new Exception("Não foi possível salvar seu registro." + e.getMessage());
        }
    }

    @DeleteMapping("/municipio/{id}")
    public String deleteMunicipio(@PathVariable BigInteger id) throws Exception{
        try{ 
            repository.deleteById(id);
            return "Excluído";
        }catch (Exception ex){
            throw new Exception("Não foi possível excluir o município." + ex.getMessage());
        }
    }
}