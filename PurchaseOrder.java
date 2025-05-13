package managementsystem;

import java.io.Serializable;

public class PurchaseOrder implements Serializable{
    private static int nextId = 1; //let the ID no. autogenerate
    private String orderId;
    private String supplierName;
    private String itemName;
    private int quantityOrdered;
    private double unitPrice;
    
    public PurchaseOrder(String supplierName, String itemName, int quantityOrdered, double unitPrice) { //String orderId, 
        this.orderId = String.format("PO%04d", nextId++);
        this.supplierName = supplierName;
        this.itemName = itemName;
        this.quantityOrdered = quantityOrdered;
        this.unitPrice = unitPrice;
    }
    
    
    public static void setNextId(int id) {
        nextId = id;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    
    public static int getNextId() {
        return nextId;
    }
    public String getOrderId() {
        return orderId;
    }
    public String getSupplierName() {
        return supplierName;
    }
    public String getItemName() {
        return itemName;
    }
    public int getQuantityOrdered() {
        return quantityOrdered;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    
    public double getTotalPrice() {
        return quantityOrdered * unitPrice;
    }
    
    public String toString() {
        return "Order ID: " + orderId + ", Supplier: " + supplierName + ", Item: " + itemName +
            ", Quantity: " + quantityOrdered + ", Unit Price: RM" + String.format("%.2f", unitPrice) +
            ", Total: RM" + String.format("%.2f", getTotalPrice());
    }

}
