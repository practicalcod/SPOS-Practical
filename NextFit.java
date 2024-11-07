package Memory_Allocation;

import java.util.*;

public class NextFit {
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
        int j = 0;

        for (int i = 0; i < noOfJobs; i++) {
            int start = j;
            while (true) {
                if (jobs[i] <= blocks[j] && !flagArr[j]) {
                    totalSumOfJobsDone += jobs[i];
                    flagArr[j] = true;
                    j = (j + 1) % noOfBlocks;
                    break;
                }
                j = (j + 1) % noOfBlocks;
                if (j == start) {
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

        sc.close();
    }
}
