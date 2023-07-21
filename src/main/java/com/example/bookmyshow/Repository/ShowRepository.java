package com.example.bookmyshow.Repository;

import com.example.bookmyshow.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface ShowRepository extends JpaRepository<Show,Integer> {

     @Query(value="select movie_id from shows group by movie_id order by count(*) desc limit 1;",nativeQuery=true)
     public Integer getMostShowedMovie(Date checkDate);

}
