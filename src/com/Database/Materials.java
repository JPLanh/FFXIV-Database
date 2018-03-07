package com.Database;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Materials implements Parcelable{

    private String name;
    private int level;
    private String extra;
    private String classes;
    private ArrayList<String> map = new ArrayList<String>();
    private ArrayList<Integer> areaLvl = new ArrayList<Integer>();
    private ArrayList<String> areas = new ArrayList<String>();
    private ArrayList<Integer> xCord = new ArrayList<Integer>();
    private ArrayList<Integer> yCord = new ArrayList<Integer>();
    
    public Materials(){
    }
    public Materials(Materials mats){
        name = mats.getName();
        level = mats.getLevel();
        extra = mats.getExtra();
        classes = mats.getClasses();
        map = mats.getMap();
        areaLvl = mats.getAreaLevel();
        areas = mats.getArea();
        xCord = mats.getXCord();
        yCord = mats.getYCord();
    }
    public Materials(String nm, String classe, Integer lvl, String xtra, ArrayList<String> mp, ArrayList<Integer> al, ArrayList<String> aa, ArrayList<Integer> xC, ArrayList<Integer> yC){
        name = nm;
        level = lvl;
        extra = xtra;
        classes = classe;
        map = mp;
        areaLvl = al;
        areas = aa;
        xCord = xC;
        yCord = yC;
    }
    
    public void getEverything(){
        Log.i("Material info", name + " | " + classes + " | " + level + " | " + extra + " | " + map.toString() + " | " + areaLvl.toString() + " | " + areas.toString() + " | " + 
    xCord.toString() + " | " + yCord.toString());
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String input){
        name = input;
    }
    public Integer getLevel(){
        return level;
    }
    public void setClasses(String input){
        classes = input;
    }
    public void setLevel(int input){
        level = input;
    }
    public String getClasses(){
        return classes;
    }
    public String getExtra(){
        return extra;
    }
    public void setExtra(String input){
        extra = input;
    }
    public ArrayList<String> getMap(){
        return map;
    }
    public void addMap(String input){
        map.add(input);
    }
    public ArrayList<String> getArea(){
        return areas;
    }
    public void addArea(String input){
        areas.add(input);
    }
    public ArrayList<Integer> getAreaLevel(){
        return areaLvl;
    }
    public void addAreaLvl(int input){
        areaLvl.add(input);
    }
    public ArrayList<Integer> getXCord(){
        return xCord;
    }
    public void addXCord(int input){
        xCord.add(input);
    }
    public ArrayList<Integer> getYCord(){
        return yCord;
    }
    public void addYCord(int input){
        yCord.add(input);
    }
    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(level);
        dest.writeString(extra);
        dest.writeString(classes);
        dest.writeStringArray(map.toArray(new String[map.size()]));
        int[] areaLvlArray = new int[areaLvl.size()];
        for (int i = 0; i < areaLvl.size(); i++){
            areaLvlArray[i] = areaLvl.get(i);
        }
        dest.writeIntArray(areaLvlArray);
        dest.writeStringArray(areas.toArray(new String[areas.size()]));
        int[] xCordArray = new int[xCord.size()];
        for (int i = 0; i < xCord.size(); i++){
            xCordArray[i] = xCord.get(i);
        }
        dest.writeIntArray(xCordArray);
        int[] yCordArray = new int[yCord.size()];
        for (int i = 0; i < yCord.size(); i++){
            yCordArray[i] = yCord.get(i);
        }
        dest.writeIntArray(yCordArray);
        
    }
    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Materials> CREATOR = new Parcelable.Creator<Materials>() {
        public Materials createFromParcel(Parcel in) {
            return new Materials(in);
        }

        public Materials[] newArray(int size) {
            return new Materials[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Materials(Parcel in) {
        String[] tempArray;
        int[] tempIntArray;

        name = in.readString();
        level = in.readInt();
        extra = in.readString();
        classes = in.readString();
        tempArray = in.createStringArray();
        for (int i = 0; i < tempArray.length; i++){
            map.add(tempArray[i]);
        }
        tempIntArray = in.createIntArray();
        for (int i = 0; i < tempIntArray.length; i++){
            areaLvl.add(tempIntArray[i]);
        }
        tempArray = in.createStringArray();
        for (int i = 0; i < tempArray.length; i++){
            areas.add(tempArray[i]);
        }
        tempIntArray = in.createIntArray();
        for (int i = 0; i < tempIntArray.length; i++){
            xCord.add(tempIntArray[i]);
        }
        tempIntArray = in.createIntArray();
        for (int i = 0; i < tempIntArray.length; i++){
            yCord.add(tempIntArray[i]);
        }
       
    }
}
