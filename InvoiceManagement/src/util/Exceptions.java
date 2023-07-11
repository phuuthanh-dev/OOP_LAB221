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

    public int getProductId(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        int n;

        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());

                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public int getInvoice(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        int n;

        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());

                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public double getPrice(String inputMsg, String errorMsg) {
        double n;

        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                break;               
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
        return n;
    }
    
    public int getChoice(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        int n;

        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
        return n;
    }
    
    public int getProductButId0(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        int n;

        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n == 0) {
                    return n;
                }

                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
}
