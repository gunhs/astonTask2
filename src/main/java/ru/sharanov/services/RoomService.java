package ru.sharanov.services;

import ru.sharanov.dto.RoomDto;
import ru.sharanov.mapper.RoomMapper;
import ru.sharanov.models.Room;
import ru.sharanov.repository.RoomRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class RoomService {
    RoomRepositoryImpl roomRepository = new RoomRepositoryImpl();

    public List<RoomDto> getAllRooms() {
        return RoomMapper.roomToRoomDtoList(roomRepository.getAllRooms());
    }

    public RoomDto getRoomDtoById(int id) throws SQLException {
        Room room = roomRepository.getRoomById(id);
        return RoomMapper.roomToRoomDto(room);
    }
}
