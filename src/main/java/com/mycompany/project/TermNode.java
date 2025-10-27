/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

public class TermNode {
    int coefficient; // 
    int exponent;
    TermNode next;

    public TermNode(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
        this.next = null;
    }

    @Override
    public String toString() {
        if (exponent == 0) return String.valueOf(coefficient); //5
        if (exponent == 1) return coefficient + "X";  //5X
        return coefficient + "X^" + exponent;  //5X^2
    }
    
}
