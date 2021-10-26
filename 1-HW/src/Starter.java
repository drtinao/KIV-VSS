import java.util.*;

public class Starter {
    private static final int HISTOGRAM_FIRST_X_NUMS = 10; //

    private static final int DEMO_COUNT_1 = 1000000;
    private static final int DEMO_COUNT_2 = 100000;
    private static final double DEMO_P_1 = 0.4;
    private static final double DEMO_P_2 = 0.6;

    public static void main(String[] args){
        if(checkParamsValidRegRun(args)){ //params for regular run
            System.out.println("Parameters entered - regular mode - printing vals - START");
            int count = Integer.parseInt(args[0]);
            double p = Double.parseDouble(args[1]);
            System.out.println("Count is: " + count + ", p is: " + p);
            System.out.println("Parameters entered - regular mode - printing vals - END");
            performRun(count, p);
        }else if(checkParamsValidDemoRun(args)){ //no params -> run demo
            System.out.println("Parameters NOT entered - DEMO mode - printing vals1 - START");
            System.out.println("Count is: " + DEMO_COUNT_1 + ", p is: " + DEMO_P_1);
            System.out.println("Parameters NOT entered - DEMO mode - printing vals1 - END");
            performRun(DEMO_COUNT_1, DEMO_P_1);

            System.out.println("Parameters NOT entered - DEMO mode - printing vals2 - START");
            System.out.println("Count is: " + DEMO_COUNT_2 + ", p is: " + DEMO_P_2);
            System.out.println("Parameters NOT entered - DEMO mode - printing vals2 - END");
            performRun(DEMO_COUNT_2, DEMO_P_2);
        }else{ //invalid parameters...
            System.out.print("Invalid params entered, exiting...");
            return;
        }
    }

    /**
     * Prints calculated / retrieved values to console.
     * @param calc initialized StatisticsCalc instance - calculates E, D using histogram
     * @param geomDistrExpected initialized GeometricDistribution - calcs expected E, D
     * @param countToPrint prints first X nums with highest occurance from histogram
     */
    public static void printResults(StatisticsCalc calc, GeometricDistribution geomDistrExpected, int countToPrint){
        double histogramMean = calc.retrieveMean();
        double histogramVariance = calc.retrieveVariance(histogramMean);
        double expectedMean = geomDistrExpected.calcExpectMean();
        double expectedVariance = geomDistrExpected.calcExpectVariance();

        System.out.println("E_teorie=" + expectedMean);
        System.out.println("D_teorie=" + expectedVariance);
        System.out.println("E_vypocet=" + histogramMean);
        System.out.println("D_vypocet=" + histogramVariance);

        HashMap sortedHistogram = calc.retrieveSortedHistogram();
        List<Integer>  sortHistVals = new ArrayList<Integer>(sortedHistogram.keySet());
        Collections.reverse(sortHistVals);
        for(Integer strKey : sortHistVals){
            System.out.println("Key : "  + strKey + "\t\t"
                    + "Value : "  + sortedHistogram.get(strKey));
        }
    }

    /**
     * Returns true if given parameters are valid for regular run (ie. params are present), else false.
     * @param args arguments given by user
     * @return true if args are valid for regular run, else false
     */
    public static boolean checkParamsValidRegRun(String[] args){
        if(args.length == 2){ //expect count, p and x
            try{
                double count = Double.parseDouble(args[0]);
                double p = Double.parseDouble(args[1]);
                return true;
            }catch(NumberFormatException nfe){
                return false;
            }
        }else{ //count args not ok
            return false;
        }
    }

    /**
     * Checks whether program was run with no params from user -> start demo run.
     * @param args arguments given by user
     * @return true if args are valid for demo run, else false
     */
    public static boolean checkParamsValidDemoRun(String[] args){
        if(args.length == 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Starts program in regular mode - ie. values are entered by user (not generated).
     * @param count count of numbers which should be generated
     * @param p probability of success - value <0, 1>
     */
    public static void performRun(int count, double p){
        StatisticsCalc calc = new StatisticsCalc(count, p);
        GeometricDistribution geomDistrExpected = new GeometricDistribution(p);
        printResults(calc, geomDistrExpected, HISTOGRAM_FIRST_X_NUMS);
    }

    /**
     * Prints calculated / expected mean + variance and histogram to console.
     * @param histogram map for calc mean / invariance
     * @param retrievedMean calculated mean (E, "stredni hodnota")
     * @param retrievedVariance calculated variance (D, "rozptyl")
     * @param expectedMean expected E
     * @param expectedVariance expected D
     */
    public void printResults(HashMap<Double, Integer> histogram, double retrievedMean, double retrievedVariance, double expectedMean, double expectedVariance){
        System.out.println("E_teorie=" + expectedMean);
        System.out.println("D_teorie=" + expectedVariance);
        System.out.println("E_vypocet=" + retrievedMean);
        System.out.println("D_vypocet=" + retrievedVariance);
    }
}
