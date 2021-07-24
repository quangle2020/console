package com.quanglv.service.dto.request;

import com.quanglv.domain.first.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class UpdateRolesRequest {
    private List<Roles> roles;
}
