package com.brighton;

import java.util.HashMap;

public class Process extends Thread implements Comparable<Process> {
    static final HashMap<String, Integer> processesBurstTime = new HashMap<>();
    static final HashMap<String, Integer> processesTakenTime = new HashMap<>();

    private String processId;
    private int burstTime;
    private final int priority;

    public Process(String processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.priority = priority;
        processesBurstTime.put(processId, burstTime);
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void run() {
        while (!isInterrupted()) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    @Override
    public int compareTo(Process o) {
        return this.priority <= o.priority ? -1 : 1;
    }

    @Override
    public String toString() {
        return processId;
    }
}
