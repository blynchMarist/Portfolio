import java.util.ArrayList;

public class Insertion {
    int comparisons = 0;
    void sort(ArrayList<String> allMagicItems){
        //increments ascending through list, and compares descending through the list
        for(int index = 1; index<allMagicItems.size(); index++){
            for(int swapIndex = index; swapIndex>0;swapIndex--){
                //compares item to previous item in list
                comparisons++;
                if(allMagicItems.get(swapIndex).compareTo(allMagicItems.get(swapIndex-1))<0){
                    //swap item with previous item in list
                    String tempItem = allMagicItems.get(swapIndex);
                    allMagicItems.set(swapIndex,allMagicItems.get(swapIndex-1));
                    allMagicItems.set(swapIndex-1,tempItem);
                } else{
                    break;
                }
            }
        }
    }
}
