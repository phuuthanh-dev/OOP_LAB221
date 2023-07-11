/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import util.Exceptions;

/**
 *
 * @author Admin
 */
public class InvoiceManagement {

    private Exceptions toy = new Exceptions();
    private StoreHouse itemList;
    private CustomerManagement customerList;
    private ArrayList<Invoice> invoiceList = new ArrayList();

    private Scanner sc = new Scanner(System.in);

    public InvoiceManagement(StoreHouse itemList, CustomerManagement customerList) {
        this.itemList = itemList;
        this.customerList = customerList;
    }

    public void createInvoice() {
        int invoideCode;
        System.out.print("Enter customer code: ");
        String id = sc.nextLine();

        Customer customer = customerList.findCustomerObjectById(id);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }
        if (invoiceList.isEmpty()) {
            invoideCode = toy.getInvoice("Input invoice id(XXXXXXX): ", "Item code must be 7 digits!!!", 1000000, 9999999);
        } else {
            Invoice lastInvoice = invoiceList.get(invoiceList.size() - 1);
            invoideCode = lastInvoice.getInvoiceCode() + 1;
        }

        ArrayList<InvoiceItem> invoiceItems = new ArrayList();

        while (true) {
            int itemCode = toy.getProductButId0("Enter item code (0 to finish): ", "Item code must be 8 digits!!!", 10000000, 99999999);

            if (itemCode == 0) {
                break;
            }
            Item tmp = itemList.findItemObjectById(itemCode);

            if (tmp == null) {
                System.out.println("Item not found!");
                continue;
            }

            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(sc.nextLine());

            InvoiceItem invoiceItem = new InvoiceItem(tmp, quantity);
            invoiceItems.add(invoiceItem);
        }
        Invoice invoice = new Invoice(invoideCode, LocalDateTime.MIN, customer, invoiceItems);
        invoiceList.add(invoice);

        System.out.println("Invoice created successfully!");
        System.out.println("Here is your invoice!!!");
        printInvoice(invoice);
    }

    public void printInvoice(Invoice invoice) {
        System.out.println("+-----------------------------------------+");
        System.out.println("|                   BILL                  |");
        System.out.printf("|Invoice Code: %27d|\n", invoice.getInvoiceCode());
        System.out.printf("|Creation Time: %26s|\n", invoice.getCreationTime());
        System.out.printf("|Customer: %31s|\n", invoice.getCustomer().getFullName());
        System.out.printf("|Customer Code: %26s|\n", invoice.getCustomer().getCustomerCode());
        System.out.println("+-----------------------------------------+");

        ArrayList<InvoiceItem> invoiceItems = invoice.getInvoiceItems();
        System.out.println("|     Item Name |    Quantity |Total Price|");
        for (InvoiceItem x : invoiceItems) {
            System.out.printf("|%15s|%13d|%11.1f|\n",
                    x.getProduct().getItemName(), x.getQuantity(), x.getTotalPrice());
        }
        System.out.println("+-----------------------------------------+");
        System.out.printf("|Total Quantity: %25.1f|\n", invoice.getTotalQuatity());
        System.out.printf("|Total Price: %28.1f|\n", invoice.getTotalPrice());
        System.out.println("+-----------------------------------------+");
    }

    public void printAllInvoice() {
        System.out.println("HERE IS INVOICE LIST!!!");
        for (int i = 0; i < invoiceList.size(); i++) {
            System.out.println();
            System.out.println();
            printInvoice(invoiceList.get(i));
        }
    }

    public void sortByCustomerName() {
        if (invoiceList.isEmpty()) {
            System.out.println("Nothing to sort!");
        }
        for (int i = 0; i < invoiceList.size() - 1; i++) {
            for (int j = i + 1; j < invoiceList.size(); j++) {
                if (invoiceList.get(i).getCustomer().getFullName().compareToIgnoreCase(invoiceList.get(j).getCustomer().getFullName()) > 0) {
                    Invoice tmp = invoiceList.get(i);
                    invoiceList.set(i, invoiceList.get(j));
                    invoiceList.set(j, tmp);
                }
            }
        }
        System.out.println("The invoice list is sorted by customer name!");
    }

    public void sortByQuantity() {
        if (invoiceList.isEmpty()) {
            System.out.println("Nothing to sort!");
        }
        for (int i = 0; i < invoiceList.size() - 1; i++) {
            for (int j = i + 1; j < invoiceList.size(); j++) {
                if (invoiceList.get(i).getTotalQuatity() < invoiceList.get(j).getTotalQuatity()) {
                    Invoice tmp = invoiceList.get(i);
                    invoiceList.set(i, invoiceList.get(j));
                    invoiceList.set(j, tmp);
                }
            }
        }
        System.out.println("The invoice list is sorted by total quantity!");
    }

    public void sortByTotalPrice() {
        if (invoiceList.isEmpty()) {
            System.out.println("Nothing to sort!");
        }
        for (int i = 0; i < invoiceList.size() - 1; i++) {
            for (int j = i + 1; j < invoiceList.size(); j++) {
                if (invoiceList.get(i).getTotalPrice() < invoiceList.get(j).getTotalPrice()) {
                    Invoice tmp = invoiceList.get(i);
                    invoiceList.set(i, invoiceList.get(j));
                    invoiceList.set(j, tmp);
                }
            }
        }
        System.out.println("The invoice list is sorted by total quantity!");
    }

    public void searchByCustomerName() {
        System.out.print("Input the customer name that you want to find the invoice for: ");
        String name = sc.nextLine();

        if (invoiceList.isEmpty()) {
            System.out.println("Nothing to search!");
            return;
        }

        for (int i = 0; i < invoiceList.size(); i++) {
            if (invoiceList.get(i).getCustomer().getFullName().equalsIgnoreCase(name) == true) {
                System.out.println("Invoice found!!! Here is the invoice");
                printInvoice(invoiceList.get(i));
                return;
            }
        }
        System.out.println("Invoice not found!");
    }

//    public void searchByItemName() {
//        System.out.print("Enter item name to search: ");
//        String itemName = sc.nextLine();
//        ArrayList<Invoice> invoicelist = new ArrayList();
//
//        for (int i = 0; i < invoiceList.size(); i++) {
//            Invoice invoice = invoiceList.get(i);
//            ArrayList<InvoiceItem> invoiceItems = invoice.getInvoiceItems();
//            for (int j = 0; j < invoiceItems.size(); j++) {
//                InvoiceItem invoiceItem = invoiceItems.get(j);
//                if (invoiceItem.getProduct().getItemName().equalsIgnoreCase(itemName)) {
//                    invoicelist.add(invoiceList.get(i));
//                }
//
//            }
//        }
//        System.out.println("Invoice not found!");
//
//    }

    public void searchByItemName() {
        System.out.print("Enter item name: ");
        String itemName = sc.nextLine();
        ArrayList<Invoice> invoiceLists = new ArrayList();
        
        for (int i = 0; i < invoiceList.size(); i++) {
            Invoice invoice = invoiceList.get(i);
            ArrayList<InvoiceItem> itemList = invoice.getInvoiceItems();
            for (int j = 0; j < itemList.size(); j++) {
                if (itemList.get(j).getProduct().getItemName().equalsIgnoreCase(itemName)){
                    invoiceLists.add(invoice);
                }
            }
        }
        printAllInvoiceByList(invoiceLists);
    }
    
    public void printAllInvoiceByList(ArrayList<Invoice> invoiceLists) {
        if (invoiceLists.isEmpty()){
            System.out.println("Invoice not found!");
            return;
        }
        System.out.println("Invoice found!!! Here is the invoice");
        for (int i = 0; i < invoiceLists.size(); i++) {
            printInvoice(invoiceLists.get(i));
        }
    }
}
