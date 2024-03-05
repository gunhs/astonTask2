package ru.sharanov.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sharanov.dto.RoomDto;
import ru.sharanov.mapper.RoomMapper;
import ru.sharanov.models.Room;
import ru.sharanov.models.RoomType;
import ru.sharanov.repository.GuestRepositoryImpl;
import ru.sharanov.repository.RoomRepositoryImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {
    @Mock
    private RoomRepositoryImpl roomRepository;
    @Mock
    private GuestRepositoryImpl guestRepository;
    @InjectMocks
    private RoomService roomService;
    private Room room1;
    private RoomDto roomDto1;
    private Room room2;
    private RoomDto roomDto2;

    @BeforeEach
    public void setup() {
        LocalDate dateBeginGuest1 = LocalDate.of(2024, 1, 31);
        LocalDate dateEndGuest1 = LocalDate.of(2024, 2, 3);
        String nameGuest1 = "Dima";
        String surnameGuest1 = "Medvedev";
        String passportNumberGuest1 = "0987 123456";
        int roomIdGuest1 = 2;
        int idGuest1 = 1;

        LocalDate dateBeginGuest2 = LocalDate.of(2024, 2, 14);
        LocalDate dateEndGuest2 = LocalDate.of(2024, 2, 25);
        String nameGuest2 = "Igor";
        String surnameGuest2 = "Antonov";
        String passportNumberGuest2 = "6543 345687";
        int roomIdGuest2 = 4;
        int idGuest2 = 2;


        room1 = Room.builder().id(1).number(1).price(3000).type(RoomType.ECONOMY).build();
        roomDto1 = RoomDto.builder().id(1).number(1).price(3000).type(RoomType.ECONOMY).build();
        room2 = Room.builder().id(2).number(2).price(5000).type(RoomType.STANDARD).build();
        roomDto2 = RoomDto.builder().id(2).number(2).price(5000).type(RoomType.STANDARD).build();
    }


    @Test
    @DisplayName("Метод получения room DTO по id")
    public void getRoomDtoByIdTest() throws SQLException {
        when(roomRepository.getRoomById(1)).thenReturn(room1);
        RoomDto expect = roomDto1;
        RoomDto actual = roomService.getRoomDtoById(1);
        Assertions.assertEquals(expect, actual);
    }

    @Test
    @DisplayName("Метод получения всех rooms DTO")
    public void getAllRoomsTest() {
        List<RoomDto> roomDtoList = new ArrayList<>();
        List <Room> roomList = new ArrayList<>();
        roomList.add(room1);
        roomList.add(room2);
        roomDtoList.add(roomDto1);
        roomDtoList.add(roomDto2);
//        try (MockedStatic<RoomMapper> roomMapperMockedStatic = Mockito.mockStatic(RoomMapper.class)) {
//            roomMapperMockedStatic.when(()->RoomMapper.roomToRoomDtoList(roomList)).thenReturn(roomDtoList);
//            List<RoomDto> expect = new ArrayList<>();
//            expect.add(roomDto1);
//            expect.add(roomDto2);
//            List<RoomDto> actual = roomService.getAllRooms();
//            Assertions.assertEquals(expect, actual);
//        }

        when(roomRepository.getAllRooms()).thenReturn(roomList);
        when(guestRepository.getAllGuestsByRoomId(anyInt())).thenReturn(null);
        List<RoomDto> actual =  roomService.getAllRooms();
        Assertions.assertEquals(roomDtoList, actual);

    }
}