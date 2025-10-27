/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PolynomialCalculatorWindow extends JFrame {
    //تعريف المتغيرات الأساسية
    private JTextField poly1Field, poly2Field, xValueField;
    private JComboBox<String> operationBox, formatBox;
    private JTextArea resultArea;
    private JButton computeButton, evaluateButton, solveButton, clearButton;
    private PolynomialLinkedList lastResult;
    private final String[] availableOperations = {"+", "-", "*", "/"};
    private final String[] outputFormats = {"Infix", "Postfix", "Prefix"};
    //تعريف الألوان
    private final Color PRIMARY_COLOR = new Color(52, 152, 219);
    private final Color SECONDARY_COLOR = new Color(41, 128, 185);
    private final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private final Color TEXT_COLOR = new Color(44, 62, 80);

 // Constructor 
public PolynomialCalculatorWindow() {
    // إعدادات أولية لنافذة البرنامج
    setTitle("Mathematical equation calculator"); // عنوان النافذة
    setSize(700, 650); // أبعاد النافذة
    setDefaultCloseOperation(EXIT_ON_CLOSE); // إنهاء البرنامج عند إغلاق النافذة
    setLocationRelativeTo(null); // لجعل النافذة في وسط الشاشة
    getContentPane().setBackground(BACKGROUND_COLOR); // تغيير لون خلفية الواجهة

    // اللوحة الأساسية التي تحتوي كل المكونات
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // تخطيط عمودي
    mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25)); // هامش داخلي
    mainPanel.setBackground(BACKGROUND_COLOR);

    // عنوان كبير أعلى الواجهة
    JLabel title = new JLabel("Mathematical equation calculator", SwingConstants.CENTER);
    title.setFont(new Font("Arial", Font.BOLD, 28));
    title.setForeground(PRIMARY_COLOR);
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    mainPanel.add(title);

    // وصف صغير تحت العنوان
    JLabel description = new JLabel("Enter the equations and choose the operation to perform the calculations.", SwingConstants.CENTER);
    description.setFont(new Font("Arial", Font.ITALIC, 14));
    description.setForeground(TEXT_COLOR);
    description.setAlignmentX(Component.CENTER_ALIGNMENT);
    mainPanel.add(description);

    // لوحة إدخال المعادلات واختيار العمليات
    JPanel inputPanel = new JPanel(new GridLayout(3, 1, 5, 10));
    inputPanel.setBackground(BACKGROUND_COLOR);
    inputPanel.setBorder(BorderFactory.createTitledBorder("Input"));

    // حقول الإدخال للمعادلتين
    poly1Field = new JTextField("");
    poly2Field = new JTextField("");
    inputPanel.add(createLabeledPanel("The first equation:", poly1Field));
    inputPanel.add(createLabeledPanel("The second equation:", poly2Field));

    // لوحة لاختيار العملية والتنسيق
    JPanel operationsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 0));
    operationsPanel.setBackground(BACKGROUND_COLOR);

    // إنشاء ComboBox لخيارات العمليات
operationBox = new JComboBox<String>();
operationBox.addItem("+");
operationBox.addItem("-");
operationBox.addItem("*");
operationBox.addItem("/");
operationBox.setFont(new Font("Arial", Font.BOLD, 14));
    operationBox.setBackground(Color.WHITE);
    operationBox.setForeground(TEXT_COLOR);

    // إنشاء ComboBox لخيارات تنسيق الإخراج
formatBox = new JComboBox<String>();
formatBox.addItem("Infix");
formatBox.addItem("Postfix");
formatBox.addItem("Prefix");
    formatBox.setFont(new Font("Arial", Font.BOLD, 14));
    formatBox.setBackground(Color.WHITE);
    formatBox.setForeground(TEXT_COLOR);

    // عناوين اختيارية بجانب ComboBox
    JLabel opLabel = new JLabel("The process:");
    opLabel.setFont(new Font("Arial", Font.BOLD, 14));
    opLabel.setForeground(TEXT_COLOR);

    JLabel formatLabel = new JLabel("Output format:");
    formatLabel.setFont(new Font("Arial", Font.BOLD, 14));
    formatLabel.setForeground(TEXT_COLOR);

    // ترتيب المكونات داخل لوحة العمليات
    operationsPanel.add(opLabel);
    operationsPanel.add(operationBox);

    JPanel gapPanel = new JPanel(); // فراغ بسيط للفصل
    gapPanel.setBackground(BACKGROUND_COLOR);
    operationsPanel.add(gapPanel);

    operationsPanel.add(formatLabel);
    operationsPanel.add(formatBox);
    inputPanel.add(operationsPanel);
    mainPanel.add(inputPanel);

    // لوحة أزرار العمليات الرئيسية
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
    buttonPanel.setBackground(BACKGROUND_COLOR);

    computeButton = new JButton("Calculate"); // زر تنفيذ العملية
    styleButton(computeButton);

    clearButton = new JButton("Clear"); // زر مسح المدخلات
    styleButton(clearButton);

    buttonPanel.add(computeButton);
    buttonPanel.add(clearButton);
    mainPanel.add(buttonPanel);

    // لوحة التقييم عند قيمة X
    JPanel evaluationPanel = new JPanel(new BorderLayout(10, 5));
    evaluationPanel.setBackground(BACKGROUND_COLOR);
    evaluationPanel.setBorder(BorderFactory.createTitledBorder("Evaluation"));

    JPanel xPanel = new JPanel(new BorderLayout(10, 5));
    xPanel.setBackground(BACKGROUND_COLOR);

    JLabel xLabel = new JLabel("value of X:");
    xLabel.setFont(new Font("Arial", Font.BOLD, 14));
    xLabel.setForeground(TEXT_COLOR);

    xValueField = new JTextField("0");
    xValueField.setPreferredSize(new Dimension(80, 30));

    JPanel xInputPanel = new JPanel(new BorderLayout());
    xInputPanel.setBackground(BACKGROUND_COLOR);
    xInputPanel.add(xLabel, BorderLayout.WEST);
    xInputPanel.add(xValueField, BorderLayout.CENTER);

    JPanel evalButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
    evalButtonPanel.setBackground(BACKGROUND_COLOR);

    evaluateButton = new JButton("Evaluation of X ");
    styleButton(evaluateButton);

    solveButton = new JButton("Solve the equation");
    styleButton(solveButton);

    evalButtonPanel.add(evaluateButton);
    evalButtonPanel.add(solveButton);

    xPanel.add(xInputPanel, BorderLayout.NORTH);
    xPanel.add(evalButtonPanel, BorderLayout.CENTER);
    evaluationPanel.add(xPanel, BorderLayout.CENTER);
    mainPanel.add(evaluationPanel);

    // لوحة عرض النتائج
    JPanel resultPanel = new JPanel(new BorderLayout());
    resultPanel.setBackground(BACKGROUND_COLOR);
    resultPanel.setBorder(BorderFactory.createTitledBorder("Result"));

    resultArea = new JTextArea(8, 40);
    resultArea.setEditable(false);
    resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
    resultArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    resultArea.setBackground(Color.WHITE);
    resultArea.setForeground(TEXT_COLOR);

    JScrollPane scroll = new JScrollPane(resultArea);
    scroll.setBorder(BorderFactory.createLineBorder(SECONDARY_COLOR)); // يمكن استبداله إذا أردت شيء تقليدي
    resultPanel.add(scroll, BorderLayout.CENTER);
    mainPanel.add(resultPanel);

    // نص مساعد للمستخدم في الأسفل
    JPanel helpPanel = new JPanel(new BorderLayout());
    helpPanel.setBackground(BACKGROUND_COLOR);
    JLabel helpLabel = new JLabel("Example: 3X^2 - 2X + 1, notation: X (big) for the variable, ^ for the exponent", SwingConstants.CENTER);
    helpLabel.setFont(new Font("Arial", Font.ITALIC, 12));
    helpLabel.setForeground(TEXT_COLOR);
    helpPanel.add(helpLabel, BorderLayout.CENTER);
    mainPanel.add(helpPanel);

    // إضافة المكونات كلها إلى النافذة
    add(mainPanel);

    // استدعاء إعدادات الأحداث (Listeners)
    setupActions();

    // إظهار النافذة للمستخدم
    setVisible(true);
}


//Helper Method
    private JPanel createLabeledPanel(String labelText, JComponent field) {
      //  لتنظيم العناصر بشكل أفقي
        JPanel panel = new JPanel(new BorderLayout(10, 5)); 
        panel.setBackground(BACKGROUND_COLOR);

        JLabel label = new JLabel(labelText); //First Equation
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(TEXT_COLOR);

        panel.add(label, BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER); //[ Label: ][ TextField ]

        return panel;
    }

    //توحيد مظهر كل الأزرار في الواجهة
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14)); //الخط بالزر
        button.setBackground(PRIMARY_COLOR); //لون الازرق الفاتح
        button.setForeground(Color.WHITE); //النص لونه ازرق
    }

    private void setupActions() {
        computeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { //قراءة المعادلتين من المستخدم
                try {
                    //تحليلهم باستخدام PolynomialParser
                    PolynomialLinkedList p1 = PolynomialParser.parse(poly1Field.getText());
                    PolynomialLinkedList p2 = PolynomialParser.parse(poly2Field.getText());

                    //اختيار العملية المطلوبة 
                  String operation = (String) operationBox.getSelectedItem();
switch (operation) {
                        case "+": lastResult = p1.add(p2); break;
                        case "-": lastResult = p1.subtract(p2); break;
                        case "*": lastResult = p1.multiply(p2); break;
                        case "/": lastResult = p1.divide(p2); break;
                    }

                    // الاختيار الافتراضي اكيد انفيكس
                    String infix = lastResult.toString();
                    if (infix.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(PolynomialCalculatorWindow.this, "Result is empty, cannot format.");
                        return;
}

                    String format = (String) formatBox.getSelectedItem();
                    
                    resultArea.setForeground(TEXT_COLOR); //  اللون الافتراضي

                    //هاد اختيار المستخدم لطريقة العرض
                   String formatted;
try {
    switch (format) {
        case "Prefix": formatted = ExpressionFormatter.toPrefix(infix); break;
        case "Postfix": formatted = ExpressionFormatter.toPostfix(infix); break;
        default: formatted = infix; break;
    }
} catch (Exception formatEx) {
    formatted = infix; // استخدم التنسيق الأصلي إذا فشل التحويل
}

                    resultArea.setText(formatted); //بيعرض النتيجة في النص
                } catch (Exception ex) {
JOptionPane.showMessageDialog(
    null,
    "⚠️ " + ex.getMessage(),
    "Computation Error",
    JOptionPane.ERROR_MESSAGE
);
                }
            }
        });

       evaluateButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try {
            if (lastResult == null) { //يفحص إذا كانت هناك معادلة تم حسابها مسبقا
JOptionPane.showMessageDialog(
    null,
    "⚠️ Please calculate the equation first before evaluating X.",
    "Missing Calculation",
    JOptionPane.WARNING_MESSAGE
);
        

                return;
            }

            double x = Double.parseDouble(xValueField.getText()); //بيقرأ القيمة وبيحولها عشري
            double value = lastResult.evaluate(x);

            resultArea.setForeground(new Color(0, 128, 0)); // أخضر
            resultArea.append("\n\nValue at X = " + x + " -> " + value);

        } catch (NumberFormatException ex) { //اذا كانت القيمة مش رقم بيرمي اكسبشن
            resultArea.setForeground(Color.RED); // أحمر
            resultArea.append("\n\n⚠ Invalid number for X.");
            JOptionPane.showMessageDialog(null, "Invalid number for X.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
});


        solveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (lastResult == null) { //بيفحص اذا فيه معادلة او لا اكيد
                        JOptionPane.showMessageDialog(null,
                                "There is no equation to solve. Please calculate the equation first.",
                                "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    DoubleLinkedList roots = lastResult.solveQuadratic(); // نستدعي الدالة اكيد وبنحط الجذور في DoubleLinkedListت
                    // نص الرسالة
                    StringBuilder message = new StringBuilder("Solutions to the equation: " + lastResult.toString() + " = 0\n\n");
//أنواع الحلول المحتملة
                    if (roots.toString().equals(String.valueOf(Double.POSITIVE_INFINITY))) {
                        message.append("All real numbers are solutions."); // اعداد حقيقة 
                    } else if (roots.toString().contains(",")) {
                        String[] splitRoots = roots.toString().split(", ");
                        message.append("The two solutions are:\n"); // حلين 
                        message.append("X₁ = ").append(splitRoots[0]).append("\n");
                        message.append("X₂ = ").append(splitRoots[1]);
                    } else {
                        message.append("The only solution: X = ").append(roots.toString()); //جذر واحد
                    }
// بدنا نعرض النتيجة
                    JOptionPane.showMessageDialog(null, message.toString(), "Solution Results", JOptionPane.INFORMATION_MESSAGE);

                    resultArea.setForeground(new Color(0, 128, 0)); // أخضر
resultArea.append("\n\n" + message);

                } catch (Exception ex) { // عشان بدي تربيعي او لو مستخدم عمل خبوصة
                    resultArea.setForeground(Color.RED); // أحمر
resultArea.append("\n\n⚠ This equation cannot be solved: " + ex.getMessage());

                    JOptionPane.showMessageDialog(null,
                            "This equation cannot be solved: " + ex.getMessage(),
                            "Solution Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                poly1Field.setText("");
                poly2Field.setText("");
                xValueField.setText("0");
                resultArea.setText("");
                lastResult = null;
                resultArea.setForeground(TEXT_COLOR); // نرجع اللون طبيعي 

            }
        });
    }
} 