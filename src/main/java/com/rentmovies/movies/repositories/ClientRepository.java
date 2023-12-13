package com.rentmovies.movies.repositories;

import com.rentmovies.movies.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
