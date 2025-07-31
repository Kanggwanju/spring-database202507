package com.spring.database.jpa.chap04.pokemon.entity;

import com.spring.database.jpa.chap01.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/*CREATE TABLE pokemon (
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL COMMENT '포켓몬 이름',
    type1 VARCHAR(50) NOT NULL COMMENT '타입 1',
    hp INT NOT NULL COMMENT 'HP',
    attack INT NOT NULL COMMENT '공격력',
    defense INT NOT NULL COMMENT '방어력',
    speed INT NOT NULL COMMENT '속도',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='포켓몬 정보';*/

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tbl_pokemon")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;  // PK를 string으로

    @Column(nullable = false)
    private String name; // 포켓몬 이름

    @Column(nullable = false)
    private String type1; // 포켓몬 타입

    @Column(nullable = false)
    private int hp; // 생명력

    @Column(nullable = false)
    private int attack; // 공격력

    @Column(nullable = false)
    private int defense; // 방어력

    @Column(nullable = false)
    private int speed; // 속도

    // 수정용 편의 메서드
    public void updateHp(int hp) {
        this.hp = hp;
    }

    public void changeStatus(int hp, int attack, int defense, int speed) {
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }
}
