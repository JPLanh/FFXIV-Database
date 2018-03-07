package com.example.ffxivitemdatabase;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DataGet extends Activity {

    String action, firstName, lastName, test;
    TextView getQuery;
    boolean flag;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_get);
        
        getQuery = (TextView) findViewById(R.id.retrieveData);
        Intent getIntent = getIntent();
        

        while(!flag){
            getQuery = (TextView) findViewById(R.id.retrieveData);
            test = getQuery.getText().toString();
            if (!test.equals("Small Text")) {
                flag = true;
            }
        }
            
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.data_get, menu);
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
