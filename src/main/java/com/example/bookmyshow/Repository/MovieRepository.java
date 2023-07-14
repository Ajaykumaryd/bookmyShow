package com.example.bookmyshow.Repository;

import com.example.bookmyshow.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
}
