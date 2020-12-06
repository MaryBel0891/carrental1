package one.model.dao.impl;


import one.model.dao.CarRentalDao;
import one.model.entity.CarRental;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarRentalDaoImpl implements CarRentalDao {
    private Connection connection;

    public CarRentalDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(CarRental entity) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO  car_rental (startDate, daysAmount, price, client_id, car_id) \n" +
                            "  VALUES (?, ?, ?, ?, ?)")) {
                ps.setDate(1, Date.valueOf(entity.getStartDate()));
                ps.setInt(2, entity.getDaysAmount());
                ps.setInt(3, entity.getPrice());
                ps.setInt(4, entity.getClientId());
                ps.setInt(5, entity.getCarId());
                ps.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }

    @Override
    public Optional<CarRental> findById(int id) {
        CarRental carRental = null;
        try(PreparedStatement ps = connection.prepareStatement(
                "select * from car_rental  as c where c.id =?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                carRental = extractFromResultSet(rs);
            }
        }catch (Exception e){

        }

        return Optional.ofNullable(carRental);
    }

    @Override
    public List<CarRental> findAll() {
        List<CarRental> result = new ArrayList<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(
                    "select * from car_rental as c");
            while (rs.next()) {
                CarRental carRental = extractFromResultSet(rs);
                result.add(carRental);
            }
        }catch (Exception e){

        }
        return result;
    }

    @Override
    public void update(CarRental entity) {
        try(PreparedStatement ps = connection.prepareStatement(
                "UPDATE car_rental SET  startDate = ?, daysAmount = ?, price = ?, clientId = ?, carId = ? \n" +
                        " WHERE (id = ?);")) {
            ps.setDate(1, Date.valueOf(entity.getStartDate()));
            ps.setInt(2, entity.getDaysAmount());
            ps.setInt(3, entity.getPrice());
            ps.setInt(4, entity.getClientId());
            ps.setInt(5, entity.getCarId());
            ps.setInt(6, entity.getId());
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
        }
    }

    private CarRental extractFromResultSet(ResultSet rs) throws SQLException {
        return new CarRental.Builder()
                .id(rs.getInt("c.id"))
                .startDate(rs.getDate("c.startDate").toLocalDate())
                .daysAmount(rs.getInt("c.daysAmount"))
                .price(rs.getInt("c.price"))
                .carId(rs.getInt("c.carId"))
                .clientId(rs.getInt("c.clientId"))
                .build();


    }




}
