package com.Database;
import java.io.IOException;

import android.os.Parcel;
import android.os.Parcelable;


public class RareFish implements Parcelable {

    String fishName;
    String area;
    String hole;
    String bait;
    String weather;
    int iLvl;
    String min;
    int timeBegin;
    int timeEnd;
    
    public RareFish(){
        
    }
    public RareFish(RareFish object){
        fishName = object.fishName;
        area = object.area;
        hole = object.hole;
        bait  = object.bait;
        weather = object.weather;
        iLvl  = object.iLvl;
        min = object.min;
        timeBegin = object.timeBegin;
        timeEnd = object.timeEnd;
    }
    public RareFish(String nm, String aa, String ho, String ba, 
            String wa, int il, String mi, int tb, int te) throws IOException{
        fishName = nm;
        area = aa;
        hole = ho;
        bait = ba;
        weather = wa;
        iLvl = il;
        min = mi;
        timeBegin = tb;
        timeEnd = te;
    }
    public RareFish setTime(int begin, int end){
        timeBegin = begin;
        timeEnd = end;
        return this;
    }
    public String getEverything(){
        String returnString = fishName + " " + area + " " + hole + " " + bait + " " +
    weather + " " + iLvl + " " + min + " " + timeBegin + " " + timeEnd;
        return returnString;
    }
    
    public String getName(){
        return fishName;
    }
    public String getArea(){
        return area;
    }
    public String getHole(){
        return hole;
    }
    public String getBait(){
        return bait;
    }
    public String getWeather(){
        return weather;
    }
    public int getILvl(){
        return iLvl;
    }
    public String getMin(){
        return min;
    }
    public int getTimeBegin(){
        return timeBegin;
    }
    public int getTimeEnd(){
        return timeEnd;
    }
    

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fishName);
        dest.writeString(area);
        dest.writeString(hole);
        dest.writeString(bait);
        dest.writeString(weather);
        dest.writeInt(iLvl);
        dest.writeString(min);
        dest.writeInt(timeBegin);
        dest.writeInt(timeEnd);
    }
    
    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<RareFish> CREATOR = new Parcelable.Creator<RareFish>() {
        public RareFish createFromParcel(Parcel in) {
            return new RareFish(in);
        }

        public RareFish[] newArray(int size) {
            return new RareFish[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private RareFish(Parcel in) {
        this.fishName = in.readString();
        this.area = in.readString();
        this.hole = in.readString();
        this.bait = in.readString();
        this.weather = in.readString();
        this.iLvl = in.readInt();
        this.min = in.readString();
        this.timeBegin = in.readInt();
        this.timeEnd = in.readInt();
        
    }
    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }
}

