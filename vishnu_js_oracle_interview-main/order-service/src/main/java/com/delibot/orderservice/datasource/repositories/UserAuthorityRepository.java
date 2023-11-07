package com.delibot.orderservice.datasource.repositories;

import com.delibot.orderservice.datasource.entities.UserAuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthorityEntity,Long> {
    public List<UserAuthorityEntity> findAllByUserId(Long userId);
}
