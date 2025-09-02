package com.ginko.httpforward.repository;

import com.ginko.httpforward.entity.IdmUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdmUserRepository extends JpaRepository<IdmUser, Long> {
    IdmUser findByName(String name);
}
