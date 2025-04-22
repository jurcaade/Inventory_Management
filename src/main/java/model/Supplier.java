package model;

public class Supplier {
    private int supplierId;
    private String name;
    private String contactInfo;
    private String address;

    public Supplier() {
    }

    public Supplier(int supplierId, String name, String contactInfo, String address) {
        this.supplierId = supplierId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.address = address;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Supplier [id=" + supplierId + ", name=" + name + ", contact=" + contactInfo + "]";
    }
}
