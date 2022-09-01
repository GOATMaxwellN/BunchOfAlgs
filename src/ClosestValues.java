/*
 * This Class takes in a sequence of space separated integers and prints
 * out the two integers that make the smallest non-negative difference.
 */

import java.util.*;
import java.io.*;

public class ClosestValues {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int[] values = null;
        try {
            if (args.length != 0) {
                values = Arrays.stream(args)
                               .mapToInt(s -> Integer.parseInt(s))
                               .toArray();
            } else {
                values = Arrays.stream(sc.nextLine().split(" "))
                               .mapToInt(s -> Integer.parseInt(s))
                               .toArray();
            }
        } catch (NumberFormatException e) {
            System.out.println("Input must be space separated integers. Fix and try again.");
            System.exit(1);
        }
        
      
      int[] closestValues = ClosestValues.closestValues(values);
      System.out.format("%d %d", closestValues[0], closestValues[1]);
    }
    
    // Find the two successive integers with the lowest difference
    public static int[] closestValues(int[] arr) {
        // Sort the array in ascending order and compute the differences
        Arrays.parallelSort(arr);
        int[][] diffs = new int[arr.length - 1][2];
        for (int i = 1; i < arr.length; i++) {
            diffs[i-1] = new int[]{arr[i] - arr[i-1], i};
        }
        
        int smallest = diffs[0][0];
        int index = diffs[0][1];
        for (int j = 1; j < diffs.length; j++) {
            if (diffs[j][0] < smallest) {
                smallest = diffs[j][0];
                index = diffs[j][1];
            }
        }
        
        return new int[]{arr[index-1], arr[index]};
    }
}