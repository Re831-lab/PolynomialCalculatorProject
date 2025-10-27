/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

//لتخزين جذور المعادلة
class DoubleLinkedList {
    DoubleNode head;
 //Constructor
    public DoubleLinkedList() {
        head = null;
    }
//ينشئ عقدة جديدة فيها القيمة
    public void add(double value) {
        
        DoubleNode newNode = new DoubleNode(value);
     //   إذا كانت القائمة فارغة
        if (head == null) {
            head = newNode;
            // إذا فيها عناصر
        } else {
            DoubleNode current = head;
            while (current.next != null) //بدنا نتحرك حتى نصل لآخر عنصر 
                current = current.next; // والعنصر الجديد بنحطه بالاخر
            current.next = newNode;
        }
        //إضافة تتم في نهاية القائمة
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        DoubleNode current = head;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null)
                sb.append(", ");
            current = current.next;
        }
        return sb.toString();
    }
}

