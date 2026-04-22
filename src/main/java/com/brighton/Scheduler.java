package com.brighton;
import java.util.List;

public interface Scheduler {
    final int QUANTUM_LIMIT = 4000;
    default Process handleProcess(Process p) {
        int val = Math.min(p.getBurstTime(),QUANTUM_LIMIT);
        p.setBurstTime(p.getBurstTime() - val);
        return p;
    }
    void schedule(List<Process> processes, List<Process> completedProcesses);
}