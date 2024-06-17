package rs.ac.bg.fon.ai.naprednoProgramiranje.director;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestDirectorService {

    @Mock
    private DirectorRepository directorRepository;

    @InjectMocks
    private DirectorService directorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveDirectorSuccess() {
        Director director = Director.builder()
                .name("Steven")
                .lastName("Spielberg")
                .noOfOscars(3)
                .build();

        when(directorRepository.save(any(Director.class))).thenReturn(director);

        Director savedDirector = directorService.saveDirector(director);
        assertNotNull(savedDirector, "Saved director should not be null");
        assertEquals("Steven", savedDirector.getName(), "Director's name should be Steven");
        assertEquals("Spielberg", savedDirector.getLastName(), "Director's last name should be Spielberg");
        assertEquals(3, savedDirector.getNoOfOscars(), "Director's number of Oscars should be 3");

        verify(directorRepository, times(1)).save(director);
    }

    @Test
    void testSaveDirectorNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            directorService.saveDirector(null);
        });

        String expectedMessage = "Director is null!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "Exception message should be 'Director is null!'");
    }


}
