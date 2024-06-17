package rs.ac.bg.fon.ai.naprednoProgramiranje.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.ai.naprednoProgramiranje.director.Director;
import rs.ac.bg.fon.ai.naprednoProgramiranje.director.DirectorRepository;
import rs.ac.bg.fon.ai.naprednoProgramiranje.genre.Genre;
import rs.ac.bg.fon.ai.naprednoProgramiranje.genre.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FilmService {
    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository, DirectorRepository directorRepository, GenreRepository genreRepository) {
        this.filmRepository = filmRepository;
        this.directorRepository = directorRepository;
        this.genreRepository = genreRepository;
    }

    public List<FilmDTO> getFilmList() {

        List<Film> films = filmRepository.findAll();

        List<FilmDTO> filmsDTOs = new ArrayList<>();

        for(Film f:films){
            FilmDTO filmDto = new FilmDTO();
            filmDto.setDescription(f.getDescription());
            filmDto.setDateReleased(f.getDateReleased());
            filmDto.setTitle(f.getTitle());
            filmDto.setLandOfOrigin(f.getLandOfOrigin());
            filmsDTOs.add(filmDto);
        }

        return filmsDTOs;
    }

    public FilmDTO getFilmById(Long id) {
        Optional<Film> film=filmRepository.findById(id);
        if(film.isEmpty()){
            throw new RuntimeException("Film not found!");
        }
        FilmDTO filmDto = new FilmDTO();
        filmDto.setDescription(film.get().getDescription());
        filmDto.setDateReleased(film.get().getDateReleased());
        filmDto.setTitle(film.get().getTitle());
        filmDto.setLandOfOrigin(film.get().getLandOfOrigin());
        return filmDto;
    }

    public Film saveFilm(Film film,Long requestedDirector, Long requestedGenre) {

        Optional<Director> directorOptional = directorRepository.findById(requestedDirector);
        Optional<Genre> genreOptional = genreRepository.findById(requestedGenre);

        if(directorOptional.isEmpty()||genreOptional.isEmpty())
            throw new RuntimeException("Director or genre not present in H2!");

        film.setFilm_director(directorOptional.get());
        film.setFilm_genre(genreOptional.get());

        return filmRepository.save(film);
    }

    public Film updateFilm(Film film,Long requestedFilm,Long requestedDirector, Long requestedGenre){
        Optional<Director> directorOptional = directorRepository.findById(requestedDirector);
        Optional<Genre> genreOptional = genreRepository.findById(requestedGenre);
        Optional<Film> filmToUpdate = filmRepository.findById(requestedFilm);

        if(directorOptional.isEmpty()||genreOptional.isEmpty()||filmToUpdate.isEmpty())
            throw new RuntimeException("The provided director, genre, or film to be updated don't exist in H2 db!");

        filmToUpdate.get().setFilm_director(directorOptional.get());
        filmToUpdate.get().setFilm_genre(genreOptional.get());
        filmToUpdate.get().setDateReleased(film.getDateReleased());
        filmToUpdate.get().setRoleSet(film.getRoleSet());
        filmToUpdate.get().setDescription(film.getDescription());
        filmToUpdate.get().setLandOfOrigin(film.getLandOfOrigin());
        filmToUpdate.get().setTitle(film.getTitle());
        filmToUpdate.get().setReviews(film.getReviews());

        return filmRepository.save(filmToUpdate.get());
    }

    public void FilmDelete(Long requestedId){
        filmRepository.deleteById(requestedId);
    }
}



