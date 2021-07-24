package com.quanglv.web.rest;

import com.quanglv.repository.first.RolesRepository;
import com.quanglv.service.RolesService;
import com.quanglv.service.dto.request.UpdateRolesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RolesResource {

    @Autowired
    private RolesService rolesService;

    @Autowired
    private RolesRepository rolesRepository;

    @PostMapping(value = "/update-roles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Long>> getEmployees(@RequestBody UpdateRolesRequest request) {
        return ResponseEntity.ok(rolesService.saveRoles(request.getRoles()));
    }

    @PostMapping(value = "/get-all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getEmployees() {
        return ResponseEntity.ok((rolesRepository.findAll()));
    }
}
