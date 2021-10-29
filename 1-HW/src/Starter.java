import java.util.*;

/**
 * Class serves as a starting point of the application and contains methods for checking args validity + executing calculations.
 */
public class Starter {
    private static int HISTOGRAM_FIRST_X_NUMS = 5; //how many most relevant values from histogram should be printed
    private static int MAX_STARS_COUNT = 10; //how many stars should be used for max occ key
    // demo vals - START
    private static final int DEMO_COUNT_1 = 1000000;
    private static final int DEMO_COUNT_2 = 100000;
    private static final double DEMO_P_1 = 0.4;
    private static final double DEMO_P_2 = 0.6;
    // demo vals - END

    /**
     * Starting point of app, check args, run calculations.
     * @param args arguments entered by user
     */
    public static void main(String[] args){
        if(checkParamsValidRegRunNoExtraPar(args)){ //params for regular run with NO extra params
            System.out.println("Parameters entered - regular mode, NO optional params - printing vals - START");
            int count = Integer.parseInt(args[0]);
            double p = Double.parseDouble(args[1]);
            System.out.println("Count is: " + count + ", p is: " + p);
            System.out.println("Parameters entered - regular mode, NO optional params - printing vals - END");
            performRun(count, p);
        }else if(checkParamsValidRegRunExtraPar(args)){ //params for regular run, extra params entered
            System.out.println("Parameters entered - regular mode, optional params present - printing vals - START");
            int count = Integer.parseInt(args[0]);
            double p = Double.parseDouble(args[1]);
            String[] splittedExtraArgs = args[2].split(";");
            int histogramNums = Integer.parseInt(splittedExtraArgs[0]); //represents HISTOGRAM_FIRST_X_NUMS
            int starsCount = Integer.parseInt(splittedExtraArgs[1]); //represents MAX_STARS_COUNT
            System.out.println("Count is: " + count + ", p is: " + p + ", max histogram size is: " + histogramNums + ", max stars count is: " + starsCount);
            System.out.println("Parameters entered - regular mode, optional params present - printing vals - END");
            HISTOGRAM_FIRST_X_NUMS = histogramNums;
            MAX_STARS_COUNT = starsCount;
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

        ArrayList<Integer> printedKeys = new ArrayList<>();
        ArrayList<Integer> printedValues = new ArrayList<>();
        int printCounter = 0; //print max HISTOGRAM_FIRST_X_NUMS values
        for(Integer key : sortHistVals){
            if(printCounter < HISTOGRAM_FIRST_X_NUMS){
                printedKeys.add(key);
                printedValues.add((Integer) sortedHistogram.get(key));
                printCounter++;
            }else{
                break;
            }
        }

        //first value is max, -> adapt other values (value / max) * max stars count
        for(int i = 0; i < printedKeys.size(); i++){
            int starsCount = (int)(((double) printedValues.get(i) / printedValues.get(0)) * MAX_STARS_COUNT);
            System.out.print(printedKeys.get(i) + ":");
            for(int j = 0; j < starsCount; j++){ //print stars according to occ
                System.out.print("*");
            }
            System.out.print("(exact num: " + printedValues.get(i)+ ")\n");
        }
    }

    /**
     * Returns true if given parameters are valid for regular run (ie. params are present), else false.
     * @param args arguments given by user
     * @return true if args are valid for regular run, else false
     */
    public static boolean checkParamsValidRegRunNoExtraPar(String[] args){
        if(args.length == 2){ //expect count, p and x
            try{
                Integer.parseInt(args[0]);
                Double.parseDouble(args[1]);
                return true;
            }catch(NumberFormatException nfe){
                return false;
            }
        }else{ //count args not ok
            return false;
        }
    }

    /**
     * Returns true if given parameters are valid for regular run with extra params  (ie. HISTOGRAM_FIRST_X_NUMS + MAX_STARS_COUNT specified), else false.
     * @param args arguments given by user
     * @return true if args are valid for regular run, else false
     */
    public static boolean checkParamsValidRegRunExtraPar(String[] args){
        if(args.length == 3){ //HISTOGRAM_FIRST_X_NUMS + MAX_STARS_COUNT specified
            try{
                Integer.parseInt(args[0]);
                Double.parseDouble(args[1]);
                String[] splittedExtraArgs = args[2].split(";");
                Integer.parseInt(splittedExtraArgs[0]); //represents HISTOGRAM_FIRST_X_NUMS
                Integer.parseInt(splittedExtraArgs[1]); //represents MAX_STARS_COUNT
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
     * Starts calculations using given values.
     * @param count count of numbers which should be generated
     * @param p probability of success - value <0, 1>
     */
    public static void performRun(int count, double p){
        StatisticsCalc calc = new StatisticsCalc(count, p);
        GeometricDistribution geomDistrExpected = new GeometricDistribution(p);
        printResults(calc, geomDistrExpected, HISTOGRAM_FIRST_X_NUMS);
    }
}
