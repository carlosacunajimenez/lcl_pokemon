package lcl.pokemon.repository;

import lcl.pokemon.domain.Pokemon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface IPokemon {

    Optional<Pokemon> getPokemonById(@Param("pokemonId") Integer pokemonId);
}
