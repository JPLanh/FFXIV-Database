package com.example.ffxivitemdatabase;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {
    private SharedPreferences user;
    private EditText firstName;
    private EditText lastName;
    private String access = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = getSharedPreferences("User", MODE_PRIVATE);
        
        firstName = (EditText) findViewById(R.id.firstNameEditText);
        lastName = (EditText) findViewById(R.id.lastNameEditText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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
    
    public void logInSetListener(View view){

        Intent getMaterial = new Intent(view.getContext(), MainActivity.class);
        getMaterial.putExtra("firstName", firstName.getText().toString());
        getMaterial.putExtra("lastName", lastName.getText().toString());
        //new SigninActivity(this, access, "Login").execute(firstName.getText().toString(), lastName.getText().toString());
        setResult(7, getMaterial);
        LoginActivity.this.finish();
    }
}
