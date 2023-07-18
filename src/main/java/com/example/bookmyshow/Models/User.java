package com.example.bookmyshow.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private Integer age;

    private String mobNo;

    private String email;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Ticket> ticketList = new ArrayList<>();


}
