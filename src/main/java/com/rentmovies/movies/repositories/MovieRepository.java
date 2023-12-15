package com.rentmovies.movies.repositories;

import com.rentmovies.movies.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("FROM Movie WHERE categoryInt = :code")
    List<Movie> buscarByCategory(int code);
}
