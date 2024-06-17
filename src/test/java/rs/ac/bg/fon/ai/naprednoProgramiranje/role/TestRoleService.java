package rs.ac.bg.fon.ai.naprednoProgramiranje.role;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rs.ac.bg.fon.ai.naprednoProgramiranje.actor.Actor;
import rs.ac.bg.fon.ai.naprednoProgramiranje.actor.ActorRepository;
import rs.ac.bg.fon.ai.naprednoProgramiranje.film.Film;
import rs.ac.bg.fon.ai.naprednoProgramiranje.film.FilmRepository;
import rs.ac.bg.fon.ai.naprednoProgramiranje.role.Role;
import rs.ac.bg.fon.ai.naprednoProgramiranje.role.RoleRepository;
import rs.ac.bg.fon.ai.naprednoProgramiranje.role.RoleService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private ActorRepository actorRepository;

    @InjectMocks
    private RoleService roleService;

    private Role role;
    private Film film;
    private Actor actor;

    @BeforeEach
    void setUp() {

        role = Role.builder()
                .roleName("Protagonist")
                .description("Main character in the film.")
                .build();

        film = new Film();
        actor = new Actor();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveRole() {
        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));
        when(actorRepository.findById(1L)).thenReturn(Optional.of(actor));
        when(roleRepository.save(any(Role.class))).thenReturn(role);

        Role savedRole = roleService.saveRole(role, 1L, 1L);

        assertNotNull(savedRole);
        assertEquals(film, savedRole.getFilm());
        assertEquals(actor, savedRole.getActor());
        verify(filmRepository, times(1)).findById(1L);
        verify(actorRepository, times(1)).findById(1L);
        verify(roleRepository, times(1)).save(role);
    }

    @Test
    void testSaveRoleFilmNotFound() {
        when(filmRepository.findById(1L)).thenReturn(Optional.empty());
        when(actorRepository.findById(1L)).thenReturn(Optional.of(actor));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            roleService.saveRole(role, 1L, 1L);
        });

        assertEquals("Provided actor or film does not exist in H2.", exception.getMessage());
        verify(filmRepository, times(1)).findById(1L);
        verify(actorRepository, times(1)).findById(1L);
        verify(roleRepository, never()).save(any(Role.class));
    }

    @Test
    void testSaveRoleActorNotFound() {
        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));
        when(actorRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            roleService.saveRole(role, 1L, 1L);
        });

        assertEquals("Provided actor or film does not exist in H2.", exception.getMessage());
        verify(filmRepository, times(1)).findById(1L);
        verify(actorRepository, times(1)).findById(1L);
        verify(roleRepository, never()).save(any(Role.class));
    }
}