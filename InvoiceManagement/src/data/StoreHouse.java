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
public class StoreHouse {

    private static ArrayList<Item> itemList = new ArrayList();
    private Scanner sc = new Scanner(System.in);
    private Exceptions toy = new Exceptions();

    public void addNewProduct() {
        int itemCode;
        String itemName, origin;
        double sellingPrice;
        ProductGroup productGroup = new ProductGroup();
        String tmp;

        if (itemList.isEmpty()) {
            itemCode = toy.getProductId("Input item id(XXXXXXXX): ", "Item code must be 8 digits!!!", 10000000, 99999999);
        } else {
            Item lastItem = itemList.get(itemList.size() - 1);
            itemCode = lastItem.getItemCode() + 1;
        }

        System.out.print("Input product name: ");
        itemName = sc.nextLine();

        String group = productGroup.chooseGroup();
        
        sellingPrice = toy.getPrice("Input item price: ", "Incorrect format, please input again!!!");

        System.out.print("Input product origin: ");
        origin = sc.nextLine();

        Item product = new Item(itemCode, itemName, group, sellingPrice, origin);
        itemList.add(product);

        System.out.println("Add product successfully");
    }

    public void printAllProduct() {
        System.out.println("Here is product list: ");
        System.out.printf("|   ID   | PRODUCT NAME|    PRODUCT GROUP   |   PRICE  | ORIGIN |\n");
        for (Item x : itemList) {
            x.showInfo();
        }
    }

    public Item findItemObjectById(int id) {
        if (itemList.isEmpty()) {
            return null;
        }
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getItemCode() == id) {
                return itemList.get(i);
            }
        }
        return null;
    }
}
