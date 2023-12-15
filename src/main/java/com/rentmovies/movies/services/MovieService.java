package com.rentmovies.movies.services;

import com.rentmovies.movies.entities.Client;
import com.rentmovies.movies.entities.Movie;
import com.rentmovies.movies.entities.enums.CategoryMovie;
import com.rentmovies.movies.exception.BadRequestException;
import com.rentmovies.movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    public List<Movie> findByCategory(int code) {
        return repository.buscarByCategory(code);
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
