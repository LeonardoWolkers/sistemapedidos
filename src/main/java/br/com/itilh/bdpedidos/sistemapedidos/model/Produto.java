package br.com.itilh.bdpedidos.sistemapedidos.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor

@Entity
@Table(name = "tb_produtos")
public class Produto {
    @Id
    @SequenceGenerator(name = "seqProd", sequenceName = "tb_produtos_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProd")
    private BigInteger id;

    @Column(name = "tx_descricao")
    private String     descricao;

    @Column(name = "dbl_quantidade_estoque")
    private Double     qtdEstoque;

    @Column(name = "nu_preco_unidade_atual")
    private BigDecimal precoUnidAtual;

    @Column(name = "bo_ativo")
    private Boolean    ativo;
}
