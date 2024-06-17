package rs.ac.bg.fon.ai.naprednoProgramiranje.actor;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rs.ac.bg.fon.ai.naprednoProgramiranje.role.Role;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestActor {

    @InjectMocks
    private Actor actor;

    @Mock
    private Set<Role> roles;

    private Validator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void testActorValidations() {
        Actor validActor = Actor.builder()
                .name("John")
                .lastName("Doe")
                .noOfOscars(3)
                .filmSet(Collections.emptySet())
                .build();

        Set<jakarta.validation.ConstraintViolation<Actor>> violations = validator.validate(validActor);
        assertTrue(violations.isEmpty(), "Actor should be valid");
    }
    @Test
    void noOfOscarsNegative(){
        Actor invalidActor = Actor.builder()
                .name("John")
                .lastName("Doe")
                .noOfOscars(-1)
                .filmSet(Collections.emptySet())
                .build();

        Set<ConstraintViolation<Actor>> violations = validator.validate(invalidActor);
        assertFalse(violations.isEmpty(), "Actor should be invalid due to negative number of Oscars");

    }
    @Test
    void testActorNameTooShort() {
        Actor invalidActor = Actor.builder()
                .name("Jo")
                .lastName("Doe")
                .noOfOscars(3)
                .filmSet(Collections.emptySet())
                .build();

        Set<jakarta.validation.ConstraintViolation<Actor>> violations = validator.validate(invalidActor);
        assertFalse(violations.isEmpty(), "Actor should be invalid due to short name");
    }

    @Test
    void testActorNameTooLong() {
        Actor invalidActor = Actor.builder()
                .name("JohnathanMaximillianTHETirdKingofallLands")
                .lastName("Doe")
                .noOfOscars(3)
                .filmSet(Collections.emptySet())
                .build();

        Set<jakarta.validation.ConstraintViolation<Actor>> violations = validator.validate(invalidActor);
        assertFalse(violations.isEmpty(), "Actor should be invalid due to long name");
    }

    @Test
    void testActorLastNameTooShort() {
        Actor invalidActor = Actor.builder()
                .name("John")
                .lastName("D")
                .noOfOscars(3)
                .filmSet(Collections.emptySet())
                .build();

        Set<jakarta.validation.ConstraintViolation<Actor>> violations = validator.validate(invalidActor);
        assertFalse(violations.isEmpty(), "Actor should be invalid due to short last name");
    }

    @Test
    void testActorLastNameTooLong() {
        Actor invalidActor = Actor.builder()
                .name("John")
                .lastName("DoeSmithJohnsonMaxwell")
                .noOfOscars(3)
                .filmSet(Collections.emptySet())
                .build();

        Set<ConstraintViolation<Actor>> violations = validator.validate(invalidActor);
        assertFalse(violations.isEmpty(), "Actor should be invalid due to long last name");
    }

    @Test
    void testActorNoOfOscars() {
        Actor actorWithOscars = Actor.builder()
                .name("John")
                .lastName("Doe")
                .noOfOscars(5)
                .filmSet(Collections.emptySet())
                .build();

        assertEquals(5, actorWithOscars.getNoOfOscars(), "Number of Oscars should be 5");
    }

    @Test
    void testActorFilmSet() {
        Actor actorWithRoles = Actor.builder()
                .name("John")
                .lastName("Doe")
                .noOfOscars(1)
                .filmSet(roles)
                .build();

        when(roles.size()).thenReturn(2);

        assertEquals(2, actorWithRoles.getFilmSet().size(), "Film set size should be 2");
        verify(roles, times(1)).size();
    }
}
