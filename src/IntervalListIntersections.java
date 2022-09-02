/*
 * This class solves Leetcode #986, which asks to find the intersections
 * between two lists of closed intervals.
 *
 * Command line arguments are not supported, intervals must be entered
 * through standard input for this to work.
 *
 * The first and second lists of intervals should be on separate lines.
 * Format them as space separated integers. The start and end points of
 * intervals should be placed next to each other and the program will
 * automatically group them together.
 *
 * Example:
 * 1 4 6 9 10 11    - Converts to [[1, 4], [6, 9], [10, 11]]
 * 2 3 4 7 9 11     - Converts to [[2, 3], [4, 7], [9, 11]]
 */

import java.util.*;
import java.io.*;

public class IntervalListIntersections {
    
    private Scanner sc;
    
    public IntervalListIntersections() {
        sc = new Scanner(System.in);
    }
    
    public List<int[]> getInterval() {
        List<int[]> intervals = new ArrayList<>();
        int[] allNums = Arrays.stream(sc.nextLine().split(" "))
                              .mapToInt(s -> Integer.parseInt(s))
                              .toArray();
        
        for (int i = 0; i < allNums.length; i+=2) {
            int[] interval = new int[]{allNums[i], allNums[i+1]};
            intervals.add(interval);
        }
        
        return intervals;
    }
    
    public List<Integer> findIntersection(int[] interval1, int[] interval2) {
        List<Integer> intersection = new ArrayList<>();
        if (interval1[0] <= interval2[1] && interval1[1] >= interval2[0]) {
            int leftPoint = Math.max(interval1[0], interval2[0]);
            int rightPoint = Math.min(interval1[1], interval2[1]);
            intersection.add(leftPoint);
            intersection.add(rightPoint);
            return intersection;
        }
        return null;  // If there is no intersection
    }
    
    public static void main(String args[]) {
        IntervalListIntersections ili = new IntervalListIntersections();
        
        List<int[]> intervals1 = ili.getInterval();
        List<int[]> intervals2 = ili.getInterval();
        List<List<Integer>> intersections = new ArrayList<>();
        
        for (int i = 0; i < intervals1.size(); i++) {
            int[] interval1 = intervals1.get(i);
            for (int j = 0; j < intervals2.size(); j++) {
                int[] interval2 = intervals2.get(j);
                List<Integer> intersection = ili.findIntersection(interval1, interval2);
                if (intersection != null) {
                    intersections.add(intersection);
                }
            }
        }
        
        System.out.println(intersections);
    }
}