import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        try {
            //read and retrieve file
            File itemFile = new File("magicitems.txt");
            Scanner readFile = new Scanner(itemFile);

            //initialize and develop arraylist of items
            ArrayList<String> allMagicItems = new ArrayList<String>();
            while(readFile.hasNextLine()){
                allMagicItems.add(readFile.nextLine().toUpperCase().replace(" ",""));
            }

            //* Selection Sort
            ArrayList<String> selectionArray = new ArrayList<String>(allMagicItems);
            Selection s = new Selection();
            s.sort(selectionArray);
            System.out.println("Selection: " + s.comparisons);
            //*/

            //* Insertion Sort
            ArrayList<String> insertionArray = new ArrayList<String>(allMagicItems);
            Insertion i = new Insertion();
            i.sort(insertionArray);
            System.out.println("Insertion: " + i.comparisons);
            //*/

            //* Merge Sort
            ArrayList<String> mergeArray = new ArrayList<String>(allMagicItems);
            Merge m = new Merge();
            m.sort(mergeArray);
            System.out.println("Merge: " + m.comparisons);
            //*/

            //* Quick Sort
            ArrayList<String> quickArray = new ArrayList<String>(allMagicItems);
            Quick q = new Quick();
            q.sort(quickArray);
            System.out.println("Quick: " + q.comparisons);
            //*/

            /* EXTRA Average Quick Sort
            int sum = 0;
            int iterations = 100000;
            for(int count = 0; count<iterations; count++){
                ArrayList<String> averageQArray = new ArrayList<String>(allMagicItems);
                Quick averageQ = new Quick();
                averageQ.sort(averageQArray);
                sum += averageQ.comparisons;
            }
            System.out.println("Quick Sort Average: " + sum/iterations);
            //*/

        //prints error message if file is not found
        } catch(FileNotFoundException a){
            System.out.println("Error: File not found.");
        }
    }
}
