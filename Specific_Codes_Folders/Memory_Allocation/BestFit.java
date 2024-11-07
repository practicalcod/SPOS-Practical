package Memory_Allocation;

import java.util.*;

public class BestFit {
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

        for (int i = 0; i < noOfBlocks - 1; i++) {
            for (int j = 0; j < noOfBlocks - i - 1; j++) {
                if (blocks[j] > blocks[j + 1]) {
                    int temp = blocks[j];
                    blocks[j] = blocks[j + 1];
                    blocks[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < noOfJobs; i++) {
            for (int j = 0; j < noOfBlocks; j++) {
                if (jobs[i] <= blocks[j] && !flagArr[j]) {
                    totalSumOfJobsDone += jobs[i];
                    flagArr[j] = true;
                    break;
                }
            }
        }

        double totalSumOfBlockSize = 0;
        for (int i = 0; i < noOfBlocks; i++) {
            totalSumOfBlockSize += blocks[i];
        }

        System.out.println("Proportion of jobs allocated: "
                + (totalSumOfJobsDone / totalSumOfBlockSize));

    }
}


// 130 470 80 85 140 380 510
//410 200 600 450 500 160 230 320