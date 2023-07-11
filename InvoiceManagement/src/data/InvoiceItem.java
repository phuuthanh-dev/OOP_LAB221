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
public class InvoiceItem {
    private Item item;
    private int quantity;

    public InvoiceItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getProduct() {
        return item;
    }

    public void setProduct(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "QuantityBill{" + "item=" + item + ", quantity=" + quantity + '}';
    }
    
    public double getTotalPrice() {
        return item.getSellingPrice() * quantity;
    }
    
}
