package com.brighton;

import com.brighton.Scheduler;

import java.util.List;
import java.util.LinkedList;
import java.util.PriorityQueue;


public class RoundRobin extends Scheduler {
    Queue processQueue;

    @Override
    boolean hasProcess() {
        return !processQueue.isEmpty();
    }

    @Override
    void initializeQueue(List<Process> processes) {
        processQueue = new Queue(processes);
    }

    @Override
    void addProcess(Process process) {
        processQueue.enqueue(process);
    }

    @Override
    Process removeProcess() {
        return processQueue.dequeue();
    }
}
