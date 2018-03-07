package com.Database;

import java.util.ArrayList;

import android.util.Log;

public class SearchingItem {

    public static void searchFor(String itemName, ItemList itemDatabase, MaterialList materialDatabase, FishList fishDatabase){
        ArrayList<Character> querySet = new ArrayList<Character>();
        ArrayList<Character> searchSet = new ArrayList<Character>();

        for (char c : itemName.toLowerCase().toCharArray())
            querySet.add(c);

        for (int i = 0; i < itemDatabase.getCarpenter().size(); i++){
            ArrayList<Character> listSet = new ArrayList<Character>();
            for (char c : itemDatabase.getCarpenter().get(i).getRecipe().toLowerCase().toCharArray())
                searchSet.add(c);
            for (int j = 0; j < querySet.size(); j++){
                if (searchSet.contains(querySet.get(j))) {
                    listSet.add(querySet.get(j));
                    searchSet.remove(searchSet.indexOf(querySet.get(j)));
                }
            }
            if (itemDatabase.getCarpenter().get(i).getRecipe().length() - listSet.size()< 3) Log.i("searching item List", listSet.toString() + " | " + itemDatabase.getCarpenter().get(i).getRecipe() + " | " + listSet.size() + " / " + itemDatabase.getCarpenter().get(i).getRecipe().length());            
        }

    }
}
