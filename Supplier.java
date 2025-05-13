package managementsystem;

import java.io.Serializable;

public class Supplier implements Serializable {
    private String supplierId;
    private String supplierName;
    private String contactNum;
    private String address;
    
    public Supplier(String supplierId, String supplierName, String contactNum, String address) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.contactNum = contactNum;
        this.address = address;
    }
    
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getSupplierId() {
        return supplierId;
    }
    public String getSupplierName() {
        return supplierName;
    }
    public String getContactNum() {
        return contactNum;
    }
    public String getAddress() {
        return address;
    }
}
