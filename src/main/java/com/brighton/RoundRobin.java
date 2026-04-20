package com.brighton;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RoundRobin {
    final static int QUANTUM_LIMIT = 4000;

    public static void run(List<Process> processes, List<Process> completedProcesses) {
        Queue<Process> q = new LinkedList<>(processes);
        while (!q.isEmpty()) {
            Process process = q.poll();
            if (process.getBurstTime() > QUANTUM_LIMIT) {
                process.setBurstTime(process.getBurstTime() - QUANTUM_LIMIT);
                q.add(process);
            } else {
                completedProcesses.add(process);
            }
        }
    }
}