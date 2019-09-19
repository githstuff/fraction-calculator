package com.githstuff;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        int runCycle=1;
        Scanner scanner = new Scanner(System.in);
        String operationString="Start";
        while (!operationString.equals("Q")){
            System.out.println("Run"+ runCycle);
            runCycle++;
            operationString = getOperation(scanner);
            preformOperation(operationString, scanner);
        }
    }

    private static void preformOperation(String operationString, Scanner scanner) {
        switch(operationString){
            case "+": {
                Fraction firstFraction=getFraction(scanner);
                Fraction secondFraction = getFraction(scanner);
                Fraction sumFraction=firstFraction.add(secondFraction);
                System.out.println(firstFraction.toString() +" plus " +secondFraction.toString()+ " = " + sumFraction.toString());
                break;
            }
            case "-": {
                Fraction firstFraction=getFraction(scanner);
                Fraction secondFraction = getFraction(scanner);
                Fraction differenceFraction=firstFraction.subtract(secondFraction);
                System.out.println(firstFraction.toString() +" minus " +secondFraction.toString()+ " = " + differenceFraction.toString());
                break;
            }
            case "/": {
                Fraction firstFraction=getFraction(scanner);
                Fraction secondFraction = getFraction(scanner);
                Fraction quotientFraction=firstFraction.divide(secondFraction);
                System.out.println(firstFraction.toString() +" divided by " +secondFraction.toString()+ " = " + quotientFraction.toString());
                break;
            }
            case "*": {
                Fraction firstFraction=getFraction(scanner);
                Fraction secondFraction = getFraction(scanner);
                Fraction productFraction=firstFraction.multiply(secondFraction);
                System.out.println(firstFraction.toString() +" times " +secondFraction.toString()+ " = " + productFraction.toString());
                break;
            }
            case "=": {
                Fraction firstFraction=getFraction(scanner);
                Fraction secondFraction = getFraction(scanner);
                if(firstFraction.equals(secondFraction)){
                    System.out.println(firstFraction.toString()+ " equals " + secondFraction.toString());
                } else{
                    System.out.println(firstFraction.toString()+ " does not equal " + secondFraction.toString());
                }
                break;

            }
            case "Q": break;
        }
    }

    public static String getOperation(Scanner scanner){

        boolean isValidOperation=false;
        String operationString = "Q";
        while (!isValidOperation){System.out.println("Please enter an operation  (+, -, /, *, = or Q to Quit): ");
            operationString= scanner.next();

            if(!(Pattern.matches("^([+\\-/*=Q])",operationString))) {
                System.out.println("Invalid Input. ");
        }else {
                isValidOperation= true;
            }
        }
        return operationString;
    }

    public static boolean validateFraction(String fractionString){
        boolean isValidFraction=false;
        boolean hasNumerator=checkForNumerator(fractionString);
        if(!hasNumerator){
            isValidFraction = Pattern.matches("^-?\\d{1,10}$", fractionString);

        }else{
            isValidFraction = Pattern.matches("^-?\\d{1,10}[/]-?\\d{1,10}\n?$", fractionString);
        }
        if (!isValidFraction){
            System.out.println("Check format. (integer/integer) or (integer). Integer must be no longer than 10 digits.");
            System.out.println("Example: 2/17 or 20)");
        }
        return isValidFraction;
    }

    private static boolean checkForNumerator(String fractionString) {
        for(int i = 0; i<fractionString.length()-1;i++){
            if (fractionString.charAt(i)=='/'){
                return true;
            }
        }
        return false;
    }

    private static Fraction getFraction(Scanner scanner){
        boolean isValidFraction=false;
        int numerator=1;
        int denominator=1;
        String fractionString ="";
        while (!isValidFraction){
            System.out.println("Please enter a fraction (a/b): ");
            fractionString=scanner.next();
            isValidFraction= validateFraction(fractionString);
        }
        int slashLocation=0;
        boolean hasNumerator=false;
        for(int i=0;i<fractionString.length();i++){
            if ((fractionString.charAt(i) !='/')&& !(hasNumerator)){
                slashLocation++;
            }else{
                hasNumerator=true;
            }
        }

        if (hasNumerator){
            numerator = Integer.parseInt(fractionString,0,slashLocation,10);
            denominator = Integer.parseInt(fractionString,
                    slashLocation+1,fractionString.length(),10);

        } else{
            numerator=Integer.parseInt(fractionString);
            denominator = 1;
        }
        return new Fraction(numerator, denominator);
    }

}
