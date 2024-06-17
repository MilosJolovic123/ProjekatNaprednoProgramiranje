package rs.ac.bg.fon.ai.naprednoProgramiranje.genre;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TestGenreService {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreService genreService;

    private Genre genre;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        genre = Genre.builder()
                .genreDesctiption("A genre that describes action films.")
                .build();
    }

    @Test
    void testSaveGenre() {
        when(genreRepository.save(any(Genre.class))).thenReturn(genre);

        Genre savedGenre = genreService.saveGenre(genre);

        assertNotNull(savedGenre);
        assertEquals("A genre that describes action films.", savedGenre.getGenreDesctiption());
        verify(genreRepository, times(1)).save(genre);
    }
}