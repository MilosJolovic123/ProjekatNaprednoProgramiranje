package rs.ac.bg.fon.ai.naprednoProgramiranje.film;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rs.ac.bg.fon.ai.naprednoProgramiranje.director.Director;
import rs.ac.bg.fon.ai.naprednoProgramiranje.director.DirectorRepository;
import rs.ac.bg.fon.ai.naprednoProgramiranje.genre.Genre;
import rs.ac.bg.fon.ai.naprednoProgramiranje.genre.GenreRepository;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestFilmService {

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private DirectorRepository directorRepository;

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private FilmService filmService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFilmList() {
        Film film1 = Film.builder()
                .title("Inception")
                .description("A mind-bending thriller.")
                .landOfOrigin("United States")
                .dateReleased(Date.valueOf("2010-07-16"))
                .build();

        Film film2 = Film.builder()
                .title("Interstellar")
                .description("A journey through space and time.")
                .landOfOrigin("United States")
                .dateReleased(Date.valueOf("2014-11-07"))
                .build();

        when(filmRepository.findAll()).thenReturn(Arrays.asList(film1, film2));

        List<FilmDTO> filmList = filmService.getFilmList();

        assertEquals(2, filmList.size());
        assertEquals("Inception", filmList.get(0).getTitle());
        assertEquals("Interstellar", filmList.get(1).getTitle());
        verify(filmRepository, times(1)).findAll();
    }

    @Test
    void testGetFilmById() {
        Film film = Film.builder()
                .title("Inception")
                .description("A mind-bending thriller.")
                .landOfOrigin("United States")
                .dateReleased(Date.valueOf("2010-07-16"))
                .build();

        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));

        FilmDTO filmDTO = filmService.getFilmById(1L);

        assertNotNull(filmDTO);
        assertEquals("Inception", filmDTO.getTitle());
        assertEquals("A mind-bending thriller.", filmDTO.getDescription());
        verify(filmRepository, times(1)).findById(1L);
    }

    @Test
    void testGetFilmByIdNotFound() {
        when(filmRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            filmService.getFilmById(1L);
        });

        assertEquals("Film not found!", exception.getMessage());
        verify(filmRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveFilm() {
        Director director = new Director();
        Genre genre = new Genre();

        Film film = Film.builder()
                .title("Inception")
                .description("A mind-bending thriller.")
                .landOfOrigin("United States")
                .dateReleased(Date.valueOf("2010-07-16"))
                .build();

        when(directorRepository.findById(1L)).thenReturn(Optional.of(director));
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        when(filmRepository.save(film)).thenReturn(film);

        Film savedFilm = filmService.saveFilm(film, 1L, 1L);

        assertNotNull(savedFilm);
        assertEquals("Inception", savedFilm.getTitle());
        verify(directorRepository, times(1)).findById(1L);
        verify(genreRepository, times(1)).findById(1L);
        verify(filmRepository, times(1)).save(film);
    }

    @Test
    void testSaveFilmDirectorOrGenreNotFound() {
        Film film = Film.builder()
                .title("Inception")
                .description("A mind-bending thriller.")
                .landOfOrigin("United States")
                .dateReleased(Date.valueOf("2010-07-16"))
                .build();

        when(directorRepository.findById(1L)).thenReturn(Optional.empty());
        when(genreRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            filmService.saveFilm(film, 1L, 1L);
        });

        assertEquals("Director or genre not present in H2!", exception.getMessage());
        verify(directorRepository, times(1)).findById(1L);
        verify(genreRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateFilm() {
        Director director = new Director();
        Genre genre = new Genre();
        Film film = Film.builder()
                .title("Inception")
                .description("A mind-bending thriller.")
                .landOfOrigin("United States")
                .dateReleased(Date.valueOf("2010-07-16"))
                .build();

        Film existingFilm = new Film();
        when(directorRepository.findById(1L)).thenReturn(Optional.of(director));
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        when(filmRepository.findById(1L)).thenReturn(Optional.of(existingFilm));
        when(filmRepository.save(existingFilm)).thenReturn(existingFilm);

        Film updatedFilm = filmService.updateFilm(film, 1L, 1L, 1L);

        assertNotNull(updatedFilm);
        assertEquals("Inception", updatedFilm.getTitle());
        verify(directorRepository, times(1)).findById(1L);
        verify(genreRepository, times(1)).findById(1L);
        verify(filmRepository, times(1)).findById(1L);
        verify(filmRepository, times(1)).save(existingFilm);
    }

    @Test
    void testUpdateFilmNotFound() {
        Film film = Film.builder()
                .title("Inception")
                .description("A mind-bending thriller.")
                .landOfOrigin("United States")
                .dateReleased(Date.valueOf("2010-07-16"))
                .build();

        when(directorRepository.findById(1L)).thenReturn(Optional.empty());
        when(genreRepository.findById(1L)).thenReturn(Optional.empty());
        when(filmRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            filmService.updateFilm(film, 1L, 1L, 1L);
        });

        assertEquals("The provided director, genre, or film to be updated don't exist in H2 db!", exception.getMessage());
        verify(directorRepository, times(1)).findById(1L);
        verify(genreRepository, times(1)).findById(1L);
        verify(filmRepository, times(1)).findById(1L);
    }

    @Test
    void testFilmDelete() {
        doNothing().when(filmRepository).deleteById(1L);

        filmService.FilmDelete(1L);

        verify(filmRepository, times(1)).deleteById(1L);
    }
}
