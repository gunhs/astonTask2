package ru.sharanov.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
    private int id;
    private int number;
    private int price;
    private RoomType type;
    private List<Guest> guests;
}
