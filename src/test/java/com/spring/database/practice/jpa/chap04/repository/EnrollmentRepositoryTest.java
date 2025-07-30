package com.spring.database.practice.jpa.chap04.repository;

import com.spring.database.jpa.pokemon.service.PokemonServiceJpa;
import com.spring.database.practice.jpa.chap04.entity.Course;
import com.spring.database.practice.jpa.chap04.entity.Student;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableJpaRepositories(basePackages = "com.spring.database.practice.jpa.chap04.repository")
@EntityScan(basePackages = "com.spring.database.practice.jpa.chap04.entity")
@Transactional
@Rollback(false)
class EnrollmentRepositoryTest {

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager em;

    @MockBean
    PokemonServiceJpa pokemonServiceJpa;

    private Student student1;
    private Student student2;
    private Student student3;
    private Course course1;
    private Course course2;
    private Course course3;

    @BeforeEach
    void setUp() {
        student1 = Student.builder().name("하치와레").email("123@naver.com").build();
        student2 = Student.builder().name("치이카와").email("1234@google.com").build();
        student3 = Student.builder().name("우사기").email("AAA@naver.com").build();

        course1 = Course.builder().title("Spring Boot").instructor("김철수").price(3000).build();
        course2 = Course.builder().title("React 심화").instructor("이영희").price(4000).build();
        course3 = Course.builder().title("SQL").instructor("홍순구").price(5000).build();

        studentRepository.saveAllAndFlush(
                List.of(student1, student2, student3)
        );

        courseRepository.saveAllAndFlush(
                List.of(course1, course2, course3)
        );
    }

    @Test
    @DisplayName("1. 기본 데이터 생성 및 조회")
    void saveAndFindTest() {
        //given


        //when

        //then
    }



}