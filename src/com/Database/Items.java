package com.Database;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;


public class Items implements Parcelable {

	private String recipe;
	private int level;
	private int amount;
	private int difficulty;
	private int durability;
	private int max_Quality;
	private ArrayList<String> materials = new ArrayList<String>();
    private ArrayList<Integer> matAmount = new ArrayList<Integer>();

	
	public Items(String rec, int lvl, int amt, int dif, int dur, int qua, ArrayList<String> mat, ArrayList<Integer> matA){
	    recipe = rec;
	    level = lvl;
	    difficulty = dif;
        durability = dur;
        amount = amt;
        max_Quality = qua;
        materials = mat;
        matAmount = matA;
	}
	
	public Items(Items item){
	    this.recipe = item.getRecipe();
	    this.level = item.getLevel();
	    this.difficulty = item.getDifficulty();
	    this.durability = item.getDurability();
	    this.amount = item.getAmount();
	    this.max_Quality = item.getQuality();
	    this.materials = item.getMatArray();
	    this.matAmount = item.getMatAmountArray();
	}
	
	public Items(){
	    this.recipe = null;
	    this.level = 0;
	    this.difficulty = 0;
	    this.durability = 0;
	    this.amount = 0;
	    this.max_Quality = 0;
	    this.materials = null;
	    this.matAmount = null;
	}
	public String getEverything2(){
        String everything = recipe + " / " + level + " / " + amount + " / " + difficulty + " / " + durability + " / " + 
                max_Quality + " / " + materials.toString() + " / " + matAmount.toString();
        return everything;
    }
	
	public void setRecipe(String set){
	    recipe = set;
	}
	
    public void setLevel(int set){
        level = set;
    }

    public void setAmount(int set){
        amount = set;
    }

    public void setDifficulty(int set){
        difficulty = set;
    }

    public void setDurability(int set){
        durability = set;
    }

    public void setQuality(int set){
        max_Quality = set;
    }
    
    public void setMaterials(ArrayList<String> set){
        materials = set;
    }

    public void setMatAmount(ArrayList<Integer> set){
        matAmount = set;
    }
	public String getRecipe(){
	    return recipe;
	}
	public int getLevel(){
	    return level;
	}
	public String getMaterial(int i){
	    return materials.get(i);
	}
	public int getDifficulty(){
	    return difficulty;
	}
	public int getDurability(){
	    return durability;
	}
	public int getAmount(){
	    return amount;
	}
	public int getQuality(){
	    return max_Quality;
	}
	public ArrayList<String> getMatArray(){
	    return materials;
	}
	public ArrayList<Integer> getMatAmountArray(){
	    return matAmount;
	}
	
    public int getMatAmount(int i){
        return matAmount.get(i);
    }
    
    public void removeMat(){
        materials.remove(0);
        matAmount.remove(0);
    }

    public boolean isEmpty(){
        if (materials.isEmpty()) {
            return true;
        }
        else return false;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        int[] matAmountArray = new int[matAmount.size()];
        for (int i = 0; i < matAmount.size(); i++){
            matAmountArray[i] = matAmount.get(i);
        }
        dest.writeString(recipe);
        dest.writeInt(level);
        dest.writeInt(amount);
        dest.writeInt(difficulty);
        dest.writeInt(durability);
        dest.writeInt(max_Quality);
        dest.writeStringArray(materials.toArray(new String[materials.size()]));
        dest.writeIntArray(matAmountArray);
    }
    
    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Items> CREATOR = new Parcelable.Creator<Items>() {
        public Items createFromParcel(Parcel in) {
            return new Items(in);
        }

        public Items[] newArray(int size) {
            return new Items[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Items(Parcel in) {
        String[] tempArray;
        int[] tempIntArray;
        this.recipe = in.readString();
        this.level = in.readInt();
        this.difficulty = in.readInt();
        this.durability = in.readInt();
        this.amount = in.readInt();
        this.max_Quality = in.readInt();
        tempArray = in.createStringArray();
        for (int i = 0; i < tempArray.length; i++){
            materials.add(tempArray[i]);
        }
        tempIntArray = in.createIntArray();
        for (int i = 0; i < tempIntArray.length; i++){
            matAmount.add(tempIntArray[i]);
        }
    }
}
