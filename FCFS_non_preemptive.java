package Scheduling;
import java.util.*;

public class FCFS_non_preemptive {
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

        System.out.println("Enter the process IDs, arrival times, and burst times of the processes:");
        for (int i = 0; i < n; i++) {
            pid[i] = sc.nextInt();
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (at[j] > at[j + 1]) {
                    int temp1 = at[j];
                    at[j] = at[j + 1];
                    at[j + 1] = temp1;

                    int temp2 = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp2;

                    int temp3 = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = temp3;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                ct[i] = at[i] + bt[i];
            } else {
                if (at[i] > ct[i - 1]) {
                    ct[i] = at[i] + bt[i];
                } else {
                    ct[i] = bt[i] + ct[i - 1];
                }
            }
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
        }

        System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");
        double totalTat = 0;
        double totalWt = 0;
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
            totalTat += tat[i];
            totalWt += wt[i];
        }

        System.out.println();
        System.out.println("Average TAT: "+  totalTat/n );
        System.out.println("Average WT: "+  totalWt/n );
    }
}
// NO. of lines of logic : 30 (23-53)
//4
//        1 0 2
//        2 1 2
//        3 5 3
//        4 6 4