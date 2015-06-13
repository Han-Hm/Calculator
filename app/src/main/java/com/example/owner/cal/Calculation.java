package com.example.owner.cal;
import java.util.Stack;

public class Calculation {
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
                sb.append(' ');
                i--;
            }//end else
        }//wnd for
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
            sb.append(' ');
        }//end while
        return sb.toString();

    }//postfix



    private static int operatorPriority(char operator) {
        if(operator == '+' || operator == '-') {
            return 1;
        } else if(operator == '*' || operator == '/') {
            return 2;
        } else {
            return 3;
        }//end else
    }//operatorPriority





    public static boolean isOperator(char ch) {
        return (ch == '+' || ch == '-' || ch == '*' || ch == '/');
    }//isOperator





    public static boolean isNumeric(char ch) {
//        return (ch >= '0' && ch <= '9');
        return (ch >= '0' && ch <= '9' || ch == '.');
    }//isNumeric

    //
//    public static boolean isDot(char ch) {
//        return ch=='.';
//    }//isDot



    public static double postfixCalc(String expression) {
        char[] exp;
        double num;
        int a=1;

        exp = expression.toCharArray();

        for(int i=0; i<exp.length; i++) {
            if(isNumeric(exp[i])) {
                num = 0;

                    do {
                            num = num * 10 + exp[i++] - '0';
                    } while (i < exp.length && isNumeric(exp[i]));

                    stack.push(num);
                    i--;
//
//                do {
//                    num = num * 10 + exp[i++] - '0';
//                } while (i < exp.length && isNumeric(exp[i]) && exp[i] != '.');
//                stack.push(num);
//
//                if (exp[i]=='.') {
//                    num=0;
//                    i++;
//                    int flag = i;
//                    do {
//                        num = num * 10 + exp[++i] - '0';
//                    } while(i < exp.length && isNumeric(exp[i]));
//
//                    for (int j=0; j <= (i-flag+1); j++) {
//                        a= a*10;
//                    }
//                    num = num / a;
//                    stack.push((double)stack.pop()+ num);
//                }
//                i--;
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
            }//end else
        }//end for
        return (double)stack.pop();
    }//postfixCalc

}//class