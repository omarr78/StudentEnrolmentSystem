package com.brighton;

public class Process extends Thread implements Comparable<Process> {
    private String processId;
    private int currentBurstTime;
    private int burstTime;
    private int takenTime;
    private final int priority;

    public Process(String processId, int burstTime, int priority) {
        this.processId = processId;
        this.currentBurstTime = burstTime;
        this.burstTime = burstTime;
        this.takenTime = 0;
        this.priority = priority;
    }

    public String getProcessId() {
        return processId;
    }

    public int getCurrentBurstTime() {
        return currentBurstTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getTakenTime() {
        return takenTime;
    }

    public void setTakenTime(int takenTime) {
        this.takenTime = takenTime;
    }

    public void addTakenTime(int takenTime) {
        this.takenTime += takenTime;
    }

    public void setCurrentBurstTime(int currentBurstTime) {
        this.currentBurstTime = currentBurstTime;
    }

    public void run() {
        while (currentBurstTime > 0) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {

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
