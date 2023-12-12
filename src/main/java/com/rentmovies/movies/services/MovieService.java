package com.rentmovies.movies.services;

import com.rentmovies.movies.entities.Movie;
import com.rentmovies.movies.exception.BadRequestException;
import com.rentmovies.movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository repository;

    public Movie createMovie(Movie movie) {
        return repository.save(movie);
    }

    public Movie findById(Long id) {
        Optional<Movie> movie = repository.findById(id);
        return movie.orElseThrow(() -> new BadRequestException("Movie %d not exists".formatted(id)));
    }

    public List<Movie> findAll() {
        return repository.findAll();
    }

    public void deleteMovie(Long id) {
        repository.findById(id).ifPresentOrElse((moviePresent) -> {
            repository.delete(moviePresent);
        }, () -> {
            throw new BadRequestException("Movie %d not exists!".formatted(id));
        });
    }

    public Movie update(Long id, Movie movie) {
        repository.findById(id).ifPresentOrElse((moviePresent) -> {
            movie.setId(id);
            repository.save(movie);
        }, () -> {
            throw new BadRequestException("Movie %d not exists!".formatted(id));
        });

        return movie;
    }
}
