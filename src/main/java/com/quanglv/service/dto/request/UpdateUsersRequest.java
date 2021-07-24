package com.quanglv.service.dto.request;

import com.quanglv.domain.first.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UpdateUsersRequest implements Serializable {

    private static final long serialVersionUID = 6641306186001226671L;
    private Users user;
}
