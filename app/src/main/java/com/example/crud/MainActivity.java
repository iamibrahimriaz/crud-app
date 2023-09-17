package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void saveData(View view) {
        EditText name = findViewById(R.id.txtName);
        EditText loc = findViewById(R.id.txtLocation);
        EditText desig = findViewById(R.id.txtDesignation);
        String username = name.getText().toString();
        String location = loc.getText().toString();
        String designation = desig.getText().toString();
        DbHandler dbHandler = new DbHandler(MainActivity.this);
        dbHandler.insertUserDetails(username, location, designation);
        Toast.makeText(getApplicationContext(), "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
        name.getText().clear();
        loc.getText().clear();
        desig.getText().clear();
    }

    public void showData(View view) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        startActivity(intent);
    }
}
