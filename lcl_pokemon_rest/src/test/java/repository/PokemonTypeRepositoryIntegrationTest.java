package repository;

import lcl.pokemon.config.DbConfig;
import lcl.pokemon.domain.PokemonType;
import lcl.pokemon.repository.PokemonTypeRepository;

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
public class PokemonTypeRepositoryIntegrationTest {

    private static final int TEST_NON_EXISTENT_TYPE_ID = 6;

    private static final int TEST_POKEMON_TYPE_ID = 1;

    private static final String TEST_POKEMON_TYPE_NAME = "fire";

    PokemonType expectedPokemonType = PokemonType.builder()
            .typeId(TEST_POKEMON_TYPE_ID)
            .name(TEST_POKEMON_TYPE_NAME).build();

    @Autowired
    private PokemonTypeRepository repository;

    @Test
    @DisplayName("Given an existing Pokemon Type ID the returned pokemon is equal to the expected")
    public void checkRepository(){
        PokemonType returnedPokemonType = repository.getPokemonTypeById(TEST_POKEMON_TYPE_ID);

        assertTrue("Assert returned pokemon type is equal to the expected",
                expectedPokemonType.equals(returnedPokemonType));
    }

    @Test
    @DisplayName("Given a non existing Pokemon Type ID null pointer exception is not thrown")
    public void assertNullPointerExceptionIsNotThrownAfterCallingEmptyPokemonType(){
        PokemonType returnedEmptyPokemonType = repository.getPokemonTypeById(TEST_NON_EXISTENT_TYPE_ID);

        assertNotNull("Returned pokemon should not be null", returnedEmptyPokemonType);
        assertTrue("Returned pokemon type is non existent",returnedEmptyPokemonType.getTypeId() == null);
        assertTrue("Returned pokemon name is non existent", returnedEmptyPokemonType.getName() == null);
    }
}
