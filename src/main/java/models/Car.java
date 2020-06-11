package models;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Car {

    private int id;
    @NotBlank(message = "{empty.car.brand}")
    private String brand;
    @NotBlank(message = "{empty.car.model}")
    private String model;
    @NotBlank(message = "{empty.car.typeBody}")
    private String typeBody;
    @NotBlank(message = "{empty.car.typeEngine}")
    private String typeEngine;
    @NotBlank(message = "{empty.car.bodyColor}")
    private String bodyColor;
    @NotBlank(message = "{empty.car.transmission}")
    private String transmission;
    @Min(value = 50, message = "{min.car.costPerOneDay}")
    @NotNull(message = "{null.car.costPerOneDay}")
    private Integer costPerOneDay;
    private boolean available;

    public Car() {
        this.available=true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTypeBody() {
        return typeBody;
    }

    public void setTypeBody(String typeBody) {
        this.typeBody = typeBody;
    }

    public String getTypeEngine() {
        return typeEngine;
    }

    public void setTypeEngine(String typeEngine) {
        this.typeEngine = typeEngine;
    }

    public String getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(String bodyColor) {
        this.bodyColor = bodyColor;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public Integer getCostPerOneDay() {
        return costPerOneDay;
    }

    public void setCostPerOneDay(Integer costPerOneDay) {
        this.costPerOneDay = costPerOneDay;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}