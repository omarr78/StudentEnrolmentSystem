package com.brighton;
import com.brighton.Scheduler;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;


public class RoundRobin implements Scheduler {
    @Override
    public void schedule(List<Process> processes, List<Process> completedProcesses) {
        Queue<Process> q = new LinkedList<>(processes);
        while (!q.isEmpty()) {
            Process process = handleProcess(q.poll());
            if (process.getState() == Thread.State.NEW) {
                process.start();
                try {
                    Thread.sleep(QUANTUM_LIMIT);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                q.add(process);
            } else if (process.getState() == Thread.State.TERMINATED) {
                completedProcesses.add(process);
            } else {
                process.interrupt();
                try {
                    Thread.sleep(QUANTUM_LIMIT);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                q.add(process);
            }
        }
    }
}
