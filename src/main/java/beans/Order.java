package beans;


public class Order {
    private int id;
    private int userId;
    private int carId;
    private String passportSeries;
    private Integer passportNumber;
    private String passportId;
    private Boolean orderApproved;
    private int rentalPeriodInDays;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
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

    public boolean isOrderApproved() {
        return orderApproved;
    }

    public void setOrderApproved(boolean orderApproved) {
        this.orderApproved = orderApproved;
    }

    public int getRentalPeriodInDays() {
        return rentalPeriodInDays;
    }

    public void setRentalPeriodInDays(int rentalPeriodInDays) {
        this.rentalPeriodInDays = rentalPeriodInDays;
    }
}
