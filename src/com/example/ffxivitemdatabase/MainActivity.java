package com.example.ffxivitemdatabase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.os.Vibrator;

import com.Database.Fish;
import com.Database.FishList;
import com.Database.ItemList;
import com.Database.Items;
import com.Database.MaterialList;
import com.Database.Materials;
import com.Database.Reagents;
import com.Database.itemHeirarchy;
import com.Database.itemSet;
import com.Database.SearchingItem;
//import com.Database.MaterialList;
import com.Database.index;

@SuppressLint("DefaultLocale")
public class MainActivity extends ActionBarActivity{


    private SharedPreferences savedItems;
    private SharedPreferences user;
    private SharedPreferences savedItemsAmount;
    
    private String[] name = new String[2];
    private TableLayout queryTableLayout;
    private SigninActivity resultGet;
    private TextView test;
    private String testString;
    
    private EditText searchText;
    private TextView userName;
    private ItemList itemDatabase = new ItemList();
    private static Context context;
    ListView itemSets;
    private MaterialList materialDatabase = new MaterialList();
    private FishList fishDatabase = new FishList();
    ArrayList<Integer> materialAmount = new ArrayList<Integer>();
    private itemSet setOfItems = new itemSet();
    private boolean dataRead = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //itemSets = (ListView) this.findViewById(R.id.itemLists);
        
        searchText = (EditText) findViewById(R.id.searchBox);
        userName = (TextView) findViewById(R.id.loggedInAsText);
        test = (TextView) findViewById(R.id.listTestText);
        
        //saved items list
        savedItems = getSharedPreferences("Searches", MODE_PRIVATE);
        user = getSharedPreferences("User", MODE_PRIVATE);
        savedItemsAmount = getSharedPreferences("Amount", MODE_PRIVATE);
        
        queryTableLayout = (TableLayout) findViewById(R.id.queryTableLayout);
        
        
        MainActivity.context = getApplicationContext();
        Button addToList = (Button) findViewById(R.id.addToListButton);
        addToList.setOnClickListener(addToListButtonListener);
        Button clearAll = (Button) findViewById(R.id.ClearAll);
        clearAll.setOnClickListener(clearTagsButtonListener);
        Button composeRaw = (Button) findViewById(R.id.ComposeListButton);
        composeRaw.setOnClickListener(composeRawButtonListener);
        Button searchFor = (Button) findViewById(R.id.searchButton);
        searchFor.setOnClickListener(searchForButtonListener);
        Button composeGatherer = (Button) findViewById(R.id.ComposeGathererButton);
        composeGatherer.setOnClickListener(composeGathererButtonListener);
        Button saveListButton = (Button) findViewById(R.id.logIn);
        saveListButton.setOnClickListener(logInButtonListener);
        
        if(user.getAll().keySet().toArray().length > 0){
            String usernameTemp[] = user.getAll().keySet().toArray(new String[0]);
            name[0] = usernameTemp[1];
            name[1] = usernameTemp[0];
            userName.setText(name[0] + " " + name[1]);
        } else {
            userName.setText("");
        }
        
        refreshButton(null, 0);
        if (!dataRead){
            try {
                materialDatabase.loadList(MainActivity.context);
                itemDatabase.loadList(MainActivity.context);
                fishDatabase.loadList(MainActivity.context);
                dataRead = true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    public void compileMaterialList(){
        String[] itemSetList =
                savedItems.getAll().keySet().toArray(new String[0]);
        setOfItems.clearSet();

        for (int itemNumber = 0; itemNumber < itemSetList.length; ++itemNumber){
            if (itemSetList[itemNumber] != "Lightning Shard"){
                if (index.searchForItem(false, itemSetList[itemNumber], itemDatabase) != null){
                    diveIntoItem(new Items(index.searchForItem(false, itemSetList[itemNumber], itemDatabase)));
                } else if (index.searchForMaterial(itemSetList[itemNumber], materialDatabase) != null){
                    setOfItems.addMaterial(index.searchForMaterial(itemSetList[itemNumber], materialDatabase));
                } else if (index.searchForFish(itemSetList[itemNumber], fishDatabase) != null){
                    setOfItems.addFish(index.searchForFish(itemSetList[itemNumber], fishDatabase));
                } else if (index.searchForReagents(itemSetList[itemNumber], materialDatabase) != null){
                    setOfItems.addReagents(index.searchForReagents(itemSetList[itemNumber], materialDatabase));
                }
            }
        }
    }
    public OnClickListener composeRawButtonListener = new OnClickListener(){

        @Override
        public void onClick(View v){
            
            compileMaterialList();
            Intent myIntent = new Intent(v.getContext(), DataActivity.class);
            myIntent.putExtra("ItemType", "All");
            myIntent.putExtra("ItemSet", setOfItems);
            startActivityForResult(myIntent, 1);
        }
    };
    public OnClickListener composeGathererButtonListener = new OnClickListener(){

        @Override
        public void onClick(View v){
            
            compileMaterialList();
            Intent myIntent = new Intent(v.getContext(), GathererActivity.class);
            myIntent.putExtra("ItemSet", setOfItems);
            startActivityForResult(myIntent, 1);
        }
    };    
    private void refreshButton(String newQuery, int amount){
        String[] query  = 
                savedItems.getAll().keySet().toArray(new String[0]);
        //Arrays.sort(query , String.CASE_INSENSITIVE_ORDER);
        
        if (newQuery != null){
            makeTagGUI(newQuery, Arrays.binarySearch(query, newQuery), amount);
        } else {
            for (int index = 0; index < query.length; index+=2) { 
                Log.i("item test", query[index] + " | " + query[index+1]);
                makeTagGUI(query[index], index, Integer.parseInt(query[index+1]));
            }
        }
    }
    
    @SuppressLint("NewApi")
    private void makeTag(String query, int amount){
        String originalQuery = savedItems.getString(query, null);
        
        //Store
        SharedPreferences.Editor preferencesEditor = savedItems.edit();
        preferencesEditor.putString(query, null);
        preferencesEditor.putString(Integer.toString(amount), null);
        preferencesEditor.apply();

        String[] testQuery  = 
                savedItems.getAll().keySet().toArray(new String[0]);
        for (int i = 0; i < testQuery.length; i++){
            Log.i("Test array", testQuery[i] + " ");
        }
        if (originalQuery == null) refreshButton(query, amount);
    }
    
    private void makeTagGUI(String query, int index, int amount){
        //create a new layout
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        
        //This create a new spot, set the two buttons and put it in.
        View newTagView = inflater.inflate(R.layout.new_tag_view,  null);
        Button newItemButton = (Button) newTagView.findViewById(R.id.newItemButton);
        Button newItemAmountButton = (Button) newTagView.findViewById(R.id.itemAmountButton);
        newItemButton.setText(query);
        newItemButton.setOnClickListener(itemButtonListener);
        newItemButton.setOnLongClickListener(itemButtonLongListener);
        newItemAmountButton.setText("1");
        newItemAmountButton.setOnClickListener(itemAmountButtonListener);
        
        queryTableLayout.addView(newTagView, index);
    }
    
    private void clearButtons(boolean refresh, final List<String> refreshList){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.missingTitle);
        
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                queryTableLayout.removeAllViews();            
                SharedPreferences.Editor preferencesEditor = savedItems.edit();
                preferencesEditor.clear();
                preferencesEditor.apply();
                
//                if (!refreshList.isEmpty()){
//                    for (int i = 0; i < refreshList.size(); i ++){
//                        makeTag(refreshList.get(i), 0);
//                    }
//                }
                
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
                
            }
        });
        
        builder.setMessage(R.string.clear);
        
        //Instantiate it
        AlertDialog errorDialog = builder.create();
        errorDialog.show();
        
    }
    
    //============Allows the dyanmic buttons
    public OnClickListener addToListButtonListener = new OnClickListener(){
        
        Items itemGot;
        Materials materialGot;
        Fish fishGot;
        @Override
        public void onClick(View v)
        {
            //Test to see if searchbox is empty
            if (searchText.getText().length() > 0){
                if(savedItems.getAll().keySet().toArray().length > 0){
                    String savedItemsTemp[] = savedItems.getAll().keySet().toArray(new String[0]);
                    for (int i = 0; i < savedItemsTemp.length; i++){
                        if (searchText.getText().toString().toLowerCase().equals(savedItemsTemp[i].toLowerCase())){
                            Log.i("print", "Similar search found");
                        }
                    }
                }
                
                itemGot = index.searchForItem(false, searchText.getText().toString().toLowerCase(), itemDatabase);
                materialGot = index.searchForMaterial(searchText.getText().toString().toLowerCase(), materialDatabase);
                fishGot = index.searchForFish(searchText.getText().toString().toLowerCase(), fishDatabase);
                
                if (itemGot != null){
                    makeTag(itemGot.getRecipe(), 1);
                    searchText.setText("");

                    //Hide the keyboard
                    ((InputMethodManager) 
                            getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(searchText.getWindowToken(), 0);
                } else if (materialGot != null){
                    makeTag(materialGot.getName(), 1);
                    searchText.setText("");

                    //Hide the keyboard
                    ((InputMethodManager) 
                            getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(searchText.getWindowToken(), 0);
                } else if (fishGot != null){
                    makeTag(fishGot.getName(), 1);
                    searchText.setText("");

                    //Hide the keyboard
                    ((InputMethodManager) 
                            getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(searchText.getWindowToken(), 0);
                } else {

                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(R.string.missingTitle);
                    
                    builder.setPositiveButton(R.string.ok, null);
                    
                    builder.setMessage(R.string.unfindable);
                    
                    //Instantiate it
                    AlertDialog errorDialog = builder.create();
                    errorDialog.show();
                }
            } else {
                //Create the bits and peices of the error dialogue
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.missingTitle);
                
                builder.setPositiveButton(R.string.ok, null);
                
                builder.setMessage(R.string.missingMessage);
                
                //Instantiate it
                AlertDialog errorDialog = builder.create();
                errorDialog.show();
            }
        }
    };
    
    public void lookupItem(String itemType, String name, boolean addHeir){     
        Items itemSearch = index.searchForItem(false, name, itemDatabase);
        Materials materialGot = index.searchForMaterial(name, materialDatabase);
        Fish fishGot = index.searchForFish(name, fishDatabase);
        Reagents reagentGot = index.searchForReagents(name, materialDatabase);
        if (itemSearch != null){
            setOfItems.searchItem(itemSearch);
            if (addHeir) setOfItems.addItem(itemSearch);
            Intent getMaterial = new Intent(MainActivity.this, DataActivity.class);
            getMaterial.putExtra("ItemType", "Single");
            getMaterial.putExtra("ItemSet", (Parcelable) setOfItems);
            startActivityForResult(getMaterial, 1);
        } else if (fishGot != null){
            Intent getMaterial = new Intent(MainActivity.this, InfoActivity.class);
            setOfItems.searchFish(fishGot);
            getMaterial.putExtra("ItemSet",  (Parcelable) setOfItems);
            startActivityForResult(getMaterial, 1);
        } else if (materialGot != null || reagentGot != null) {
            Intent getMaterial = new Intent(MainActivity.this, InfoActivity.class);
            if (reagentGot != null){
                setOfItems.searchReagent(reagentGot);
            }
            if (materialGot != null){
                setOfItems.searchMaterial(materialGot);
            } 
            getMaterial.putExtra("ItemSet", (Parcelable) setOfItems);
            startActivityForResult(getMaterial, 1);
        } else {
            if (name == ""){
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.missingTitle);

                builder.setPositiveButton(R.string.ok, null);

                builder.setMessage(R.string.missingMessage);

                //Instantiate it
                AlertDialog errorDialog = builder.create();
                errorDialog.show();
            } else {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.missingTitle);

                builder.setPositiveButton(R.string.ok, null);

                builder.setMessage(R.string.unfindable);

                //Instantiate it
                AlertDialog errorDialog = builder.create();
                errorDialog.show();
            }
        }
    }
    public OnClickListener searchForButtonListener = new OnClickListener(){
        
        @Override
        public void onClick(View v){
            lookupItem("Single", searchText.getText().toString().toLowerCase(), true);
        }
    };
    
    public OnClickListener clearTagsButtonListener = new OnClickListener(){
        @SuppressLint("NewApi")
        @Override 
        public void onClick(View v){

            clearButtons(false, null);
            
            SharedPreferences.Editor preferencesEditor = savedItems.edit();
            preferencesEditor.clear();
            preferencesEditor.apply();
            
        }
    };
    
    public OnClickListener logInButtonListener = new OnClickListener(){
        
        @Override
        public void onClick(View v){

            Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivityForResult(myIntent, 7);
        }
    };
    public OnClickListener itemButtonListener = new OnClickListener(){
        @Override
        public void onClick(View v){
            lookupItem("Single", ((Button)v).getText().toString(), true);    
        }
    };
    public OnLongClickListener itemButtonLongListener = new OnLongClickListener(){

        @Override
        public boolean onLongClick(final View v) {
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("Extra settings");

            builder.setPositiveButton("Cancel", null);
            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    removeButton(v);
                }
            });
            //Instantiate it
            AlertDialog errorDialog = builder.create();
            errorDialog.show();
            return true;
        }
        
    };
    public OnClickListener itemAmountButtonListener = new OnClickListener(){

        @Override
        public void onClick(final View v){
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(MainActivity.this);
            final EditText input = new EditText(MainActivity.this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);

            builder.setPositiveButton("Ok",  new DialogInterface.OnClickListener(){
            
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    TableRow buttonTableRow = (TableRow) v.getParent().getParent();
                    Button amount = (Button) buttonTableRow.findViewById(R.id.itemAmountButton);
                    amount.setText(input.getText().toString());
                    
                    }
            });
            builder.setView(input);
        }
    };
    

    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        
Log.i("result code", "code:" + requestCode);
        if (resultCode == 1 && data != null) {
            //When a item is going to be searched up
            setOfItems = data.getParcelableExtra("ItemSet");
            lookupItem("Single", setOfItems.getItemSet().get(setOfItems.getItemSet().size()-1).getRecipe(), true);
        } else if (resultCode == 2 && data != null){
            //All 
            setOfItems = data.getParcelableExtra("ItemSet");
            setOfItems.clearSet();
        } else if (resultCode == 3 && data != null){
            //For compose all portion
            Intent myIntent = new Intent(MainActivity.this, DataActivity.class);
            myIntent.putExtra("Type", "All");
            myIntent.putExtra("ItemSet", setOfItems);
            myIntent.putIntegerArrayListExtra("Amount", materialAmount);
            startActivityForResult(myIntent, 1);
        } else if (resultCode == 4 && data != null){
            // Went back and there's still item in itemHeirarchy
            setOfItems = data.getParcelableExtra("ItemSet");
            lookupItem("Single", setOfItems.getItemSet().get(setOfItems.getItemSet().size()-1).getRecipe(), false);
        } else if (resultCode == 5 && data != null){
            setOfItems = data.getParcelableExtra("ItemSet");
            lookupItem("Single", data.getStringExtra("clickSent"), true);
        } else if (resultCode == 6 && data != null){

            setOfItems.clearSet();
            compileMaterialList();
            Intent myIntent = new Intent(MainActivity.this, DataActivity.class);
            myIntent.putExtra("ItemType", "All");
            myIntent.putExtra("ItemSet", setOfItems);
            startActivityForResult(myIntent, 1);
        } else if (resultCode == 7 && data != null){

            //Store
            SharedPreferences.Editor preferencesEditor = user.edit();
            preferencesEditor.clear();
            String firstNameGet = data.getStringExtra("firstName");
            String lastNameGet = data.getStringExtra("lastName");
            preferencesEditor.putString(firstNameGet, null);
            preferencesEditor.putString(lastNameGet, null);
            preferencesEditor.apply();
            userName.setText(firstNameGet + " " + lastNameGet);
        }
    }
    public void removeButton(View v){
        TableRow buttonTableRow = (TableRow) v.getParent();
        String removingButton = (String) ((Button) buttonTableRow.findViewById(R.id.newItemButton)).getText();
        buttonTableRow.removeAllViews();

        String[] query  = 
                savedItems.getAll().keySet().toArray(new String[0]);

        SharedPreferences.Editor preferencesEditor = savedItems.edit();
        preferencesEditor.remove(removingButton);
        preferencesEditor.apply();
        
    }
    public OnClickListener removeButtonListener = new OnClickListener()
    {
        @Override
        public void onClick(View v){
            removeButton(v);
//            TableRow buttonTableRow = (TableRow) v.getParent();
//            String removingButton = (String) ((Button) buttonTableRow.findViewById(R.id.newItemButton)).getText();
//            buttonTableRow.removeAllViews();
//
//            String[] query  = 
//                    savedItems.getAll().keySet().toArray(new String[0]);
//
//            SharedPreferences.Editor preferencesEditor = savedItems.edit();
//            preferencesEditor.remove(removingButton);
//            preferencesEditor.apply();

            
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    public void sendList(View view){
        String[] query  = 
                savedItems.getAll().keySet().toArray(new String[0]);
        for (int i = 0; i < savedItems.getAll().keySet().size(); i++){
            new SigninActivity(this, "sendList").execute(name[0], name[1], query[i]);
        }
    }
    public void getList(View view) throws InterruptedException, ExecutionException{
        String getList = new SigninActivity(this, "Get").execute(name[0], name[1]).get();
        List<String> separator = new ArrayList<String>(Arrays.asList(getList.split("/")));
        clearButtons(true, separator);
    }
    
    public void processFinish(String[] output) {
        String[] temp = output;
    }

    public String searchLike(String input){
        return null;
    }
    
    
    public void diveIntoItem(Items item){
        for (int matSize = 0; matSize < item.getMatArray().size(); matSize++){

            if (!item.getMaterial(matSize).equals("Lightning Shard") && !item.getMaterial(matSize).equals("Fire Shard") &&
                    !item.getMaterial(matSize).equals("Water Shard") && !item.getMaterial(matSize).equals("Earth Shard") &&
                    !item.getMaterial(matSize).equals("Wind Shard")  && !item.getMaterial(matSize).equals("Ice Shard")) {
                if (index.searchForItem(false, item.getMaterial(matSize), itemDatabase) == null){
                    if (index.searchForMaterial(item.getMaterial(matSize), materialDatabase) != null){
                        setOfItems.addMaterial(index.searchForMaterial(item.getMaterial(matSize), materialDatabase));
                    } else if (index.searchForFish(item.getMaterial(matSize), fishDatabase) != null){
                        setOfItems.addFish(index.searchForFish(item.getMaterial(matSize), fishDatabase));
                    } else if (index.searchForReagents(item.getMaterial(matSize), materialDatabase) != null){
                        setOfItems.addReagents(index.searchForReagents(item.getMaterial(matSize), materialDatabase));
                    }
                } else {
                    setOfItems.addItem(index.searchForItem(false, item.getMaterial(matSize), itemDatabase));
                    diveIntoItem(index.searchForItem(false, item.getMaterial(matSize), itemDatabase));
                }
            }
        }
    }
}
