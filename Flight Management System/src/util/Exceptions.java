/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Exceptions {

    Scanner sc = new Scanner(System.in);

    public String inputFlightNumber(String inputMsg, String errorMsg, String format) {
        String id;
        boolean match;
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim().toUpperCase();
            match = id.matches(format);
            if (id.length() == 0 || id.isEmpty() || match == false) {
                System.out.println(errorMsg);
            } else {
                return id;
            }
        }

    }
    
    public int inputChoiceMain(int numberChoice) {
        int n;
        while (true) {
            try {
                System.out.print("Input your choice (1..." + numberChoice + "): ");
                n = Integer.parseInt(sc.nextLine());
                if (n < 1 || n > numberChoice) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Choose from 1 to " + numberChoice + "!");
            }
        }
        return n;
    }
    
    public int getAnInteger(String inputMsg, String errorMsg) {
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    
    public int getAnInteger(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < lowerBound || n > upperBound)
                    throw new Exception();                
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    
    public String getString(String inputMsg, String errorMsg) {
        String id;        
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine();            
            if (id.length() == 0 || id.isEmpty())
                System.out.println(errorMsg);
            else 
                return id;
        }
    }
    
    public boolean confirm(String inputMsg) {
        System.out.print(inputMsg);
        int result = Integer.parseInt(sc.nextLine());
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }
}
