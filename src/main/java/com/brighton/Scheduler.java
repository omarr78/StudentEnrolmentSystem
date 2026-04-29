package com.brighton;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

abstract class Scheduler {
    static final int QUANTUM_LIMIT = 4000;

    Process handleProcess(Process p) {
        int val = Math.min(p.getBurstTime(), QUANTUM_LIMIT);
        p.setBurstTime(p.getBurstTime() - val);
        return p;
    }

    public String formatProcessOutput(Process p) {
        return p.getProcessId() +
                ", COMPLETE, " +
                Process.processesBurstTime.get(p.getProcessId()) + "ms" +
                ", " + Process.processesTakenTime.get(p.getProcessId()) * QUANTUM_LIMIT + "ms";
    }

    abstract boolean hasProcess();

    abstract void initializeQueue(List<Process> processes);

    abstract void addProcess(Process process);

    abstract Process removeProcess();

    void schedule(List<Process> processes, List<Process> completedProcesses) {
        initializeQueue(processes);
        while (hasProcess()) {
            Process process = handleProcess(removeProcess());
            Integer time = Process.processesTakenTime.get(process.getProcessId());
            if (time == null) {
                time = 0;
            }
            Process.processesTakenTime.put(process.getProcessId(), time + 1);
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

            if (process.getBurstTime() == 0 && process.getState() == Thread.State.TERMINATED) {
                process.interrupt();
                completedProcesses.add(process);
            } else {
                process.interrupt();
                addProcess(process);
            }
        }
    }
}