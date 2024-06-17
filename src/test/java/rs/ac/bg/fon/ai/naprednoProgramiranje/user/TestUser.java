package rs.ac.bg.fon.ai.naprednoProgramiranje.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rs.ac.bg.fon.ai.naprednoProgramiranje.newsletter.Newsletter;
import rs.ac.bg.fon.ai.naprednoProgramiranje.review.Review;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TestUser {

    @InjectMocks
    private AppUser appUser;

    @Mock
    private Set<Newsletter> newsletterSet;

    @Mock
    private Set<Review> reviews;

    private Validator validator;

    @BeforeEach
    void setUp() {
        appUser = AppUser.builder()
                .NameAndLastName("John Doe")
                .username("johndoe")
                .password("password123")
                .roles("ROLE_USER")
                .newsletterSet(newsletterSet)
                .reviewSet(reviews)
                .build();
        MockitoAnnotations.openMocks(this);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testAppUserValidations() {
        AppUser validAppUser = AppUser.builder()
                .NameAndLastName("John Doe")
                .username("johndoe")
                .password("password123")
                .roles("ROLE_USER")
                .newsletterSet(newsletterSet)
                .reviewSet(reviews)
                .build();

        Set<ConstraintViolation<AppUser>> violations = validator.validate(validAppUser);
        assertTrue(violations.isEmpty(), "AppUser should be valid");
    }

    @Test
    void testNameAndLastNameTooShort() {
        AppUser invalidAppUser = AppUser.builder()
                .NameAndLastName("John")
                .username("johndoe")
                .password("password123")
                .roles("ROLE_USER")
                .newsletterSet(newsletterSet)
                .reviewSet(reviews)
                .build();

        Set<ConstraintViolation<AppUser>> violations = validator.validate(invalidAppUser);
        assertFalse(violations.isEmpty(), "AppUser should be invalid due to short name and last name");
    }

    @Test
    void testNameAndLastNameTooLong() {
        String longNameAndLastName = "A".repeat(51);
        AppUser invalidAppUser = AppUser.builder()
                .NameAndLastName(longNameAndLastName)
                .username("johndoe")
                .password("password123")
                .roles("ROLE_USER")
                .newsletterSet(newsletterSet)
                .reviewSet(reviews)
                .build();

        Set<ConstraintViolation<AppUser>> violations = validator.validate(invalidAppUser);
        assertFalse(violations.isEmpty(), "AppUser should be invalid due to long name and last name");
    }

    @Test
    void testUsernameTooShort() {
        AppUser invalidAppUser = AppUser.builder()
                .NameAndLastName("John Doe")
                .username("john")
                .password("password123")
                .roles("ROLE_USER")
                .newsletterSet(newsletterSet)
                .reviewSet(reviews)
                .build();

        Set<ConstraintViolation<AppUser>> violations = validator.validate(invalidAppUser);
        assertFalse(violations.isEmpty(), "AppUser should be invalid due to short username");
    }

    @Test
    void testUsernameTooLong() {
        String longUsername = "A".repeat(61);
        AppUser invalidAppUser = AppUser.builder()
                .NameAndLastName("John Doe")
                .username(longUsername)
                .password("password123")
                .roles("ROLE_USER")
                .newsletterSet(newsletterSet)
                .reviewSet(reviews)
                .build();

        Set<ConstraintViolation<AppUser>> violations = validator.validate(invalidAppUser);
        assertFalse(violations.isEmpty(), "AppUser should be invalid due to long username");
    }

    @Test
    void testPasswordTooShort() {
        AppUser invalidAppUser = AppUser.builder()
                .NameAndLastName("John Doe")
                .username("johndoe")
                .password("pass")
                .roles("ROLE_USER")
                .newsletterSet(newsletterSet)
                .reviewSet(reviews)
                .build();

        Set<ConstraintViolation<AppUser>> violations = validator.validate(invalidAppUser);
        assertFalse(violations.isEmpty(), "AppUser should be invalid due to short password");
    }

    @Test
    void testPasswordTooLong() {
        String longPassword = "A".repeat(61);
        AppUser invalidAppUser = AppUser.builder()
                .NameAndLastName("John Doe")
                .username("johndoe")
                .password(longPassword)
                .roles("ROLE_USER")
                .newsletterSet(newsletterSet)
                .reviewSet(reviews)
                .build();

        Set<ConstraintViolation<AppUser>> violations = validator.validate(invalidAppUser);
        assertFalse(violations.isEmpty(), "AppUser should be invalid due to long password");
    }

    @Test
    void testRolesNotNull() {
        AppUser appUserWithRoles = AppUser.builder()
                .NameAndLastName("John Doe")
                .username("johndoe")
                .password("password123")
                .roles("ROLE_USER")
                .newsletterSet(newsletterSet)
                .reviewSet(reviews)
                .build();

        assertNotNull(appUserWithRoles.getRoles(), "Roles should not be null");
    }

    @Test
    void testNewsletterAndReviews() {
        AppUser appUserWithNewsletterAndReviews = AppUser.builder()
                .NameAndLastName("John Doe")
                .username("johndoe")
                .password("password123")
                .roles("ROLE_USER")
                .newsletterSet(newsletterSet)
                .reviewSet(reviews)
                .build();

        assertNotNull(appUserWithNewsletterAndReviews.getNewsletterSet(), "Newsletter set should not be null");
        assertNotNull(appUserWithNewsletterAndReviews.getReviewSet(), "Reviews set should not be null");
    }
}