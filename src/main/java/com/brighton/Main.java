package com.brighton;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CSVReader fileReader = new CSVReader();
        ArrayList<Process> processes1 = fileReader.read("input.csv");
        ArrayList<Process> processes2 = fileReader.read("input.csv");
        ArrayList<Process> processes3 = fileReader.read("input.csv");

        Priority priorityScheduler = new Priority();
        RoundRobin roundRobinScheduler = new RoundRobin();
        MLFQ mlfqScheduler = new MLFQ();
        List<Process> prioritySchedulerCompletedProcess = priorityScheduler.schedule(processes1);
        List<Process> roundRobinSchedulerCompletedProcess = roundRobinScheduler.schedule(processes2);
        List<Process> mlfqSchedulerCompletedProcess = mlfqScheduler.schedule(processes3);

        System.out.println("Priority Scheduler:");
        for (Process p : prioritySchedulerCompletedProcess) {
            System.out.println(Scheduler.formatProcessOutput(p));
        }
        System.out.println("============================================");
        System.out.println("Round Robin Scheduler:");
        for (Process p : roundRobinSchedulerCompletedProcess) {
            System.out.println(Scheduler.formatProcessOutput(p));
        }
        System.out.println("============================================");
        System.out.println("MLFQ Scheduler:");
        for (Process p : mlfqSchedulerCompletedProcess) {
            System.out.println(Scheduler.formatProcessOutput(p));
        }
    }
}