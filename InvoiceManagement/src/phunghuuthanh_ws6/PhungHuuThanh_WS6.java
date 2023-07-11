/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phunghuuthanh_ws6;

import data.StoreHouse;
import data.CustomerManagement;
import data.Invoice;
import data.InvoiceManagement;
import java.util.Scanner;
import util.Exceptions;

/**
 *
 * @author Admin
 */
public class PhungHuuThanh_WS6 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;
        StoreHouse shop = new StoreHouse();
        CustomerManagement customer = new CustomerManagement();
        InvoiceManagement invoice = new InvoiceManagement(shop, customer);
        Exceptions toy = new Exceptions();
        do {
            printMenu();
            choice = toy.getChoice("Input your choice (1...8): ", "Choose from 1 to 8!!", 1, 8);
            switch (choice) {
                case 1:
                    shop.addNewProduct();
                    shop.printAllProduct();
                    break;
                case 2:
                    customer.addNewCustomer();
                    customer.printAllCustomer();
                    break;
                case 3:
                    invoice.createInvoice();
                    break;
                case 4:
                    optionSort(invoice, sc, toy);
                    break;
                case 5:
                    optionSearch(invoice, sc, toy);
                    break;
                case 6:
                    shop.printAllProduct();
                    break;
                case 7:
                    customer.printAllCustomer();
                case 8:
                    System.out.println("Bye bye, see you next time");
                    break;
            }
        } while (choice != 8);

    }

    public static void printMenu() {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Prescription Orders Management System");
        System.out.println("Choose the following functions to work");
        System.out.println("1. Add a new item. show a list of items contained.");
        System.out.println("2. Add a new customer. show a list of customers.");
        System.out.println("3. Create an invoice for the selected customer by entering the customer code.");
        System.out.println("4. Sort the invoice list.");
        System.out.println("5. Search invoices.");
        System.out.println("6. Show all products in store.");
        System.out.println("7. Show all customers coming to the store.");
        System.out.println("8. Quit");
    }

    public static void printMenuSort() {
        System.out.println("------------------------------------");
        System.out.println("Choose sort based on");
        System.out.println("4.1 Customer name (a-z).");
        System.out.println("4.2 Total quantity of goods purchased by customers (descending)");
        System.out.println("4.3 Total amount to be paid by the customer (decreasing). ");
        System.out.println("4.4 Print all invoice. ");
        System.out.println("4.5 Quit");

    }

    public static void printSearchMethod() {
        System.out.println("------------------------------------");
        System.out.println("Choose search based on.");
        System.out.println("5.1 Search invoice by customer name.");
        System.out.println("5.2 Search invoice by item name.");
        System.out.println("5.3 Print all invoice.");
        System.out.println("5.4 Quit");

    }

    public static void optionSort(InvoiceManagement invoice, Scanner sc, Exceptions toy) {
        int choice;
        do {
            printMenuSort();
            choice = toy.getChoice("Input your choice (1...5): ", "Choose from 1 to 5!", 1, 5);
            switch (choice) {
                case 1:
                    invoice.sortByCustomerName();
                    break;
                case 2:
                    invoice.sortByQuantity();
                    break;
                case 3:
                    invoice.sortByTotalPrice();
                    break;
                case 4:
                    invoice.printAllInvoice();
                    break;
                case 5:
                    System.out.println("Quitting...!");
                    break;
            }
        } while (choice != 5);
    }

    public static void optionSearch(InvoiceManagement invoice, Scanner sc, Exceptions toy) {
        int choice;
        do {
            printSearchMethod();
            choice = toy.getChoice("Input your choice (1...4): ", "Choose from 1 to 4!!", 1, 4);
            switch (choice) {
                case 1:
                    invoice.searchByCustomerName();
                    break;
                case 2:
                    invoice.searchByItemName();
                    break;
                case 3:
                    invoice.printAllInvoice();
                    break;
                case 4:
                    System.out.println("Quitting...!");
                    break;
                default:
                    System.out.println("Choose from 1 to 6!!");
                    break;
            }
        } while (choice != 4);
    }
}
