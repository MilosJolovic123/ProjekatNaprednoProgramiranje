package rs.ac.bg.fon.ai.naprednoProgramiranje.newsletter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import rs.ac.bg.fon.ai.naprednoProgramiranje.user.AppUser;
import rs.ac.bg.fon.ai.naprednoProgramiranje.user.UserRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NewsletterServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private NewsletterRepository newsletterRepository;

    @Mock
    private UserDetails loggedUser;

    @InjectMocks
    private NewsletterService newsletterService;

    private Newsletter newsletter;

    @BeforeEach
    void setUp() {
        newsletter = Newsletter.builder()
                .newsletter_date(Date.valueOf(LocalDate.now()))
                .build();
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testSaveNewsletter() {
        AppUser user = new AppUser();
        when(loggedUser.getUsername()).thenReturn("username");
        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));
        when(newsletterRepository.save(any(Newsletter.class))).thenReturn(newsletter);

        Newsletter savedNewsletter = newsletterService.save(loggedUser, newsletter);

        assertNotNull(savedNewsletter);
        assertEquals(user, savedNewsletter.getNewsletter_user());
        verify(userRepository, times(1)).findByUsername("username");
        verify(newsletterRepository, times(1)).save(newsletter);
    }

    @Test
    void testSaveNewsletterUserNotFound() {
        when(loggedUser.getUsername()).thenReturn("username");
        when(userRepository.findByUsername("username")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            newsletterService.save(loggedUser, newsletter);
        });

        assertEquals("User not found", exception.getMessage());
        verify(userRepository, times(1)).findByUsername("username");
        verify(newsletterRepository, never()).save(any(Newsletter.class));
    }



    @Test
    void testFindOne() {
        when(newsletterRepository.findById(1L)).thenReturn(Optional.of(newsletter));

        Optional<Newsletter> foundNewsletter = newsletterService.findOne(1L);

        assertTrue(foundNewsletter.isPresent());
        assertEquals(newsletter, foundNewsletter.get());
        verify(newsletterRepository, times(1)).findById(1L);
    }

    @Test
    void testFindOneNewsletterNotFound() {
        when(newsletterRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            newsletterService.findOne(1L);
        });

        assertEquals("Newsletter not found!", exception.getMessage());
        verify(newsletterRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAll() {
        List<Newsletter> newsletters = Arrays.asList(newsletter, newsletter);
        when(newsletterRepository.findAll()).thenReturn(newsletters);

        List<Newsletter> allNewsletters = newsletterService.findAll();

        assertEquals(2, allNewsletters.size());
        verify(newsletterRepository, times(1)).findAll();
    }
}