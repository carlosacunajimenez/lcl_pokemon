package lcl.pokemon.repository;

import lcl.pokemon.domain.PokemonType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface IPokemonType {

    Optional<PokemonType> getPokemonTypeById(@Param("typeId") Integer typeId);
}
