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

public class FishList {

    private ArrayList<Fish> listOfFish = new ArrayList<Fish>();
    private Fish foundFish;
    private boolean searching = true;
    
    public FishList(){
        
    }
    
    public FishList(ArrayList<Fish> fishList){
        listOfFish = fishList;
    }
    
    public void loadList(Context myContext) throws IOException{
        myContext.getAssets();
        int count = 0;
        InputStream is = myContext.getAssets().open("Material/Fishing.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        
        String getLine = reader.readLine();
        while (getLine != null){
            List<String> separator = new ArrayList<String>(Arrays.asList(getLine.split(" ")));
            foundFish = new Fish();
            foundFish.setName(separator.get(0));
            foundFish.setLvl(separator.get(1));
            foundFish.setWaterBody(separator.get(2));
            count = 4;
            while(!separator.get(count).contains("Weather")){
                foundFish.addArea(separator.get(count), separator.get(count+1), separator.get(count+2));
                count += 3;
            }
            count++;
            while(!separator.get(count).contains("Bait")){
                foundFish.addWeather(separator.get(count), separator.get(count+1));
                count += 2;
            }
            count++;
            while(!separator.get(count).contains("Desynth")){
                foundFish.addBait(separator.get(count), separator.get(count+1), separator.get(count+2));
                count += 3;
            }
            count++;
            while(!separator.get(count).contains("/")){
                foundFish.addDesynth(separator.get(count), separator.get(count+1));
                count += 2;
            }
            //foundFish.print();
            listOfFish.add(foundFish);
            getLine = reader.readLine();
            
        }
    }
    
    public Fish searchList(String search){
        searching = true;
        for(int i = 0; i < listOfFish.size(); i++){            
            if (listOfFish.get(i).getName().toLowerCase().replace('_', ' ').equals(search)){
                foundFish = new Fish(listOfFish.get(i));
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
