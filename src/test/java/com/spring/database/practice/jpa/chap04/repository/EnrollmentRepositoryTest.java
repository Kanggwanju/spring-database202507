package com.spring.database.practice.jpa.chap04.repository;

import com.spring.database.practice.jpa.chap04.entity.Course;
import com.spring.database.practice.jpa.chap04.entity.Enrollment;
import com.spring.database.practice.jpa.chap04.entity.MtmStudent;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class EnrollmentRepositoryTest {

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    StudentMtmRepository studentMtmRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager em;

    private MtmStudent student1;
    private MtmStudent student2;
    private MtmStudent student3;
    private Course course1;
    private Course course2;
    private Course course3;

    @BeforeEach
    void setUp() {
        student1 = MtmStudent.builder().name("하치와레").email("123@naver.com").build();
        student2 = MtmStudent.builder().name("치이카와").email("1234@google.com").build();
        student3 = MtmStudent.builder().name("우사기").email("AAA@naver.com").build();

        course1 = Course.builder().title("Spring Boot").instructor("김철수").price(3000).build();
        course2 = Course.builder().title("React 심화").instructor("이영희").price(4000).build();
        course3 = Course.builder().title("SQL").instructor("홍순구").price(5000).build();

        studentMtmRepository.saveAllAndFlush(
                List.of(student1, student2, student3)
        );

        courseRepository.saveAllAndFlush(
                List.of(course1, course2, course3)
        );

        Enrollment enrollment = Enrollment.builder()
                .progressRate(25)
                .completed(true)
                .mtmStudent(student1)
                .course(course1)
                .build();

        Enrollment enrollment2 = Enrollment.builder()
                .progressRate(50)
                .completed(false)
                .mtmStudent(student1)
                .course(course2)
                .build();
        Enrollment enrollment3 = Enrollment.builder()
                .progressRate(75)
                .completed(true)
                .mtmStudent(student2)
                .course(course1)
                .build();

        Enrollment saved = enrollmentRepository.save(enrollment);
        Enrollment saved2 = enrollmentRepository.save(enrollment2);
        Enrollment saved3 = enrollmentRepository.save(enrollment3);

        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("1. 기본 데이터 생성 및 조회")
    void saveAndFindTest() {
        //given
        List<Enrollment> enrollments = enrollmentRepository.findAll();

        //when

        // 하치와레가 수강신청한 강의 수
        long hachiCount = enrollments.stream()
                .filter(e -> e.getMtmStudent().getName().equals("하치와레"))
                .count();

        // Spring Boot 강의를 수강신청한 학생 수
        long springBootCount = enrollments.stream()
                .filter(e -> e.getCourse().getTitle().equals("Spring Boot"))
                .count();

        //then
        assertEquals(2, hachiCount);
        System.out.println("\n\n하치와레가 수강신청한 강의수 = " + hachiCount);
        assertEquals(2, springBootCount);
        System.out.println("\n\nSpring Boot 강의를 수강신청한 학생수 = " + springBootCount);
    }


    @Test
    @DisplayName("하치와레의 \"Spring Boot\" 수강신청의 진도율을 85%로 업데이트")
    void updateTest() {
        //given

        // 학생 이름과 과목 이름으로 Enrollment를 찾음
        List<Enrollment> founds = enrollmentRepository
                .getEnrollmentByNameAndTitle("하치와레", "Spring Boot");

        // selectedEnrollment는 영속 상태 (find 등으로 조회한 객체)
        Enrollment selectedEnrollment = founds.get(0);


        //when

        // JPA가 영속성 컨텍스트에서 해당 객체가 변경되었음을 감지
        // -> em.flush() 혹은 커밋 시점에 UPDATE SQL 실행
        selectedEnrollment.setProgressRate(85);

        // UPDATE SQL 실행!
        em.flush();
        em.clear();

        //then
        Enrollment updated = enrollmentRepository
                .findById(selectedEnrollment.getId())
                .orElseThrow();

        assertEquals(85, updated.getProgressRate());
        System.out.println("수정된 진도율 = " + updated.getProgressRate());


        /*
        //when
        Enrollment found = enrollmentRepository
                .findByStudentNameAndCourseTitle("하치와레", "Spring Boot")
                .orElseThrow();

        found.setProgressRate(85);
        em.flush();
        em.clear();
        Enrollment fen = enrollmentRepository.findById(1L).orElseThrow();

        //then
        assertEquals(85, fen.getProgressRate());
        */
    }


    @Test
    @DisplayName("완료된 수강신청 조회")
    void findCompletedEnrollmentTest() {
        //given
        List<Enrollment> enrollments = enrollmentRepository.findAll();

        //when
        // 하치와레의 "React 심화" 수강신청을 완료상태로 변경
        enrollments.stream()
                .filter(e ->
                        e.getCourse().getTitle().equals("React 심화") &&
                        e.getMtmStudent().getName().equals("하치와레")
                )
                .forEach(e -> e.setCompleted(true));

        // UPDATE SQL 실행!
        em.flush();
        em.clear();

        //then
        // completed가 true인 수강신청만 조회했을 때 3건인지 확인
        long count = enrollmentRepository.findAll().stream()
                .filter(e -> e.getCompleted() == true)
                .count();

        assertEquals(3, count);
    }

    
    @Test
    @DisplayName("진도율 기준 조회")
    void findByProgressRateTest() {
        //given
        List<Enrollment> enrollments = enrollmentRepository.findAll();

        //when
        long count = enrollments.stream()
                .filter(e -> e.getProgressRate() >= 70)
                .count();

        //then
        assertEquals(1, count);
    }
    
    
    @Test
    @DisplayName("연관관계 삭제")
    void deleteTest() {
        //given
        Course course = courseRepository.findById(1L).orElseThrow();
        List<Enrollment> enrollments = course.getEnrollments();

        // 과목1의 수강신청 리스트 확인
        System.out.println("\n\ncourse1's enrollments = " + enrollments);

        //when
        courseRepository.delete(course);

        em.flush();
        em.clear();
        
        //then
        List<Enrollment> enrollmentList = enrollmentRepository.findAll();
        System.out.println("\n\nafter user deletion enrollmentList = " + enrollmentList);
        assertEquals(1, enrollmentList.size());

    }

}