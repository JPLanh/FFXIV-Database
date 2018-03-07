package com.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.util.Log;

public class RareFishList {

    private ArrayList<RareFish> listOfRareFish = new ArrayList<RareFish>();
    private RareFish foundFish;
    private boolean searching = true;
    
    public RareFishList(){
        
    }
    
    public RareFishList(ArrayList<RareFish> fishList){
        listOfRareFish = fishList;
    }
    
    public void loadList(Context myContext) throws IOException{
        myContext.getAssets();
        InputStream is = myContext.getAssets().open("Material/RareFishing.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        
        String getLine = reader.readLine();
        while (getLine != null){
            List<String> separator = new ArrayList<String>(Arrays.asList(getLine.split(" ")));
            foundFish = new RareFish(separator.get(0).replace('_', ' '), separator.get(1).replace('_', ' '), 
                    separator.get(2).replace('_', ' '), separator.get(3).replace('_', ' '), 
                    separator.get(4).replace('_', ' '), Integer.parseInt(separator.get(5)), 
                    separator.get(6).replace('_', ' '), Integer.parseInt(separator.get(7)), Integer.parseInt(separator.get(8)));
            listOfRareFish.add(foundFish);
            getLine = reader.readLine();
            
        }
    }
    
    public RareFish searchList(String search){
        searching = true;
        for(int i = 0; i < listOfRareFish.size(); i++){            
            if (listOfRareFish.get(i).getName().toLowerCase().equals(search)){
                Log.i("found fish", listOfRareFish.get(i).getName() + " " );
                foundFish = new RareFish(listOfRareFish.get(i));
                searching = false;
                break;
            }
        }
        
        if (searching) {
            return null;
        }
        else {
            return foundFish;
        }
    }

}
