package rs.ac.bg.fon.ai.naprednoProgramiranje.newsletter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rs.ac.bg.fon.ai.naprednoProgramiranje.user.AppUser;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.sql.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TestNewsletter {

    @InjectMocks
    private Newsletter newsletter;

    @Mock
    private AppUser user;

    private Validator validator;

    @BeforeEach
    void setUp() {
         newsletter = Newsletter.builder()
                .newsletter_date(Date.valueOf("2024-06-15"))
                .newsletter_user(user)
                .build();
        MockitoAnnotations.openMocks(this);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testNewsletterValidations() {
        Newsletter validNewsletter = Newsletter.builder()
                .newsletter_date(Date.valueOf("2024-06-15"))
                .newsletter_user(user)
                .build();

        Set<ConstraintViolation<Newsletter>> violations = validator.validate(validNewsletter);
        assertTrue(violations.isEmpty(), "Newsletter should be valid");
    }



    @Test
    void testNewsletterUser() {
        Newsletter newsletterWithUser = Newsletter.builder()
                .newsletter_date(Date.valueOf("2024-06-15"))
                .newsletter_user(user)
                .build();

        assertNotNull(newsletterWithUser.getNewsletter_user(), "Newsletter user should not be null");
    }
}