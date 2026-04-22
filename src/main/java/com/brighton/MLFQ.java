package com.brighton;
import com.brighton.Scheduler;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

public class MLFQ implements Scheduler {
    @Override
    public void schedule(List<Process> processes, List<Process> completedProcesses) {
        Queue<Process> young = new LinkedList<>(processes);
        Queue<Process> old = new LinkedList<>();
        while (!young.isEmpty()) {
            Process process = handleProcess(young.poll());
            if (process.getBurstTime() == 0) {
                completedProcesses.add(process);
            } else {
                old.add(process);
            }
            if(young.isEmpty() && !old.isEmpty()) {
                young.add(old.poll());
            }
        }
    }
}
