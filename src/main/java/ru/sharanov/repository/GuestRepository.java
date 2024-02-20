package ru.sharanov.repository;

import ru.sharanov.models.Guest;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GuestRepository {

    List<Guest> getAllGuests();

    void saveGuest(Guest guest) throws SQLException;

    void updateGuest(Guest guest);

    void deleteGuestById(int i) throws SQLException;

    Optional<Guest> getGuestById(int i) throws SQLException;
}
