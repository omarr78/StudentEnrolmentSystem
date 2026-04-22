package com.brighton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Process process1 = new Process("P1", 4000, 1);
        Process process2 = new Process("P2", 5000, 2);
        Process process3 = new Process("P3", 4000, 3);
        Process process4 = new Process("P4", 6000, 1);
        Process process5 = new Process("P5", 5000, 1);
        Process process6 = new Process("P6", 4000, 3);

        List<Process> processes = new ArrayList<>(Arrays.asList(
                process1, process2, process3, process4, process5, process6
        ));

        List<Process> completedProcess = new ArrayList<>();
        RoundRobin scheduler = new RoundRobin();
        scheduler.schedule(processes, completedProcess);

        for (Process p : completedProcess) {
            System.out.println(p.toString());
        }
    }
}