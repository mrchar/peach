package com.github.mrchar.peach.authorization.domain.user.repository;

import com.github.mrchar.peach.authorization.domain.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
