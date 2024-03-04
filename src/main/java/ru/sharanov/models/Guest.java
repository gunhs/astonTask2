package ru.sharanov.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Guest {
    private int id;
    private String name;
    private String surname;
    private String passportNumber;
    private int roomId;
    LocalDate firstDateOfStay;
    LocalDate lastDateOfStay;
}
