package ru.sharanov.services;

import ru.sharanov.dto.GuestDto;
import ru.sharanov.models.Guest;
import ru.sharanov.repository.GuestRepositoryImpl;
import ru.sharanov.utils.DBConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GuestService {
    List<GuestDto> guests = new ArrayList<>();
    GuestRepositoryImpl guestRepository = new GuestRepositoryImpl();

    public List<GuestDto> getAllGuests() throws SQLException {

        LocalDate begin = LocalDate.of(2024, 1, 3);
        LocalDate end = LocalDate.of(2024, 2, 3);
        guests.add(new GuestDto(1, "Игорь", "Веселков", "7809 321198", begin, end));
        Connection connection = DBConfig.connection();
        connection.close();
        return guests;
    }

    public Optional<GuestDto> getGuestDtoById(int id) throws SQLException {


        Optional<Guest> guestOptional = guestRepository.getGuestById(id - 1);
        if (guestOptional.isEmpty()) {
            return Optional.empty();
        }
        Guest guest = guestOptional.get();
        GuestDto guestDto = new GuestDto();
        guestDto.setName(guest.getName());
        guestDto.setSurname(guest.getSurname());
        guestDto.setFirstDateOfStay(guest.getFirstDateOfStay());
        guestDto.setLastDateOfStay(guest.getLastDateOfStay());
        guestDto.setPassportNumber(guest.getPassportNumber());
        Optional<GuestDto> result = Optional.of(guestDto);
        return result;
    }

    public void saveGuest(GuestDto guestDto) throws SQLException {
        Guest guest = new Guest();
        guest.setName(guestDto.getName());
        guest.setSurname(guestDto.getSurname());
        guest.setFirstDateOfStay(guestDto.getFirstDateOfStay());
        guest.setLastDateOfStay(guestDto.getLastDateOfStay());
        guest.setPassportNumber(guestDto.getPassportNumber());
        guestRepository.saveGuest(guest);
    }

    public void deleteGuestDtoById(int id) throws SQLException {
        guestRepository.deleteGuestById(id);
    }
}
