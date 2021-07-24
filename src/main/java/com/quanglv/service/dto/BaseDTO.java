package com.quanglv.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BaseDTO implements Serializable {

    private Date createdDate ;

    private Long createdUser;

    private Date updatedDate ;

    private Long updatedUser;
}
