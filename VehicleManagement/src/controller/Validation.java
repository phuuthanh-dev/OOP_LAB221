/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Validation {

    Scanner sc = new Scanner(System.in);

    public String inputVehicleId(String inputMsg, String errorMsg, String format) {
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

    public long getAnLong(String inputMsg, String errorMsg) {
        long n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Long.parseLong(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public long getAnLongCanEmpty(String inputMsg, String errorMsg) {
        long n;
        while (true) {
            try {
                System.out.print(inputMsg);
                String input = sc.nextLine().trim();
                if (input.isEmpty()) {
                    return 0;
                }
                n = Long.parseLong(input);
                return n;
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            }
        }
    }

    public String getString(String inputMsg, String errorMsg) {
        String id;
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine();
            if (id.length() == 0 || id.isEmpty()) {
                System.out.println(errorMsg);
            } else {
                return id.trim();
            }
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

    public boolean checkEmptyString(String input) {
        if (input.isEmpty()) {
            return true;
        }
        return false;
    }

    public Year inputYear(String msg) {
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
        Scanner sc = new Scanner(System.in);
        Year year = null;

        while (year == null) {
            try {
                System.out.print(msg);
                String yearString = sc.nextLine().trim();
                year = Year.parse(yearString, yearFormatter);
            } catch (Exception e) {
                System.out.println("Invalid year format. Please use yyyy.");
            }
        }

        return year;
    }

    public Year inputYearCanEmpty(String msg) {
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
        Scanner sc = new Scanner(System.in);
        Year year = null;

        while (year == null) {
            try {
                System.out.print(msg);
                String yearString = sc.nextLine().trim();
                if (yearString.isEmpty()) {
                    return null;
                }
                year = Year.parse(yearString, yearFormatter);
            } catch (Exception e) {
                System.out.println("Invalid year format. Please use yyyy.");
            }
        }

        return year;
    }
}
