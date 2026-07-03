package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    // Search box: return all countries whose name contains the given text.
    // e.g. findByNameContaining("ou") -> Bouvet Island, Djibouti, Guadeloupe, ...
    List<Country> findByNameContaining(String text);

    // Same search, but results sorted by name ascending.
    List<Country> findByNameContainingOrderByNameAsc(String text);

    // Alphabet index: return all countries whose name starts with the given letter.
    // e.g. findByNameStartingWith("Z") -> Zambia, Zimbabwe
    List<Country> findByNameStartingWith(String letter);
}
