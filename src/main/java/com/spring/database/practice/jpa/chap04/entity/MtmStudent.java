package com.spring.database.practice.jpa.chap04.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = {"enrollments"})
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tbl_mtm_prac_student")
public class MtmStudent { // 학생

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "student_name")
    private String name; // 학생명

    @Column(name = "student_email")
    private String email; // 학생 이메일

    @OneToMany(mappedBy = "mtmStudent", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Enrollment> enrollments = new ArrayList<>();
}
