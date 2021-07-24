package com.quanglv.service.impl;

import com.quanglv.domain.first.Roles;
import com.quanglv.repository.first.RolesRepository;
import com.quanglv.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public List<Long> saveRoles(List<Roles> roles) {
        List<Long> response = new ArrayList<>();
        roles.forEach(u ->{
            Roles user = rolesRepository.save(u);
            response.add(user.getRoleId());
        });
        return response;
    }
}
