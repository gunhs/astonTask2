package ru.sharanov.repository;

import ru.sharanov.models.Guest;

import java.util.List;

public interface GuestRepository {

    List<Guest> getAllGuests();

    void saveGuest(Guest guest);

    void updateGuest(Guest guest);

    void deleteGuestById(int i);
}
