package com.brighton;

import com.brighton.Scheduler;

import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

public class MLFQ extends Scheduler {
    Queue<Process> oldQueue = new LinkedList<>();

    @Override
    void initializeQueue(List<Process> processes) {
        queue = new LinkedList<>(processes);
    }

    @Override
    boolean hasProcess() {
        if (!queue.isEmpty()) {
            return true;
        }
        if (!oldQueue.isEmpty()) {
            queue.add(oldQueue.poll());
            return true;
        }
        return false;
    }
}
