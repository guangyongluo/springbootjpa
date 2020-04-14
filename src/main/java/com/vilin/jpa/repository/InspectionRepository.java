package com.vilin.jpa.repository;

import com.vilin.jpa.entity.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface InspectionRepository extends JpaRepository<Inspection, Long> {

    @Query(value = "select * from jpa_inspection where date(check_time) = :timestamp", nativeQuery = true)
    List<Inspection> findAllInspectionByDate(@Param("timestamp") String date);
}
