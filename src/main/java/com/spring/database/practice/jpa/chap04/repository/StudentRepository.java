package com.spring.database.practice.jpa.chap04.repository;

import com.spring.database.practice.jpa.chap04.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
