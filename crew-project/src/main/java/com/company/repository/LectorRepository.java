package com.company.repository;

import com.company.entity.LectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<LectorEntity, Integer> {
    @Query(value = "SELECT id, name, salary, degree, department FROM lector WHERE name LIKE %:name%", nativeQuery = true)
    List<LectorEntity> findAllByNameIsLike(@Param("name") String name);

    List<LectorEntity> findAllByDepartment(Integer departmentName);
}
