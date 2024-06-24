package lcl.pokemon.repository;

import lcl.pokemon.domain.Pokemon;
import lcl.pokemon.domain.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PokemonRepository {

    private final IPokemon mapper;

    @Autowired
    PokemonRepository(IPokemon mapper){
        this.mapper = mapper;
    }

    public Pokemon getPokemonById(Integer id){
        return mapper.getPokemonById(id).orElseGet(() -> Pokemon.builder().build());
    }
}
