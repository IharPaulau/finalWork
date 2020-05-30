package beans;

public enum OrderStatus {

    NOT_VERIFIED, APPROVED, REJECTED, PAID, COMPLETED;

    private Boolean orderApproved;
    private Boolean orderPaid;

}
