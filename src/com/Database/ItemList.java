package com.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.Database.Items;

@SuppressLint("DefaultLocale")
public class ItemList implements Parcelable{

    private List<Items> carpenterItemArray = new ArrayList<Items>();
    private List<Items> blacksmithItemArray = new ArrayList<Items>();
    private List<Items> armorerItemArray = new ArrayList<Items>();
    private List<Items> goldsmithItemArray = new ArrayList<Items>();
    private List<Items> leatherworkerItemArray = new ArrayList<Items>();
    private List<Items> weaverItemArray = new ArrayList<Items>();
    private List<Items> alchemistItemArray = new ArrayList<Items>();
    private List<Items> culinarianItemArray = new ArrayList<Items>();
    
    private Items foundItem;
    private String readable;
    boolean searching = true;
    boolean debug;
	
    public ItemList () {
    }
    public ItemList (List<Items> carp, List<Items> black, List<Items> armor, List<Items> gold, List<Items> leather, List<Items> weaver, List<Items> alchem, List<Items> culinary){
        carpenterItemArray = carp;
        blacksmithItemArray = black;
        armorerItemArray = armor;
        goldsmithItemArray = gold;
        leatherworkerItemArray = leather;
        weaverItemArray = weaver;
        alchemistItemArray = alchem;
        culinarianItemArray = culinary;
        
    }
    
    public List<Items> getCarpenter(){
        return carpenterItemArray;
    }
    
    public List<Items> getBlacksmith(){
        return blacksmithItemArray;
    }
    
    public List<Items> getArmorer(){
        return armorerItemArray;
    }

    public List<Items> getGoldsmith(){
        return goldsmithItemArray;
    }

    public List<Items> getLeather(){
        return leatherworkerItemArray;
    }

    public List<Items> getWeaver(){
        return weaverItemArray;
    }

    public List<Items> getAlchemist(){
        return alchemistItemArray;
    }

    public List<Items> getCulinary(){
        return culinarianItemArray;
    }
    
    public Items searchList(String itemName){
        searching = true;
        for(int i = 0; i < carpenterItemArray.size(); i++){            
            if (carpenterItemArray.get(i).getRecipe().toLowerCase().equals(itemName)){
                foundItem = new Items(carpenterItemArray.get(i));
                if (debug) debugger(foundItem, "Carpenter");
                searching = false;
                break;
            }
        }
        if (searching){
            for(int i = 0; i < blacksmithItemArray.size(); i++){            
                if (blacksmithItemArray.get(i).getRecipe().toLowerCase().equals(itemName)){
                    foundItem = new Items(blacksmithItemArray.get(i));
                    if (debug) debugger(foundItem, "Blacksmith");
                    searching = false;
                    break;
                }
            }
        }
        if (searching){
            for(int i = 0; i < armorerItemArray.size(); i++){            
                if (armorerItemArray.get(i).getRecipe().toLowerCase().equals(itemName)){
                    foundItem = new Items(armorerItemArray.get(i));
                    if (debug) debugger(foundItem, "Armorer");
                    searching = false;
                    break;
                }
            }
        }
        if (searching){
            for(int i = 0; i < goldsmithItemArray.size(); i++){            
                if (goldsmithItemArray.get(i).getRecipe().toLowerCase().equals(itemName)){
                    foundItem = new Items(goldsmithItemArray.get(i));
                    if (debug) debugger(foundItem, "Goldsmith");
                    searching = false;
                    break;
                }
            }
        }
        if (searching){
            for(int i = 0; i < leatherworkerItemArray.size(); i++){            
                if (leatherworkerItemArray.get(i).getRecipe().toLowerCase().equals(itemName)){
                    foundItem = new Items(leatherworkerItemArray.get(i));
                    if (debug) debugger(foundItem, "LeatherWorker");
                    searching = false;
                    break;
                }
            }
        }
        if (searching){
            for(int i = 0; i < weaverItemArray.size(); i++){            
                if (weaverItemArray.get(i).getRecipe().toLowerCase().equals(itemName)){
                    foundItem = new Items(weaverItemArray.get(i));
                    if (debug) debugger(foundItem, "Weaver");
                    searching = false;
                    break;
                }
            }
        }
        if (searching){
            for(int i = 0; i < alchemistItemArray.size(); i++){            
                if (alchemistItemArray.get(i).getRecipe().toLowerCase().equals(itemName)){
                    foundItem = new Items(alchemistItemArray.get(i));
                    if (debug) debugger(foundItem, "Alchemist");
                    searching = false;
                    break;
                }
            }
        }
        if (searching){
            for(int i = 0; i < culinarianItemArray.size(); i++){            
                if (culinarianItemArray.get(i).getRecipe().toLowerCase().equals(itemName)){
                    foundItem = new Items(culinarianItemArray.get(i));
                    if (debug) debugger(foundItem, "Culinarian");
                    searching = false;
                    break;
                }
            }
        }
        if (searching) {
            Log.i("item found", "no");
            return null;
        }
        else {
            Log.i("item found", "yes");
            return foundItem;
        }
    }
    public void loadList(Context myContext) throws IOException{
        for (int i = 0; i<8; i++){
            myContext.getAssets();
            if (i == 0) readable = "Craft/Carpenter.txt";
            else if (i == 1) readable = "Craft/Blacksmith.txt";
            else if (i == 2) readable = "Craft/Armorer.txt";
            else if (i == 3) readable = "Craft/Goldsmith.txt";
            else if (i == 4) readable = "Craft/Leatherworker.txt";
            else if (i == 5) readable = "Craft/Weaver.txt";
            else if (i == 6) readable = "Craft/Alchemist.txt";
            else if (i == 7) readable = "Craft/Culinarian.txt";
            InputStream is = myContext.getAssets().open(readable);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            Log.i("System.out", readable);

            String getLine = reader.readLine();
            while (getLine != null){
                //Log.i("System.out", getLine);
                List<String> separator = new ArrayList<String>(Arrays.asList(getLine.split(" ")));

                Items item = new Items();
                ArrayList<String> material = new ArrayList<String>();
                ArrayList<Integer> matAmount = new ArrayList<Integer>();
                for (int j = 0; j < separator.size(); j++){
                    //Log.i("System.out", " " + separator.get(j));

                    if (j == 0) {
                        item.setRecipe(separator.get(j).replace('_', ' '));
                    }
                    else if (j == 1) {
                        item.setLevel(Integer.parseInt((separator.get(j))));
                    }
                    else if (j == 2) item.setAmount(Integer.parseInt((separator.get(j))));
                    else if (j == 3) item.setDifficulty(Integer.parseInt((separator.get(j))));
                    else if (j == 4) item.setDurability(Integer.parseInt((separator.get(j))));
                    else if (j == 5) item.setQuality(Integer.parseInt(separator.get(j)));
                    if (j == 6 || j == 8 || j == 10 || j == 12 || j == 14 || j == 16 || j == 18 || j == 20)  
                        matAmount.add(Integer.parseInt(separator.get(j)));
                    if (j == 7 || j == 9 || j == 11 || j == 13 || j == 15 || j == 17 || j == 19 || j == 21)  
                        material.add(separator.get(j).replace('_', ' '));

                    if (j == separator.size()-1){
                        item.setMatAmount(matAmount);
                        item.setMaterials(material);
                        if (i == 0) carpenterItemArray.add(item);
                        else if (i == 1) blacksmithItemArray.add(item);
                        else if (i == 2) armorerItemArray.add(item);
                        else if (i == 3) goldsmithItemArray.add(item);
                        else if (i == 4) leatherworkerItemArray.add(item);
                        else if (i == 5) weaverItemArray.add(item);
                        else if (i == 6) alchemistItemArray.add(item);
                        else if (i == 7) culinarianItemArray.add(item);
                        getLine = reader.readLine();
                        break;

                    }
                }
            }
        }

    }

    public static void debugger(Items item, String location){
        if (location == "none"){
            System.out.println(item.getEverything2());  
        } else if (location != null){
            System.out.println("Item Found at " + location + "!");
            System.out.println(item.getEverything2()); //This is to debug the item that's chosen
        }
    }
    
    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(carpenterItemArray);
        dest.writeList(blacksmithItemArray);
        dest.writeList(armorerItemArray);
        dest.writeList(goldsmithItemArray);
        dest.writeList(leatherworkerItemArray);
        dest.writeList(weaverItemArray);
        dest.writeList(alchemistItemArray);
        dest.writeList(culinarianItemArray);
    }
    
    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<ItemList> CREATOR = new Parcelable.Creator<ItemList>() {
        public ItemList createFromParcel(Parcel in) {
            return new ItemList(in);
        }

        public ItemList[] newArray(int size) {
            return new ItemList[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private ItemList(Parcel in) {
        in.readList(carpenterItemArray, null);
        in.readList(blacksmithItemArray, null);
        in.readList(armorerItemArray, null);
        in.readList(goldsmithItemArray, null);
        in.readList(leatherworkerItemArray, null);
        in.readList(weaverItemArray, null);
        in.readList(alchemistItemArray, null);
        in.readList(culinarianItemArray, null);
    }
}