package com.example.ffxivitemdatabase;

import java.util.ArrayList;

import com.Database.Items;
import com.Database.index;
import com.Database.itemHeirarchy;
import com.Database.itemSet;

import android.support.v7.app.ActionBarActivity;
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
import android.widget.TableLayout;
import android.widget.TextView;

public class DataActivity extends ActionBarActivity {

    private TableLayout itemQueryTableLayout;
    private Items currentItem;
    private String searchType;
    private itemSet setOfItems;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_data);
        itemQueryTableLayout = (TableLayout) findViewById(R.id.ItemQueryTableLayout);
        TextView materialName = (TextView) findViewById(R.id.itemNameText);
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(backButtonListener);
        
        Items itemGot = null;

        Intent getMaterial = getIntent(); 
        searchType = getMaterial.getStringExtra("ItemType");
        setOfItems = getMaterial.getParcelableExtra("ItemSet");
        if (searchType.equals("All")){
            itemQueryTableLayout.removeAllViews();
            for (int matAt = 0; matAt < setOfItems.getMaterialSet().size(); matAt++){
                createNewView(setOfItems.getMaterialSet().get(matAt).getName(), setOfItems.getSetAmount("Materials").get(matAt), matAt);
            }
            for (int matAt = 0; matAt < setOfItems.getReagentSet().size(); matAt++){
                createNewView(setOfItems.getReagentSet().get(matAt).getName(), setOfItems.getSetAmount("Reagent").get(matAt), matAt);
            }

        } else if (searchType.equals("Single")){
            itemQueryTableLayout.removeAllViews();
            itemGot = setOfItems.returnItemSearch();
            materialName.setText(itemGot.getRecipe());
            for (int matAt = 0; matAt < itemGot.getMatArray().size(); matAt++){
                createNewView(itemGot.getMaterial(matAt), itemGot.getMatAmount(matAt), matAt);
            }
        }
    }
    private void createNewView(String got, int amount, int index){

        //This create a new spot, set the two buttons and put it in.
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View newItemView = inflater.inflate(R.layout.new_material_view,  null);
        Button newItemButton = (Button) newItemView.findViewById(R.id.newItemButton);
        newItemButton.setText(got);
        newItemButton.setOnClickListener(itemButtonListener);
        Button newAmountButton = (Button) newItemView.findViewById(R.id.newAmountButton);
        newAmountButton.setText(Integer.toString(amount));
        //newEditButton.setOnClickListener(editButtonListener);

        itemQueryTableLayout.addView(newItemView, index);
    }

    public OnClickListener backButtonListener = new OnClickListener(){
        @Override
        public void onClick(View v){

            Intent getMaterial = new Intent(v.getContext(), MainActivity.class);

            if (searchType.equals("All")){
                setResult(2);
                DataActivity.this.finish();  
            } else {
                if (setOfItems.getItemSet().size() > 1){
                    setOfItems.decraseItem();
                    getMaterial.putExtra("ItemType", searchType);
                    getMaterial.putExtra("ItemSet", setOfItems);
                    setResult(4, getMaterial);
                    DataActivity.this.finish();
                } else {
                    setOfItems.removeItem();
                    getMaterial.putExtra("ItemSet", setOfItems);
                    setResult(2, getMaterial);
                    DataActivity.this.finish();              
                }
            }

        }
    };
    public OnClickListener itemButtonListener = new OnClickListener(){

        @Override
        public void onClick(View v) {
            String clickResult;

            //Include intents here
            Intent getMaterial = new Intent(v.getContext(), MainActivity.class);

            clickResult = ((Button)v).getText().toString();
            if (clickResult != null){
                getMaterial.putExtra("clickSent", clickResult.toLowerCase());
                getMaterial.putExtra("ItemType", searchType);
                getMaterial.putExtra("ItemSet", setOfItems);
                setResult(5, getMaterial);
                DataActivity.this.finish();
            } 
        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.data, menu);
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
}
