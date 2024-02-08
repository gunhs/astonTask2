package ru.sharanov.services;

import ru.sharanov.dto.GuestDto;
import ru.sharanov.utils.DBConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GuestService {

    public List<GuestDto> getAllGuests() throws SQLException {
        List<GuestDto> guests = new ArrayList<GuestDto>();
        LocalDate begin = LocalDate.of(2024, 1, 3);
        LocalDate end = LocalDate.of(2024, 2, 3);
        guests.add(new GuestDto(1, "Игорь", "Веселков", 7809321198L, begin, end));
        Connection connection = DBConfig.connection();
        connection.close();
        return guests;
    }
}
