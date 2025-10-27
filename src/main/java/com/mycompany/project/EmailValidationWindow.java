/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//نافذة التحقق من صحة البريد الإلكتروني
package com.mycompany.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmailValidationWindow extends JFrame {
    private JTextField emailField;  //إدخال البريد
    private final JButton submitButton; //زر التأكيد
    private JLabel messageLabel;

    // تعريف الألوان
    private final Color PRIMARY_COLOR = new Color(52, 152, 219);
    private final Color TEXT_COLOR = new Color(44, 62, 80);
    private final Color BACKGROUND_COLOR = new Color(236, 240, 241);

    public EmailValidationWindow() {
        setTitle("Email Validation");
        setSize(400, 170);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // تحسين تخطيط الواجهة
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10)); //عشان ما تلزق مكونات ببعض
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); //حواف عشان ما يلزقوا بالحواف
        mainPanel.setBackground(BACKGROUND_COLOR);

        // حقل إدخال البريد الإلكتروني
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5)); //inputPanel
inputPanel.add(new JLabel("Enter your email:"), BorderLayout.NORTH);  //label
        emailField = new JTextField(); //حقل الادخال
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        emailField.setBackground(Color.WHITE);
        emailField.setForeground(TEXT_COLOR);
        inputPanel.add(emailField, BorderLayout.CENTER); //حطينا على بانيل
        inputPanel.setBackground(BACKGROUND_COLOR);

        // زر التأكيد
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(PRIMARY_COLOR);
        submitButton.setForeground(Color.WHITE);

        // رسالة حالة التحقق
messageLabel = new JLabel("");
messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        messageLabel.setForeground(TEXT_COLOR);

        // إضافة المكونات إلى اللوحة الرئيسية
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.add(submitButton, BorderLayout.NORTH);
        bottomPanel.add(messageLabel, BorderLayout.SOUTH); // الرسالة تظهر تحت الزر مباشرة، عشان المستخدم يشوف الخطأ
        bottomPanel.setBackground(BACKGROUND_COLOR); //توحيد الخلفية مع باقي الواجهة

        mainPanel.add(bottomPanel, BorderLayout.SOUTH); 
        add(mainPanel);

        submitButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String email = emailField.getText();
        if (isValidEmail(email)) {
    setVisible(false); // إخفاء النافذة بدل إغلاقها
            new PolynomialCalculatorWindow(); // فتح النافذة التالية
        } else {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Invalid email format. Try again.");
            
              // مسح حقل الإيميل
            emailField.setText("");
            

        }
    }
});


        setVisible(true);
    }
    
//    //التحقق من صحة  الإيميل
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".") && email.indexOf('@') < email.lastIndexOf('.');
    }
}
