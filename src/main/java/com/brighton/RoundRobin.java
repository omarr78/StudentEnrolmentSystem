package com.brighton;
import com.brighton.Scheduler;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;


public class RoundRobin extends Scheduler {
    @Override
    void initializeQueue(List<Process> processes) {
        queue = new LinkedList<>(processes);
    }
}
