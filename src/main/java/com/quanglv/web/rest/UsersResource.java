package com.quanglv.web.rest;

import com.quanglv.service.UsersService;
import com.quanglv.service.dto.request.UpdateUsersRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UsersResource {

    @Autowired
    private UsersService usersService;

    @PostMapping(value = "/update-users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> getEmployees(@RequestBody UpdateUsersRequest request) {
        return ResponseEntity.ok(usersService.saveUsers(request.getUser()));
    }
}
