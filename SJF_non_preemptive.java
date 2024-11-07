package Scheduling;

import java.util.Scanner;

public class SJF_non_preemptive {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes:");
        int n = sc.nextInt();

        int[] pid = new int[n];
        int[] at = new int[n];
        int[] bt = new int[n];
        int[] ct = new int[n];
        int[] tat = new int[n];
        int[] wt = new int[n];
        boolean[] fg = new boolean[n];

        System.out.println("Enter the process IDs, arrival times, and burst times of the processes:");
        for (int i = 0; i < n; i++) {
            pid[i] = sc.nextInt();
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
        }

        int time = 0;
        int total_processes_complete = 0;

        while (total_processes_complete < n) {
            int min_index = -1;
            int min_bus_time = 99;

            for (int i = 0; i < n; i++) {
                if (at[i] <= time && !fg[i] && bt[i] < min_bus_time) {
                    min_index = i;
                    min_bus_time = bt[i];
                }
            }

            if (min_index == -1) {
                time++;
            } else {
                time += bt[min_index];
                ct[min_index] = time;
                tat[min_index] = ct[min_index] - at[min_index];
                wt[min_index] = tat[min_index] - bt[min_index];

                fg[min_index] = true;
                total_processes_complete++;
            }
        }

        // Output results
        System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");
        double totalTat = 0;
        double totalWt = 0;
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
            totalTat += tat[i];
            totalWt += wt[i];
        }

        System.out.println();
        System.out.println("Average TAT: " + (totalTat / n));
        System.out.println("Average WT: " + (totalWt / n));
    }
}

// No. of lines of logic : 25(26-51)
//        5
//        1 4 5
//        2 4 10
//        3 0 2
//        4 3 1
//        5 8 4
//        PID	AT	BT	CT	TAT	WT
//        1	4	5	9	5	0
//        2	4	10	23	19	9
//        3	0	2	2	2	0
//        4	3	1	4	1	0
//        5	8	4	13	5	1
//
//Average TAT: 6.4
//Average WT: 2.0