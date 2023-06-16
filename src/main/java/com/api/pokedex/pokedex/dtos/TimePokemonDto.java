package com.api.pokedex.pokedex.dtos;

import com.api.pokedex.pokedex.models.TimePokemonModel;
import com.api.pokedex.pokedex.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimePokemonDto {
    private Long id;
    private String pokemon1;
    private String pokemon2;
    private String pokemon3;
    private String pokemon4;
    private String pokemon5;
    private String pokemon6;
    private Long userId;

    public static TimePokemonModel toObject(TimePokemonDto timePokemonDto, UserModel user) {
        return new TimePokemonModel(
                timePokemonDto.getPokemon1(),
                timePokemonDto.getPokemon2(),
                timePokemonDto.getPokemon3(),
                timePokemonDto.getPokemon4(),
                timePokemonDto.getPokemon5(),
                timePokemonDto.getPokemon6(),
                user
        );
    }

    public static TimePokemonDto toDTO(TimePokemonModel timePokemonModel) {
        return new TimePokemonDto(
                timePokemonModel.getId(),
                timePokemonModel.getPokemon1(),
                timePokemonModel.getPokemon2(),
                timePokemonModel.getPokemon3(),
                timePokemonModel.getPokemon4(),
                timePokemonModel.getPokemon5(),
                timePokemonModel.getPokemon6(),
                timePokemonModel.getUser().getId());
    }

    public static List<TimePokemonDto> listToDTO(List<TimePokemonModel> list) {

        return list.stream().map(TimePokemonDto::toDTO).toList();
    }
}
