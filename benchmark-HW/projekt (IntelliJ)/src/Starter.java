import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Class is a starting point (contains main). Also methods for generating random data (and conversion to ArrayList, LinkedList) are present.
 */
public class Starter {
    /**
     * App starting point. Generates random data (ie. strings of length 10) - converts them to desired structures and finally performs benchmark.
     * @param args cmd params - not used here
     */
    public static void main(String[] args){
        String[] genDummyData = generateDummyData(); //generate random strings for benchmarking - array
        String[] dummyDataToKeep = getDummyDataToKeep(genDummyData); //half of dummy data which is supposed to be kept - array

        ArrayList<String> dummyCompleteDataAL = convDummyDataAL(genDummyData); //retrieve ArrayList with COMPLETE random data
        LinkedList<String> dummyCompleteDataLL = convDummyDataLL(genDummyData); //retrieve LinkedList with COMPLETE random data

        ArrayList<String> dummyKeepDataAL = convDummyDataAL(dummyDataToKeep); //ArrayList with data which should be KEPT
        LinkedList<String> dummyKeepDataLL = convDummyDataLL(dummyDataToKeep); //LinkedList with data which should be KEPT

        System.out.println("***CONDITIONS - START***");
        System.out.println("total items generated (strings of length 10): " + genDummyData.length);
        System.out.println("items to be kept: " + dummyDataToKeep.length);
        System.out.println("***CONDITIONS - END***");

        long arrayListTime = Benchmarker.benchArrayList(dummyCompleteDataAL, dummyKeepDataAL);
        long linkedListTim = Benchmarker.benchLinkedList(dummyCompleteDataLL, dummyKeepDataLL);
    }

    /**
     * Generate random data used for benchmarking.
     * @return array with random strings (size 10, each)
     */
    public static String[] generateDummyData(){
        String[] dummyDataArr = new String[300000]; //300000 dummy strings used for benchmarking

        for(int i = 0; i < dummyDataArr.length; i++){
            byte[] byteArr = new byte[10]; //string of length 10
            new Random().nextBytes(byteArr);
            String generatedString = new String(byteArr, Charset.forName("UTF-8"));
            dummyDataArr[i] = generatedString;
        }

        return dummyDataArr;
    }

    /**
     * Returns half of data which were generated for benchmarking purposes and are supposed to be kept in data structure.
     * @param genDummyData all generated dummy data (in form of array)
     * @return half of dummy data, should be kept in structure later on
     */
    public static String[] getDummyDataToKeep(String[] genDummyData){
        String[] dummyDataToRemove = new String[genDummyData.length / 2];

        int itemCounter = 0;
        for(int i = 0; i < genDummyData.length / 2; i++){
            if(i % 2 == 0){
                dummyDataToRemove[itemCounter] = genDummyData[i];
                itemCounter++;
            }
        }

        return dummyDataToRemove;
    }

    /**
     * Converts original array to form of ArrayList<String>, containing the same data.
     * @param origList array with dummy content to be benchmarked
     * @return ArrayList<String> with dummy objects
     */
    public static ArrayList<String> convDummyDataAL(String[] origList){
        ArrayList<String> dummyDataAL = new ArrayList<>(); //will contain items present in orig origList
        for(String dummyString : origList){
            dummyDataAL.add(dummyString);
        }

        return dummyDataAL;
    }

    /**
     * Converts original array to form of LinkedList<String>, containing the same data.
     * @param origList array with dummy content to be benchmarked
     * @return LinkedList<String> with dummy objects
     */
    public static LinkedList<String> convDummyDataLL(String[] origList){
        LinkedList<String> dummyDataLL = new LinkedList<>();
        for(String dummyString : origList){
            dummyDataLL.add(dummyString);
        }

        return dummyDataLL;
    }
}
