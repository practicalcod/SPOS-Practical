import java.util.*; 

public class BestFitMemoryAllocation { 
    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in); 
        System.out.print("Enter the number of jobs: "); 
        int jobsize = scanner.nextInt(); 
        int[] job = new int[jobsize];  
        
        System.out.println("Enter the sizes of the jobs:"); 
        for (int i = 0; i < jobsize; i++) { 
            System.out.print("Job " + (i + 1) + ": "); 
            job[i] = scanner.nextInt(); 
        } 
        
        System.out.print("Enter the number of memory blocks: "); 
        int blocksize = scanner.nextInt(); 
        int[] block = new int[blocksize]; 
        int[] flag = new int[blocksize]; 
        int[] allocation = new int[jobsize]; 
        
        System.out.println("Enter the sizes of the memory blocks:"); 
        for (int i = 0; i < blocksize; i++) { 
            System.out.print("Block " + (i + 1) + ": "); 
            block[i] = scanner.nextInt(); 
            flag[i] = 0; 
        } 
        
        // Initialize allocation array to -1 (indicating no allocation initially)
        Arrays.fill(allocation, -1);

        int totalMemoryUsed = 0; 
        int allocatedJobs = 0; 

        // Best Fit Allocation
        for (int i = 0; i < jobsize; i++) { 
            int bestIdx = -1; 
            for (int j = 0; j < blocksize; j++) { 
                if (job[i] <= block[j] && flag[j] == 0) { 
                    if (bestIdx == -1 || block[j] < block[bestIdx]) { 
                        bestIdx = j; 
                    } 
                } 
            } 
            if (bestIdx != -1) { 
                allocation[i] = bestIdx; 
                flag[bestIdx] = 1; 
                totalMemoryUsed += job[i]; 
                allocatedJobs++; 
                System.out.println("Job " + (i + 1) + " of size " + job[i] + " is allocated to block " + (bestIdx + 1)); 
            } else { 
                allocation[i] = -1; 
                System.out.println("Job " + (i + 1) + " of size " + job[i] + " could not be allocated."); 
            } 
        } 

        int totalMemory = 0; 
        for (int i = 0; i < blocksize; i++) { 
            totalMemory += block[i]; 
        } 

        double averageMemoryUtilization = ((double) totalMemoryUsed / totalMemory) * 100; 

        System.out.println("\nMemory Utilization:"); 
        System.out.printf("Total memory utilized: %d/%d\n", totalMemoryUsed, totalMemory); 
        System.out.printf("Average memory utilization: %.2f%%\n", averageMemoryUtilization); 
    } 
}

// Enter the number of jobs: 4
// Enter the sizes of the jobs:
// Job 1: 212
// Job 2: 147
// Job 3: 112
// Job 4: 426
// Enter the number of memory blocks: 5
// Enter the sizes of the memory blocks:
// Block 1: 100  
// Block 2: 500
// Block 3: 300
// Block 4: 600
// Block 5: 200
// Job 1 of size 212 is allocated to block 3
// Job 2 of size 147 is allocated to block 5
// Job 3 of size 112 is allocated to block 2
// Job 4 of size 426 is allocated to block 4

// Memory Utilization:
// Total memory utilized: 897/1700
// Average memory utilization: 52.76%