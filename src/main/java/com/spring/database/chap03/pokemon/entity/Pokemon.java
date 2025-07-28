package com.spring.database.chap03.pokemon.entity;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

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
public class Pokemon {
    private Long id;
    private String name;
    private String type1;
    private int hp;
    private int attack;
    private int defense;
    private int speed;

    public Pokemon(ResultSet rs) throws SQLException {
        this.id = rs.getLong("id");
        this.name = rs.getString("name");
        this.type1 = rs.getString("type1");
        this.hp = rs.getInt("hp");
        this.attack = rs.getInt("attack");
        this.defense = rs.getInt("defense");
        this.speed = rs.getInt("speed");
    }
}
