package com.example.ffxivitemdatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.Database.Materials;
import com.Database.itemSet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class GathererActivity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private itemSet itemList = new itemSet();
    private List<Materials> setOfMaterials = new ArrayList<Materials>();
    private List<Integer> setOfMaterialsAmount = new ArrayList<Integer>();
    private TableLayout materialQueryTable;
    private List<String> mapList = new ArrayList<String>();
    private List<String> areaList = new ArrayList<String>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gatherer);

        Intent getItent = getIntent();
        
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(backButtonListener);

        itemList = getItent.getParcelableExtra("ItemSet");
        setOfMaterials = itemList.getMaterialSet();
        setOfMaterialsAmount = itemList.getSetAmount("Material");
        materialQueryTable = (TableLayout) findViewById(R.id.materialLayout);

        for (int x = 0; x < setOfMaterials.size(); x++){
            if (areaList.isEmpty()){
                for (int i = 0; i < setOfMaterials.get(x).getArea().size(); i++){
                    if (!areaList.contains(setOfMaterials.get(x).getArea().get(i))){
                        areaList.add(setOfMaterials.get(x).getArea().get(i));
                        mapList.add(setOfMaterials.get(x).getMap().get(i));
                    }
                }
            } else if (!areaList.contains(setOfMaterials.get(x).getArea().toString())){
                for (int i = 0; i < setOfMaterials.get(x).getArea().size(); i++){
                    if (!areaList.contains(setOfMaterials.get(x).getArea().get(i))){
                        areaList.add(setOfMaterials.get(x).getArea().get(i));
                        mapList.add(setOfMaterials.get(x).getMap().get(i));
                    }
                }
            }
            for (int y = x+1; y < setOfMaterials.size(); y++){
                if (setOfMaterials.get(x).getMap().equals(setOfMaterials.get(y).getMap())){
                    setOfMaterials.add(x, setOfMaterials.get(y));
                    setOfMaterials.remove(y+1);
                }
            }            
        }
        /*
        //This create a new spot, set the two buttons and put it in.
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (int i = 0; i < setOfMaterials.size()-1; i++){
            View newAreaView = inflater.inflate(R.layout.new_gatherer_view,  null);
            TextView newAreaText = (TextView) newAreaView.findViewById(R.id.genericText);
            newAreaText.setText(setOfMaterials.get(i).getName()  + "{" + itemList.getSetAmount("Materials").get(i) + "}" + " (" + setOfMaterials.get(i).getArea() + " [" + setOfMaterials.get(i).getAreaLevel() + "] (X:" +
            setOfMaterials.get(i).getXCord() + ", Y:" + setOfMaterials.get(i).getYCord() + ")");
            materialQueryTable.addView(newAreaView, i);
        }*/

        // preparing list data
        prepareListData(setOfMaterials, setOfMaterialsAmount);

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.areaExpendView);

        // setting list adapter
        expListView.setAdapter(listAdapter);

    }

    private void prepareListData(List<Materials> mats, List<Integer> matsAmount) {
        
        //Initialize list
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        List<String> Lower_La_Noscea = new ArrayList<String>();
        List<String> Western_La_Noscea = new ArrayList<String>();
        List<String> Eastern_La_Noscea = new ArrayList<String>();
        List<String> Middle_La_Noscea = new ArrayList<String>();
        List<String> Upper_La_Noscea = new ArrayList<String>();
        
        List<String> Central_Thanalan = new ArrayList<String>();
        List<String> Western_Thanalan = new ArrayList<String>();
        List<String> Eastern_Thanalan = new ArrayList<String>();
        List<String> Southern_Thanalan = new ArrayList<String>();
        List<String> Northern_Thanalan = new ArrayList<String>();
        
        List<String> Central_Shroud = new ArrayList<String>();
        List<String> Northern_Shroud = new ArrayList<String>();
        List<String> Southern_Shroud = new ArrayList<String>();
        List<String> East_Shroud = new ArrayList<String>();

        List<String> The_Churning_Mists = new ArrayList<String>();
        
        List<String> The_Dravanian_ForeLand = new ArrayList<String>();
        List<String> The_Dravanian_Hinterland = new ArrayList<String>();
        
        List<String> Coerthas_Western_Highlands = new ArrayList<String>();
        List<String> Coerthas_Central_Highlands = new ArrayList<String>();
        
        List<String> The_Sea_of_Clouds = new ArrayList<String>();
        
        // Adding child data
        for (int i = 0 ; i < mats.size(); i++){

            StringBuilder loLaNo = new StringBuilder();
            StringBuilder weLaNo = new StringBuilder();
            StringBuilder eaLaNo = new StringBuilder();
            StringBuilder miLaNo = new StringBuilder();
            StringBuilder upLaNo = new StringBuilder();
            
            StringBuilder ceTh = new StringBuilder();
            StringBuilder weTh = new StringBuilder();
            StringBuilder eaTh = new StringBuilder();
            StringBuilder soTh = new StringBuilder();
            StringBuilder noTh = new StringBuilder();
            

            StringBuilder ceSh = new StringBuilder();
            StringBuilder noSh = new StringBuilder();
            StringBuilder soSh = new StringBuilder();
            StringBuilder eaSh = new StringBuilder();
            
            StringBuilder chMi = new StringBuilder();
            StringBuilder drFo = new StringBuilder();
            StringBuilder drHi = new StringBuilder();
            StringBuilder cWH = new StringBuilder();
            StringBuilder cCH = new StringBuilder();
            
            StringBuilder soC = new StringBuilder();
            
            
            String genInfo = "[" + mats.get(i).getClasses() + "] " + mats.get(i).getName() + " (" + matsAmount.get(i) + ") :\n";
            for (int j = 0 ; j < mats.get(i).getMap().size(); j++){   
                if (mats.get(i).getMap().get(j).equals("Lower_La_Noscea")){
                    loLaNo.append(mats.get(i).getArea().get(j) + " (" + mats.get(i).getXCord().get(j) + ", " + mats.get(i).getYCord().get(j) + ")\n");
                }     
                if (mats.get(i).getMap().get(j).equals("Western_La_Noscea")){
                    weLaNo.append(mats.get(i).getArea().get(j) + " (" + mats.get(i).getXCord().get(j) + ", " + mats.get(i).getYCord().get(j) + ")\n"); 
                }    
                if (mats.get(i).getMap().get(j).equals("Eastern_La_Noscea")){
                    eaLaNo.append(mats.get(i).getArea().get(j) + " (" + mats.get(i).getXCord().get(j) + ", " + mats.get(i).getYCord().get(j) + ")\n");
                }   
                if (mats.get(i).getMap().get(j).equals("Middle_La_Noscea")){
                    miLaNo.append(mats.get(i).getArea().get(j) + " (" + mats.get(i).getXCord().get(j) + ", " + mats.get(i).getYCord().get(j) + ")\n"); 
                }    
                if (mats.get(i).getMap().get(j).equals("Upper_La_Noscea")){
                    upLaNo.append(mats.get(i).getArea().get(j) + " (" + mats.get(i).getXCord().get(j) + ", " + mats.get(i).getYCord().get(j) + ")\n");
                }        
                
                if (mats.get(i).getMap().get(j).equals("Central_Thanalan")){
                    ceTh.append(mats.get(i).getArea().get(j) + " (" + mats.get(i).getXCord().get(j) + ", " + mats.get(i).getYCord().get(j) + ")\n");
                }     
                if (mats.get(i).getMap().get(j).equals("Western_Thanalan")){
                    weTh.append(mats.get(i).getArea().get(j) + " (" + mats.get(i).getXCord().get(j) + ", " + mats.get(i).getYCord().get(j) + ")\n"); 
                }    
                if (mats.get(i).getMap().get(j).equals("Eastern_Thanalan")){
                    eaTh.append(mats.get(i).getArea().get(j) + " (" + mats.get(i).getXCord().get(j) + ", " + mats.get(i).getYCord().get(j) + ")\n");
                }   
                if (mats.get(i).getMap().get(j).equals("Southern_Thanalan")){
                    soTh.append(mats.get(i).getArea().get(j) + " (" + mats.get(i).getXCord().get(j) + ", " + mats.get(i).getYCord().get(j) + ")\n"); 
                }    
                if (mats.get(i).getMap().get(j).equals("Northern_Thanalan")){
                    noTh.append(mats.get(i).getArea().get(j) + " (" + mats.get(i).getXCord().get(j) + ", " + mats.get(i).getYCord().get(j) + ")\n");
                }          
                
                if (mats.get(i).getMap().get(j).equals("Central_Shroud")){
                    ceSh.append(mats.get(i).getArea().get(j) + " (" + mats.get(i).getXCord().get(j) + ", " + mats.get(i).getYCord().get(j) + ")\n");
                }     
                if (mats.get(i).getMap().get(j).equals("Northern_Shroud")){
                    noSh.append(mats.get(i).getArea().get(j) + " (" + mats.get(i).getXCord().get(j) + ", " + mats.get(i).getYCord().get(j) + ")\n"); 
                }    
                if (mats.get(i).getMap().get(j).equals("Southern_Shroud")){
                    soSh.append(mats.get(i).getArea().get(j) + " (" + mats.get(i).getXCord().get(j) + ", " + mats.get(i).getYCord().get(j) + ")\n");
                }   
                if (mats.get(i).getMap().get(j).equals("East_Shroud")){
                    eaSh.append(mats.get(i).getArea().get(j) + " (" + mats.get(i).getXCord().get(j) + ", " + mats.get(i).getYCord().get(j) + ")\n"); 
                }     
                
                if (mats.get(i).getMap().get(j).equals("The_Churning_Mists")){
                    chMi.append(mats.get(i).getArea().get(j) + " (" + mats.get(i).getXCord().get(j) + ", " + mats.get(i).getYCord().get(j) + ")\n");
                }     
                if (mats.get(i).getMap().get(j).equals("The_Dravanian_ForeLand")){
                    drFo.append(mats.get(i).getArea().get(j) + " (" + mats.get(i).getXCord().get(j) + ", " + mats.get(i).getYCord().get(j) + ")\n"); 
                }    
                if (mats.get(i).getMap().get(j).equals("The_Dravanian_Hinterland")){
                    drHi.append(mats.get(i).getArea().get(j) + " (" + mats.get(i).getXCord().get(j) + ", " + mats.get(i).getYCord().get(j) + ")\n");
                }   
                if (mats.get(i).getMap().get(j).equals("Coerthas_Western_Highlands")){
                    cWH.append(mats.get(i).getArea().get(j) + " (" + mats.get(i).getXCord().get(j) + ", " + mats.get(i).getYCord().get(j) + ")\n"); 
                }    
                if (mats.get(i).getMap().get(j).equals("Coerthas_Central_Highlands")){
                    cCH.append(mats.get(i).getArea().get(j) + " (" + mats.get(i).getXCord().get(j) + ", " + mats.get(i).getYCord().get(j) + ")\n");
                }
                if (mats.get(i).getMap().get(j).equals("The_Sea_of_Clouds")){
                    soC.append(mats.get(i).getArea().get(j) + " (" + mats.get(i).getXCord().get(j) + ", " + mats.get(i).getYCord().get(j) + ")\n");
                }
            }
            if (loLaNo.length() > 0){
                Lower_La_Noscea.add(genInfo + loLaNo.toString());
            }
            if (weLaNo.length() > 0){
                Western_La_Noscea.add(genInfo + weLaNo.toString());
            }
            if (eaLaNo.length() > 0){
                Eastern_La_Noscea.add(genInfo + eaLaNo.toString());
            }
            if (miLaNo.length() > 0){
                Middle_La_Noscea.add(genInfo + miLaNo.toString());
            }
            if (upLaNo.length() > 0){
                Upper_La_Noscea.add(genInfo + upLaNo.toString());
            }
            
            if (ceTh.length() > 0){
                Central_Thanalan.add(genInfo + ceTh.toString());
            }
            if (weTh.length() > 0){
                Western_Thanalan.add(genInfo + weTh.toString());
            }
            if (eaTh.length() > 0){
                Eastern_Thanalan.add(genInfo + eaTh.toString());
            }
            if (soTh.length() > 0){
                Southern_Thanalan.add(genInfo + soTh.toString());
            }
            if (noTh.length() > 0){
                Northern_Thanalan.add(genInfo + noTh.toString());
            }
            
            if (soSh.length() > 0){
                Southern_Shroud.add(genInfo + soSh.toString());
            }
            if (noSh.length() > 0){
                Northern_Shroud.add(genInfo + noSh.toString());
            }
            if (ceSh.length() > 0){
                Central_Shroud.add(genInfo + ceSh.toString());
            }
            if (eaSh.length() > 0){
                East_Shroud.add(genInfo + eaSh.toString());
            }           
            
            if (chMi.length() > 0){
                The_Churning_Mists.add(genInfo + chMi.toString());
            }
            if (drFo.length() > 0){
                The_Dravanian_ForeLand.add(genInfo + drFo.toString());
            }
            if (drHi.length() > 0){
                The_Dravanian_Hinterland.add(genInfo + eaTh.toString());
            }
            if (cWH.length() > 0){
                Coerthas_Western_Highlands.add(genInfo + cWH.toString());
            }
            if (cCH.length() > 0){
                Coerthas_Central_Highlands.add(genInfo + cCH.toString());
            }
            if (soC.length() > 0){
                The_Sea_of_Clouds.add(genInfo + soC.toString());
            }
        }
        
        //Create the lists
        int count = 0;
        //La Noscea
        if (!Lower_La_Noscea.isEmpty()) {
            listDataHeader.add("Lower La Noscea");
            listDataChild.put(listDataHeader.get(count), Lower_La_Noscea);
            count++;
        }
        if (!Western_La_Noscea.isEmpty()) {
            listDataHeader.add("Western La Noscea");
            listDataChild.put(listDataHeader.get(count), Western_La_Noscea);
            count++;
        }
        if (!Eastern_La_Noscea.isEmpty()) {
            listDataHeader.add("Eastern La Noscea");
            listDataChild.put(listDataHeader.get(count), Eastern_La_Noscea);
            count++;
        }
        if (!Middle_La_Noscea.isEmpty()) {
            listDataHeader.add("Middle La Noscea");
            listDataChild.put(listDataHeader.get(count), Middle_La_Noscea);
            count++;
        }
        if (!Upper_La_Noscea.isEmpty()) {
            listDataHeader.add("Upper La Noscea");
            listDataChild.put(listDataHeader.get(count), Upper_La_Noscea);
            count++;
        }

        //Thanalan
        if (!Central_Thanalan.isEmpty()) {
            listDataHeader.add("Central Thanalan");
            Log.i("print", " " +Central_Thanalan);
            listDataChild.put(listDataHeader.get(count), Central_Thanalan);
            count++;
        }
        if (!Western_Thanalan.isEmpty()) {
            listDataHeader.add("Western Thanalan");
            listDataChild.put(listDataHeader.get(count), Western_Thanalan);
            count++;
        }
        if (!Eastern_Thanalan.isEmpty()) {
            listDataHeader.add("Eastern Thanalan");
            listDataChild.put(listDataHeader.get(count), Eastern_Thanalan);
            count++;
        }
        if (!Southern_Thanalan.isEmpty()) {
            listDataHeader.add("Southern Thanalan");
            listDataChild.put(listDataHeader.get(count), Southern_Thanalan);
            count++;
        }
        if (!Northern_Thanalan.isEmpty()) {
            listDataHeader.add("Northern Thanalan");
            listDataChild.put(listDataHeader.get(count), Northern_Thanalan);
            count++;
        }
        
        //The Black Shroud
        if (!Central_Shroud.isEmpty()){
            listDataHeader.add("Central Shroud");
            listDataChild.put(listDataHeader.get(count), Central_Shroud);
            count++;
        }

        if (!Northern_Shroud.isEmpty()){
            listDataHeader.add("Northern Shroud");
            listDataChild.put(listDataHeader.get(count), Northern_Shroud);
            count++;
        }
        if (!Southern_Shroud.isEmpty()){
            listDataHeader.add("Southern Shroud");
            listDataChild.put(listDataHeader.get(count), Southern_Shroud);
            count++;
        }
        if (!East_Shroud.isEmpty()){
            listDataHeader.add("East Shroud");
            listDataChild.put(listDataHeader.get(count), East_Shroud);
            count++;
        }

        //Heavenlysward
        if (!The_Churning_Mists.isEmpty()){
            listDataHeader.add("The Churning Mists");
            listDataChild.put(listDataHeader.get(count), The_Churning_Mists);
            count++;
        }

        if (!The_Dravanian_ForeLand.isEmpty()){
            listDataHeader.add("The Dravanian Foreland");
            listDataChild.put(listDataHeader.get(count), The_Dravanian_ForeLand);
            count++;
        }
        if (!The_Dravanian_Hinterland.isEmpty()){
            listDataHeader.add("The Dravanian Hinterland");
            listDataChild.put(listDataHeader.get(count), The_Dravanian_Hinterland);
            count++;
        }

        if (!Coerthas_Western_Highlands.isEmpty()){
            listDataHeader.add("Coerthas Western Highlands");
            listDataChild.put(listDataHeader.get(count), Coerthas_Western_Highlands);
            count++;
        }
        if (!Coerthas_Central_Highlands.isEmpty()){
            listDataHeader.add("Coerthas Central Highlands");
            listDataChild.put(listDataHeader.get(count), Coerthas_Central_Highlands);  
            count++;
        }
        
        //Sea of cloud
        if (!The_Sea_of_Clouds.isEmpty()){
            listDataHeader.add("The_Sea_of_Clouds");
            listDataChild.put(listDataHeader.get(count), The_Sea_of_Clouds);
        }

    }
    public OnClickListener backButtonListener = new OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent getMaterial = new Intent(v.getContext(), MainActivity.class);

            GathererActivity.this.finish(); 
            setResult(2);

        }

    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gatherer, menu);
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
}
