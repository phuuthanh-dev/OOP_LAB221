/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.Scanner;
import util.Exceptions;

/**
 *
 * @author Admin
 */
public class CustomerManagement {

    private static ArrayList<Customer> customerList = new ArrayList();
    private Scanner sc = new Scanner(System.in);

    public void addNewCustomer() {
        String customerCode;
        String fullName;
        String address;
        String email;
        String phoneNumber;

        System.out.print("Input product customerCode: ");
        customerCode = sc.nextLine();

        System.out.print("Input product full name: ");
        fullName = sc.nextLine();

        System.out.print("Input product address: ");
        address = sc.nextLine();

        System.out.print("Input product email: ");
        email = sc.nextLine();

        System.out.print("Input product phone number: ");
        phoneNumber = sc.nextLine();

        Customer x = new Customer(customerCode, fullName, address, email, phoneNumber);
        customerList.add(x);
        System.out.println("Add customer successfully");
    }

    public void printAllCustomer() {
        System.out.println("Here is customer list: ");
        System.out.printf("|CUSTOMER CODE| CUSTOMER NAME |       ADDRESS       |     EMAIL     |   PHONE  |\n");
        for (Customer x : customerList) {
            x.showProfile();
        }
    }

    public Customer findCustomerObjectById(String id) {
        if (customerList.isEmpty()) {
            return null;
        }
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getCustomerCode().equalsIgnoreCase(id)) {
                return customerList.get(i);
            }
        }
        return null;
    }

    public int searchPositionCustomerByID(String id) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getCustomerCode().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }
}
