import java.util.ArrayList;

public class Selection {
    int comparisons = 0;
    void sort(ArrayList<String> allMagicItems){
        for(int index = 0; index<allMagicItems.size()-1;index++){
            //set swapIndex to index. if swapIndex does not change value, index will be swapped with itself.
            int swapIndex = index;
            for(int i = index+1; i<allMagicItems.size();i++){
                comparisons++;
                //find smallest value in remainder of array, and set it to swapIndex.
                if(allMagicItems.get(i).compareTo(allMagicItems.get(swapIndex))<0){
                    swapIndex = i;
                }
            }
            //swap lowest value from remainder of list with index
            String tempString = allMagicItems.get(swapIndex);
            allMagicItems.set(swapIndex,allMagicItems.get(index));
            allMagicItems.set(index,tempString);
        }
    }
}
