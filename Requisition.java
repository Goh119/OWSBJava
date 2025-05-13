package managementsystem;

import java.io.Serializable;

public class Requisition implements Serializable {
    private String requisitionId;
    private String itemName;
    private int quantityRequested;
    private String requestedBy;
    
    public Requisition(String requisitionId, String itemName, int quantityRequested, String requestedBy) {
        this.requisitionId = requisitionId;
        this.itemName = itemName;
        this.quantityRequested = quantityRequested;
        this.requestedBy = requestedBy;
    }
    
    public void setRequisitionId(String requisitionId) {
        this.requisitionId = requisitionId;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public void setQuantityRequested(int quantityRequested) {
        this.quantityRequested = quantityRequested;
    }
    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }
    
    public String getRequisitionId() {
        return requisitionId;
    }
    public String getItemName() {
        return itemName;
    }
    public int getQuantityRequested() {
        return quantityRequested;
    }
    public String getRequestedBy() {
        return requestedBy;
    }
}
