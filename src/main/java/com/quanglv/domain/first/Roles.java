package com.quanglv.domain.first;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles extends AbstractAuditingEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "roles_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_seq")
    @Column(name = "role_id", nullable = false)
    @NotNull
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_code")
    private String roleCode;

//    @ManyToMany(mappedBy = "roles")
//    private List<Users> users = new ArrayList<>();
}
