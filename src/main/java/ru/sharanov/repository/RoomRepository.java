package ru.sharanov.repository;

import ru.sharanov.models.Room;

import java.util.List;

public interface RoomRepository {
    Room getRoomById(int id);
    List<Room> getAllRooms();
}
