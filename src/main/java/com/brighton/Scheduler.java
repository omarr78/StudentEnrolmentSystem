package com.brighton;

import java.util.ArrayList;
import java.util.List;

abstract class Scheduler {
    static final int QUANTUM_LIMIT = 20;

    Process handleProcess(Process p) {
        int val = Math.min(p.getCurrentBurstTime(), QUANTUM_LIMIT);
        p.setCurrentBurstTime(p.getCurrentBurstTime() - val);
        return p;
    }

    public String formatProcessOutput(Process p) {
        return p.getProcessId() +
                ", COMPLETE, " +
                p.getBurstTime() + "ms" +
                ", " + p.getTakenTime() + "ms";
    }

    abstract boolean hasProcess();

    abstract void initializeQueue(List<Process> processes);

    abstract void addProcess(Process process);

    abstract Process removeProcess();

    List<Process> schedule(List<Process> processes) {
        List<Process> completedProcesses = new ArrayList<Process>();
        initializeQueue(processes);
        while (hasProcess()) {
            Process process = handleProcess(removeProcess());
            process.addTakenTime(QUANTUM_LIMIT);
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

            if (process.getState() == Thread.State.TERMINATED) {
                process.interrupt();
                completedProcesses.add(process);
            } else {
                process.interrupt();
                addProcess(process);
            }
        }

        for (int i = 1; i < completedProcesses.size(); i++) {
            completedProcesses.get(i).addTakenTime(completedProcesses.get(i - 1).getTakenTime());
        }
        return completedProcesses;
    }
}