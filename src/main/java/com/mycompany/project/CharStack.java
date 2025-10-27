/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

public class CharStack {
    private char[] stack;
    private int top;
    private static final int MAX_SIZE = 100;

    public CharStack() {
        stack = new char[MAX_SIZE];
        top = -1;
    }

    public void push(char c) {
        if (top < MAX_SIZE - 1) {
            stack[++top] = c;
        } else {
            throw new RuntimeException("Stack overflow");
        }
    }

    public char pop() {
        if (top >= 0) {
            return stack[top--];
        } else {
            throw new RuntimeException("Stack underflow");
        }
    }

    public char peek() {
        if (top >= 0) {
            return stack[top];
        } else {
            throw new RuntimeException("Stack is empty");
        }
    }

    public boolean isEmpty() {
        return top == -1;
    }
}