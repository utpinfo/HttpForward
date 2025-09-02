package com.ginko.httpforward.repository;

import com.ginko.httpforward.entity.UfmHanlpDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UfmHanlpDictRepository extends JpaRepository<UfmHanlpDict, Long> {
    //UfmHanlpDict findByName(String name);
}
