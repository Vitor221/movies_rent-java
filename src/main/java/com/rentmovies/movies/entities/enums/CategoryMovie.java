package com.rentmovies.movies.entities.enums;

public enum CategoryMovie {
    ADVENTURE(1),
    ACTION(2),
    DRAMA(3),
    TERROR(4),
    THRILLER(5);

    private int code;

    private CategoryMovie(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static CategoryMovie valueCategory(int code) {
        for(CategoryMovie cat : CategoryMovie.values()) {
            if (cat.getCode() == code) {
                return cat;
            }
        }
        throw new IllegalArgumentException("Invalid category code!");
    }
}
