package com.spring.database.practice.jpa.chap04.entity;

import com.spring.database.chap01.entity.Book;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString(exclude = {"course", "student"})
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tbl_mtm_prac_enrollment")
public class Enrollment { // 수강신청

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private Long id;

    @CreationTimestamp
    @Column(name = "enrollment_date")
    private LocalDateTime enrollmentDate; // 수강신청일

    @Column(name = "progress_rate")
    private Integer progressRate; // 진도율 0-100

    private Boolean completed; // 완료 여부
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

}
