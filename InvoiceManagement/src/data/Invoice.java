/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * @author Admin
 */
public class Invoice {

    Scanner sc = new Scanner(System.in);
    public int invoiceCode;
    public LocalDateTime creationTime;
    public Customer customer;
    public ArrayList<InvoiceItem> invoiceItems;

    public Invoice(int invoiceCode, LocalDateTime creationTime, Customer customer, ArrayList<InvoiceItem> listInvoiceItem) {
        this.invoiceCode = invoiceCode;
        this.creationTime = LocalDateTime.now();
        this.customer = customer;
        this.invoiceItems = listInvoiceItem;
    }

    public int getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(int invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(ArrayList<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }      

    public void addItem(Item item, int quantity) {
        InvoiceItem invoiceItem = new InvoiceItem(item, quantity);
    }

    public int getTotalNumberOfItems() {
        int total = 0;
        for (InvoiceItem x : invoiceItems) {
            total += x.getQuantity();         //từng món đồ trong invoiceItems sẽ lấy số lượng rồi cộng tất cả lại
        }
        return total;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (InvoiceItem x : invoiceItems) {
            totalPrice += x.getTotalPrice();
        }
        return totalPrice;
    }

    public double getTotalQuatity() {
        double totalQuatity = 0;
        for (InvoiceItem x : invoiceItems) {
            totalQuatity += x.getQuantity();
        }
        return totalQuatity;
    }
}
