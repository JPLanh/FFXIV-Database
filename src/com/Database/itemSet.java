package com.Database;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class itemSet implements Parcelable{
    
    private List<Items> itemSearch = new ArrayList<Items>();
    private List<Materials> materialSearch = new ArrayList<Materials>();
    private List<Reagents> reagentSearch = new ArrayList<Reagents>();
    private List<Fish> fishSearch = new ArrayList<Fish>();
    private List<Items> setOfItems = new ArrayList<Items>();
    private List<Integer> setOfItemsAmount = new ArrayList<Integer>();
    
    private List<Materials> setOfMaterials = new ArrayList<Materials>();
    private List<Integer> setOfMaterialsAmount = new ArrayList<Integer>();
    
    private List<Fish> setOfFishs = new ArrayList<Fish>();
    private List<Integer> setOfFishsAmount = new ArrayList<Integer>();
    
    private List<Reagents> setOfReagents = new ArrayList<Reagents>();
    private List<Integer> setOfReagentsAmount = new ArrayList<Integer>();

    private List<String> listOfMap = new ArrayList<String>();
    
    public itemSet(){
        
    }

    public void searchItem(Items object){
        while (itemSearch.size() > 0) itemSearch.remove(0);
        itemSearch.add(object);
    }
    public void searchMaterial(Materials object){
        while (materialSearch.size() > 0) materialSearch.remove(0);
        materialSearch.add(object);
    }
    public void searchFish(Fish object){
        while (fishSearch.size() > 0) fishSearch.remove(0);
        fishSearch.add(object);
    }
    public void searchReagent(Reagents object){
        while (reagentSearch.size() > 0) reagentSearch.remove(0);
        reagentSearch.add(object);
    }
    public Items returnItemSearch(){
        if (itemSearch != null) return itemSearch.get(0);
        else return null;
    }
    public boolean itemSearchTest(){
        if (itemSearch.size() > 0) return true;
        else return false;
    }
    public boolean materialSearchTest(){
        if (materialSearch.size() > 0) return true;
        else return false;
    }
    public boolean reagentSearchTest(){
        if (reagentSearch.size() > 0) return true;
        else return false;
    }
    public void decreaseItemHeir(){
        itemSearch.remove(itemSearch.size()-1);
    }
    public void decreaseMaterialHeir(){
        materialSearch.remove(materialSearch.size()-1);
    }
    public void decreaseReagentHeir(){
        reagentSearch.remove(reagentSearch.size()-1);
    }
    public void decreaseFishHeir(){
        fishSearch.remove(fishSearch.size()-1);
    }
    public void decraseItem(){
        setOfItems.remove(setOfItems.size()-1);
    }
    public void clearSet(){
         itemSearch = new ArrayList<Items>();
         materialSearch = new ArrayList<Materials>();
         reagentSearch = new ArrayList<Reagents>();
         fishSearch = new ArrayList<Fish>();
         setOfItems = new ArrayList<Items>();
         setOfItemsAmount = new ArrayList<Integer>();
        
         setOfMaterials = new ArrayList<Materials>();
         setOfMaterialsAmount = new ArrayList<Integer>();
        
         setOfFishs = new ArrayList<Fish>();
         setOfFishsAmount = new ArrayList<Integer>();
        
         setOfReagents = new ArrayList<Reagents>();
         setOfReagentsAmount = new ArrayList<Integer>();
    }
    public Reagents returnReagentSearch(){
        if (reagentSearch.size() > 0) return reagentSearch.get(0);
        else return null;
    }
    public Fish returnFishSearch(){
        if (fishSearch.size() > 0) return fishSearch.get(0);
        else return null;
    }
    public Materials returnMaterialSearch(){
        if (materialSearch.size() > 0) return materialSearch.get(0);
        else return null;
    }
    public void addItem(Items object){
        boolean flag = false;
        for (int i = 0; i < setOfItems.size(); i++){
            if (setOfItems.get(i).getRecipe().equals(object.getRecipe())){
                setOfItemsAmount.add(i, setOfItemsAmount.get(i) + 1);
                setOfItemsAmount.remove(i+1);
                flag = true;
            }
        }
        if (!flag){
            setOfItems.add(object);
            setOfItemsAmount.add(1);
        }
    }
    public void addMaterial(Materials object){
        boolean flag = false;
        for (int i = 0; i < setOfMaterials.size() ; i++){
            if (setOfMaterials.get(i).getName().equals(object.getName())){
                setOfMaterialsAmount.add(i, setOfMaterialsAmount.get(i) + 1);
                setOfMaterialsAmount.remove(i+1);
                flag = true;
            }
        }
        if (!flag){
            setOfMaterials.add(object);
            setOfMaterialsAmount.add(1);
        }
    }
    public void addReagents(Reagents object){
        boolean flag = false;
        for (int i = 0; i < setOfReagents.size(); i++){
            if (setOfReagents.get(i).getName().equals(object.getName())){
                setOfReagentsAmount.add(i, setOfReagentsAmount.get(i) + 1);
                setOfReagentsAmount.remove(i+1);
                flag = true;
            }
        }
        if (!flag){
            setOfReagents.add(object);
            setOfReagentsAmount.add(1);
        }
    }
    public void addFish(Fish object){
        setOfFishs.add(object);
    }
    
    public List<String> getMapList(){
        return listOfMap;
    }
    public void removeItem(){
        setOfItems.remove(setOfItems.size()-1);
    }
    public List<Items> getItemSet(){
        return setOfItems;
    }
    
    public List<Materials> getMaterialSet(){
        return setOfMaterials;
    }
    public List<Reagents> getReagentSet(){
        return setOfReagents;
    }
    public List<Integer> getSetAmount(String set){
        if (set == "Materials") {
            return setOfMaterialsAmount;
        } else if (set == "Reagent"){
            return setOfReagentsAmount;
        }
        return null;
    }
    
    public List<Fish> getFishSet(){
        return setOfFishs;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(setOfItems);
        dest.writeList(setOfMaterials);
        dest.writeList(setOfFishs);
        dest.writeList(listOfMap);
        dest.writeList(setOfMaterialsAmount);
        dest.writeList(setOfReagents);
        dest.writeList(setOfReagentsAmount);
        dest.writeList(materialSearch);
        dest.writeList(reagentSearch);
        dest.writeList(fishSearch);
        dest.writeList(itemSearch);
    }
    
    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<itemSet> CREATOR = new Parcelable.Creator<itemSet>() {
        public itemSet createFromParcel(Parcel in) {
            return new itemSet(in);
        }

        public itemSet[] newArray(int size) {
            return new itemSet[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    @SuppressWarnings("unchecked")
    private itemSet(Parcel in) {
        this.setOfItems = in.readArrayList(Items.class.getClassLoader());
        this.setOfMaterials = in.readArrayList(Materials.class.getClassLoader());
        this.setOfFishs = in.readArrayList(Fish.class.getClassLoader());
        in.readList(listOfMap, null);
        in.readList(setOfMaterialsAmount, null);
        this.setOfReagents = in.readArrayList(Reagents.class.getClassLoader());
        in.readList(setOfReagentsAmount, null);
        this.materialSearch = in.readArrayList(Materials.class.getClassLoader());
        this.reagentSearch = in.readArrayList(Reagents.class.getClassLoader());
        this.fishSearch = in.readArrayList(Fish.class.getClassLoader());
        this.itemSearch = in.readArrayList(Items.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

}
