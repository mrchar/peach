package com.github.mrchar.peach.authorization.domain.user.repository;

import com.github.mrchar.peach.authorization.domain.user.model.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<EmailEntity, Long> {
    @Query("select email from EmailEntity email where email.address = :emailAddress")
    Optional<EmailEntity> findOneByAddress(String emailAddress);
}
