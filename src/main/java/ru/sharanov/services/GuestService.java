package ru.sharanov.services;

import ru.sharanov.dto.GuestDto;
import ru.sharanov.mapper.GuestMapper;
import ru.sharanov.models.Guest;
import ru.sharanov.repository.GuestRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class GuestService {
    GuestRepositoryImpl guestRepository = new GuestRepositoryImpl();

    public List<GuestDto> getAllGuests() {
        return GuestMapper.guestToGuestDtoList(guestRepository.getAllGuests());
    }

    public GuestDto getGuestDtoById(int id) throws SQLException {
        Guest guest = guestRepository.getGuestById(id);
        return GuestMapper.guestToGuestDto(guest);
    }

    public void updateGuest(GuestDto guestDto) throws SQLException {
        Guest guest = GuestMapper.guestDtoToGuest(guestDto);
        guestRepository.updateGuest(guest);
    }

    public void saveGuest(GuestDto guestDto) {
        Guest guest = GuestMapper.guestDtoToGuest(guestDto);
        guestRepository.saveGuest(guest);
    }

    public void deleteGuestDtoById(int id) throws SQLException {
        guestRepository.deleteGuestById(id);
    }
}
