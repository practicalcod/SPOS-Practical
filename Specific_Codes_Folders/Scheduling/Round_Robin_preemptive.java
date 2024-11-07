package Scheduling;

import java.util.*;

public class Round_Robin_preemptive {

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

        System.out.println("Enter the process IDs, arrival times, and burst times of the processes:");
        for (int i = 0; i < n; i++) {
            pid[i] = sc.nextInt();
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
            rt[i] = bt[i];
            fg[i] = false;
        }

        System.out.println("Enter the time quantum:");
        int tq = sc.nextInt();

        int total_process_count = 0;
        int st = 0;

        while (total_process_count < n) {
            for (int i = 0; i < n; i++) {
                if (at[i] <= st && !fg[i]) {
                    if (tq >= rt[i]) {
                        ct[i] = st + rt[i];
                        st = ct[i];
                        rt[i] = 0;
                        fg[i] = true;
                        total_process_count++;
                    } else {
                        rt[i] -= tq;
                        st += tq;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
        }

        // Print results
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

//Enter the number of processes:
//5
//Enter the process IDs, arrival times, and burst times of the processes:
//1 0 5
//2 0 10
//3 0 2
//4 0 1
//5 0 4
//Enter the time quantum:
//2
//PID	AT	BT	CT	TAT	WT
//1	0	5	16	16	11
//2	0	10	22	22	12
//3	0	2	6	6	4
//4	0	1	7	7	6
//5	0	4	15	15	11
//
//Average TAT: 13.2
//Average WT: 8.8

