package com.spring.database.practice.jpa.chap04.repository;

import com.spring.database.practice.jpa.chap04.entity.MtmStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentMtmRepository extends JpaRepository<MtmStudent, Long> {
}
