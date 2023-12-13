package com.rentmovies.movies.services;

import com.rentmovies.movies.entities.Client;
import com.rentmovies.movies.exception.BadRequestException;
import com.rentmovies.movies.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Client create(Client client) {
        return repository.save(client);
    }

    public Client findById(Long id) {
        Optional<Client> client = repository.findById(id);
        return client.orElseThrow(() -> new BadRequestException("Client %d not found!".formatted(id)));
    }

    public List<Client> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.findById(id).ifPresentOrElse((clientPresent) -> {
            repository.delete(clientPresent);
        }, () -> {
            throw new BadRequestException("Client %d not found!".formatted(id));
        });
    }

    public Client update(Long id, Client client) {
        repository.findById(id).ifPresentOrElse((clientPresent) -> {
            client.setId(id);
            repository.save(client);
        }, () -> {
            throw new BadRequestException("Client %d not found!".formatted(id));
        });

        return client;
    }
}
