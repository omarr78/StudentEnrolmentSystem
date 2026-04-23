package com.brighton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CSVReader fileReader = new CSVReader();
        ArrayList<Process> processes = fileReader.read("input.csv");

        List<Process> completedProcess = new ArrayList<>();
        Priority scheduler = new Priority();
        scheduler.schedule(processes, completedProcess);

        for (Process p : completedProcess) {
            System.out.println(p.toString());
        }
    }
}