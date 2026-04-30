package com.brighton;

import java.util.List;

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
