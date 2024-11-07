package Deadlock_Banker_Algorithm;
import java.util.Scanner;
public class deadlockAvoidanceBankersAlogorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of resources: ");
        int resources = sc.nextInt();

        System.out.print("Enter the number of processes: ");
        int processes = sc.nextInt();

        int[][] maxRequirement = new int[processes][resources];
        int[][] currentlyAllocated = new int[processes][resources];
        int[][] need = new int[processes][resources];
        int[] available = new int[resources];
        boolean[] finished = new boolean[processes];
        int[] safeSequence = new int[processes];

        System.out.println("Enter the max requirement for each process:");
        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < resources; j++) {
                maxRequirement[i][j] = sc.nextInt();
            }
        }
        System.out.println("Enter the currently allocated resources for each process:");
        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < resources; j++) {
                currentlyAllocated[i][j] = sc.nextInt();
            }
        }
        // Calculate the need matrix
        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < resources; j++) {
                need[i][j] = maxRequirement[i][j] - currentlyAllocated[i][j];
            }
        }
        System.out.println("Enter the available instances for each resource:");
        for (int i = 0; i < resources; i++) {
            available[i] = sc.nextInt();
        }
        int finishedCount = 0;
        boolean progress = true;

        while (finishedCount < processes && progress) {
            progress = false;
            for (int i = 0; i < processes; i++) {
                if (!finished[i]) {
                    boolean canProceed = true;
                    for (int j = 0; j < resources; j++) {
                        if (need[i][j] > available[j]) {
                            canProceed = false;
                            break;
                        }
                    }
                    if (canProceed) {
                        for (int j = 0; j < resources; j++) {
                            available[j] += currentlyAllocated[i][j];
                        }
                        finished[i] = true;
                        safeSequence[finishedCount] = i;
                        finishedCount++;
                        progress = true;
                    }
                }
            }
            if (!progress) {
                System.out.println("No progress made in this iteration. System may be in an unsafe state or deadlock.");
            }
        }
        if (finishedCount == processes) {
            System.out.println("The system is in a safe state. Safe sequence is:");
            for (int i = 0; i < processes; i++) {
                System.out.print("P" + safeSequence[i] + " ");
            }
            System.out.println();
        } else {
            System.out.println("The system is not in a safe state. Deadlock may occur.");
        }
    }
}

//Enter the number of resources: 3
//Enter the number of processes: 5
//Enter the max requirement for each process:
//        7 5 3
//        3 2 2
//        9 0 2
//        2 2 2
//        4 3 3
//Enter the currently allocated resources for each process:
//        0 1 0
//        2 0 0
//        3 0 2
//        2 1 1
//        0 0 2
//Enter the available instances for each resource:
//        3 3 2
//The system is in a safe state. Safe sequence is:
//P1 P3 P4 P0 P2

//7 5 5
//        3 2 5
//        9 0 0
//        2 2 2
//        4 4 0

//0 1 0
//        2 0 0
//        3 5 0
//        2 5 0
//        1 2 0

//
//
//Enter the number of resources: 3
//Enter the number of processes: 5
//Enter the max requirement for each process:
//        8 4 2
//        4 2 1
//        8 3 2
//        3 1 2
//        5 3 1
//Enter the currently allocated resources for each process:
//        6 2 1
//        0 2 0
//        5 1 1
//        0 0 1
//Enter the available instances for each resource:
//        2 1 3
//The system is in a safe state. Safe sequence is:
//P3 P0 P1 P2 P4