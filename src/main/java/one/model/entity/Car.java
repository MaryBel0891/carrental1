package one.model.entity;


public class Car {
    private int id;
    private String name;
    private int price;
    private int insuranceValue;

    public Car(int id, String name, int price, int insuranceValue) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.insuranceValue = insuranceValue;
    }

    public Car(String name, int price, int insuranceValue) {
        this.name = name;
        this.price = price;
        this.insuranceValue = insuranceValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getInsuranceValue() {
        return insuranceValue;
    }

    public void setInsuranceValue(int insuranceValue) {
        this.insuranceValue = insuranceValue;
    }
}
