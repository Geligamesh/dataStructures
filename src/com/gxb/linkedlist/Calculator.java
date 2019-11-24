package com.gxb.linkedlist;

public class Calculator {
}

class ArrayStack2 {

    private int maxSize;//栈的大小
    private int top = -1;//栈顶，初始化为-1
    private int[] stack;//数组，数组模拟栈，数据就放在该数组

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
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
