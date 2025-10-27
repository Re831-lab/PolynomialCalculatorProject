/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

public class PolynomialLinkedList {
    TermNode head;   //هو رأس القائمة (أول عنصر فيها)، وبيمثل الحد الأعلى في كثيرة الحدود.

    public PolynomialLinkedList() {
        head = null; //في البداية، القائمة فاضية لذلك head = null.
    }

    
    //خطوتي ثانية انه نعمل دالة هذه الدالة مسؤولة عن إدخال حد جديد إلى كثيرة الحدود (يعني: معامل + أس).
//
    public void insertTerm(int coefficient, int exponent) {
        if (coefficient == 0) return;

        TermNode newNode = new TermNode(coefficient, exponent);

        if (head == null || head.exponent < exponent) {
            newNode.next = head;
            head = newNode;
        } else {
            TermNode current = head;
            TermNode prev = null;

            while (current != null && current.exponent > exponent) {
                prev = current;
                current = current.next;
            }

            if (current != null && current.exponent == exponent) {
                current.coefficient += coefficient;
                if (current.coefficient == 0) {
                    if (prev == null) head = current.next;
                    else prev.next = current.next;
                }
            } else {
                newNode.next = current;
                if (prev == null) head = newNode;
                else prev.next = newNode;
            }
        }
    }

    //هذي دالة جمع كثيرتين حدود، كل وحدة مخزّنة على شكل LinkedList.
    /*الدالة تمر على كل حدين من القائمتين وتدمجهم حسب الأس.
    وتستخدم insertTerm() 
    لضمان الترتيب والتعامل مع التكرار*/
    public PolynomialLinkedList add(PolynomialLinkedList other) {
        PolynomialLinkedList result = new PolynomialLinkedList();
        TermNode p1 = this.head, p2 = other.head;

        while (p1 != null && p2 != null) {
            if (p1.exponent == p2.exponent) {
                result.insertTerm(p1.coefficient + p2.coefficient, p1.exponent);
                p1 = p1.next;
                p2 = p2.next;
            } else if (p1.exponent > p2.exponent) {
                result.insertTerm(p1.coefficient, p1.exponent);
                p1 = p1.next;
            } else {
                result.insertTerm(p2.coefficient, p2.exponent);
                p2 = p2.next;
            }
        }

        while (p1 != null) {
            result.insertTerm(p1.coefficient, p1.exponent);
            p1 = p1.next;
        }

        while (p2 != null) {
            result.insertTerm(p2.coefficient, p2.exponent);
            p2 = p2.next;
        }

        return result;
    }
    //هذه الدالة تنفّذ عملية طرح بين قائمتين تمثلان كثيرتي حدود.

//الدالة تطرح بين قائمتين تمثل كل منهما كثيرة حدود، وتقارن الأسس وتطرح المعاملات أو تضيف الحد كما هو بإشارته المناسبة.
  public PolynomialLinkedList subtract(PolynomialLinkedList secondPoly) {
    PolynomialLinkedList result = new PolynomialLinkedList();
    TermNode p1 = this.head;
    TermNode p2 = secondPoly.head;

    while (p1 != null && p2 != null) {
        if (p1.exponent == p2.exponent) {
            result.insertTerm(p1.coefficient - p2.coefficient, p1.exponent);
            p1 = p1.next;
            p2 = p2.next;
        } else if (p1.exponent > p2.exponent) {
            result.insertTerm(p1.coefficient, p1.exponent);
            p1 = p1.next;
        } else {
            result.insertTerm(-p2.coefficient, p2.exponent);
            p2 = p2.next;
        }
    }

    while (p1 != null) {
        result.insertTerm(p1.coefficient, p1.exponent);
        p1 = p1.next;
    }

    while (p2 != null) {
        result.insertTerm(-p2.coefficient, p2.exponent);
        p2 = p2.next;
    }

    return result;
}

//هذه الدالة تقوم بـ ضرب كثيرتين حدود مخزّنتين على شكل قائمتين مرتبطة (Linked Lists).
/*كل حد من اولى بنضربه بالثانية  النتيجة بتدخل 
  للinsertTerm()
  اللي تنظم الترتيب وتدمج الحدود المتشابهة تلقائيًا*/
  public PolynomialLinkedList multiply(PolynomialLinkedList other) {
        PolynomialLinkedList result = new PolynomialLinkedList();
        for (TermNode p1 = this.head; p1 != null; p1 = p1.next) {
            for (TermNode p2 = other.head; p2 != null; p2 = p2.next) {
                int coeff = p1.coefficient * p2.coefficient;
                int expo = p1.exponent + p2.exponent;
                result.insertTerm(coeff, expo);
            }
        }
        return result;
    }
/**
 * هذه الطريقة مستوحاة من خوارزمية قسمة كثيرات الحدود (Polynomial Division)
  *Polynomial ÷ Polynomial */

public PolynomialLinkedList divide(PolynomialLinkedList divisor) {
    if (divisor.head == null) {
    //   التحقق من القسمة على صفر
        throw new ArithmeticException("Division by zero polynomial.");
    }
    //التحقق إن درجة المقسوم أقل
    if (this.head == null || this.head.exponent < divisor.head.exponent) {
        return new PolynomialLinkedList(); //// نتيجة القسمة = 0
    }
    
    PolynomialLinkedList quotient = new PolynomialLinkedList(); //لتخزين الناتج النهائي
    PolynomialLinkedList remainder = this.copy(); 
    
    //القسمة المطولة 
    while (remainder.head != null && divisor.head != null &&  //لو درجة الباقي اكبر او تساوي المقسوم عليه
           remainder.head.exponent >= divisor.head.exponent) {
        // نحسب الحد التالي في الناتج
        int coeff = remainder.head.coefficient / divisor.head.coefficient;
        int expo = remainder.head.exponent - divisor.head.exponent;
        // نتوقف اذا طلع صفر
        if (coeff == 0) {
            break;
        }
        //نجيب الحد المؤقت
        PolynomialLinkedList term = new PolynomialLinkedList();
        term.insertTerm(coeff, expo);
        //نضيفه للناتج
        quotient.insertTerm(coeff, expo);
        //نضرب المقسوم عليه بالحد المؤقت 
        PolynomialLinkedList product = divisor.multiply(term);
        //ونطرحه من الباقي
        remainder = remainder.subtract(product);
    }
    
    return quotient;
}


//ننسخ كل حد إلى قائمة جديدة بدون أي ارتباط بالقائمة القديمة.
//الدالة هذه تنشئ نسخة جديدة مستقلة بالكامل من كثيرة الحدود الأصلية
public PolynomialLinkedList copy() {
    PolynomialLinkedList result = new PolynomialLinkedList();
    TermNode current = this.head; //نبدأ بالأول حد في الاصلية طبعا
    //نمر على كل حد في القائمة الأصلية
    while (current != null) {
        result.insertTerm(current.coefficient, current.exponent); //نضيف الحد لنسخة جديدة
        current = current.next;
    }
    
    return result;
}



    @Override
    public String toString() {
        if (head == null) return "0"; // اذا فش حدود رجلعي صفر

        StringBuilder sb = new StringBuilder(); //نص نهائي
        TermNode current = head; //نبدأ من الأس الاكبر

        while (current != null) {
            if (sb.length() > 0 && current.coefficient > 0) sb.append(" + "); //اذا الحد مش الاول ومعامله موجب ضيف زائد
            else if (current.coefficient < 0) sb.append(" - "); //اذا الحد سالب
// بدي أخذ القيمة المطلقة
            int absCoeff = Math.abs(current.coefficient);
            if (current.exponent == 0) sb.append(absCoeff); // اذا الاس صفر -> عدد ثابت
            else if (current.exponent == 1) sb.append(absCoeff).append("X"); //مثل 5X
            else sb.append(absCoeff).append("X^").append(current.exponent); //مثل 5X^3

            current = current.next;
        }

        return sb.toString();
    }
    
    
    public int getCoefficient(int exponent) {
    TermNode current = head;
    while (current != null) {
        if (current.exponent == exponent) {
            return current.coefficient;
        }
        current = current.next;
    }
    return 0; // إذا ما لقيت الحد بالأس المطلوب
}

//الدالة تحسب قيمة  كثيرة الحدود عند قيمة معينة ّ 
    public double evaluate(double x) {
        double result = 0.0;
        TermNode current = head;

        while (current != null) {
            result += current.coefficient * Math.pow(x, current.exponent);
            current = current.next;
        }

        return result;
    }
    public boolean isEmpty() {
    return head == null; // أو أي منطق مناسب حسب بنيتك
}

    /*الدالة تمر على كل حد وتستخرج معاملات
    a, b, c.
    لو المعادلة من الدرجة الثانية أو أقل، تحسب الجذور حسب الحالة: ثابتة، خطية، أو تربيعية
    وتستخدم delta للفصل بين نوع الحلول*/
 public DoubleLinkedList solveQuadratic() throws Exception {
    double a = 0, b = 0, c = 0;
    int highestDegree = 0;

    //  تحديد أعلى درجة وتجميع معاملات a, b, c
    TermNode current = head;
    while (current != null) {
        if (current.exponent > highestDegree) {
            highestDegree = current.exponent;
        }

        switch (current.exponent) {
            case 2 -> a = current.coefficient;
            case 1 -> b = current.coefficient;
            case 0 -> c = current.coefficient;
        }

        current = current.next;
    }

    //  نتحقق إن المعادلة من الدرجة الثانية أو أقل
    if (highestDegree > 2) {
throw new Exception("This is a degree " + highestDegree + " equation. Only quadratic (degree 2) equations can be solved.");
    }

    //  نجهز القائمة لتخزين الحلول
    DoubleLinkedList result = new DoubleLinkedList();

    //  حالة خاصة: a = 0 → معادلة خطية أو ثابتة
    if (a == 0) {
        if (b == 0) {
            if (c == 0) {
                result.add(Double.POSITIVE_INFINITY); // جميع القيم حل
                return result;
            } else {
throw new Exception("No solution for the constant equation: " + c + " = 0");
            }
        }

        // معادلة خطية: bx + c = 0
        double solution = -c / b;
        solution = Math.round(solution * 10000) / 10000.0;
        result.add(solution);
        return result;
    }

    // حساب المميز (delta)
    double delta = b * b - 4 * a * c;

    if (Math.abs(delta) < 1e-10) delta = 0; // تصحيح خطأ التقريب

    if (delta < 0) {
throw new Exception("No real solutions.\nDiscriminant (Delta) is negative: " + delta);
    }

    //  حساب الجذور
    double sqrtDelta = Math.sqrt(delta);
    double x1 = (-b + sqrtDelta) / (2 * a);
    double x2 = (-b - sqrtDelta) / (2 * a);

    // تقريب النتائج
    x1 = Math.round(x1 * 10000) / 10000.0;
    x2 = Math.round(x2 * 10000) / 10000.0;

    //  تخزين الجذور في القائمة
    result.add(x1);
    if (delta != 0) result.add(x2);

    return result;
}
}

