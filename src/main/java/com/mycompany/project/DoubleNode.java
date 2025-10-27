/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;


class DoubleNode {
    double value; // قيمة الجذر 
    DoubleNode next; //عقدة 

    //Constructor
    public DoubleNode(double value) {
        this.value = value; //ننشء عقدة بالقيمة المطلوبة 
        this.next = null; //يمكن تكون وحيدة :(
    }
}
