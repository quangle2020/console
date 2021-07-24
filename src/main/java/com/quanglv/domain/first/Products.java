package com.quanglv.domain.first;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products extends AbstractAuditingEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "product_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @Column(name = "id", nullable = false)
    @NotNull
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "url")
    private String url;

    @Column(name = "promotion")
    private Integer promotion;

    @Column(name = "status")
    private Integer status;
}

