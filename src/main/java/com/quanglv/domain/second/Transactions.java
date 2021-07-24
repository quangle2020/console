package com.quanglv.domain.second;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transactions implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @NotNull
    private String id;

    @Column(name = "price")
    private BigDecimal price;

}

