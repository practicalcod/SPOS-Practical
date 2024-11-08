import java.util.Scanner;

public class WorstFit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of jobs: ");
        int jobsize = sc.nextInt();

        System.out.print("Enter the number of memory blocks: ");
        int blocksize = sc.nextInt();

        int[] job = new int[jobsize];
        int[] block = new int[blocksize];
        int[] flag = new int[blocksize];
        int[] allocation = new int[jobsize];

        int totalMemoryUsed = 0;

        System.out.println("Enter the size of each job:");
        for (int i = 0; i < jobsize; i++) {
            System.out.print("Job " + (i + 1) + ": ");
            job[i] = sc.nextInt();
            allocation[i] = -1;
        }

        System.out.println("Enter the size of each memory block:");
        for (int i = 0; i < blocksize; i++) {
            System.out.print("Block " + (i + 1) + ": ");
            block[i] = sc.nextInt();
            flag[i] = 0;
        }

        for (int i = 0; i < jobsize; i++) {
            int worstIdx = -1;
            for (int j = 0; j < blocksize; j++) {
                if (block[j] >= job[i] && flag[j] == 0) {
                    if (worstIdx == -1 || block[j] > block[worstIdx]) {
                        worstIdx = j;
                    }
                }
            }
            if (worstIdx != -1) {
                allocation[i] = worstIdx;
                flag[worstIdx] = 1;
                totalMemoryUsed += job[i];
                System.out.println("Job " + (i + 1) + " is allocated to Block " + (worstIdx + 1));
            } else {
                System.out.println("Job " + (i + 1) + " could not be allocated.");
            }
        }

        int totalBlockSize = 0;
        for (int i = 0; i < blocksize; i++) {
            totalBlockSize += block[i];
        }
        double averageMemoryUtilization = (double) totalMemoryUsed / totalBlockSize * 100;

        System.out.printf("\nTotal Memory Used: %d\n", totalMemoryUsed);
        System.out.printf("Average Memory Utilization: %.2f%%\n", averageMemoryUtilization);

        System.out.println("\nJob Allocation Results:");
        for (int i = 0; i < jobsize; i++) {
            if (allocation[i] != -1) {
                System.out.println("Job " + (i + 1) + " -> Block " + (allocation[i] + 1));
            } else {
                System.out.println("Job " + (i + 1) + " -> Not Allocated");
            }
        }

        sc.close();
    }
}


// Enter the number of jobs: 4
// Enter the number of memory blocks: 5
// Enter the size of each job:
// Job 1: 212
// Job 2: 147
// Job 3: 112
// Job 4: 426
// Enter the size of each memory block:
// Block 1: 100
// Block 2: 500
// Block 3: 300
// Block 4: 600
// Block 5: 200
// Job 1 is allocated to Block 4
// Job 2 is allocated to Block 2
// Job 3 is allocated to Block 3
// Job 4 could not be allocated.

// Total Memory Used: 471
// Average Memory Utilization: 27.71%

// Job Allocation Results:
// Job 1 -> Block 4
// Job 2 -> Block 2
// Job 3 -> Block 3
// Job 4 -> Not Allocated