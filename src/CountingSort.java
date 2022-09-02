/*
 * This class implements the 'Counting Sort algorithm'
 * It takes in a line of space separated integers and expects it in this format.
 * MIN_VALUE MAX_VALUE N0 N1 N2 N3 N4 ...
 * 
 * MIN_VALUE is the smallest value in the list of integers to be sorted.
 * MAX_VALUE is the largest value in the list of integers to be sorted.
 * N0 N1 ... is the sequence of integers within the above specified bounds
 *     that will be sorted.
 */

import java.util.*;
import java.io.*;

public class CountingSort {
    public static void main(String args[]) {
        int minValue = 0;
        int maxValue = 0;
        int[] toSort = null;
        try {
            if (args.length != 0) {
                minValue = Integer.parseInt(args[0]);
                maxValue = Integer.parseInt(args[1]);
                toSort = Arrays.stream(Arrays.copyOfRange(args, 2, args.length))
                               .mapToInt(s -> Integer.parseInt(s))
                               .toArray();
            } else {
                Scanner sc = new Scanner(System.in);
                int[] inputs = Arrays.stream(sc.nextLine().split(" "))
                                     .mapToInt(s -> Integer.parseInt(s))
                                     .toArray();
                minValue = inputs[0];
                maxValue = inputs[1];
                toSort = Arrays.copyOfRange(inputs, 2, inputs.length);
            }
        } catch (NumberFormatException e) {
            System.out.println("Only integers may be in the input. Fix and try again.");
            System.exit(1);
        }

        List<Integer> sortedList = null;
        try {
            sortedList = CountingSort.countingSort(toSort, minValue, maxValue);    
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Check your MIN and MAX values and try again.");
            System.exit(1);
        }

        System.out.println(sortedList);
    }
    
    public static List<Integer> countingSort(int[] arr, int minValue, int maxValue) throws ArrayIndexOutOfBoundsException {
        int[] counter = new int[(maxValue - minValue) + 1];
        List<Integer> sortedList = new ArrayList<>();
        
        for (int i = 0; i < arr.length; i++) {
            counter[arr[i] - minValue] += 1;
        }
        
        for (int j = 0; j < counter.length; j++) {
            int occurences = counter[j];
            for (int k = 0; k < occurences; k++) {
                sortedList.add(j + minValue);
            }
        }
        
        return sortedList;
    }
}