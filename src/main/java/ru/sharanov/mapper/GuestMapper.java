package ru.sharanov.mapper;

import ru.sharanov.dto.GuestDto;
import ru.sharanov.models.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestMapper {

    public static GuestDto guestToGuestDto(Guest guest) {
        return GuestDto.builder()
                .id(guest.getId())
                .name((guest.getName()))
                .surname(guest.getSurname())
                .passportNumber(guest.getPassportNumber())
                .roomId(guest.getRoomId())
                .firstDateOfStay(guest.getFirstDateOfStay())
                .lastDateOfStay(guest.getLastDateOfStay())
                .build();
    }

    public static Guest guestDtoToGuest(GuestDto guestDto) {
        return Guest.builder()
                .name(guestDto.getName())
                .surname(guestDto.getSurname())
                .roomId(guestDto.getRoomId())
                .firstDateOfStay(guestDto.getFirstDateOfStay())
                .lastDateOfStay(guestDto.getLastDateOfStay())
                .build();
    }

    public static List<Guest> guestDtoToGuestList(List<GuestDto> guestDtoList) {
        List<Guest> result = new ArrayList<>();
        guestDtoList.forEach(g -> result.add(guestDtoToGuest(g)));
        return result;
    }

    public static List<GuestDto> guestToGuestDtoList(List<Guest> guests) {
        List<GuestDto> result = new ArrayList<>();
        guests.forEach(g -> result.add(guestToGuestDto(g)));
        return result;
    }
}
