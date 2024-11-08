import java.util.Scanner; 
public class FirstFitMemoryAllocation { 
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
        System.out.print("Enter number of jobs: "); 
        int jobsize = sc.nextInt(); 
        System.out.print("Enter number of memory blocks: "); 
        int blocksize = sc.nextInt(); 
        int[] job = new int[jobsize]; 
        int[] block = new int[blocksize]; 
        int[] flag = new int[blocksize]; 
        int[] allocation = new int[jobsize]; 
        double memoryUtilization = 0; 
        double totalMemory = 0;
        
        System.out.println("Enter job sizes:"); 
        for (int i = 0; i < jobsize; i++) { 
            System.out.print("Job " + (i + 1) + " size: "); 
            job[i] = sc.nextInt(); 
        } 
        
        System.out.println("Enter block sizes:"); 
        for (int i = 0; i < blocksize; i++) { 
            System.out.print("Block " + (i + 1) + " size: "); 
            block[i] = sc.nextInt(); 
            flag[i] = 0; 
            totalMemory += block[i]; 
        } 
        
        for (int i = 0; i < jobsize; i++) { 
            allocation[i] = -1; 
        }

        for (int i = 0; i < jobsize; i++) { 
            for (int j = 0; j < blocksize; j++) { 
                if (job[i] <= block[j] && flag[j] == 0) { 
                    System.out.println("Job " + (i + 1) + " of size " + job[i] + " fitted in Block " + (j + 1) + " of size " + block[j]); 
                    flag[j] = 1; 
                    memoryUtilization += job[i]; 
                    allocation[i] = j; 
                    break; 
                } 
            } 
        }

        double averageMemoryUtilization = (memoryUtilization / totalMemory) * 100; 
        System.out.println("\nAverage Memory Utilization: " + averageMemoryUtilization + "%"); 

        System.out.println("\nJob Allocation Results:"); 
        for (int i = 0; i < jobsize; i++) { 
            if (allocation[i] != -1) { 
                System.out.println("Job " + (i + 1) + " allocated to Block " + (allocation[i] + 1)); 
            } else { 
                System.out.println("Job " + (i + 1) + " not allocated"); 
            } 
        }
    } 
}


// Enter number of jobs: 4
// Enter number of memory blocks: 5
// Enter job sizes:
// Job 1 size: 212
// Job 2 size: 147
// Job 3 size: 112
// Job 4 size: 426
// Enter block sizes:
// Block 1 size: 100
// Block 2 size: 500
// Block 3 size: 300
// Block 4 size: 600
// Block 5 size: 200
// Job 1 of size 212 fitted in Block 2 of size 500
// Job 2 of size 147 fitted in Block 3 of size 300
// Job 3 of size 112 fitted in Block 4 of size 600

// Average Memory Utilization: 27.705882352941174%

// Job Allocation Results:
// Job 1 allocated to Block 2
// Job 2 allocated to Block 3
// Job 3 allocated to Block 4
// Job 4 not allocated