package models;


import enums.OrderStatus;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class Order {
    private int id;
    private User user;
    private Car car;
    @Pattern(regexp = "^[A-Z]{2}$", message = "{pattern.passport.series}")
    private String passportSeries;
    @NotNull(message = "{passport.number.validation1}")
    // TODO VALID ON MIN AND MAX SIZE
    @Min(value = 1000000, message = "{passport.number.validation2}")
    @Max(value = 9999999, message = "{passport.number.validation3}")
    private Integer passportNumber;
    @Pattern(regexp = "^([0-9]{7})([A-Z])([0-9]{3})([A-Z]{2})([0-9])$", message = "{pattern.passport.id}")
    private String passportId;
    @Min(value = 1, message = "{rental.period.min}")
    @Max(value = 30, message = "{rental.period.max}")
    @NotNull(message = "{rental.period.validation}")
    private Integer rentalPeriodInDays;
    private Date payTillDate;
    private Date rentalStartTime;
    private Date rentalEndTime;
    private OrderStatus orderStatus;
    private Integer compensationAmount;

    public Order() {
        this.orderStatus = OrderStatus.NOT_VERIFIED;
        this.compensationAmount = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public Integer getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(Integer passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public Integer getRentalPeriodInDays() {
        return rentalPeriodInDays;
    }

    public void setRentalPeriodInDays(Integer rentalPeriodInDays) {
        this.rentalPeriodInDays = rentalPeriodInDays;
    }

    public Date getPayTillDate() {
        return payTillDate;
    }

    public void setPayTillDate(Date payTillDate) {
        this.payTillDate = payTillDate;
    }

    public Date getRentalStartTime() {
        return rentalStartTime;
    }

    public void setRentalStartTime(Date rentalStartTime) {
        this.rentalStartTime = rentalStartTime;
    }

    public Date getRentalEndTime() {
        return rentalEndTime;
    }

    public void setRentalEndTime(Date rentalEndTime) {
        this.rentalEndTime = rentalEndTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getCompensationAmount() {
        return compensationAmount;
    }

    public void setCompensationAmount(Integer compensationAmount) {
        this.compensationAmount = compensationAmount;
    }
}
