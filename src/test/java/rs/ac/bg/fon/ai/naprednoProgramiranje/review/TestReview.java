package rs.ac.bg.fon.ai.naprednoProgramiranje.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rs.ac.bg.fon.ai.naprednoProgramiranje.film.Film;
import rs.ac.bg.fon.ai.naprednoProgramiranje.user.AppUser;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.sql.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {

    @InjectMocks
    private Review review;

    @Mock
    private Film film;

    @Mock
    private AppUser user;

    private Validator validator;

    @BeforeEach
    void setUp() {
        review = Review.builder()
                .comment("Excellent movie with great acting!")
                .grade(9)
                .dateGiven(Date.valueOf("2024-06-15"))
                .film(film)
                .user(user)
                .build();
        MockitoAnnotations.openMocks(this);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void testReviewGrade(){
        Review validReview = Review.builder()
                .comment("Excellent movie with great acting!")
                .grade(4)
                .dateGiven(Date.valueOf("2024-06-15"))
                .film(film)
                .user(user)
                .build();

        Set<ConstraintViolation<Review>> violations = validator.validate(validReview);
        assertFalse(violations.isEmpty(), "Review should not be valid due to low grade!");
    }
    @Test
    void testReviewGradeLong(){
        Review validReview = Review.builder()
                .comment("Excellent movie with great acting!")
                .grade(11)
                .dateGiven(Date.valueOf("2024-06-15"))
                .film(film)
                .user(user)
                .build();

        Set<ConstraintViolation<Review>> violations = validator.validate(validReview);
        assertFalse(violations.isEmpty(), "Review should not be valid due to high grade!");
    }
    @Test
    void testReviewValidations() {
        Review validReview = Review.builder()
                .comment("Excellent movie with great acting!")
                .grade(9)
                .dateGiven(Date.valueOf("2024-06-15"))
                .film(film)
                .user(user)
                .build();

        Set<ConstraintViolation<Review>> violations = validator.validate(validReview);
        assertTrue(violations.isEmpty(), "Review should be valid");
    }

    @Test
    void testReviewCommentTooShort() {
        Review invalidReview = Review.builder()
                .comment("Bad")
                .grade(8)
                .dateGiven(Date.valueOf("2024-06-15"))
                .film(film)
                .user(user)
                .build();

        Set<ConstraintViolation<Review>> violations = validator.validate(invalidReview);
        assertFalse(violations.isEmpty(), "Review should be invalid due to short comment");
    }

    @Test
    void testReviewCommentTooLong() {
        String longComment = "A".repeat(101);
        Review invalidReview = Review.builder()
                .comment(longComment)
                .grade(8)
                .dateGiven(Date.valueOf("2024-06-15"))
                .film(film)
                .user(user)
                .build();

        Set<ConstraintViolation<Review>> violations = validator.validate(invalidReview);
        assertFalse(violations.isEmpty(), "Review should be invalid due to long comment");
    }





    @Test
    void testReviewFilmNull() {
        Review invalidReview = Review.builder()
                .comment("Great movie!")
                .grade(8)
                .dateGiven(Date.valueOf("2024-06-15"))
                .user(user)
                .build();

        assertNull(invalidReview.getFilm(), "Film should be null");
    }

    @Test
    void testReviewUserNull() {
        Review invalidReview = Review.builder()
                .comment("Great movie!")
                .grade(8)
                .dateGiven(Date.valueOf("2024-06-15"))
                .film(film)
                .build();

        assertNull(invalidReview.getUser(), "User should be null");
    }
}
