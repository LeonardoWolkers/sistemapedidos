package br.com.itilh.bdpedidos.sistemapedidos.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.itilh.bdpedidos.sistemapedidos.model.Municipio;

@Repository
public interface MunicipioRepository extends CrudRepository<Municipio, BigInteger> {
    List<Municipio> findByEstadoId(BigInteger id);
    List<Municipio> findByEstadoNomeIgnoreCaseContaining(String nome);

    @Query("from Municipio m order by m.id desc")
    List<Municipio> findAllOrderById();
}
