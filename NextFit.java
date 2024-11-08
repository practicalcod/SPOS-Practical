import java.util.Scanner;

public class NextFit {
    public static void main(String[] args) {
        int jobSize, blockSize;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of jobs: ");
        jobSize = sc.nextInt();

        System.out.print("Enter number of memory blocks: ");
        blockSize = sc.nextInt();

        int[] job = new int[jobSize];
        int[] block = new int[blockSize];
        int[] flag = new int[blockSize];
        int[] allocation = new int[jobSize];

        int memoryUsed = 0;
        int totalMemory = 0;
        double avgMemoryUtilization;

        System.out.println("Enter job sizes: ");
        for (int i = 0; i < jobSize; i++) {
            job[i] = sc.nextInt();
            allocation[i] = -1;
        }

        System.out.println("Enter block sizes: ");
        for (int i = 0; i < blockSize; i++) {
            block[i] = sc.nextInt();
            totalMemory += block[i];
            flag[i] = 0;
        }

        int j = 0;
        for (int i = 0; i < jobSize; i++) {
            boolean allocated = false;
            for (int count = 0; count < blockSize; count++) {
                if (job[i] <= block[j] && flag[j] == 0) {
                    System.out.println("Job " + (i + 1) + " of size " + job[i] + " allocated to block " + (j + 1));
                    flag[j] = 1;
                    memoryUsed += job[i];
                    allocation[i] = j;
                    allocated = true;
                    break;
                }
                j = (j + 1) % blockSize;
            }
            if (!allocated) {
                System.out.println("Job " + (i + 1) + " of size " + job[i] + " could not be allocated.");
            }
        }

        avgMemoryUtilization = ((double) memoryUsed / totalMemory) * 100;

        System.out.println("\nJob Allocations:");
        for (int i = 0; i < jobSize; i++) {
            if (allocation[i] != -1) {
                System.out.println("Job " + (i + 1) + " allocated to block " + (allocation[i] + 1));
            } else {
                System.out.println("Job " + (i + 1) + " not allocated.");
            }
        }

        System.out.println("\nTotal Memory Used: " + memoryUsed);
        System.out.println("Total Memory Available: " + totalMemory);
        System.out.println("Average Memory Utilization: " + avgMemoryUtilization + "%");

        sc.close();
    }
}


// Enter number of jobs: 4
// Enter number of memory blocks: 5
// Enter job sizes: 
// 212
// 147
// 112
// 426
// Enter block sizes: 
// 100 
// 500
// 300
// 600
// 200
// Job 1 of size 212 allocated to block 2
// Job 2 of size 147 allocated to block 3
// Job 3 of size 112 allocated to block 4
// Job 4 of size 426 could not be allocated.

// Job Allocations:
// Job 1 allocated to block 2
// Job 2 allocated to block 3
// Job 3 allocated to block 4
// Job 4 not allocated.

// Total Memory Used: 471
// Total Memory Available: 1700
// Average Memory Utilization: 27.705882352941174%