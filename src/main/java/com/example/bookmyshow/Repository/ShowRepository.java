package com.example.bookmyshow.Repository;

import com.example.bookmyshow.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show,Integer> {
}
