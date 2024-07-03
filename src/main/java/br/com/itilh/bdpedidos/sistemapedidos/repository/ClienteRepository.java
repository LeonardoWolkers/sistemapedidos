package br.com.itilh.bdpedidos.sistemapedidos.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.itilh.bdpedidos.sistemapedidos.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository <Cliente, BigInteger> {
    List<Cliente> findByNomeRazaoSocial(String nomeRazaoSocial);

    List<Cliente> findByNomeRazaoSocialContainingIgnoreCase(String nomeRazaoSocial);

    List<Cliente> findByCpfContaining(String cpf);

    List<Cliente> findByCnpjContaining(String cnpj);

    @Query("from Cliente c order by c.id desc")
    List<Cliente> findAllOrderById();
}
