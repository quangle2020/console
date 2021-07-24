package com.quanglv.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class EmployeesDTO extends BaseDTO implements Serializable {

    /**
     * The serialVersionUID
     */
    private static final long serialVersionUID = 4659468581873271982L;

    /**
     * The newsId
     */
    private Long employeeId;

    /**
     * The tittle
     */
    private String numberPhone;

    /**
     * The tittle
     */
    private String fullname;
    private String address;

    /**
     * The content
     */
    private String birthOfDate;

    private Long departmentId;

    private String email;


}
