package com.spring.database.practice.jpa.chap04.repository;

import com.spring.database.practice.jpa.chap04.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
