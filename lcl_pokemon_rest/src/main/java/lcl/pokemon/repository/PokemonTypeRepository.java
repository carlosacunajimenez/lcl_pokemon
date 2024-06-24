package lcl.pokemon.repository;

import lcl.pokemon.domain.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PokemonTypeRepository {

    private final IPokemonType mapper;

    @Autowired
    PokemonTypeRepository(IPokemonType mapper){
        this.mapper = mapper;
    }

    public PokemonType getPokemonTypeById(Integer id){
        return mapper.getPokemonTypeById(id).orElseGet(() -> PokemonType.builder().build());
    }
}
