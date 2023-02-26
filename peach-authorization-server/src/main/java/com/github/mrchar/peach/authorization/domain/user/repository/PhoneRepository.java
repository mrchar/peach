package com.github.mrchar.peach.authorization.domain.user.repository;

import com.github.mrchar.peach.authorization.domain.user.model.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {

    @Query("select phone from PhoneEntity phone where phone.number = :phoneNumber")
    Optional<PhoneEntity> findOneByNumber(String phoneNumber);
}
