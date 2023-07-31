package com.AuthLab.repos;
import com.AuthLab.models.Hashy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashyRepository extends JpaRepository<Hashy, Long> {
    public Hashy getHashyByHashName(String hashName);
}
