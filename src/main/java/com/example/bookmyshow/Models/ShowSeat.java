package com.example.bookmyshow.Models;


import com.example.bookmyshow.Enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="show_seats")
public class ShowSeat {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer Id;

      private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private int price;

    private boolean isAvailable;

    private boolean isFoodAttached;

    @ManyToOne
    @JoinColumn
    private Show show;


}
