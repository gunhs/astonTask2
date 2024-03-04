package ru.sharanov.repository;

import ru.sharanov.models.Guest;
import ru.sharanov.utils.DBConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestRepositoryImpl implements GuestRepository {

    @Override
    public List<Guest> getAllGuests() {
        String query = "SELECT * FROM guests ORDER BY id";
        return getListGuests(query);
    }

    @Override
    public void saveGuest(Guest guest) {
        String query = "INSERT INTO guests (name, surname, passportNumber, roomId, firstDateOfStay, lastDateOfStay) " +
                "VALUES ((?), (?), (?), (?), (?), (?))";
        prepareStAndSendQuery(query, guest);
    }

    @Override
    public void updateGuest(Guest guest, int id) {
        String query = "UPDATE guests SET name = ?, surname = ?," +
                "passportNumber = ?, roomId = ?, firstDateOfStay = ?, lastDateOfStay = ? where id = ?;";
        prepareStAndSendQuery(query, guest);
    }

    private void prepareStAndSendQuery(String query, Guest guest) {
        try (Connection connection = DBConfig.connection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, guest.getName());
            ps.setString(2, guest.getSurname());
            ps.setString(3, guest.getPassportNumber());
            ps.setDate(4, java.sql.Date.valueOf(guest.getFirstDateOfStay()));
            ps.setDate(5, java.sql.Date.valueOf(guest.getLastDateOfStay()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGuestById(int id) throws SQLException {
        String query = "DELETE FROM guests where id = ?";
        try (Connection connection = DBConfig.connection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Guest getGuestById(int id) throws SQLException {
        String query = "SELECT * FROM guests where id = ?";
        try (Connection connection = DBConfig.connection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new SQLException();
            }
            return getGuestFromResulSet(rs);
        }
    }

    @Override
    public List<Guest> getAllGuestsByRoomId(int id) {
        String query = "SELECT * FROM guests WHERE id = ? ORDER BY id";
        return getListGuests(query);
    }

    private List<Guest> getListGuests(String query){
        List<Guest> guests = new ArrayList<>();
        try (Connection connection = DBConfig.connection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                guests.add(getGuestFromResulSet(rs));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guests;
    }

    private Guest getGuestFromResulSet(ResultSet rs) throws SQLException {
        return new Guest(rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getInt(5),
                rs.getDate(6).toLocalDate(),
                rs.getDate(7).toLocalDate());
    }
}
