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
            if(process.getBurstTime() == 0)
                completedProcesses.add(process);
            else
                pq.add(process);
        }
    }
}
