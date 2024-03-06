package ru.sharanov.services;

import ru.sharanov.dto.GuestDto;
import ru.sharanov.mapper.GuestMapper;
import ru.sharanov.models.Guest;
import ru.sharanov.models.Room;
import ru.sharanov.repository.GuestRepositoryImpl;
import ru.sharanov.repository.RoomRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class GuestService {
    GuestRepositoryImpl guestRepository = new GuestRepositoryImpl();
    RoomRepositoryImpl roomRepository = new RoomRepositoryImpl();

    public List<GuestDto> getAllGuests() {
        return GuestMapper.guestToGuestDtoList(guestRepository.getAllGuests());
    }

    public GuestDto getGuestDtoById(int id) throws SQLException {
        Guest guest = guestRepository.getGuestById(id);
        return GuestMapper.guestToGuestDto(guest);
    }

    public void updateGuest(GuestDto guestDto, int id) throws SQLException {
        Guest guest = GuestMapper.guestDtoToGuest(guestDto);
        guestRepository.updateGuest(guest, id);
    }

    public void saveGuest(GuestDto guestDto) {
        Guest guest = GuestMapper.guestDtoToGuest(guestDto);
//        Room room = new Room();
//        if (!roomRepository.getRoomByNumber(guestDto.getRoomId()).isEmpty()) {
//            room = roomRepository.getRoomByNumber(guestDto.getRoomId()).get();
//        }
//
//        roomRepository.updateRoom(room);
        guestRepository.saveGuest(guest);
    }

    public void deleteGuestDtoById(int id) throws SQLException {
        guestRepository.deleteGuestById(id);
    }
}
