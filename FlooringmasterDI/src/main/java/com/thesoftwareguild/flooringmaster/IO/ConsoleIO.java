/*
 * 
 */
package com.thesoftwareguild.flooringmaster.IO;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.NumberFormatException;

/**
 *
 * @author calarrick and jonathan_chiou
 *
 * Public class to build ConsoleIO objects to handle user prompts and inputs in
 * console applications. Includes methods that take a string prompt as a
 * parameter and gather a numeric input from the user. "Bounded" versions of the
 * methods also take a numeric minimum and maximum value as additional
 * parameters after the prompt string, with a type that matches the desired
 * return type.
 *
 * Public methods are displayPrompt (which only displays a prompt), readInteger,
 * readFloat, and readDouble. The "read" methods can be called with "boundary"
 * parameters or not... e.g. readInteger(prompt, minimum, maximum) OR
 * readInteger(prompt).
 *
 * The class will internally generate and handle exceptions related to invalid
 * (mistyped) user input. The methods will continue to demand user input so long
 * as they are not given correctly-typed and (for the bounded methods) in-scope
 * input.
 *
 *
 *
 *
 *
 *
 *
 */
public class ConsoleIO {

    Scanner sc = new Scanner(System.in);

    public void displayPrompt(String prompt) {
        System.out.println(prompt);
    }
    

    public void inLine(String prompt) {
        System.out.print(prompt);
    }

    private float getFloat() throws NumberFormatException {

        String response = sc.next();

        float floatCnvt = Float.parseFloat(response);
        return floatCnvt;
    }

    private double getDouble() throws NumberFormatException {

        String response = sc.next();

        double doubleCnvt = Double.parseDouble(response);
        return doubleCnvt;
    }

    private int getInteger() throws NumberFormatException {//InputMismatchException {

        String response = sc.next();

        int intCnvt = Integer.parseInt(response);
        return intCnvt;

    }

    private int getIntegerLine() throws NumberFormatException {//InputMismatchException {
        sc = new Scanner(System.in);
        String response = sc.nextLine();

        int intCnvt = Integer.parseInt(response);
        return intCnvt;

    }

    public int readInteger(String prompt) {

        int usersNumber = 0;

        System.out.println(prompt);

        boolean userChoiceValid = false;

        while (!userChoiceValid) {
            try {
                usersNumber = getInteger();

                userChoiceValid = true;
            } catch (NumberFormatException e) {

                System.out.println("Input needs to be a number.");

            } catch (Exception e) {
                throw e;
            }

        }
        return usersNumber;
    }

    public int readInteger(String prompt, int min, int max) {

        int usersNumber = 0;
        boolean userChoiceValid = false;

        while (!userChoiceValid) {

            System.out.println(prompt);

            try {
                usersNumber = getInteger();

                if (usersNumber >= min && usersNumber <= max) {
                    userChoiceValid = true;
                } else {
                    System.out.
                            println("Input must be " + min + " through " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input needs to be a number between " + min + " and " + max);

            }
        }
        return usersNumber;

    }

    public int readIntegerLine(String prompt, int min, int max) {

        int usersNumber = 0;
        boolean userChoiceValid = false;

        while (!userChoiceValid) {

            System.out.println(prompt);

            try {
                usersNumber = getIntegerLine();

                if (usersNumber >= min && usersNumber <= max) {
                    userChoiceValid = true;
                } else {
                    System.out.
                            println("Input must be " + min + " through " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input must be a number between " + min + " and " + max);

            }
        }
        return usersNumber;

    }

    public String readString(String prompt) {

        System.out.print(prompt);
        String answer = sc.next();
        return answer;
    }

    public String readLine(String prompt) {

        sc = new Scanner(System.in);
        System.out.println(prompt);
        String answer = sc.nextLine();
        return answer;

    }

    

    public float readFloat(String prompt) {
        float usersNumber = 0;

        System.out.println(prompt);

        boolean userChoiceValid = false;

        while (!userChoiceValid) {
            try {
                usersNumber = getFloat();

                userChoiceValid = true;
            } catch (NumberFormatException e) {

                System.out.println("I need a floating point number.");

            } catch (Exception e) {
                throw e;
            }

        }
        return usersNumber;

    }

    public float readFloat(String prompt, float min, float max) {

        float usersNumber = 0;
        boolean userChoiceValid = false;//better to do with boolean
        while (!userChoiceValid) {
            while (usersNumber < min || usersNumber > max) {
                System.out.println(prompt);
                try {
                    usersNumber = getFloat();
                    userChoiceValid = true;
                } catch (NumberFormatException e) {
                    System.out.println("Input must be a floating point number");

                }
            }

        }
        return usersNumber;
    }

    public double readDouble(String prompt) {
        double usersNumber = 0;

        System.out.println(prompt);

        boolean userChoiceValid = false;

        while (!userChoiceValid) {
            try {
                usersNumber = getDouble();

                userChoiceValid = true;
            } catch (NumberFormatException e) {

                System.out.println("Input must be a decimal number.");

            } catch (Exception e) {
                throw e;
            }

        }
        return usersNumber;

    }

    public double readDouble(String prompt, double min, double max) {

        double usersNumber = 0;
        boolean usersChoiceValid = false;
        while (!usersChoiceValid) {

            while (usersNumber < min || usersNumber > max) {
                System.out.println(prompt);
                try {
                    usersNumber = getDouble();
                    usersChoiceValid = true;
                } catch (NumberFormatException e) {
                    System.out.println("Input must be a number between " + min + " and " + max);
                    usersNumber = min - 1.0f;
                }
            }

        }
        return usersNumber;
    }
}
