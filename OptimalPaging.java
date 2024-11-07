package Paging_using_Simulation;
import java.util.*;

public class OptimalPaging {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of pages:");
        int pgs = sc.nextInt();

        System.out.println("Enter the pages values:");
        int[] pages = new int[pgs];
        for (int i = 0; i < pgs; i++) {
            pages[i] = sc.nextInt();
        }

        System.out.println("Enter the number of frames available:");
        int frames = sc.nextInt();

        int[][] table = new int[pgs][frames];
        int[] buffer = new int[frames];
        Arrays.fill(buffer, -1);
        int pg_fault = 0;
        int pg_hit = 0;

        for (int i = 0; i < pgs; i++) {
            boolean hit = false;

            for (int j = 0; j < frames; j++) {
                if (buffer[j] == pages[i]) {
                    pg_hit++;
                    hit = true;
                    break;
                }
            }

            if (hit) {
                if (i > 0) {
                    for (int j = 0; j < frames; j++) {
                        table[i][j] = table[i - 1][j];
                    }
                }
                continue;
            }

            pg_fault++;
            if (i > 0) {
                for (int j = 0; j < frames; j++) {
                    table[i][j] = table[i - 1][j];
                }
            }

            int replaceIndex = findOptimalPage(buffer, pages, i + 1, frames, pgs);

            buffer[replaceIndex] = pages[i];

            for (int j = 0; j < frames; j++) {
                table[i][j] = buffer[j];
            }
        }

        System.out.println("\nPage Replacement Table:");
        for (int i = 0; i < pgs; i++) {
            for (int j = 0; j < frames; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nPage Hits = " + pg_hit);
        System.out.println("Page Faults = " + pg_fault);
    }

    public static int findOptimalPage(int[] buffer, int[] pages, int currentIndex, int frames, int totalPages) {
        int farthest = currentIndex;
        int replaceIndex = -1;

        for (int i = 0; i < frames; i++) {
            boolean found = false;
            for (int j = currentIndex; j < totalPages; j++) {
                if (buffer[i] == pages[j]) {
                    if (j > farthest) {
                        farthest = j;
                        replaceIndex = i;
                    }
                    found = true;
                    break;
                }
            }

            if (!found) {
                return i;
            }
        }
        return replaceIndex == -1 ? 0 : replaceIndex;
    }
}

//Enter the number of pages:
//        12
//Enter the pages values:
//        2 3 2 1 5 2 4 5 3 2 5 2
//Enter the number of frames available:
//
//        3
//
//Page Replacement Table:
//        2 -1 -1
//        2 3 -1
//        2 3 -1
//        2 3 1
//        2 3 5
//        2 3 5
//        4 3 5
//        4 3 5
//        4 3 5
//        2 3 5
//        2 3 5
//        2 3 5
//
//Page Hits = 6
//Page Faults = 6