package ru.sharanov.repository;

import ru.sharanov.models.Room;
import ru.sharanov.models.RoomType;
import ru.sharanov.utils.DBConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomRepositoryImpl implements RoomRepository {
    @Override
    public Room getRoomById(int id) {
        String query = "SELECT * FROM rooms where id = ?";
        try (Connection connection = DBConfig.connection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new SQLException();
            }
            return getRoomFromRs(rs);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return new Room();
    }

    @Override
    public List<Room> getAllRooms() {
        String query = "SELECT * FROM rooms ORDER BY id";
        List<Room> rooms = new ArrayList<>();
        try (Connection connection = DBConfig.connection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rooms.add(getRoomFromRs(rs));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public Optional<Room> getRoomByNumber(int number) {
        String query = "SELECT * FROM rooms where id = ?";
        try (Connection connection = DBConfig.connection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, number);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return Optional.empty();
            }
            return Optional.of(getRoomFromRs(rs));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }

    private Room getRoomFromRs(ResultSet rs) throws SQLException {
        return Room.builder()
                .id(rs.getInt(1))
                .number(rs.getInt(2))
                .price(rs.getInt(3))
                .type(RoomType.valueOf(rs.getString(4).toUpperCase()))
                .build();
    }
}
