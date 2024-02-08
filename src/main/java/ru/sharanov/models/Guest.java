package ru.sharanov.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Guest {
    private int id;
    private String name;
    private String Surname;
    private long passportNumber;
    LocalDate firstDateOfStay;
    LocalDate lastDateOfStay;
}
