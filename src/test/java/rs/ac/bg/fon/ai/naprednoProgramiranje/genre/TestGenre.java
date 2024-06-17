package rs.ac.bg.fon.ai.naprednoProgramiranje.genre;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import rs.ac.bg.fon.ai.naprednoProgramiranje.film.Film;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Collections;
import java.util.Set;

//import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class TestGenre {

    @InjectMocks
    private Genre genre;

    @Mock
    private Set<Film> films;

    private Validator validator;

    @BeforeEach
    void setUp() {
        genre = Genre.builder()
                .genreDesctiption("A genre that describes action films.")
                .filmSet(Collections.emptySet())
                .build();
        MockitoAnnotations.openMocks(this);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testGenreValidations() {
        Genre validGenre = Genre.builder()
                .genreDesctiption("A genre that describes action films.")
                .filmSet(Collections.emptySet())
                .build();

        Set<ConstraintViolation<Genre>> violations = validator.validate(validGenre);
        assertTrue(violations.isEmpty(), "Genre should be valid");
    }

    @Test
    void testGenreDescriptionTooShort() {
        Genre invalidGenre = Genre.builder()
                .genreDesctiption("Gen")
                .filmSet(Collections.emptySet())
                .build();

        Set<ConstraintViolation<Genre>> violations = validator.validate(invalidGenre);
        assertFalse(violations.isEmpty(), "Genre should be invalid due to short description");
    }

    @Test
    void testGenreDescriptionTooLong() {
        String longDescription = "A".repeat(501);
        Genre invalidGenre = Genre.builder()
                .genreDesctiption(longDescription)
                .filmSet(Collections.emptySet())
                .build();

        Set<ConstraintViolation<Genre>> violations = validator.validate(invalidGenre);
        assertFalse(violations.isEmpty(), "Genre should be invalid due to long description");
    }

    @Test
    void testGenreFilmSet() {
        Genre genreWithFilms = Genre.builder()
                .genreDesctiption("A genre that describes action films.")
                .filmSet(films)
                .build();

        when(films.size()).thenReturn(2);

        assertEquals(2, genreWithFilms.getFilmSet().size(), "Film set size should be 2");
        Mockito.verify(films, times(1)).size();
    }
}
