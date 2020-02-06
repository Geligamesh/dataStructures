package com.gxb.linkedlist;

public class Calculator {

    public static void main(String[] args) {

        String expression = "7+2*6-4";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        int num1;
        int num2;
        int oper;
        int result;
        int index = 0;
        char ch;

        ch = expression.substring(index, index + 1).charAt(0);
        while (true) {
            if (operStack.isOper(ch)) {
                if (operStack.isEmpty()) {
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        result = operStack.cal(num1, num2, oper);
                        numStack.push(result);
                        operStack.push(ch);
                    }else {
                        operStack.push(ch);
                    }
                }else {
                    operStack.push(ch);
                }
            }else {
                numStack.push(ch - 48);
            }
            index++;
            if (index == expression.length()) {
                break;
            }
        }

        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            result = operStack.cal(num1, num2, oper);
            numStack.push(result);
        }
        System.out.println("result=" + numStack.pop());
    }
}

class ArrayStack2 {

    private int maxSize;//栈的大小
    private int top = -1;//栈顶，初始化为-1
    private int[] stack;//数组，数组模拟栈，数据就放在该数组

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public int cal(int num1,int num2,int oper) {
        int result = 0;
        switch (oper) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                break;
        }
        return result;
    }

    //判断是不是一个运算符
    public boolean isOper(int oper) {
        return oper == '+' || oper == '-' || oper == '*' || oper == '/';
    }

    //返回运算符的优先级，优先级使用数字表示
    //数字越大，表示优先级强高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        }else if (oper == '+' || oper == '-') {
            return 0;
        }else {
            //假定目前的表达式只有+、-、*、/
            return -1;
        }
    }

    //增加一个方法，可以返回当前栈顶的值，但是不是真正的pop
    public int peek() {
        return stack[top];
    }

    //是否栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //是否栈空
    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历时，需要从栈顶开始显示数据
    public void list(){
        if (isEmpty()) {
            System.out.println("栈顶，没有数据~~~");
            return;
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }


}
