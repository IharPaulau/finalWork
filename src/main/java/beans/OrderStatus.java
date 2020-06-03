package beans;

public enum OrderStatus {

    NOT_VERIFIED("NOT_VERIFIED"),
    REJECTED("REJECTED"),
    APPROVED("APPROVED"),
    IN_RENT("IN_RENT"),
    RETURN("RETURN"),
    RECOVERY("RECOVERY"),
    COMPLETED("COMPLETED");

    private String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
