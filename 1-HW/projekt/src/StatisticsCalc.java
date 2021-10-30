import java.util.*;

/**
 * Used for performing E, D calculations using histogram.
 */
public class StatisticsCalc {
    private int numsToGenCount; //count of numbers which should be generated
    private double p; //p probability of success - value <0, 1>

    private Random rn; //used for generating random numbers
    private HashMap<Integer, Integer> histogram; //represents histogram; key - generated value, val = number of occ

    /**
     * Constructor takes just count of nums to generate and probability of success.
     * @param numsToGenCount count of numbers which should be generated
     * @param p p probability of success - value <0, 1>
     */
    public StatisticsCalc(int numsToGenCount, double p){
        this.numsToGenCount = numsToGenCount;
        this.p = p;
        this.rn = new Random();

        this.histogram = new HashMap<>();
        performCalculations();
    }

    /**
     * Builds histogram which is then used for performing calculations of E and D.
     */
    public void performCalculations(){
        for(int i = 0; i < numsToGenCount; i++){ //generated desired count of nums
            double ranValUni = rn.nextDouble(); //generate number <0, 1>
            int generatedNum = (int) Math.ceil(Math.log(1 - ranValUni) / Math.log(1 - p)) - 1;
            //add generated val to histogram
            if(histogram.containsKey(generatedNum)){ //generated val already present in histogram, inc count
                histogram.put(generatedNum, histogram.get(generatedNum) + 1);
            }else{ //found new val, occ is 1
                histogram.put(generatedNum, 1);
            }
        }
    }

    /**
     * Sorts histogram highest -> lowest occurance and returns it.
     */
    public HashMap retrieveSortedHistogram(){
        List llist = new LinkedList(histogram.entrySet());
        Collections.sort(llist, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        HashMap sortedHistogram = new LinkedHashMap();
        for(Iterator iter = llist.iterator(); iter.hasNext();){
            Map.Entry mapEntry = (Map.Entry) iter.next();
            sortedHistogram.put(mapEntry.getKey(), mapEntry.getValue());
        }

        return sortedHistogram;
    }

    /**
     * Calculates mean (E, "stredni hodnota") and returns its value.
     * @return calculated mean
     */
    public double retrieveMean(){
        double valuesSum = 0;
        for (Integer key : histogram.keySet()) { //get sum of all values in histogram
            valuesSum += key * histogram.get(key);
        }

        return (valuesSum / numsToGenCount);
    }

    /**
     * Calculates variance (D, "rozptyl") and returns its value.
     * @return calculated variance
     */
    public double retrieveVariance(double mean){
        double variance = 0;
        for (Integer key : histogram.keySet()) { //get sum of all values in histogram
            variance += Math.pow(key - mean, 2) * ((double)histogram.get(key) / numsToGenCount);
        }

        return variance;
    }
}
