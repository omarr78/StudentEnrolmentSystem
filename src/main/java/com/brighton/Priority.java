package com.brighton;

import com.brighton.Scheduler;

import java.util.List;
import java.util.PriorityQueue;

public class Priority extends Scheduler {
    PriorityQueue<Process> processQueue;

    @Override
    boolean hasProcess() {
        return !processQueue.isEmpty();
    }

    @Override
    void initializeQueue(List<Process> processes) {
        processQueue = new PriorityQueue<Process>(processes);
    }

    @Override
    void addProcess(Process process) {
        processQueue.add(process);
    }

    @Override
    Process removeProcess() {
        return processQueue.poll();
    }
}
