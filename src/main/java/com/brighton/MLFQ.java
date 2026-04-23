package com.brighton;
import com.brighton.Scheduler;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

public class MLFQ extends Scheduler {
    @Override
    void initializeQueue(List<Process> processes) {

    }

    @Override
    public void schedule(List<Process> processes, List<Process> completedProcesses) {
        Queue<Process> young = new LinkedList<>(processes);
        Queue<Process> old = new LinkedList<>();
        while (!young.isEmpty()) {
            Process process = handleProcess(young.poll());
            if (process.getState() == Thread.State.NEW) {
                process.start();
                try {
                    Thread.sleep(QUANTUM_LIMIT);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                young.add(process);
            } else if (process.getState() == Thread.State.TERMINATED) {
                completedProcesses.add(process);
            } else {
                process.interrupt();
                try {
                    Thread.sleep(QUANTUM_LIMIT);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                old.add(process);
            }
            if(young.isEmpty() && !old.isEmpty()) {
                young.add(old.poll());
            }
        }
    }
}
