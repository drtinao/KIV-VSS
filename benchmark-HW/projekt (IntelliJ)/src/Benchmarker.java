import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Contains just static methods used for performing benchmark.
 */
public class Benchmarker {
    public static final long NS_TO_SEC = 1000000000;

    /**
     * Performs benchmark on ArrayList object.
     * @param dummyCompleteDataAL ArrayList with all generated data
     * @param dummyKeepDataAL data which should be kept in ArrayList
     * @return time bench took - in nanoseconds
     */
    public static long benchArrayList(ArrayList<String> dummyCompleteDataAL, ArrayList<String> dummyKeepDataAL){
        System.out.println("***BENCHMARK ON ARRAYLIST - START***");
        long startTimeAL = System.nanoTime();
        System.out.println("start ns: " + startTimeAL);
        dummyCompleteDataAL.retainAll(dummyKeepDataAL);
        long endTimeAL = System.nanoTime();
        long totalTimeAL = endTimeAL - startTimeAL;
        System.out.println("end ns: " + endTimeAL);
        System.out.println("took time: " + totalTimeAL + "ns => " + (totalTimeAL / NS_TO_SEC) + " seconds.");
        System.out.println("***BENCHMARK ON ARRAYLIST - END***");

        return totalTimeAL;
    }

    /**
     * Performs benchmark on LinkedList object.
     * @param dummyCompleteDataLL LinkedList with all generated data
     * @param dummyKeepDataLL data which should be kept in LinkedList
     * @return time bench took - in nanoseconds
     */
    public static long benchLinkedList(LinkedList<String> dummyCompleteDataLL, LinkedList<String> dummyKeepDataLL){
        System.out.println("***BENCHMARK ON LINKEDLIST - START***");
        long startTimeLL = System.nanoTime();
        System.out.println("start ns: " + startTimeLL);
        dummyCompleteDataLL.retainAll(dummyKeepDataLL);
        long endTimeLL = System.nanoTime();
        long totalTimeLL = endTimeLL - startTimeLL;
        System.out.println("end ns: " + endTimeLL);
        System.out.println("took time: " + totalTimeLL + "ns => " + (totalTimeLL / NS_TO_SEC) + " seconds.");
        System.out.println("***BENCHMARK ON LINKEDLIST - END***");

        return totalTimeLL;
    }
}
