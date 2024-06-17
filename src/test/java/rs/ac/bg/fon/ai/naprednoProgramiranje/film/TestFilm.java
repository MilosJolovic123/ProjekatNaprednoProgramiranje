package rs.ac.bg.fon.ai.naprednoProgramiranje.film;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rs.ac.bg.fon.ai.naprednoProgramiranje.director.Director;
import rs.ac.bg.fon.ai.naprednoProgramiranje.genre.Genre;
import rs.ac.bg.fon.ai.naprednoProgramiranje.review.Review;
import rs.ac.bg.fon.ai.naprednoProgramiranje.role.Role;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.sql.Date;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TestFilm {

    @InjectMocks
    private Film film;

    @Mock
    private Director director;

    @Mock
    private Genre genre;

    @Mock
    private Set<Role> roles;

    @Mock
    private Set<Review> reviews;

    private Validator validator;

    @BeforeEach
    void setUp() {
        film= Film.builder()
                .title("Inception")
                .description("A mind-bending thriller that delves into the realm of dreams.")
                .landOfOrigin("United States")
                .dateReleased(Date.valueOf("2010-07-16"))
                .film_director(director)
                .film_genre(genre)
                .roleSet(Collections.emptySet())
                .reviews(Collections.emptySet())
                .build();
        MockitoAnnotations.openMocks(this);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testFilmValidations() {
        Film validFilm = Film.builder()
                .title("Inception")
                .description("A mind-bending thriller that delves into the realm of dreams.")
                .landOfOrigin("United States")
                .dateReleased(Date.valueOf("2010-07-16"))
                .film_director(director)
                .film_genre(genre)
                .roleSet(Collections.emptySet())
                .reviews(Collections.emptySet())
                .build();

        Set<ConstraintViolation<Film>> violations = validator.validate(validFilm);
        assertTrue(violations.isEmpty(), "Film should be valid");
    }

    @Test
    void testFilmTitleTooShort() {
        Film invalidFilm = Film.builder()
                .title("In")
                .description("A mind-bending thriller that delves into the realm of dreams.")
                .landOfOrigin("United States")
                .dateReleased(Date.valueOf("2010-07-16"))
                .film_director(director)
                .film_genre(genre)
                .roleSet(Collections.emptySet())
                .reviews(Collections.emptySet())
                .build();

        Set<ConstraintViolation<Film>> violations = validator.validate(invalidFilm);
        assertFalse(violations.isEmpty(), "Film should be invalid due to short title");
    }

    @Test
    void testFilmTitleTooLong() {
        Film invalidFilm = Film.builder()
                .title("InceptionInceptionInceptionInceptionInceptionInceptionInceptionInception")
                .description("A mind-bending thriller that delves into the realm of dreams.")
                .landOfOrigin("United States")
                .dateReleased(Date.valueOf("2010-07-16"))
                .film_director(director)
                .film_genre(genre)
                .roleSet(Collections.emptySet())
                .reviews(Collections.emptySet())
                .build();

        Set<ConstraintViolation<Film>> violations = validator.validate(invalidFilm);
        assertFalse(violations.isEmpty(), "Film should be invalid due to long title");
    }

    @Test
    void testFilmDescriptionTooShort() {
        Film invalidFilm = Film.builder()
                .title("Inception")
                .description("Too short")
                .landOfOrigin("United States")
                .dateReleased(Date.valueOf("2010-07-16"))
                .film_director(director)
                .film_genre(genre)
                .roleSet(Collections.emptySet())
                .reviews(Collections.emptySet())
                .build();

        Set<ConstraintViolation<Film>> violations = validator.validate(invalidFilm);
        assertFalse(violations.isEmpty(), "Film should be invalid due to short description");
    }

    @Test
    void testFilmLandOfOriginTooShort() {
        Film invalidFilm = Film.builder()
                .title("Inception")
                .description("A mind-bending thriller that delves into the realm of dreams.")
                .landOfOrigin("US")
                .dateReleased(Date.valueOf("2010-07-16"))
                .film_director(director)
                .film_genre(genre)
                .roleSet(Collections.emptySet())
                .reviews(Collections.emptySet())
                .build();

        Set<ConstraintViolation<Film>> violations = validator.validate(invalidFilm);
        assertFalse(violations.isEmpty(), "Film should be invalid due to short land of origin");
    }

    @Test
    void testFilmLandOfOriginTooLong() {
        Film invalidFilm = Film.builder()
                .title("Inception")
                .description("A mind-bending thriller that delves into the realm of dreams.")
                .landOfOrigin("United States of America and all its territories and regions")
                .dateReleased(Date.valueOf("2010-07-16"))
                .film_director(director)
                .film_genre(genre)
                .roleSet(Collections.emptySet())
                .reviews(Collections.emptySet())
                .build();

        Set<ConstraintViolation<Film>> violations = validator.validate(invalidFilm);
        assertFalse(violations.isEmpty(), "Film should be invalid due to long land of origin");
    }
}