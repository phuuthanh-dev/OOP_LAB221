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
public class Customer {
    private String customerCode;
    private String fullName;
    private String address;
    private String email;
    private String phoneNumber;

    public Customer(String customerCode, String fullName, String address, String email, String phoneNumber) {
        this.customerCode = customerCode;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerCode=" + customerCode + ", fullName=" + fullName + ", address=" + address + ", email=" + email + ", phoneNumber=" + phoneNumber + '}';
    }
    
    public void showProfile() {
        System.out.printf("|%13s|%15s|%21s|%15s|%10s|\n", customerCode, fullName, address, email, phoneNumber);
    }
}
