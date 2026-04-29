package com.brighton;

import java.util.List;

class Queue {
    private Process[] arr;
    private int start;
    private int end;
    private int capacity;
    private int size;

    // Constructor to initialize the queue
    public Queue(int capacity) {
        this.capacity = capacity;
        arr = new Process[capacity];
        start = 0;
        end = -1;
        size = 0;
    }

    public Queue(List<Process> processes) {
        this(processes.size());
        for (Process p : processes) {
            enqueue(p);
        }
    }

    // Insert an element at the rear of the queue
    public void enqueue(Process item) {
        if (isFull()) {
            System.out.println("Queue is full");
            return;
        }
        end = (end + 1) % capacity;
        arr[end] = item;
        size++;
    }

    // Remove and return the element from the front of the queue
    public Process dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        Process removedItem = arr[start];
        start = (start + 1) % capacity;
        size--;
        return removedItem;
    }

    // Return the element at the front of the queue without removing it
    public Process peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        return arr[start];
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Check if the queue is full
    public boolean isFull() {
        return size == capacity;
    }
}