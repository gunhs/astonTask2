package ru.sharanov.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sharanov.dto.GuestDto;
import ru.sharanov.models.Guest;
import ru.sharanov.repository.GuestRepositoryImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GuestServiceTest {
    @Mock
    private GuestRepositoryImpl guestRepository;
    @InjectMocks
    private GuestService guestService;
    private Guest guest1;
    private GuestDto guestDto1;
    private Guest guest2;
    private GuestDto guestDto2;

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

        guest1 = new Guest(idGuest1, nameGuest1, surnameGuest1,
                passportNumberGuest1, roomIdGuest1, dateBeginGuest1, dateEndGuest1);
        guestDto1 = new GuestDto(idGuest1, nameGuest1, surnameGuest1,
                passportNumberGuest1, roomIdGuest1, dateBeginGuest1, dateEndGuest1);

        guest2 = new Guest(idGuest2, nameGuest2, surnameGuest2,
                passportNumberGuest2, roomIdGuest2, dateBeginGuest2, dateEndGuest2);
        guestDto2 = new GuestDto(idGuest2, nameGuest2, surnameGuest2,
                passportNumberGuest2, roomIdGuest2, dateBeginGuest2, dateEndGuest2);
    }

    @Test
    @DisplayName("Метод получения guest DTO по id")
    public void getGuestDtoByIdTest() throws SQLException {
        when(guestRepository.getGuestById(1)).thenReturn(guest1);
        GuestDto expect = guestDto1;
        GuestDto actual = guestService.getGuestDtoById(1);
        Assertions.assertEquals(expect, actual);
    }

    @Test
    @DisplayName("Метод обновления guest")
    public void updateGuestTest() throws SQLException{
        guestService.updateGuest(guestDto1, 1);
        verify(guestRepository).updateGuest(guest1, 1);
    }

    @Test
    @DisplayName("Метод сохранения guest")
    public void saveGuestTest() {
        guestService.saveGuest(guestDto1);

        verify(guestRepository).saveGuest(guest1);
    }

    @Test
    @DisplayName("Метод удаления guest")
    public void deleteGuestDtoByIdTest() throws SQLException {
        guestService.deleteGuestDtoById(1);
        verify(guestRepository).deleteGuestById(1);
    }

    @Test
    @DisplayName("Метод получения всех guests DTO")
    public void getAllGuestsTest() {
        List<Guest> guestList = new ArrayList<>();
        guestList.add(guest1);
        guestList.add(guest2);
        List<GuestDto> expect = new ArrayList<>();
        expect.add(guestDto1);
        expect.add(guestDto2);
        when(guestRepository.getAllGuests()).thenReturn(guestList);
        List<GuestDto> actual = guestService.getAllGuests();
        Assertions.assertEquals(expect, actual);
    }
}
