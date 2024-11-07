package Scheduling;

import java.util.Scanner;

public class priority_preemptive {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes:");
        int n = sc.nextInt();
        int[] priority = new int[n];
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

        System.out.println("Enter the Priority, process IDs, arrival times, and burst times of the processes:");
        for (int i = 0; i < n; i++) {
            priority[i] = sc.nextInt();
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
            int highest_priority_value = 99;

            for (int i = 0; i < n; i++) {
                if (at[i] <= time && !fg[i] && priority[i] < highest_priority_value) {
                    min_index = i;
                    highest_priority_value = priority[i];
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

        System.out.println("Pri\tPID\tAT\tBT\tCT\tTAT\tWT");
        double totalTat = 0;
        double totalWt = 0;
        for (int i = 0; i < n; i++) {
            System.out.println(priority[i] + "\t" + pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
            totalTat += tat[i];
            totalWt += wt[i];
        }

        System.out.println();
        System.out.println("Average TAT: " + (totalTat / n));
        System.out.println("Average WT: " + (totalWt / n));
    }
}

// No. of lines of logic : 33 (32-65)

//        6
//        4 1 4 5
//        2 2 4 10
//        6 3 0 2
//        5 4 3 1
//        1 5 8 4
//        3 6 6 2
//        Pri	PID	AT	BT	CT	TAT	WT
//        4	1	4	5	25	21	16
//        2	2	4	10	18	14	4
//        6	3	0	2	2	2	0
//        5	4	3	1	4	1	0
//        1	5	8	4	12	4	0
//        3	6	6	2	20	14	12
//
//Average TAT: 9.333333333333334
//Average WT: 5.333333333333333