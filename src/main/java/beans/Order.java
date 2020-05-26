package beans;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class Order {
    private int id;
    private User user;
    private Car car;

    @NotBlank
    private String passportSeries;
    @NotNull
    private Integer passportNumber;
    @NotBlank
    private String passportId;
    private Boolean orderApproved;
    @NotNull
    private int rentalPeriodInDays;

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

    public Boolean getOrderApproved() {
        return orderApproved;
    }

    public void setOrderApproved(Boolean orderApproved) {
        this.orderApproved = orderApproved;
    }

    public int getRentalPeriodInDays() {
        return rentalPeriodInDays;
    }

    public void setRentalPeriodInDays(int rentalPeriodInDays) {
        this.rentalPeriodInDays = rentalPeriodInDays;
    }
}
