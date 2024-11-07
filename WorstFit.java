package Memory_Allocation;

import java.util.*;

public class WorstFit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of jobs: ");
        int noOfJobs = sc.nextInt();

        System.out.print("Enter the number of blocks: ");
        int noOfBlocks = sc.nextInt();

        int[] jobs = new int[noOfJobs];
        int[] blocks = new int[noOfBlocks];
        boolean[] flagArr = new boolean[noOfBlocks];

        System.out.print("Enter the values of each job: ");
        for (int i = 0; i < noOfJobs; i++) {
            jobs[i] = sc.nextInt();
        }

        System.out.print("Enter the size of each block: ");
        for (int i = 0; i < noOfBlocks; i++) {
            blocks[i] = sc.nextInt();
        }

        double totalSumOfJobsDone = 0;

        for (int i = 0; i < noOfJobs; i++) {
            int worstBlockIndex = -1;

            for (int j = 0; j < noOfBlocks; j++) {
                if (jobs[i] <= blocks[j] && !flagArr[j]) {
                    if (worstBlockIndex == -1 || blocks[j] > blocks[worstBlockIndex]) {
                        worstBlockIndex = j;
                    }
                }
            }

            if (worstBlockIndex != -1) {
                totalSumOfJobsDone += jobs[i];
                flagArr[worstBlockIndex] = true;
            }
        }

        double totalSumOfBlockSize = 0;
        for (int i = 0; i < noOfBlocks; i++) {
            totalSumOfBlockSize += blocks[i];
        }

        System.out.println("Proportion of jobs allocated: "
                + (totalSumOfJobsDone / totalSumOfBlockSize));

        sc.close();
    }
}


//4 5 212 417 112 426 100 500 200 300 600
//400 200 600 450 500 100 250 300
//130 270 80 185 440 380 510