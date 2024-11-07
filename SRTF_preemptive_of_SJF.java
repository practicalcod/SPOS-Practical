package Scheduling;

import java.util.Scanner;

public class SRTF_preemptive_of_SJF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes:");
        int n = sc.nextInt();
        int[] pid = new int[n];
        int[] at = new int[n];
        int[] bt = new int[n];
        int[] ct = new int[n];
        int[] tat = new int[n];
        int[] wt = new int[n];
        int[] rt = new int[n];
        boolean[] fg = new boolean[n];

        for (int i = 0; i < n; i++) {
            fg[i] = false;
        }

        System.out.println("Enter the process IDs, arrival times, and burst times of the processes:");
        for (int i = 0; i < n; i++) {
            pid[i] = sc.nextInt();
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            rt[i] = bt[i];
        }

        int time = 0;
        int total_processes_completed = 0;

        while (total_processes_completed < n) {
            int min_index = -1;
            int min_time_remaining = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (at[i] <= time && !fg[i] && rt[i] < min_time_remaining) {
                    min_index = i;
                    min_time_remaining = rt[i];
                }
            }

            if (min_index == -1) {
                time++;
                continue;
            } else {
                rt[min_index]--;
                time++;

                if (rt[min_index] == 0) {
                    total_processes_completed++;
                    ct[min_index] = time;
                    fg[min_index] = true;
                    tat[min_index] = ct[min_index] - at[min_index];
                    wt[min_index] = tat[min_index] - bt[min_index];
                }
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

// No. of lines of logic : 33 (30-63)
//4
//        1 0 5
//        2 1 3
//        3 2 4
//        4 4 1
//PID	AT	BT	CT	TAT	WT
//1	0	5	9	9	4
//        2	1	3	4	3	0
//        3	2	4	13	11	7
//        4	4	1	5	1	0
//
//Average TAT: 6.0
//Average WT: 2.75