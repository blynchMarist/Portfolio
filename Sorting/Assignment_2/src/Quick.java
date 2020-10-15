import java.util.ArrayList;
import java.util.Random;

public class Quick {
    int comparisons = 0;
    void sort(ArrayList<String> allMagicItems) {
        if (allMagicItems.size() > 1 ) {
            //find the pivot index, and place all values that are less than the pivot before the index,
            //and all values greater than the pivot after the index
            int pivotIndex = partition(allMagicItems);

            //create and sort lists of values less than the pivot, and values greater than the pivot
            ArrayList<String> bottomList = new ArrayList<String>(allMagicItems.subList(0, pivotIndex));
            ArrayList<String> topList = new ArrayList<String>(allMagicItems.subList(pivotIndex + 1, allMagicItems.size()));
            sort(bottomList);
            sort(topList);

            //merge the bottom list, pivot, and top list back together.
            int bottomIndex = 0;
            int topIndex = 0;
            for(int mainIndex = 0; mainIndex<allMagicItems.size();mainIndex++){
                if(mainIndex<pivotIndex){
                    allMagicItems.set(mainIndex,bottomList.get(bottomIndex));
                    bottomIndex++;
                } else if(mainIndex>pivotIndex){
                    allMagicItems.set(mainIndex, topList.get(topIndex));
                    topIndex++;
                }
            }
        }
    }
    Integer partition(ArrayList<String> allMagicItems){
        //get random value from list
        Random r = new Random();
        int pivotIndex = r.nextInt(allMagicItems.size());
        String pivot = allMagicItems.get(pivotIndex);

        //swap pivot with last value in list
        allMagicItems.set(pivotIndex,allMagicItems.get(allMagicItems.size()-1));
        allMagicItems.set(allMagicItems.size()-1,pivot);

        //if value is less than pivot, place at next index at start of list
        int index = 0;
        for(int j = index; j<allMagicItems.size()-1; j++){
            comparisons++;
            if(allMagicItems.get(j).compareTo(pivot)<0){
                String tempString = allMagicItems.get(j);
                allMagicItems.set(j,allMagicItems.get(index));
                allMagicItems.set(index,tempString);
                index++;
            }
        }
        //swap pivot with value at index. This places all values (start...pivot)<(pivot+1...end)
        allMagicItems.set(allMagicItems.size()-1,allMagicItems.get(index));
        allMagicItems.set(index,pivot);

        //return index of pivot value
        return index;
    }
}