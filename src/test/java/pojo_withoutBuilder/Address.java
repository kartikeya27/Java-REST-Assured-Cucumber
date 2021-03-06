package pojo_withoutBuilder;

public class Address {
    private String street;
    private String flat_no;
    private int pincode;
    private String type;

    Address() {}

    public void setStreet(String street) {
        this.street = street;
    }

    public void setFlat_no(String flat_no) {
        this.flat_no = flat_no;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStreet() {
        return street;
    }

    public String getFlat_no() {
        return flat_no;
    }

    public int getPincode() {
        return pincode;
    }

    public String getType() {
        return type;
    }
}
