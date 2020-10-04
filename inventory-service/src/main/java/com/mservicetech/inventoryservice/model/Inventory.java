package com.mservicetech.inventoryservice.model;

import java.util.Arrays;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Inventory  {

    private java.math.BigDecimal unitPrice;
    private java.lang.Long quantity;
    private java.lang.Long productId;
    
    
    public enum InventorytypeEnum {
        
        INBOUND ("INBOUND"), 
        
        OUTBOUND ("OUTBOUND"); 
        

        private final String value;

        InventorytypeEnum(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static InventorytypeEnum fromValue(String text) {
            for (InventorytypeEnum b : InventorytypeEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                return b;
                }
            }
            return null;
        }
    }

    private InventorytypeEnum inventorytype;

    
    private String productName;

    public Inventory () {
    }

    @JsonProperty("unitPrice")
    public java.math.BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(java.math.BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @JsonProperty("quantity")
    public java.lang.Long getQuantity() {
        return quantity;
    }

    public void setQuantity(java.lang.Long quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("productId")
    public java.lang.Long getProductId() {
        return productId;
    }

    public void setProductId(java.lang.Long productId) {
        this.productId = productId;
    }

    @JsonProperty("inventorytype")
    public InventorytypeEnum getInventorytype() {
        return inventorytype;
    }

    public void setInventorytype(InventorytypeEnum inventorytype) {
        this.inventorytype = inventorytype;
    }

    @JsonProperty("productName")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Inventory Inventory = (Inventory) o;

        return Objects.equals(unitPrice, Inventory.unitPrice) &&
               Objects.equals(quantity, Inventory.quantity) &&
               Objects.equals(productId, Inventory.productId) &&
               Objects.equals(inventorytype, Inventory.inventorytype) &&
               Objects.equals(productName, Inventory.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitPrice, quantity, productId, inventorytype, productName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Inventory {\n");
        sb.append("    unitPrice: ").append(toIndentedString(unitPrice)).append("\n");        sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");        sb.append("    productId: ").append(toIndentedString(productId)).append("\n");        sb.append("    inventorytype: ").append(toIndentedString(inventorytype)).append("\n");        sb.append("    productName: ").append(toIndentedString(productName)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
