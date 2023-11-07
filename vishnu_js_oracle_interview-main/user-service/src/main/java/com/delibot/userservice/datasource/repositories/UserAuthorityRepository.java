package com.delibot.userservice.datasource.repositories;

import com.delibot.userservice.datasource.entities.UserAuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthorityEntity,Long> {
    public List<UserAuthorityEntity> findAllByUserId(Long userId);
}
