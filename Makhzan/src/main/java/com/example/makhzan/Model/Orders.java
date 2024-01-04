package com.example.makhzan.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "double")
    private double totalPrice=0;
    @FutureOrPresent(message = "write start date in the present or in the future")
    @JsonFormat(pattern = "yyyy:MM:DD")
    @NotNull(message = "date cannot be null!")
    @Column(columnDefinition = "date not null")
    private LocalDate startDate;
    @FutureOrPresent(message = "write end date in the present or in the future")
    @JsonFormat(pattern = "yyyy:MM:DD")
    @NotNull(message = "date cannot be null!")
    @Column(columnDefinition = "date not null")
    private LocalDate endDate;
    @Column(columnDefinition = "varchar(20) ")
    private String status; //////////////////////
    @Column(columnDefinition = "date not null")
    private LocalDate orderDate;
    @ManyToOne
    @JoinColumn(name = "customerid")
    @JsonIgnore
    private Customer customer;

}
