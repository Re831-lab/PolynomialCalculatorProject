/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;



public class ExpressionFormatter {

    public static String[] tokenize(String expr) {
        StringBuilder result = new StringBuilder();
        StringBuilder number = new StringBuilder();
        boolean isNegative = false;

        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);

            if (ch == '-' && (i == 0 || expr.charAt(i - 1) == '(' || isOperator(expr.charAt(i - 1)))) {
                isNegative = true;
                continue;
            }

            if (Character.isDigit(ch) || ch == '.') {
                number.append(ch);
            } else if (ch == 'X' || ch == 'x') {
                if (number.length() > 0) {
                    if (isNegative) {
                        result.append("- ").append(number).append(" * X ");
                        isNegative = false;
                    } else {
                        result.append(number).append(" * X ");
                    }
                    number.setLength(0);
                } else {
                    result.append("X ");
                }
            } else {
                if (number.length() > 0) {
                    if (isNegative) {
                        result.append("- ").append(number).append(" ");
                        isNegative = false;
                    } else {
                        result.append(number).append(" ");
                    }
                    number.setLength(0);
                }
                result.append(ch).append(" ");
            }
        }

        if (number.length() > 0) {
            if (isNegative) {
                result.append("- ").append(number).append(" ");
            } else {
                result.append(number).append(" ");
            }
        }

        return result.toString().trim().split("\\s+");
    }

    public static String toPostfix(String infix) {
        if (infix == null || infix.isEmpty()) return "";

        StringBuilder output = new StringBuilder();
        CharStack stack = new CharStack();
        String[] tokens = tokenize(infix);

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            if (isNumber(token) || token.equals("X")) {
                output.append(token).append(" ");
            } else if (token.equals("(")) {
                stack.push('(');
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop()).append(" ");
                }
                stack.pop(); // Remove '('
            } else if (token.length() == 1 && isOperator(token.charAt(0))) {
                char op = token.charAt(0);
                while (!stack.isEmpty() && stack.peek() != '(' &&
                        (precedence(stack.peek()) > precedence(op) ||
                                (precedence(stack.peek()) == precedence(op) && op != '^'))) {
                    output.append(stack.pop()).append(" ");
                }
                stack.push(op);
            }
        }

        while (!stack.isEmpty()) {
            output.append(stack.pop()).append(" ");
        }

        return output.toString().trim();
    }

    public static String toPrefix(String infix) {
        if (infix == null || infix.isEmpty()) return "";

        String[] tokens = tokenize(infix);

        // Reverse tokens
        for (int i = 0; i < tokens.length / 2; i++) {
            String temp = tokens[i];
            tokens[i] = tokens[tokens.length - 1 - i];
            tokens[tokens.length - 1 - i] = temp;
        }

        // Swap parentheses
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("(")) tokens[i] = ")";
            else if (tokens[i].equals(")")) tokens[i] = "(";
        }

        String reversedInfix = String.join(" ", tokens);
        String postfix = toPostfix(reversedInfix);
        String[] postTokens = postfix.split("\\s+");

        // Reverse postfix to get prefix
        for (int i = 0; i < postTokens.length / 2; i++) {
            String temp = postTokens[i];
            postTokens[i] = postTokens[postTokens.length - 1 - i];
            postTokens[postTokens.length - 1 - i] = temp;
        }

        return String.join(" ", postTokens);
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private static int precedence(char op) {
        switch (op) {
            case '+':
            case '-': return 1;
            case '*':
            case '/': return 2;
            case '^': return 3; // right-associative
            default: return -1;
        }
    }

    private static boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}






  
