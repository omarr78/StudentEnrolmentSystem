package com.brighton;
import com.brighton.Scheduler;
import java.util.List;
import java.util.PriorityQueue;

public class Priority extends Scheduler{
    @Override
    void initializeQueue(List<Process> processes) {
        queue = new PriorityQueue<>(processes);
    }
}
