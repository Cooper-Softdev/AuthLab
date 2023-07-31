package com.AuthLab.repos;

import com.AuthLab.models.HashUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashUserRepository extends JpaRepository<HashUser, Long> {
    public HashUser findHashUserByHashName(String hashName);
}
