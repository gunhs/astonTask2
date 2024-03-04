package ru.sharanov.mapper;

import ru.sharanov.dto.RoomDto;
import ru.sharanov.models.Room;
import ru.sharanov.repository.GuestRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class RoomMapper {
    public static RoomDto roomToRoomDto(Room room) {
        GuestRepositoryImpl guestRepository = new GuestRepositoryImpl();
        return RoomDto.builder()
                .id(room.getId())
                .number(room.getNumber())
                .type(room.getType())
                .guests(guestRepository.getAllGuestsByRoomId(room.getId()))
                .build();
    }

    public static Room roomDtoToRoom(RoomDto roomDto) {
        return Room.builder()
                .number(roomDto.getNumber())
                .price(roomDto.getPrice())
                .type(roomDto.getType())
                .build();
    }

    public static List<Room> roomDtoToRoomList(List<RoomDto> roomDtoList) {
        List<Room> result = new ArrayList<>();
        roomDtoList.forEach(r -> result.add(roomDtoToRoom(r)));
        return result;
    }

    public static List<RoomDto> roomToRoomDtoList(List<Room> rooms) {
        List<RoomDto> result = new ArrayList<>();
        rooms.forEach(r -> result.add(roomToRoomDto(r)));
        return result;
    }
}
