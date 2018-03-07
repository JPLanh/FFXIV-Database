package com.example.ffxivitemdatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.Database.Fish;
import com.Database.Items;
import com.Database.Materials;
import com.Database.RareFish;
import com.Database.Reagents;
import com.Database.itemHeirarchy;
import com.Database.itemSet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TableLayout;
import android.widget.TextView;

public class InfoActivity extends Activity {

    private TableLayout areaListLayout;
    private Materials materialGot;
    private Fish fishGot;
    private RareFish rareFishGot;
    private Reagents reagentsGot;
    private TextView materialName;
    private TextView materialLevel;
    private TextView materialExtra;
    private String searchType;
    private itemSet itemSets;
    
    ExpandableListAdapter listAdapter;
    ExpandableListView informationView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    
    private List<String> tab1 = new ArrayList<String>();
    private List<String> tab2 = new ArrayList<String>();
    private List<String> tab3 = new ArrayList<String>();
    private List<String> tab4 = new ArrayList<String>();
    private List<String> tab5 = new ArrayList<String>();
    private List<String> tab6 = new ArrayList<String>();
    private List<String> tab7 = new ArrayList<String>();
    private List<String> tab8 = new ArrayList<String>();
    private List<String> tab9 = new ArrayList<String>();
    private List<String> tab10 = new ArrayList<String>();
    private List<String> tab11 = new ArrayList<String>();
    private List<String> tab12 = new ArrayList<String>();
    private List<String> tab13 = new ArrayList<String>();
    private List<String> tab14 = new ArrayList<String>();
    private List<String> tab15 = new ArrayList<String>();
    private List<String> tab16 = new ArrayList<String>();
    private List<String> tab17 = new ArrayList<String>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent getItent = getIntent();
        //areaListLayout = (TableLayout) findViewById(R.id.AreaQueryTableLayout);
        //areaListLayout.removeAllViews();
        materialName = (TextView) findViewById(R.id.MaterialNameText);
        
        Button materialBackButton = (Button) findViewById(R.id.backButton);
        materialBackButton.setOnClickListener(backButtonListener);

        itemSets = getItent.getParcelableExtra("ItemSet");

//        if (itemSets.returnItemSearch() != null){ 
//            
//            materialGot = itemSets.getMaterialSet().get(itemSets.getMaterialSet().size()-1);
//            materialName.setText(materialGot.getName());
//
//            for (int matAt = 0; matAt < materialGot.getArea().size(); matAt++){
//                //This create a new spot, set the two buttons and put it in.
//                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                
//                View newMaterialView = inflater.inflate(R.layout.new_area_view,  null);
//                TextView newAreaText = (TextView) newMaterialView.findViewById(R.id.AreaText);
//                newAreaText.setText(materialGot.getArea().get(matAt));
//                TextView newAreaLevelText = (TextView) newMaterialView.findViewById(R.id.AreaLevelText);
//                newAreaLevelText.setText("(Node Lv. " + materialGot.getAreaLevel().get(matAt) + ")");
//                TextView newCordText = (TextView) newMaterialView.findViewById(R.id.CoordText);
//                newCordText.setText("(X: " + Integer.toString(materialGot.getXCord().get(matAt)) + ", Y: " +Integer.toString( materialGot.getYCord().get(matAt)) + ")");
//                TextView newMapText = (TextView) newMaterialView.findViewById(R.id.MapNameText);
//                newMapText.setText(materialGot.getMap().get(matAt));
//                
//            }
//        } 
//        if (itemSets.returnFishSearch() != null){
//            fishGot = new Fish( (Fish) getItent.getParcelableExtra("Item"));
//            materialName.setText(fishGot.getName() + " (Lv." + fishGot.getFishLvl() +")");
//            materialLevel.setText("Level Required: " + fishGot.getReq());
//            materialExtra.setText("Map: " + fishGot.getArea());
//            
//            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//            View newFishView = inflater.inflate(R.layout.new_area_view,  null);
//            TextView newAreaText = (TextView) newFishView.findViewById(R.id.AreaText);
//            newAreaText.setText(fishGot.getPoint() + "(X:"+fishGot.getxCord() +", Y:" + fishGot.getyCord() + ")");
//            TextView newAreaLevelText = (TextView) newFishView.findViewById(R.id.AreaLevelText);
//            newAreaLevelText.setText("");
//            TextView newCordText = (TextView) newFishView.findViewById(R.id.CoordText);
//            newCordText.setText("");
//            TextView newMapText = (TextView) newFishView.findViewById(R.id.MapNameText);
//            newMapText.setText("");
//                       
//            areaListLayout.addView(newFishView, 0);
//        } 
//        if (itemSets.returnReagentSearch() != null){   
//            Log.i("material case", "part 1");   
//            reagentsGot = itemSets.getReagentSet().get(itemSets.getReagentSet().size()-1);
//            materialName.setText(reagentsGot.getName());
//
//            materialGot = itemSets.getMaterialSet().get(itemSets.getMaterialSet().indexOf(reagentsGot.getName()));
//        }
        // preparing list data
        if (itemSets.returnMaterialSearch() != null) materialName.setText(itemSets.returnMaterialSearch().getName().replace('_', ' '));
        if (itemSets.returnReagentSearch() != null) materialName.setText(itemSets.returnReagentSearch().getName().replace('_', ' '));
        if (itemSets.returnFishSearch() != null) materialName.setText(itemSets.returnFishSearch().getName().replace('_', ' '));
        
       prepareListData(itemSets.returnMaterialSearch(), itemSets.returnFishSearch(), itemSets.returnReagentSearch());
        
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // get the listview
        informationView = (ExpandableListView) findViewById(R.id.infoListView);
        
        // setting list adapter
        informationView.setAdapter(listAdapter);
    }
    private void prepareListData(Materials materialGot, Fish fishGot, Reagents reagent) {
        

        //Initialize list
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        // Adding child data
        if (materialGot != null) {
           if (materialGot.getClasses().equals("MIN")){
                for (int i = 0 ; i < materialGot.getArea().size(); i++){
                    tab1.add(materialGot.getMap().get(i) + ": " + materialGot.getArea().get(i) + "\n[Node Lv. " + materialGot.getAreaLevel().get(i) + "] (X:" + materialGot.getXCord().get(i)
                            + ",Y:" + materialGot.getYCord().get(i) + ")");
                }
            }
            if (materialGot.getClasses().equals("BOT")){
                for (int i = 0 ; i < materialGot.getArea().size(); i++){
                    tab3.add(materialGot.getMap().get(i) + ": " + materialGot.getArea().get(i ) + "\n[Node Lv. " + materialGot.getAreaLevel().get(i) + "] (X:" + materialGot.getXCord().get(i)
                            + ",Y:" + materialGot.getYCord().get(i) + ")");
                }
            }
        }
        if (fishGot != null){
            for (int i = 0; i < fishGot.getSize(); i++){
                tab4.add(fishGot.getArea(i).get(1).replace('_', ' ') + ": " + fishGot.getArea(i).get(2).replace('_', ' ') + " [" + fishGot.getArea(i).get(0) + "]" );
            }
            for (int i = 0; i < fishGot.getWeatherSize(); i++){
                tab2.add(fishGot.getWeather(i).get(0).replace('_', ' ') + " [" + fishGot.getWeather(i).get(1) + "]");
            }
            for (int i = 0; i < fishGot.getBaitSize(); i++){
                tab17.add(fishGot.getBait(i).get(0).replace('_', ' ') + ": " + fishGot.getBait(i).get(1).replace('_', ' ') + " [" + fishGot.getBait(i).get(2) + "]");
            }
        }
        if (reagent != null){
            if (reagent.getSold().get(0).size() > 0){
                for (int i = 0 ; i < reagent.getSold().get(0).size(); i++){
                    tab5.add(reagent.getSold().get(3).get(i) + "\n" + reagent.getSold().get(0).get(i).toString() + ": " + 
                            reagent.getSold().get(1).get(i).toString() + " x" + reagent.getSold().get(2).get(i).toString());
                }
            }
            if (reagent.getTrade().get(0).size() > 0){
                for (int i = 0 ; i < reagent.getTrade().get(0).size(); i++){
                    tab6.add(reagent.getTrade().get(3).get(i) + "\n" + reagent.getTrade().get(0).get(i) + ": " + 
                            reagent.getTrade().get(1).get(i) + " x" + reagent.getTrade().get(2).get(i));
                }
            }
            if (reagent.getQuest().get(0).size() > 0){
                for (int i = 0 ; i < reagent.getQuest().get(0).size(); i++){
                    tab7.add(reagent.getQuest().get(0).get(i) + " (Lv." + reagent.getQuest().get(1).get(i) + ")");
                }
            }
            if (reagent.getMonster().get(0).size() > 0){
                for (int i = 0 ; i < reagent.getMonster().get(0).size(); i++){
                    tab8.add(reagent.getMonster().get(0).get(i) + " (Lv." + reagent.getMonster().get(1).get(i) + "): " +
                            reagent.getMonster().get(2).get(i));
                }
            }
            if (reagent.getExploration().get(0).size() > 0){
                for (int i = 0 ; i < reagent.getExploration().get(0).size(); i++){
                    tab9.add(reagent.getExploration().get(0).get(i) + " (Lv." + reagent.getExploration().get(1).get(i) + ")");
                }
            }
            if (reagent.getDesynth().get(0).size() > 0){
                for (int i = 0 ; i < reagent.getDesynth().get(0).size(); i++){
                    tab10.add(reagent.getDesynth().get(0).get(i) + " (" + reagent.getDesynth().get(1).get(i) + ")");
                }
            }
            if (reagent.getTreasure().get(0).size() > 0){
                for (int i = 0 ; i < reagent.getTreasure().get(0).size(); i++){
                    tab11.add(reagent.getTreasure().get(0).get(i) + " (" + reagent.getTreasure().get(1).get(i) + ")");
                }
            }
            if (reagent.getDuty().get(0).size() > 0){
                for (int i = 0 ; i < reagent.getDuty().get(0).size(); i++){
                    tab12.add(reagent.getDuty().get(0).get(i));
                }
            }
            if (reagent.getFate().get(0).size() > 0){
                for (int i = 0 ; i < reagent.getFate().get(0).size(); i++){
                    tab13.add(reagent.getFate().get(0).get(i) + " (Lv." + reagent.getFate().get(1).get(i) + "): " +
                            reagent.getFate().get(2).get(i));
                }
            }
            if (reagent.getLeve().get(0).size() > 0){
                for (int i = 0 ; i < reagent.getLeve().get(0).size(); i++){
                    tab14.add(reagent.getLeve().get(0).get(i) + " (Lv." + reagent.getLeve().get(1).get(i) + ")");
                }
            }
            if (reagent.getAirship().get(0).size() > 0){
                for (int i = 0 ; i < reagent.getAirship().get(0).size(); i++){
                    tab15.add(reagent.getAirship().get(0).get(i) + " (" + reagent.getAirship().get(1).get(i) + ")");
                }
            }
            if (reagent.getGardening().get(0).size() > 0){
                for (int i = 0 ; i < reagent.getGardening().get(0).size(); i++){
                    tab16.add(reagent.getGardening().get(0).get(i));
                }
            }
        }

        //Create the lists
        int count = 0;
        //La Noscea
        if (!tab1.isEmpty()) {
            listDataHeader.add("Mining");
            listDataChild.put(listDataHeader.get(count), tab1);
            count++;
        }
        if (!tab3.isEmpty()){
            listDataHeader.add("Harvest");
            listDataChild.put(listDataHeader.get(count), tab3);
            count++;
        }   
        if (!tab4.isEmpty()){
            listDataHeader.add("Fishing");
            listDataChild.put(listDataHeader.get(count), tab4);
            count++;
        }   
        if (!tab2.isEmpty()){
            listDataHeader.add("Weather");
            listDataChild.put(listDataHeader.get(count), tab2);
            count++;
        }
        if (!tab17.isEmpty()){
            listDataHeader.add("Bait");
            listDataChild.put(listDataHeader.get(count), tab17);
        }
        if (!tab5.isEmpty()){
            listDataHeader.add("Sold");
            listDataChild.put(listDataHeader.get(count), tab5);
            count++;
        }   
        if (!tab6.isEmpty()){
            listDataHeader.add("Traded");
            listDataChild.put(listDataHeader.get(count), tab6);
            count++;
        }   
        if (!tab7.isEmpty()){
            listDataHeader.add("Quests");
            listDataChild.put(listDataHeader.get(count), tab7);
            count++;
        }   
        if (!tab8.isEmpty()){
            listDataHeader.add("Drops");
            listDataChild.put(listDataHeader.get(count), tab8);
            count++;
        }   
        if (!tab9.isEmpty()){
            listDataHeader.add("Ventures");
            listDataChild.put(listDataHeader.get(count), tab9);
            count++;
        }   
        if (!tab10.isEmpty()){
            listDataHeader.add("Densythesis");
            listDataChild.put(listDataHeader.get(count), tab10);
            count++;
        }   
        if (!tab11.isEmpty()){
            listDataHeader.add("Treasure");
            listDataChild.put(listDataHeader.get(count), tab11);
            count++;
        }   
        if (!tab12.isEmpty()){
            listDataHeader.add("Duty");
            listDataChild.put(listDataHeader.get(count), tab12);
            count++;
        }   
        if (!tab13.isEmpty()){
            listDataHeader.add("Fate");
            listDataChild.put(listDataHeader.get(count), tab13);
            count++;
        }   
        if (!tab14.isEmpty()){
            listDataHeader.add("Leve");
            listDataChild.put(listDataHeader.get(count), tab14);
            count++;
        }   
        if (!tab15.isEmpty()){
            listDataHeader.add("Airship");
            listDataChild.put(listDataHeader.get(count), tab15);
            count++;
        }   
        if (!tab16.isEmpty()){
            listDataHeader.add("Gardening");
            listDataChild.put(listDataHeader.get(count), tab16);
            count++;
        }   
    }
    public OnClickListener backButtonListener = new OnClickListener(){

        @Override
        public void onClick(View v) {
            if (itemSets.materialSearchTest()) itemSets.decreaseMaterialHeir();
            if (itemSets.reagentSearchTest()) itemSets.decreaseReagentHeir();
            Intent getMaterial = new Intent(v.getContext(), MainActivity.class);
            if (itemSets.getItemSet().size() > 0){
                getMaterial.putExtra("ItemType", searchType);
                getMaterial.putExtra("ItemSet", itemSets);
                setResult(4, getMaterial);
            } else {
                getMaterial.putExtra("ItemSet", itemSets);
                setResult(2, getMaterial);
            }
            InfoActivity.this.finish(); 
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed()
    {

       // super.onBackPressed(); // Comment this super call to avoid calling finish()
    }
    public String timeCalc(int time){
        if (time > 12){
            int timeRemain = time - 12;     
            return timeRemain + " PM";
        } else {
            return time + " AM";
        }
    }
}
