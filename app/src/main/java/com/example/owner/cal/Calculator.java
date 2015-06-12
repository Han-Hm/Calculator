package com.example.owner.cal;
import java.util.Stack;

public class Calculator {
    private static Stack stack = new Stack();


    public static String postfix(String expression) {
        StringBuilder sb = new StringBuilder();
        char[] exp;
        char ch;

        exp = expression.toCharArray();
        for(int i=0; i<exp.length; i++) {
            if(isOperator(exp[i])) {
                while(!stack.isEmpty() && operatorPriority((Character)stack.peek()) >= operatorPriority(exp[i])) {
                    sb.append(stack.pop());
                    sb.append(' ');
                }
                stack.push(exp[i]);

            } else if(isNumeric(exp[i])) {
                do {
                    sb.append(exp[i++]);
                } while(i<exp.length && isNumeric(exp[i]));
                sb.append(' '); i--;
            }
        }
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
            sb.append(' ');
        }
        return sb.toString();
    }



    private static int operatorPriority(char operator) {
        if(operator == '+' || operator == '-') return 1;
        if(operator == '*' || operator == '/') return 2;
        return 3;
    }





    public static boolean isOperator(char ch) {
        return (ch == '+' || ch == '-' || ch == '*' || ch == '/');
    }





    public static boolean isNumeric(char ch) {
        return (ch >= '0' && ch <= '9');
    }






    public static double postfixCalc(String expression) {
        char[] exp;
        double num;

        exp = expression.toCharArray();

        for(int i=0; i<exp.length; i++) {
            if(isNumeric(exp[i])) {
                num = 0;

                do {
                    num = num * 10 + exp[i++] - '0';
                } while(i < exp.length && isNumeric(exp[i]));
                stack.push(num); i--;

            } else if(exp[i] == '*') {
                stack.push((double)stack.pop() * (double)stack.pop());

            } else if(exp[i] == '+') {
                stack.push((double)stack.pop() + (double)stack.pop());

            } else if(exp[i] == '-') {
                num = (double)stack.pop();
                stack.push((double)stack.pop() - num);

            } else if(exp[i] == '/') {
                num = (double)stack.pop();
                if(num!=0){
                    stack.push((double)stack.pop() / num);}
            }
        }
        return (double)stack.pop();
    }
}