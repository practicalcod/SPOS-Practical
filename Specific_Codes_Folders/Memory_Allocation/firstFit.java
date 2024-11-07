package Memory_Allocation;

import java.util.*;

public class firstFit {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of jobs : ");
        int noOfJobs = sc.nextInt();

        System.out.println("enter the number of blocks");
        int noOfBlocks = sc.nextInt();

        int jobs[] = new int[noOfJobs];
        int blocks[] = new int[noOfBlocks];

        System.out.println("enter the values of each job");
        for (int i = 0; i < noOfJobs; i++) {
            jobs[i] = sc.nextInt();
        }

        System.out.println("enter the size of each block");
        for (int i = 0; i < noOfBlocks; i++) {
            blocks[i] = sc.nextInt();
        }

        boolean flagArr[] = new boolean[noOfBlocks];
        for (int i = 0; i < noOfBlocks; i++) {
            flagArr[i] = false;
        }

        double totalSumOfJobsDone = 0;
        int index = 0;

        while (index < noOfJobs) {
            for (int i = 0; i < noOfBlocks; i++) {
                if (jobs[index] <= blocks[i] && flagArr[i] == false) {
                    flagArr[i] = true;
                    totalSumOfJobsDone += jobs[index];
                    break;
                }
            }
            index++;
        }

        double totalSumOfBlockSize = 0;
        for (int i = 0; i < noOfBlocks; i++) {
            totalSumOfBlockSize += blocks[i];
        }

        System.out.println("Proportion of jobs allocated : " + totalSumOfJobsDone / totalSumOfBlockSize);
    }

}

// enter the number of jobs
// 4
// enter the number of blocks
// 5
// enter the values of each job
// 212 417 112 426
// enter the size of each block
// 100 500 200 300 600
// 0.43588235294117644
// 4 5 212 417 112 426 100 500 200 300 600
