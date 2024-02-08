package ru.sharanov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor
public class GuestDto {
    private int id;
    private String name;
    private String Surname;
    private long passportNumber;
    LocalDate firstDateOfStay;
    LocalDate lastDateOfStay;

}
