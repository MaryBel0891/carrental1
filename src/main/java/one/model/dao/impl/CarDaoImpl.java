package one.model.dao.impl;


import one.model.dao.CarDao;
import one.model.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements CarDao {
    private Connection connection;

    public CarDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Car entity) {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO  car (name, price, insuranceValue) \n" +
                        "  VALUES (?, ?, ?)")) {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getPrice());
            ps.setInt(3, entity.getInsuranceValue());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public Optional<Car> findById(int id) {
        Car car = null;
        try(PreparedStatement ps = connection.prepareStatement(
                "select * from car  where car.id =?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                car = extractFromResultSet(rs);
            }
        }catch (Exception e){

        }

        return Optional.ofNullable(car);
    }

    @Override
    public List<Car> findAll() {
        List<Car> result = new ArrayList<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(
                    "select * from car");
            while (rs.next()) {
                Car car = extractFromResultSet(rs);
                result.add(car);
            }
        }catch (Exception e){

        }
        return result;
    }

    @Override
    public void update(Car entity) {
        try(PreparedStatement ps = connection.prepareStatement(
                "UPDATE car SET  name = ?, price = ?, insuranceValue = ?\n" +
                        " WHERE (id = ?);")) {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getPrice());
            ps.setInt(3, entity.getInsuranceValue());
            ps.setInt(4, entity.getId());
            ps.executeUpdate();

        }catch (Exception e){

        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM car WHERE (car.id=?);")) {
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {

        }
    }


    static Car extractFromResultSet(ResultSet rs) throws SQLException {
        return new Car(rs.getInt("car.id"), rs.getString("car.name"),
                rs.getInt("car.price"), rs.getInt("car.insuranceValue") );

    }
        @Override
        public void close () {
            try {
                connection.close();
            } catch (SQLException e) {

            }
        }

}
