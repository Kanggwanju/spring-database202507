package com.spring.database.pokemon.entity;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

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
