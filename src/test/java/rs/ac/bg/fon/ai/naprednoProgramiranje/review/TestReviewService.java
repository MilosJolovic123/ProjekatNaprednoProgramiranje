package rs.ac.bg.fon.ai.naprednoProgramiranje.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import rs.ac.bg.fon.ai.naprednoProgramiranje.film.Film;
import rs.ac.bg.fon.ai.naprednoProgramiranje.film.FilmRepository;
import rs.ac.bg.fon.ai.naprednoProgramiranje.user.AppUser;
import rs.ac.bg.fon.ai.naprednoProgramiranje.user.UserRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestReviewService {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private UserDetails loggedUser;

    @InjectMocks
    private ReviewService reviewService;

    private Review review;
    private Film film;
    private AppUser user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        review = Review.builder()
                .comment("Excellent movie with great acting!")
                .grade(9)
                .dateGiven(Date.valueOf(LocalDate.now()))
                .build();

        film = new Film();
        user = new AppUser();
    }

    @Test
    void testGetReview() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        ReviewDTO reviewDTO = reviewService.getReview(1L);

        assertNotNull(reviewDTO);
        assertEquals("Excellent movie with great acting!", reviewDTO.getComment());
        assertEquals(9, reviewDTO.getGrade());
        verify(reviewRepository, times(1)).findById(1L);
    }

    @Test
    void testGetReviewNotFound() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            reviewService.getReview(1L);
        });

        assertEquals("Review is not to be found!", exception.getMessage());
        verify(reviewRepository, times(1)).findById(1L);
    }

    @Test
    void testGetReviews() {
        when(reviewRepository.findAll()).thenReturn(Arrays.asList(review, review));

        List<ReviewDTO> reviewDTOs = reviewService.getReviews();

        assertEquals(2, reviewDTOs.size());
        verify(reviewRepository, times(1)).findAll();
    }

    @Test
    void testSaveReview() {
        when(loggedUser.getUsername()).thenReturn("username");
        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));
        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        Review savedReview = reviewService.saveReview(review, 1L, loggedUser);

        assertNotNull(savedReview);
        assertEquals(film, savedReview.getFilm());
        assertEquals(user, savedReview.getUser());
        verify(userRepository, times(1)).findByUsername("username");
        verify(filmRepository, times(1)).findById(1L);
        verify(reviewRepository, times(1)).save(review);
    }

    @Test
    void testSaveReviewUserOrFilmNotFound() {
        when(loggedUser.getUsername()).thenReturn("username");
        when(userRepository.findByUsername("username")).thenReturn(Optional.empty());
        when(filmRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            reviewService.saveReview(review, 1L, loggedUser);
        });

        assertEquals("Film or user is not found in H2!", exception.getMessage());
        verify(userRepository, times(1)).findByUsername("username");
        verify(filmRepository, times(1)).findById(1L);
        verify(reviewRepository, never()).save(any(Review.class));
    }



    @Test
    void testUpdateReview() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        Review updatedReview = reviewService.updateReview(review, 1L);

        assertNotNull(updatedReview);
        assertEquals("Excellent movie with great acting!", updatedReview.getComment());
        assertEquals(9, updatedReview.getGrade());
        verify(reviewRepository, times(1)).findById(1L);
        verify(reviewRepository, times(1)).save(review);
    }

    @Test
    void testUpdateReviewNotFound() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            reviewService.updateReview(review, 1L);
        });

        assertEquals("Such review does not exist in H2!", exception.getMessage());
        verify(reviewRepository, times(1)).findById(1L);
        verify(reviewRepository, never()).save(any(Review.class));
    }

    @Test
    void testDeleteReview() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        doNothing().when(reviewRepository).deleteById(1L);

        Review deletedReview = reviewService.DeleteReview(1L);

        assertNotNull(deletedReview);
        assertEquals(review, deletedReview);
        verify(reviewRepository, times(1)).findById(1L);
        verify(reviewRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteReviewNotFound() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            reviewService.DeleteReview(1L);
        });

        assertEquals("Such review does not exist in H2!", exception.getMessage());
        verify(reviewRepository, times(1)).findById(1L);
        verify(reviewRepository, never()).deleteById(1L);
    }
}
