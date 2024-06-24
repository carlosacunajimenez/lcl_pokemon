package repository;

import lcl.pokemon.domain.PokemonType;
import lcl.pokemon.repository.IPokemonType;
import lcl.pokemon.repository.PokemonTypeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(MockitoExtension.class)
public class PokemonTypeUnitTest {

    private static final Random random = new Random();

    private static final int TEST_POKEMON_TYPE_ID = random.nextInt(100);

    private static final String TEST_POKEMON_TYPE_NAME = "TEST TYPE";

    PokemonType expectedPokemonType = PokemonType.builder()
            .typeId(TEST_POKEMON_TYPE_ID)
            .name(TEST_POKEMON_TYPE_NAME)
            .build();

    PokemonType emptyPokemonType = PokemonType.builder()
            .build();

    @Mock
    private IPokemonType mapper;

    @InjectMocks
    private PokemonTypeRepository repository;

    @Test
    @DisplayName("Assert that the returned and the expected pokemon type are the same")
    public void pokemonTypeRepositoryReturnExpectedPokemonType(){
        when(mapper.getPokemonTypeById(TEST_POKEMON_TYPE_ID)).thenReturn(Optional.of(expectedPokemonType));

        PokemonType returnedPokemon = repository.getPokemonTypeById(TEST_POKEMON_TYPE_ID);
        assertTrue("Assert that both pokemon are equals", expectedPokemonType.equals(returnedPokemon));
    }

    @Test
    @DisplayName("Assert that null pointer is not thrown")
    public void pokemonTypeRepositoryDoesNotThrowNullPointerException(){
        when(mapper.getPokemonTypeById(TEST_POKEMON_TYPE_ID)).thenReturn(Optional.of(emptyPokemonType));

        PokemonType returnedPokemon = repository.getPokemonTypeById(TEST_POKEMON_TYPE_ID);

        assertDoesNotThrow(() ->{
            assertNotNull("Assert that returned pokemon type is not null",returnedPokemon);
        });
    }
}
