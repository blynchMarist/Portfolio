import java.util.ArrayList;

public class Merge {
    int comparisons = 0;
    void sort(ArrayList<String> allMagicItems) {
        if (allMagicItems.size() > 1) {
            //split list into 2 separate lists
            int midPoint = allMagicItems.size() / 2;
            ArrayList<String> bottomList = new ArrayList<String>(allMagicItems.subList(0, midPoint));
            ArrayList<String> topList = new ArrayList<String>(allMagicItems.subList(midPoint, allMagicItems.size()));

            //recurse bottom and top lists
            sort(bottomList);
            sort(topList);

            //merge the bottom and top lists
            int bottomIndex = 0;
            int topIndex = 0;
            for(int index = 0; index<allMagicItems.size(); index++){
                //tests if bottomIndex has additional testable values, topIndex has additional testable values, or both
                if(bottomIndex<bottomList.size() && topIndex<topList.size()){
                    //tests lower value between bottom or top list and places into main array
                    comparisons++;
                    if(bottomList.get(bottomIndex).compareTo(topList.get(topIndex))<0){
                        allMagicItems.set(index,bottomList.get(bottomIndex));
                        bottomIndex++;
                    } else{
                        allMagicItems.set(index,topList.get(topIndex));
                        topIndex++;
                    }
                } else if(bottomIndex<bottomList.size()){
                    //if executes, only items in bottom list still need to be placed. places remaining values
                    allMagicItems.set(index,bottomList.get(bottomIndex));
                    bottomIndex++;
                } else if(topIndex<topList.size()){
                    //if executes, only items in top list still need to be placed. places remaining values
                    allMagicItems.set(index,topList.get(topIndex));
                    topIndex++;
                }
            }
        }
    }
}