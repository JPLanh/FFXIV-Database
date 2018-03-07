package com.Database;

import java.util.ArrayList;

import android.util.Log;

import com.Database.ItemList;
import com.Database.Items;


public class index {

    boolean debug = false;    
    static ArrayList<String> materialSet = new ArrayList<String>();
    static ArrayList<Integer> materialAmount = new ArrayList<Integer>();
    public static Fish searchForFish(String search, FishList database){
        search = search.toLowerCase();
        if (database.searchList(search) != null){     
            return database.searchList(search);
        }
        return null;
    }
    public static Items searchForItem(boolean debug, String search, ItemList database){
        
        search = search.toLowerCase();
        if (database.searchList(search) != null){                  
            return database.searchList(search);
        }
        return null;

    }
    public static Materials searchForMaterial(String search, MaterialList database){
        search = search.toLowerCase();
        if (database.materialSearch(search) != null){
            return database.materialSearch(search);
        }
        return null;
    }
    public static Reagents searchForReagents(String search, MaterialList database){
        search = search.toLowerCase();
        if (database.reagentSearch(search) != null){
            return database.reagentSearch(search);
        }
        return null;
    }
    public static ArrayList<String> composeList(boolean debug, ArrayList<Items> items){
        Items itemInHold;
        ArrayList<String> finalizedSet = new ArrayList<String>();

        while (!items.isEmpty()){
            itemInHold = new Items(items.get(0));
            items.remove(0);
            for (int i = 0; i < itemInHold.getMatArray().size(); i++){
                if (!materialSet.contains(itemInHold.getMaterial(i)))
                {
                    materialSet.add(itemInHold.getMaterial(i));
                    materialAmount.add(itemInHold.getMatAmount(i));
                } else {
                    materialAmount.set(materialSet.indexOf(itemInHold.getMaterial(i)), 
                            materialAmount.get(materialSet.indexOf(itemInHold.getMaterial(i))) + itemInHold.getMatAmount(i));
                }
            }
        }
        for (int i = 0; i < materialSet.size(); i++){
            finalizedSet.add(materialSet.get(i));
            finalizedSet.add(String.valueOf(materialAmount.get(i)));
        }
        return finalizedSet;
    }

}
