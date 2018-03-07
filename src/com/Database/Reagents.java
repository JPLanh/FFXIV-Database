package com.Database;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Reagents implements Parcelable {

    String name;
    
    //Sold Info
    private List<String> soldName = new ArrayList<String>();
    private List<String> soldPrice = new ArrayList<String>();
    private List<String> soldLocation = new ArrayList<String>();
    private List<String> soldItem = new ArrayList<String>();
    
    //Trade Info
    private List<String> tradeName = new ArrayList<String>();
    private List<String> tradePrice = new ArrayList<String>();
    private List<String> tradeLocation = new ArrayList<String>();
    private List<String> tradeItem = new ArrayList<String>();
    
    //Quest Info
    private List<String> questName = new ArrayList<String>();
    private List<String> questLevel = new ArrayList<String>();
        
    //Dropped By Info
    private List<String> monsterName = new ArrayList<String>();
    private List<String> monsterLevel = new ArrayList<String>();
    private List<String> monsterLocation = new ArrayList<String>();
    
    //Venture info
    private List<String> explorationName = new ArrayList<String>();
    private List<String> explorationLevel = new ArrayList<String>();
 
    //Desync Info
    private List<String> desynName = new ArrayList<String>();
    private List<String> desynSkill = new ArrayList<String>();
    
    //Treasure Info
    private List<String> treasureName = new ArrayList<String>();
    private List<String> teasureDecipher = new ArrayList<String>();
    
    //Duty Info
    private List<String> dutyName = new ArrayList<String>();
    
    //Fate info
    private List<String> fateName = new ArrayList<String>();
    private List<String> fateLocation = new ArrayList<String>();
    private List<String> fateLevel = new ArrayList<String>();
    
    //LeveQuest info
    private List<String> leveName = new ArrayList<String>();
    private List<String> leveLevel = new ArrayList<String>();
    
    //Airship info
    private List<String> airshipDestination = new ArrayList<String>();
    private List<String> airshipQuantity = new ArrayList<String>();
    
    //Gardening Info
    private List<String> gardening = new ArrayList<String>();

    public Reagents(){
    }
    public Reagents(Reagents re){
        name = re.getName();

        //Sold Info
        soldName = re.getSold().get(0);
        soldItem = re.getSold().get(1);
        soldPrice = re.getSold().get(2);
        soldLocation = re.getSold().get(3);
        //Trade Info
        tradeName = re.getTrade().get(0);
        tradeItem = re.getTrade().get(1);
        tradePrice = re.getTrade().get(2);
        tradeLocation = re.getTrade().get(3);

        //Quest Info
        questName = re.getQuest().get(0);
        questLevel = re.getQuest().get(1);
        
        //Dropped By Info
        monsterName = re.getMonster().get(0);
        monsterLevel = re.getMonster().get(1);
        monsterLocation = re.getMonster().get(2);
        
        //Venture info
        explorationName = re.getExploration().get(0);
        explorationLevel = re.getExploration().get(1);
     
        //Desync Info
        desynName = re.getDesynth().get(0);
        desynSkill = re.getDesynth().get(1);
        
        //Treasure Info
        treasureName = re.getTreasure().get(0);
        teasureDecipher = re.getTreasure().get(1);
        
        //Duty Info
        dutyName = re.getDuty().get(0);
        
        //Fate info
        fateName = re.getFate().get(0);
        fateLocation = re.getFate().get(1);
        fateLevel = re.getFate().get(2);
        
        //LeveQuest info
        leveName = re.getLeve().get(0);
        leveLevel = re.getLeve().get(1);
        
        //Airship info
        airshipDestination = re.getAirship().get(0);
        airshipQuantity = re.getAirship().get(1);
        
        //Gardening
        gardening = re.getGardening().get(0);
        
    }
    public void setName(String nameAdd){
        name = nameAdd;
    }
    public void addSold(String name, String item, String location, String price){
        soldName.add(name);
        soldPrice.add(price);
        soldLocation.add(location);
        soldItem.add(item);
    }
    
    public void addTrade(String name, String item, String location, String price){
        tradeName.add(name);
        tradePrice.add(price);
        tradeLocation.add(location);
        tradeItem.add(item);
    }
    
    public void addQuest(String name, String level){
        questName.add(name);
        questLevel.add(level);
    }
    
    public void addMonster(String name, String location, String level){
        monsterName.add(name);
        monsterLevel.add(level);
        monsterLocation.add(location);
    }
    
    public void addExploration(String name, String level){
        explorationName.add(name);
        explorationLevel.add(level);
    }
 
    public void addDesynth(String name, String skill){
        desynName.add(name);
        desynSkill.add(skill);
    }
    
    public void addTreasure(String name, String decipher){
        treasureName.add(name);
        teasureDecipher.add(decipher);
    }
    
    public void addDuty(String name){
        dutyName.add(name);
    }
    
    public void addFate(String name, String location, String level){
        fateName.add(name);
        fateLocation.add(location);
        fateLevel.add(level);
    }
    
    public void addLeve(String name, String level){
        leveName.add(name);
        leveLevel.add(level);
    }
    
    public void addAirship(String destination, String quantity){
        airshipDestination.add(destination);
        airshipQuantity.add(quantity);
    }


    public void addGardening(String name){
        gardening.add(name);
    }
    public String getName(){
        return name;
    }
    public List<List<String>> getMonster(){
        List<List<String>> tempList = new ArrayList<List<String>>();
        tempList.add(monsterName);
        tempList.add(monsterLevel);
        tempList.add(monsterLocation);
        return tempList;
    }
    public List<List<String>> getQuest(){
        List<List<String>> tempList = new ArrayList<List<String>>();
        tempList.add(questName);
        tempList.add(questLevel);
        return tempList;
    }
    public List<List<String>> getExploration(){
        List<List<String>> tempList = new ArrayList<List<String>>();
        tempList.add(explorationName);
        tempList.add(explorationLevel);
        return tempList;
    }
    public List<List<String>> getDesynth(){
        List<List<String>> tempList = new ArrayList<List<String>>();
        tempList.add(desynName);
        tempList.add(desynSkill);
        return tempList;
    }
    public List<List<String>> getTreasure(){
        List<List<String>> tempList = new ArrayList<List<String>>();
        tempList.add(treasureName);
        tempList.add(teasureDecipher);
        return tempList;
    }
    public List<List<String>> getDuty(){
        List<List<String>> tempList = new ArrayList<List<String>>();
        tempList.add(dutyName);
        return tempList;
    }
    public List<List<String>> getTrade(){
        List<List<String>> tempList = new ArrayList<List<String>>();
        tempList.add(tradeName);
        tempList.add(tradeItem);
        tempList.add(tradePrice);
        tempList.add(tradeLocation);
        return tempList;
    }
    public List<List<String>> getSold(){
        List<List<String>> tempList = new ArrayList<List<String>>();
        tempList.add(soldName);
        tempList.add(soldItem);
        tempList.add(soldPrice);
        tempList.add(soldLocation);
        return tempList;
    }
    public List<List<String>> getFate(){
        List<List<String>> tempList = new ArrayList<List<String>>();
        tempList.add(fateName);
        tempList.add(fateLevel);
        tempList.add(fateLocation);
        return tempList;
    }
    public List<List<String>> getLeve(){
        List<List<String>> tempList = new ArrayList<List<String>>();
        tempList.add(leveName);
        tempList.add(leveLevel);
        return tempList;
    }
    public List<List<String>> getAirship(){
        List<List<String>> tempList = new ArrayList<List<String>>();
        tempList.add(airshipDestination);
        tempList.add(airshipQuantity);
        return tempList;
    }
    public List<List<String>> getGardening(){
        List<List<String>> tempList = new ArrayList<List<String>>();
        tempList.add(gardening);
        return tempList;
    }
    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        
        if (soldName.size() > 0){
            dest.writeString("sold");
            dest.writeStringArray(soldName.toArray(new String[soldName.size()]));
            dest.writeStringArray(soldPrice.toArray(new String[soldPrice.size()]));
            dest.writeStringArray(soldLocation.toArray(new String[soldLocation.size()]));
            dest.writeStringArray(soldItem.toArray(new String[soldItem.size()]));
        }
        

        if (tradeName.size() > 0){
            dest.writeString("trade");
            dest.writeStringArray(tradeName.toArray(new String[tradeName.size()]));
            dest.writeStringArray(tradePrice.toArray(new String[tradePrice.size()]));
            dest.writeStringArray(tradeLocation.toArray(new String[tradeLocation.size()]));
            dest.writeStringArray(tradeItem.toArray(new String[tradeItem.size()]));
        }
        
        if (questName.size() > 0){
            dest.writeString("quest");
            dest.writeStringArray(questName.toArray(new String[questName.size()]));
            dest.writeStringArray(questLevel.toArray(new String[questLevel.size()]));
        }
        

        if (monsterName.size() > 0){
            dest.writeString("drop");
            dest.writeStringArray(monsterName.toArray(new String[monsterName.size()]));
            dest.writeStringArray(monsterLevel.toArray(new String[monsterLevel.size()]));
            dest.writeStringArray(monsterLocation.toArray(new String[monsterLocation.size()]));
        }

        if (explorationName.size() > 0){
            dest.writeString("explore");
            dest.writeStringArray(explorationName.toArray(new String[explorationName.size()]));
            dest.writeStringArray(explorationLevel.toArray(new String[explorationLevel.size()]));
        }

        if (desynName.size() > 0){
            dest.writeString("desyn");
            dest.writeStringArray(desynName.toArray(new String[desynName.size()]));
            dest.writeStringArray(desynSkill.toArray(new String[desynSkill.size()]));
        }

        if (treasureName.size() > 0){
            dest.writeString("treasure");
            dest.writeStringArray(treasureName.toArray(new String[treasureName.size()]));
            dest.writeStringArray(teasureDecipher.toArray(new String[teasureDecipher.size()]));
        }

        if (dutyName.size() > 0){
            dest.writeString("duty");
            dest.writeStringArray(dutyName.toArray(new String[dutyName.size()]));
        }

        if (fateName.size() > 0){
            dest.writeString("fate");
            dest.writeStringArray(fateName.toArray(new String[fateName.size()]));
            dest.writeStringArray(fateLocation.toArray(new String[fateLocation.size()]));
            dest.writeStringArray(fateLevel.toArray(new String[fateLevel.size()]));
        }

        if (leveName.size() > 0){
            dest.writeString("leve");
            dest.writeStringArray(leveName.toArray(new String[leveName.size()]));
            dest.writeStringArray(leveLevel.toArray(new String[leveLevel.size()]));
        }

        if (airshipDestination.size() > 0){
            dest.writeString("airship");
            dest.writeStringArray(airshipDestination.toArray(new String[airshipDestination.size()]));
            dest.writeStringArray(airshipQuantity.toArray(new String[airshipQuantity.size()]));
        }

        if (gardening.size() > 0){
            dest.writeString("garden");
            dest.writeStringArray(gardening.toArray(new String[gardening.size()]));
        }
        dest.writeString("end");
    }
    
    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Reagents> CREATOR = new Parcelable.Creator<Reagents>() {
        public Reagents createFromParcel(Parcel in) {
            return new Reagents(in);
        }

        public Reagents[] newArray(int size) {
            return new Reagents[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Reagents(Parcel in) {
        String[] tempArray;
        String parcelableRead;
        name = in.readString();

        parcelableRead = in.readString();
        while (!parcelableRead.equals("end")){
            if (parcelableRead.equals("sold")){
                Log.i("pracelableRead", "sold");
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    soldName.add(tempArray[i]);
                }
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    soldPrice.add(tempArray[i]);
                }
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    soldLocation.add(tempArray[i]);
                }
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    soldItem.add(tempArray[i]);
                }
            } else if (parcelableRead.equals("trade")){
                Log.i("pracelableRead", "trade");
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    tradeName.add(tempArray[i]);
                }
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    tradePrice.add(tempArray[i]);
                }
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    tradeLocation.add(tempArray[i]);
                }
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    tradeItem.add(tempArray[i]);
                }
            } else if (parcelableRead.equals("quest")){
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    questName.add(tempArray[i]);
                }
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    questLevel.add(tempArray[i]);
                }
                Log.i("pracelableRead", "quest done");
            } else if (parcelableRead.equals("drop")){
                Log.i("pracelableRead", "drop");
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    monsterName.add(tempArray[i]);
                }
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    monsterLevel.add(tempArray[i]);
                }
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    monsterLocation.add(tempArray[i]);
                }
            } else if (parcelableRead.equals("explore")){
                Log.i("pracelableRead", "explore");
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    explorationName.add(tempArray[i]);
                }
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    explorationLevel.add(tempArray[i]);
                }
            } else if (parcelableRead.equals("desyn")){
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    desynName.add(tempArray[i]);
                }
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    desynSkill.add(tempArray[i]);
                }
            } else if (parcelableRead.equals("treasure")){
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    treasureName.add(tempArray[i]);
                }
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    teasureDecipher.add(tempArray[i]);
                }
            } else if (parcelableRead.equals("duty")){
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    dutyName.add(tempArray[i]);
                }
            } else if (parcelableRead.equals("fate")){
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    fateName.add(tempArray[i]);
                }
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    fateLocation.add(tempArray[i]);
                }
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    fateLevel.add(tempArray[i]);
                }
            } else if (parcelableRead.equals("leve")){
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    leveName.add(tempArray[i]);
                }
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    leveLevel.add(tempArray[i]);
                }
            } else if (parcelableRead.equals("airship")){
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    airshipDestination.add(tempArray[i]);
                }
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    airshipQuantity.add(tempArray[i]);
                }
            } else if (parcelableRead.equals("garden")){
                tempArray = in.createStringArray();
                for (int i = 0; i < tempArray.length; i++){
                    gardening.add(tempArray[i]);
                }
            }
            Log.i("pracelableRead", "parcelable done");
            parcelableRead = in.readString();
        }
    }

}
