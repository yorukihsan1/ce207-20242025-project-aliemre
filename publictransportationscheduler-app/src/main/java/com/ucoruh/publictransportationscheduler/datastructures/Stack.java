package com.ucoruh.publictransportationscheduler.datastructures;

public class Stack {
  private int[] stack;
  private int top;
  private int capacity;

  public Stack(int capacity) {
    this.capacity = capacity;
    stack = new int[capacity];
    top = -1;
  }

  public void push(int value) {
    if (top == capacity - 1) {
      System.out.println("Stack overflow. Cannot push " + value);
      return;
    }

    stack[++top] = value;
  }

  public int pop() {
    if (top == -1) {
      System.out.println("Stack underflow. Nothing to pop.");
      return -1;
    }

    return stack[top--];
  }

  public int peek() {
    if (top == -1) {
      System.out.println("Stack is empty.");
      return -1;
    }

    return stack[top];
  }

  public boolean isEmpty() {
    return top == -1;
  }

  public void display() {
    if (top == -1) {
      System.out.println("Stack is empty.");
      return;
    }

    for (int i = 0; i <= top; i++) {
      System.out.print(stack[i] + " ");
    }

    System.out.println();
  }
}
