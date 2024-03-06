package ru.sharanov.repository;

import ru.sharanov.models.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    Room getRoomById(int id);
    List<Room> getAllRooms();
    Optional<Room> getRoomByNumber(int number);
}
