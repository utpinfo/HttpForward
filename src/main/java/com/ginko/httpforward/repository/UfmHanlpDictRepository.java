package com.ginko.httpforward.repository;

import com.ginko.httpforward.entity.UfmHanlpDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UfmHanlpDictRepository extends JpaRepository<UfmHanlpDict, Long> {
    //UfmHanlpDict findByName(String name);
    @Query("SELECT u FROM UfmHanlpDict u WHERE u.trDate = :date")
    UfmHanlpDict findByHello(@Param("date") long id);

    //@Query(value = "SELECT * FROM UFM_HANLP_DICT u WHERE trunc(u.TR_DATE) >= trunc(:start)", nativeQuery = true)
    @Query("SELECT u FROM UfmHanlpDict u WHERE u.trDate >= :start")
    List<UfmHanlpDict> findTrDateAfter(@Param("start") Date start);
}
