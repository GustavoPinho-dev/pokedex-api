package com.api.pokedex.pokedex.models;

import com.api.pokedex.pokedex.dtos.TimePokemonDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Table(name = "TB_TIME_POKEMON")
public class TimePokemonModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pokemon1;
    private String pokemon2;
    private String pokemon3;
    private String pokemon4;
    private String pokemon5;
    private String pokemon6;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    public TimePokemonModel(String pokemon1, String pokemon2, String pokemon3, String pokemon4, String pokemon5, String pokemon6, UserModel user) {
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.pokemon3 = pokemon3;
        this.pokemon4 = pokemon4;
        this.pokemon5 = pokemon5;
        this.pokemon6 = pokemon6;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Time Pokemon: " +
                "Pokemon 1 = '" + pokemon1 + '\'' +
                ", Pokemon 2 = '" + pokemon2 + '\'' +
                ", Pokemon3 = '" + pokemon3 + '\'' +
                ", Pokemon 4 = '" + pokemon4 + '\'' +
                ", Pokemon 5 = '" + pokemon5 + '\'' +
                ", Pokemon 6 = '" + pokemon6 + '\'' +
                ", User = " + user +
                '}';
    }
}
