package rs.ac.bg.fon.ai.naprednoProgramiranje.director;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rs.ac.bg.fon.ai.naprednoProgramiranje.film.Film;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestDirector {

    @InjectMocks
    private Director director;

    @Mock
    private Set<Film> films;

    private Validator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testDirectorValidations() {
        Director validDirector = Director.builder()
                .name("Steven")
                .lastName("Spielberg")
                .noOfOscars(3)
                .filmSet(Collections.emptySet())
                .build();

        Set<ConstraintViolation<Director>> violations = validator.validate(validDirector);
        assertTrue(violations.isEmpty(), "Director should be valid");
    }
    @Test
    void noOfOscarsNegative(){
        Director invalidDirector = Director.builder()
                .name("Steven")
                .lastName("Spielberg")
                .noOfOscars(-1)
                .filmSet(Collections.emptySet())
                .build();

        Set<ConstraintViolation<Director>> violations = validator.validate(invalidDirector);
        assertFalse(violations.isEmpty(), "Director should be invalid due negative Oscars");

    }
    @Test
    void testDirectorNameTooShort() {
        Director invalidDirector = Director.builder()
                .name("St")
                .lastName("Spielberg")
                .noOfOscars(3)
                .filmSet(Collections.emptySet())
                .build();

        Set<ConstraintViolation<Director>> violations = validator.validate(invalidDirector);
        assertFalse(violations.isEmpty(), "Director should be invalid due to short name");
    }

    @Test
    void testDirectorNameTooLong() {
        Director invalidDirector = Director.builder()
                .name("StevenStevenStevenSteven")
                .lastName("Spielberg")
                .noOfOscars(3)
                .filmSet(Collections.emptySet())
                .build();

        Set<ConstraintViolation<Director>> violations = validator.validate(invalidDirector);
        assertFalse(violations.isEmpty(), "Director should be invalid due to long name");
    }

    @Test
    void testDirectorLastNameTooShort() {
        Director invalidDirector = Director.builder()
                .name("Steven")
                .lastName("Sp")
                .noOfOscars(3)
                .filmSet(Collections.emptySet())
                .build();

        Set<ConstraintViolation<Director>> violations = validator.validate(invalidDirector);
        assertFalse(violations.isEmpty(), "Director should be invalid due to short last name");
    }

    @Test
    void testDirectorLastNameTooLong() {
        Director invalidDirector = Director.builder()
                .name("Steven")
                .lastName("SpielbergSpielbergSpielberg")
                .noOfOscars(3)
                .filmSet(Collections.emptySet())
                .build();

        Set<ConstraintViolation<Director>> violations = validator.validate(invalidDirector);
        assertFalse(violations.isEmpty(), "Director should be invalid due to long last name");
    }

    @Test
    void testDirectorNoOfOscars() {
        Director directorWithOscars = Director.builder()
                .name("Steven")
                .lastName("Spielberg")
                .noOfOscars(5)
                .filmSet(Collections.emptySet())
                .build();

        assertEquals(5, directorWithOscars.getNoOfOscars(), "Number of Oscars should be 5");
    }

    @Test
    void testDirectorFilmSet() {
        Director directorWithFilms = Director.builder()
                .name("Steven")
                .lastName("Spielberg")
                .noOfOscars(1)
                .filmSet(films)
                .build();

        when(films.size()).thenReturn(2);

        assertEquals(2, directorWithFilms.getFilmSet().size(), "Film set size should be 2");
        verify(films, times(1)).size();
    }
}
