package ru.sharanov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sharanov.models.Guest;
import ru.sharanov.models.RoomType;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto {
    private int id;
    private int number;
    private RoomType type;
    private int price;
    private List<Guest> guests;
}
