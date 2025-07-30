package com.spring.database.practice.jpa.chap04.repository;

import com.spring.database.practice.jpa.chap04.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // 순수 SQL
    // 학생 이름과 과목 이름으로 Enrollment 조회
    @Query(value = """
                    SELECT e.*
                    FROM tbl_mtm_prac_enrollment e
                    JOIN tbl_mtm_prac_student s ON e.student_id = s.student_id
                    JOIN tbl_mtm_prac_course c ON e.course_id = c.course_id
                    WHERE s.student_name = ?1
                        AND c.title = ?2
                    """, nativeQuery = true)
    Optional<Enrollment> findByStudentNameAndCourseTitle(String name, String title);

    // JPQL
    // 학생 이름과 과목 이름으로 Enrollment 조회
    @Query(value = """
                    SELECT e FROM Enrollment e
                    WHERE e.student.name = ?1
                        AND e.course.title = ?2
                    """)
    List<Enrollment> getEnrollmentByNameAndTitle(String name, String title);


}
