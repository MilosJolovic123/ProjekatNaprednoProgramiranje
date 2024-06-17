package rs.ac.bg.fon.ai.naprednoProgramiranje.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TestUserService {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private AppUser appUser;
    private AppUserDTO appUserDTO;

    @BeforeEach
    void setUp() {

        appUser = AppUser.builder()
                .NameAndLastName("John Doe")
                .username("johndoe")
                .password("password123")
                .roles("ROLE_USER")
                .build();
        appUserDTO = new AppUserDTO();
        appUserDTO.setUsername("johndoe");
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testCreateUser() {
        when(userRepository.save(any(AppUser.class))).thenReturn(appUser);

        AppUser createdUser = userService.createUser(appUser);

        assertNotNull(createdUser);
        assertEquals("johndoe", createdUser.getUsername());
        verify(userRepository, times(1)).save(appUser);
    }

    @Test
    void testCreateUserNull() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.createUser(null);
        });

        assertEquals("The provided app user is null!", exception.getMessage());
        verify(userRepository, never()).save(any(AppUser.class));
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(appUser, appUser));

        List<AppUserDTO> userDTOs = userService.getAllUsers();

        assertEquals(2, userDTOs.size());
        assertEquals("johndoe", userDTOs.get(0).getUsername());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(appUser));

        AppUserDTO userDTO = userService.getUserById(1L);

        assertNotNull(userDTO);
        assertEquals("johndoe", userDTO.getUsername());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testGetUserByIdNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.getUserById(1L);
        });

        assertEquals("The provided user does not exist in H2!", exception.getMessage());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(appUser));
        when(userRepository.save(any(AppUser.class))).thenReturn(appUser);

        AppUser updatedUser = userService.updateUser(appUser, 1L);

        assertNotNull(updatedUser);
        assertEquals("johndoe", updatedUser.getUsername());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(appUser);
    }

    @Test
    void testUpdateUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.updateUser(appUser, 1L);
        });

        assertEquals("The given user does not exist!", exception.getMessage());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, never()).save(any(AppUser.class));
    }

    @Test
    void testDeleteUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(appUser));
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.deleteUser(1L);
        });

        assertEquals("The provided user does not exist in DB!", exception.getMessage());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, never()).deleteById(1L);
    }
}