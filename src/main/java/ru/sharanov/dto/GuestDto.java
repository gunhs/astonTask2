package ru.sharanov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestDto {
    private int id;
    private String name;
    private String Surname;
    private String passportNumber;
    LocalDate firstDateOfStay;
    LocalDate lastDateOfStay;

}
