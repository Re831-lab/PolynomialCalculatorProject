/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

public class PolynomialParser {
public static PolynomialLinkedList parsePolynomial(String expression) {
    PolynomialLinkedList polynomial = new PolynomialLinkedList();

    // التحقق من الإدخال
    if (expression == null || expression.equals("") || expression.equals(" ")) {
        throw new IllegalArgumentException("️ The equation is empty! Please enter an equation like: 3X^2 - 2X + 1");
    }

    // حذف المسافات يدويًا
    StringBuilder cleaned = new StringBuilder();
    for (int i = 0; i < expression.length(); i++) {
        char ch = expression.charAt(i);
        if (ch != ' ') {
            cleaned.append(ch);
        }
    }

    expression = cleaned.toString();
    int i = 0;
    int len = expression.length();

    while (i < len) {
        StringBuilder termBuilder = new StringBuilder();

        // التقاط الإشارة إذا موجودة
        if (expression.charAt(i) == '+' || expression.charAt(i) == '-') {
            termBuilder.append(expression.charAt(i));
            i++;
        }

        // بناء الحد حتى الإشارة التالية
        while (i < len && expression.charAt(i) != '+' && expression.charAt(i) != '-') {
            termBuilder.append(expression.charAt(i));
            i++;
        }

        String part = termBuilder.toString();
        if (part.equals("")) continue;

        int coefficient = 0;
        int exponent = 0;

        try {
            if (part.contains("X")) {
                int xIndex = part.indexOf("X");
                String coeffStr = part.substring(0, xIndex);

                // استخراج المعامل
                if (coeffStr.equals("") || coeffStr.equals("+")) {
                    coefficient = 1;
                } else if (coeffStr.equals("-")) {
                    coefficient = -1;
                } else {
                    coefficient = Integer.parseInt(coeffStr);
                }

                // استخراج الأس
                if (part.contains("^")) {
                    exponent = Integer.parseInt(part.substring(part.indexOf("^") + 1));
                    if (exponent < 0) {
                        throw new IllegalArgumentException("️ Negative exponent is not supported!");
                    }
                } else {
                    exponent = 1;
                }
            } else {
                // عدد ثابت
                coefficient = Integer.parseInt(part);
                exponent = 0;
            }

            polynomial.insertTerm(coefficient, exponent);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("️ Invalid formula in part: " + part);
        }
    }

    if (polynomial.isEmpty()) {
        throw new IllegalArgumentException("️ The polynomial is empty or invalid. Please check the equation format.");
    }

    return polynomial;
}



    // بديش اكتب الاسم اللي قد البلد هاد اختصار 
    static PolynomialLinkedList parse(String text) {
        return parsePolynomial(text);
    }
}
