package com.Database;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Fish implements Parcelable{

    private String name;
    private String lvl;
    private String waterBody;
    private String req;
    private ArrayList<String> filv = new ArrayList<String>();
    private ArrayList<String> map = new ArrayList<String>();
    private ArrayList<String> area = new ArrayList<String>();
    private String xCord;
    private String yCord;
    
    //added in
    private String timeBegin;
    private String timeEnd;
    private ArrayList<String> weather = new ArrayList<String>();
    private ArrayList<String> weatherPer = new ArrayList<String>();
    private ArrayList<String> bait = new ArrayList<String>();
    private ArrayList<String> baitLvl = new ArrayList<String>();
    private ArrayList<String> typeOfBait = new ArrayList<String>();
    private ArrayList<String> desynthPer = new ArrayList<String>();
    private ArrayList<String> deSynth = new ArrayList<String>();
    

    public Fish(){
        
    }
    
    public Fish(Fish fish) {
        name = fish.name;
        lvl = fish.lvl;
        waterBody = fish.waterBody;
        req = fish.req;
        filv = fish.filv;
        map = fish.map;
        area = fish.area;
        xCord = fish.xCord;
        yCord = fish.yCord;
        
        //added in
        timeBegin = fish.timeBegin;
        timeEnd = fish.timeEnd;
        weather = fish.weather;
        weatherPer = fish.weatherPer;
        bait = fish.bait;
        baitLvl =  fish.baitLvl;
        typeOfBait = fish.typeOfBait;
        desynthPer = fish.desynthPer;
        deSynth =  fish.deSynth;
    }
    
    public void setName(String input){
        name = input;
    }
    
    public void setLvl(String input){
        lvl = input;
    }
    
    public void setWaterBody(String input){
        waterBody = input;
    }
    
    public void setReq(String input){
        req = input;
    }
    
    public void addArea(String input1, String input2, String input3){
        filv.add(input1);
        map.add(input2);
        area.add(input3);
    }
    
    public void addWeather(String input1, String input2){
        weather.add(input1);
        weatherPer.add(input2);
    }
    
    public void addBait(String input1, String input2, String input3){
        bait.add(input1);
        baitLvl.add(input2);
        typeOfBait.add(input3);
    }
    
    public void addDesynth(String input1, String input2){
        desynthPer.add(input1);
        deSynth.add(input2);
    }

    public String getName(){
        return name;
    }
    
    public String getLvl(){
        return lvl;
    }
    
    public String getWaterBody(){
        return waterBody;
    }
    
    public String getReq(){
        return req;
    }
    
    public int getSize(){
        return area.size();
    }
    
    public int getWeatherSize(){
        return weather.size();
    }
    public int getBaitSize(){
        return bait.size();
    }
    public ArrayList<String> getArea(int input){
        ArrayList<String> tempArray = new ArrayList<String>();
        tempArray.add(filv.get(input));
        tempArray.add(map.get(input));
        tempArray.add(area.get(input));
        return tempArray;
    }
    
    public ArrayList<String> getWeather(int input){
        ArrayList<String> tempArray = new ArrayList<String>();
        tempArray.add(weather.get(input));
        tempArray.add(weatherPer.get(input));
        return tempArray;
    }
    
    public ArrayList<String> getBait(int input){
        ArrayList<String> tempArray = new ArrayList<String>();
        tempArray.add(bait.get(input));
        tempArray.add(baitLvl.get(input));
        tempArray.add(typeOfBait.get(input));
        return tempArray;
    }
    
    public ArrayList<String> getDesynth(int input){
        ArrayList<String> tempArray = new ArrayList<String>();
        tempArray.add(desynthPer.get(input));
        tempArray.add(deSynth.get(input));
        return tempArray;
    }
    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }
    public void print(){
        Log.i("fish Print", name + " " + lvl  + " " +  waterBody + " area " + filv.toString() + " " + map.toString() + " " + area.toString()
                + " weather " + weather.toString() + " " + weatherPer.toString() + " bait " + bait.toString() + " " + baitLvl.toString() + " " + 
                typeOfBait.toString() + " desynth " + desynthPer.toString() + " " + deSynth.toString());
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(lvl);
        dest.writeString(waterBody);
        dest.writeString(req);
        dest.writeStringArray(filv.toArray(new String[filv.size()]));
        dest.writeStringArray(map.toArray(new String[map.size()]));
        dest.writeStringArray(area.toArray(new String[area.size()]));
        dest.writeString(xCord);
        dest.writeString(yCord);
        dest.writeString(timeBegin);
        dest.writeString(timeEnd);
        dest.writeStringArray(weather.toArray(new String[weather.size()]));
        dest.writeStringArray(weatherPer.toArray(new String[weatherPer.size()]));
        dest.writeStringArray(bait.toArray(new String[bait.size()]));
        dest.writeStringArray(baitLvl.toArray(new String[baitLvl.size()]));
        dest.writeStringArray(typeOfBait.toArray(new String[typeOfBait.size()]));
        dest.writeStringArray(desynthPer.toArray(new String[desynthPer.size()]));
        dest.writeStringArray(deSynth.toArray(new String[deSynth.size()]));
        
    }
    
    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Fish> CREATOR = new Parcelable.Creator<Fish>() {
        public Fish createFromParcel(Parcel in) {
            return new Fish(in);
        }

        public Fish[] newArray(int size) {
            return new Fish[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Fish(Parcel in) {
        String[] tempArray;
        this.name = in.readString();
        this.lvl = in.readString();
        this.waterBody = in.readString();
        this.req = in.readString();
        tempArray = in.createStringArray();
        for (int i = 0; i < tempArray.length; i++){
            filv.add(tempArray[i]);
        }
        tempArray = in.createStringArray();
        for (int i = 0; i < tempArray.length; i++){
            map.add(tempArray[i]);
        }
        tempArray = in.createStringArray();
        for (int i = 0; i < tempArray.length; i++){
            area.add(tempArray[i]);
        }
        this.xCord = in.readString();
        this.yCord = in.readString();
        this.timeBegin = in.readString();
        this.timeEnd = in.readString();
        tempArray = in.createStringArray();
        for (int i = 0; i < tempArray.length; i++){
            weather.add(tempArray[i]);
        }
        tempArray = in.createStringArray();
        for (int i = 0; i < tempArray.length; i++){
            weatherPer.add(tempArray[i]);
        }
        tempArray = in.createStringArray();
        for (int i = 0; i < tempArray.length; i++){
            bait.add(tempArray[i]);
        }
        tempArray = in.createStringArray();
        for (int i = 0; i < tempArray.length; i++){
            baitLvl.add(tempArray[i]);
        }
        tempArray = in.createStringArray();
        for (int i = 0; i < tempArray.length; i++){
            typeOfBait.add(tempArray[i]);
        }
        tempArray = in.createStringArray();
        for (int i = 0; i < tempArray.length; i++){
            desynthPer.add(tempArray[i]);
        }
        tempArray = in.createStringArray();
        for (int i = 0; i < tempArray.length; i++){
            deSynth.add(tempArray[i]);
        }
    }

}
