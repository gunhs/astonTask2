package ru.sharanov.repository;

import ru.sharanov.models.Guest;
import ru.sharanov.utils.DBConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GuestRepositoryImpl implements GuestRepository {

    private Connection connection;

    @Override
    public List<Guest> getAllGuests() {
        String query = "SELECT * FROM guests ORDER BY id";
        List<Guest> guests = new ArrayList<>();
        try {
            connection = DBConfig.connection();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LocalDate beginDate = rs.getDate(5).toLocalDate();
                LocalDate endDate = rs.getDate(6).toLocalDate();
                guests.add(new Guest(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        beginDate,
                        endDate));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return guests;
    }

    @Override
    public void saveGuest(Guest guest) throws SQLException {
        String query = "INSERT INTO guests (VALUES )";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
    }

    @Override
    public void updateGuest(Guest guest) {

    }

    @Override
    public void deleteGuestById(int i) throws SQLException {
        String query = "DELETE * FROM guests where id = i";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
    }

    @Override
    public Optional<Guest> getGuestById(int i) throws SQLException {
        String query = "SELECT * FROM guests where id = i";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        if (rs == null) {
            return Optional.empty();
        }
        LocalDate beginDate = rs.getDate(5).toLocalDate();
        LocalDate endDate = rs.getDate(6).toLocalDate();
        Guest guest = new Guest(rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                beginDate,
                endDate);
        return Optional.of(guest);
    }
}
