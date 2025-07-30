package com.spring.database.practice.jpa.chap04.repository;

import com.spring.database.practice.jpa.chap04.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
