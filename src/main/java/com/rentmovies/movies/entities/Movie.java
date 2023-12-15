package com.rentmovies.movies.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rentmovies.movies.entities.enums.CategoryMovie;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_movies")
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date launchDay;
    private Double priceRent;
    private Double priceBuy;

    private Integer categoryInt;

    @ManyToMany(mappedBy = "movies")
    private Set<Client> clients = new HashSet<>();

    public Movie() {
    }

    public Movie(Long id, String name, String description, Date launchDay, Double priceRent, Double priceBuy, CategoryMovie categoryMovie) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.launchDay = launchDay;
        this.priceRent = priceRent;
        this.priceBuy = priceBuy;
        setCategoryMovie(categoryMovie);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(Double priceBuy) {
        this.priceBuy = priceBuy;
    }

    public Double getPriceRent() {
        return priceRent;
    }

    public void setPriceRent(Double priceRent) {
        this.priceRent = priceRent;
    }

    public Date getLaunchDay() {
        return launchDay;
    }

    public void setLaunchDay(Date launchDay) {
        this.launchDay = launchDay;
    }

    @JsonIgnore
    public Set<Client> getClients() {
        return clients;
    }

    public CategoryMovie getCategoryMovie() {
        return CategoryMovie.valueCategory(categoryInt);
    }

    public void setCategoryMovie(CategoryMovie categoryMovie) {
        this.categoryInt = categoryMovie.getCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movies = (Movie) o;
        return Objects.equals(id, movies.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
