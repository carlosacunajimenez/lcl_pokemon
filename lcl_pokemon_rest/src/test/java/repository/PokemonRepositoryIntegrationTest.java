package repository;

import lcl.pokemon.config.DbConfig;
import lcl.pokemon.domain.Pokemon;
import lcl.pokemon.repository.PokemonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DbConfig.class)
public class PokemonRepositoryIntegrationTest {

    private static final int TEST_NON_EXISTENT_ID = 9;

    private static final int TEST_POKEMON_ID = 1;

    private static final String TEST_POKEMON_NAME = "Charmander";

    private static final String TEST_POKEMON_GENDER = "M";

    Pokemon expectedPokemon = Pokemon.builder()
            .pokemonId(TEST_POKEMON_ID)
            .name(TEST_POKEMON_NAME)
            .gender(TEST_POKEMON_GENDER).build();
    @Autowired
    private PokemonRepository pokemonRepository;

    @Test
    @DisplayName("Given an existing Pokemon ID the returned pokemon is equal to the expected")
    public void checkRepository(){
        Pokemon returnedPokemon = pokemonRepository.getPokemonById(TEST_POKEMON_ID);

        assertTrue("Assert returned pokemon is equal to the expected",
                expectedPokemon.equals(returnedPokemon));
    }
    @Test
    @DisplayName("Given a non existing Pokemon Type ID null pointer exception is not thrown")
    public void assertNullPointerExceptionIsNotThrownAfterCallingEmptyPokemonType(){
        Pokemon returnedEmptyPokemon = pokemonRepository.getPokemonById(TEST_NON_EXISTENT_ID);

        assertNotNull("Returned pokemon should not be null", returnedEmptyPokemon);
        assertTrue("Returned pokemon is non existent", returnedEmptyPokemon.getPokemonId() == null);
        assertTrue("Returned pokemon name is non existent", returnedEmptyPokemon.getName() == null);
        assertTrue("Returned pokemon gender is non existent", returnedEmptyPokemon.getGender()== null);
    }

}
