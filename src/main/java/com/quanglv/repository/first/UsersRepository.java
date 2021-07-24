package com.quanglv.repository.first;

import com.quanglv.domain.first.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByUsernameAndStatus(String username, Long status);
}
