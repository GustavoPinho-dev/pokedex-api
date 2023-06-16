package com.api.pokedex.pokedex.dtos;

import com.api.pokedex.pokedex.models.TimePokemonModel;
import com.api.pokedex.pokedex.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserRespostaDto {

    private String username;
    private String password;
    private List<TimePokemonModel> timesPokemon;


    public static UserRespostaDto toDTO(UserModel userModel) {
        return new UserRespostaDto(userModel.getUsername(), userModel.getPassword(), userModel.getTimePokemons());
    }

    public static List<UserRespostaDto> listToDTO(List<UserModel> list) {

        return list.stream().map(UserRespostaDto::toDTO).toList();
    }
}
