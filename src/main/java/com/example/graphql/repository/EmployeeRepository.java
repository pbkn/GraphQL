package com.example.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.graphql.entity.Employee;

@Repository
@Transactional(isolation = Isolation.REPEATABLE_READ)
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
