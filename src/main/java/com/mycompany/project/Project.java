/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


package com.mycompany.project;

public class Project {
    public static void main(String[] args) {
        //  واجهة المستخدم الرسومية في 
                EmailValidationWindow window = new EmailValidationWindow();

        
        // طباعة رسالة للتأكيد
        System.out.println("The program has been started successfully!");
        
        //هاد كان تيست اثناء عملية بناء مش مهم فمنعلقه
        
        
        /*
        System.out.println(" [1] Testing Polynomial Parsing");
        testParsing("3X^2 - 5X + 1");

        System.out.println("\n [2] Testing Addition");
        testOperation("X^2 + 2X + 1", "3X^3 + X + 10", "+");

        System.out.println("\n [3] Testing Subtraction");
        testOperation("X^2 + 2X + 1", "3X^3 + X + 10", "-");

        System.out.println("\n️ [4] Testing Multiplication");
        testOperation("X^2 + 2X + 1", "3X^3 + X + 10", "*");

        System.out.println("\n [5] Testing Division by the First Term");
        testOperation("X^2 + 2X + 1", "X", "/");

        System.out.println("\n [6] Testing Evaluation");
        testEvaluation("X^2 + 3X + 2", 1); // Expected: 6

        System.out.println("\n [7] Testing Output Formats");
       
        System.out.println("\n [8] Testing Invalid Inputs");
        testInvalid("3X^^2");       // Invalid syntax
        testInvalid("");            // Empty input
        testInvalid("abc + X^2");   // Invalid characters

        System.out.println("\n  Final testing completed.");
        */
    }

    private static void testParsing(String expr) {
        try {
            PolynomialLinkedList poly = PolynomialParser.parsePolynomial(expr);
            System.out.println("Input Expression: " + expr);
            System.out.println("Parsed Polynomial: " + poly);
        } catch (Exception e) {
            System.out.println("️ Parsing Error: " + e.getMessage());
        }
    }

    private static void testOperation(String p1Str, String p2Str, String op) {
        try {
            PolynomialLinkedList p1 = PolynomialParser.parsePolynomial(p1Str);
            PolynomialLinkedList p2 = PolynomialParser.parsePolynomial(p2Str);
            PolynomialLinkedList result;

            switch (op) {
                case "+" -> result = p1.add(p2);
                 case "-" -> result = p1.subtract(p2);
                    case "*" -> result = p1.multiply(p2);
                   case "/" -> result = p1.divide(p2);
default -> throw new IllegalArgumentException("Unknown operation.");
            }

            System.out.println("P1: " + p1);
            System.out.println("P2: " + p2);
            System.out.println("Operation: " + op);
            System.out.println("Result: " + result);

        } catch (Exception e) {
            System.out.println("️ Operation Error: " + e.getMessage());
        }
    }

    private static void testEvaluation(String expr, double x) {
        try {
            PolynomialLinkedList poly = PolynomialParser.parsePolynomial(expr);
            double result = poly.evaluate(x);
            System.out.println("Polynomial: " + expr);
            System.out.println("X = " + x + " → Result = " + result);
        } catch (Exception e) {
            System.out.println("️ Evaluation Error: " + e.getMessage());
        }
    }

    private static void testInvalid(String expr) {
        try {
            System.out.println("Testing invalid expression: " + expr);
            PolynomialLinkedList poly = PolynomialParser.parsePolynomial(expr);
            // بدي رموز هون فش وقت للايقونات   تذكير 
      
            System.out.println(" Unexpected success: " + poly);
        } catch (Exception e) {
            System.out.println("️ Correctly caught error: " + e.getMessage());
        }
        
        
      
    }
}