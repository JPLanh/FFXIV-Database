package com.Database;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.Database.Items;

public class itemHeirarchy implements Parcelable{

    private List<Items> listOfItems = new ArrayList<Items>();
    
    public itemHeirarchy(){
        
    }
    
    public itemHeirarchy(itemHeirarchy newList){
        listOfItems = newList.getHeirarchy();
    }
    
    public List<Items> getHeirarchy(){
        return listOfItems;
    }
    
    public void removeLastItem(){
        listOfItems.remove(listOfItems.size()-1);
    }
    public Items getLastItem(){
        return listOfItems.get(listOfItems.size()-1);
    }
    
    public void addHeirarchy(Items object){
        listOfItems.add(object);
    }
    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(listOfItems);
        
    }
    
    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<itemHeirarchy> CREATOR = new Parcelable.Creator<itemHeirarchy>() {
        public itemHeirarchy createFromParcel(Parcel in) {
            return new itemHeirarchy(in);
        }

        public itemHeirarchy[] newArray(int size) {
            return new itemHeirarchy[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private itemHeirarchy(Parcel in) {
        this.listOfItems = in.readArrayList(Items.class.getClassLoader());
    }

}
