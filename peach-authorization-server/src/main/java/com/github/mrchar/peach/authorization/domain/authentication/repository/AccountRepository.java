package com.github.mrchar.peach.authorization.domain.authentication.repository;

import com.github.mrchar.peach.authorization.domain.authentication.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    @Query("select a from AccountEntity a where a.name = :name")
    Optional<AccountEntity> findOneByName(@Param("name") String name);
}