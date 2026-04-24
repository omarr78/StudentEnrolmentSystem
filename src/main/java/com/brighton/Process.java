package com.brighton;

public class Process extends Thread implements Comparable<Process> {
    private String processId;
    private int burstTime;
    private final int priority;

    public Process(String processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.priority = priority;
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
        return this.priority >= o.priority ? -1 : 1;
    }

    @Override
    public String toString() {
        return "Process{" +
                "processId='" + processId + '\'' +
                ", burstTime=" + burstTime +
                ", priority=" + priority +
                '}';
    }
}
