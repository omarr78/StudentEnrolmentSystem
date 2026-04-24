package com.brighton;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

abstract class Scheduler {
    public final int QUANTUM_LIMIT = 4000;
    Queue<Process> queue;

    Process handleProcess(Process p) {
        int val = Math.min(p.getBurstTime(), QUANTUM_LIMIT);
        p.setBurstTime(p.getBurstTime() - val);
        return p;
    }

    boolean hasProcess() {
        return !queue.isEmpty();
    }

    abstract void initializeQueue(List<Process> processes);

    void schedule(List<Process> processes, List<Process> completedProcesses) {
        initializeQueue(processes);
        while (hasProcess()) {
            Process process = handleProcess(queue.poll());
            if (process.getState() == Thread.State.NEW) {
                process.start();
            } else {
                process.interrupt();
            }

            try {
                Thread.sleep(QUANTUM_LIMIT);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if (process.getBurstTime() == 0 || process.getState() == Thread.State.TERMINATED) {
                process.interrupt();
                completedProcesses.add(process);
            } else {
                process.interrupt();
                queue.add(process);
            }
        }
    }
}