import java.util.*;

public class StatisticsCalc {
    private int numsToGenCount; //count of numbers which should be generated
    private double p; //p probability of success - value <0, 1>

    private Random rn; //used for generating random numbers
    private HashMap<Integer, Integer> histogram; //represents histogram; key - generated value, val = number of occ

    public StatisticsCalc(int numsToGenCount, double p){
        this.numsToGenCount = numsToGenCount;
        this.p = p;
        this.rn = new Random();

        this.histogram = new HashMap<>();
        performCalculations();
    }


    public void performCalculations(){
        GeometricDistribution geomDist = new GeometricDistribution(p);

        double calculatedMean = 0;

        for(int i = 0; i < numsToGenCount; i++){ //generated desired count of nums
            double ranValUni = rn.nextDouble(); //generate number <0, 1>
//            if(ranValUni < p){
//                generated = 0;
//            }else{
//                generated = Math.ceil(Math.log(1 - ranValUni) / Math.log(1 - p));
//            }
            int generatedNum = (int) Math.ceil(Math.log(1 - ranValUni) / Math.log(1 - p));
            System.out.println("Generated " + generatedNum);
            //long u = Math.round(Double.MAX_VALUE * randomValu); //map random num to function range - function is defined for positive whole numbers

            //add generated val to histogram
            if(histogram.containsKey(generatedNum)){ //generated val already present in histogram, inc count
                histogram.put(generatedNum, histogram.get(generatedNum) + 1);
            }else{ //found new val, occ is 1
                histogram.put(generatedNum, 1);
            }
        }

        double mean = geomDist.calcExpectMean();
        System.out.println("Expected mean is: " + mean + ", calc is: " + (calculatedMean / numsToGenCount));
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
        double mean = 0;
        return 0;
    }

    /**
     * Calculates variance (D, "rozptyl") and returns its value.
     * @return calculated variance
     */
    public double retrieveVariance(){
        double variance = 0;
        return 0;
    }
}
