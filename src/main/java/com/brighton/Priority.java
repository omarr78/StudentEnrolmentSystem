package com.brighton;
import com.brighton.Scheduler;
import java.util.List;
import java.util.PriorityQueue;

public class Priority implements Scheduler{
    @Override
    public void schedule(List<Process> processes, List<Process> completedProcesses) {
        PriorityQueue<Process> pq = new PriorityQueue<>(processes);
        while(!pq.isEmpty()) {
            Process process = handleProcess(pq.poll());
            if (process.getState() == Thread.State.NEW) {
                process.start();
                try {
                    Thread.sleep(QUANTUM_LIMIT);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                pq.add(process);
            } else if (process.getState() == Thread.State.TERMINATED) {
                completedProcesses.add(process);
            } else {
                process.interrupt();
                try {
                    Thread.sleep(QUANTUM_LIMIT);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                pq.add(process);
            }
        }
    }
}
