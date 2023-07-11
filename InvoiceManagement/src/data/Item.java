/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Admin
 */
public class Item {

    private int itemCode;
    private String itemName;
    private String productGroup;
    private double sellingPrice;
    private String origin;

    public Item(int itemCode, String itemName, String productGroup, double sellingPrice, String origin) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.productGroup = productGroup;
        this.sellingPrice = sellingPrice;
        this.origin = origin;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "Item{" + "itemCode=" + itemCode + ", itemName=" + itemName + ", productGroup=" + productGroup + ", sellingPrice=" + sellingPrice + ", origin=" + origin + '}';
    }

    public void showInfo() {
        System.out.printf("|%8s|%13s|%20s|%10.1f|%8s|\n", itemCode, itemName, productGroup, sellingPrice, origin);
    }

}
