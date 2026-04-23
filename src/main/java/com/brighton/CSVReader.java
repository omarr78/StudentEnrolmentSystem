package com.brighton;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
    public ArrayList<Process> read(String filePath) {
        ArrayList<Process> processes = new ArrayList<>();
        filePath = "src/main/java/com/brighton/" + filePath;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");

                if (isHeader) {
                    isHeader = false; // skip header row
                    continue;
                }

                // Access each column by index
                String process_id = columns[0].trim();
                int burst_time  = Integer.parseInt(columns[1].trim());
                int priority = Integer.parseInt(columns[2].trim());
                Process p = new Process(process_id, burst_time, priority);
                processes.add(p);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return processes;
    }
}
