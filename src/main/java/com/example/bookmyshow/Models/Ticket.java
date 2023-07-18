package com.example.bookmyshow.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="tickets")
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int totalTicketsPrice;

    private String bookedSeats;

    @CreationTimestamp
    private Data bookedAt;

    @ManyToOne
    @JoinColumn
    private Show show;

    @ManyToOne
    @JoinColumn
    private User user;

}
