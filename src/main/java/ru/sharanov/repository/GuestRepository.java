package ru.sharanov.repository;

import ru.sharanov.models.Guest;

import java.sql.SQLException;
import java.util.List;

public interface GuestRepository {
    List<Guest> getAllGuests() throws SQLException;

    void saveGuest(Guest guest) throws SQLException;

    void updateGuest(Guest guest);

    void deleteGuestById(int i) throws SQLException;

    Guest getGuestById(int i) throws SQLException;

    List<Guest> getAllGuestsByRoomId(int id) throws SQLException;
}
