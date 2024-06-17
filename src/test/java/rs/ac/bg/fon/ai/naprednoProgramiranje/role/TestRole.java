package rs.ac.bg.fon.ai.naprednoProgramiranje.role;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rs.ac.bg.fon.ai.naprednoProgramiranje.actor.Actor;
import rs.ac.bg.fon.ai.naprednoProgramiranje.film.Film;
import rs.ac.bg.fon.ai.naprednoProgramiranje.role.Role;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TestRole {

    @InjectMocks
    private Role role;

    @Mock
    private Actor actor;

    @Mock
    private Film film;

    private Validator validator;

    @BeforeEach
    void setUp() {
        role = Role.builder()
                .roleName("Protagonist")
                .description("Main character in the film.")
                .actor(actor)
                .film(film)
                .build();
        MockitoAnnotations.openMocks(this);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testRoleValidations() {
        Role validRole = Role.builder()
                .roleName("Protagonist")
                .description("Main character in the film.")
                .actor(actor)
                .film(film)
                .build();

        Set<ConstraintViolation<Role>> violations = validator.validate(validRole);
        assertTrue(violations.isEmpty(), "Role should be valid");
    }

    @Test
    void testRoleNameTooShort() {
        Role invalidRole = Role.builder()
                .roleName("Pro")
                .description("Main character in the film.")
                .actor(actor)
                .film(film)
                .build();

        Set<ConstraintViolation<Role>> violations = validator.validate(invalidRole);
        assertFalse(violations.isEmpty(), "Role should be invalid due to short role name");
    }

    @Test
    void testRoleNameTooLong() {
        String longRoleName = "A".repeat(51);
        Role invalidRole = Role.builder()
                .roleName(longRoleName)
                .description("Main character in the film.")
                .actor(actor)
                .film(film)
                .build();

        Set<ConstraintViolation<Role>> violations = validator.validate(invalidRole);
        assertFalse(violations.isEmpty(), "Role should be invalid due to long role name");
    }

    @Test
    void testDescriptionTooShort() {
        Role invalidRole = Role.builder()
                .roleName("Protagonist")
                .description("Desc")
                .actor(actor)
                .film(film)
                .build();

        Set<ConstraintViolation<Role>> violations = validator.validate(invalidRole);
        assertFalse(violations.isEmpty(), "Role should be invalid due to short description");
    }

    @Test
    void testDescriptionTooLong() {
        String longDescription = "A".repeat(51);
        Role invalidRole = Role.builder()
                .roleName("Protagonist")
                .description(longDescription)
                .actor(actor)
                .film(film)
                .build();

        Set<ConstraintViolation<Role>> violations = validator.validate(invalidRole);
        assertFalse(violations.isEmpty(), "Role should be invalid due to long description");
    }

    @Test
    void testRoleActorAndFilm() {
        Role roleWithActorAndFilm = Role.builder()
                .roleName("Protagonist")
                .description("Main character in the film.")
                .actor(actor)
                .film(film)
                .build();

        assertNotNull(roleWithActorAndFilm.getActor(), "Actor should not be null");
        assertNotNull(roleWithActorAndFilm.getFilm(), "Film should not be null");
    }
}