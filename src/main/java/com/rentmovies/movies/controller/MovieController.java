package com.rentmovies.movies.controller;

import com.rentmovies.movies.entities.Movie;
import com.rentmovies.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService service;

    @GetMapping
    public ResponseEntity<List<Movie>> findAll() {
        List<Movie> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Movie> findById(@PathVariable Long id) {
        Movie movie = service.findById(id);
        return ResponseEntity.ok().body(movie);
    }

    @PostMapping
    public ResponseEntity<Movie> create(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") Movie movie) {
        Movie _movie = service.createMovie(movie);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(movie.getId()).toUri();

        return ResponseEntity.created(uri).body(_movie);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Movie> update(@PathVariable Long id, @RequestBody Movie movie) {
        Movie _movie = service.update(id, movie);
        return ResponseEntity.ok().body(_movie);
    }
}
