package com.brighton;

import java.util.List;

public class MLFQ extends Scheduler {
    Queue processQueue;
    Queue oldProcessQueue;

    @Override
    void initializeQueue(List<Process> processes) {
        processQueue = new Queue(processes);
        oldProcessQueue = new Queue(processes.size());
    }

    @Override
    void addProcess(Process process) {
        oldProcessQueue.enqueue(process);
    }

    @Override
    Process removeProcess() {
        return processQueue.dequeue();
    }

    @Override
    boolean hasProcess() {
        if (!processQueue.isEmpty()) {
            return true;
        }
        if (!oldProcessQueue.isEmpty()) {
            processQueue.enqueue(oldProcessQueue.dequeue());
            return true;
        }
        return false;
    }
}
