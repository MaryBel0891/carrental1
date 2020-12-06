package one.model.entity;


import java.time.LocalDate;

public class CarRental {
    private int id;
    private LocalDate startDate;
    private int daysAmount;
    private int price;
    private int clientId;
    private int carId;

    public CarRental() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDaysAmount() {
        return daysAmount;
    }

    public void setDaysAmount(int daysAmount) {
        this.daysAmount = daysAmount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public static class Builder{
        CarRental carRental = new CarRental();

        public Builder id(int id){
            carRental.id = id;
            return this;
        }
        public Builder startDate(LocalDate startDate){
            carRental.startDate = startDate;
            return this;
        }
        public Builder daysAmount(int daysAmount){
            carRental.daysAmount = daysAmount;
            return this;
        }
        public Builder price(int price){
            carRental.price = price;
            return this;
        }
        public Builder clientId(int clientId){
            carRental.clientId = clientId;
            return this;
        }
        public Builder carId(int carId){
            carRental.carId = carId;
            return this;
        }
        public CarRental build(){
            return carRental;
        }
    }
}
