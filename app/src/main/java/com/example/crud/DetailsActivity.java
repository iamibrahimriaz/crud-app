package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;

public class DetailsActivity extends AppCompatActivity {

    private ListView lv;
    private ArrayList<HashMap<String, String>> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        lv = findViewById(R.id.user_list);
        refreshListView();

        // Set an OnItemClickListener for the ListView to handle updates when the Update button is clicked.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> user = userList.get(position);
                int userId = Integer.parseInt(user.get("id"));
                // Handle click on the list item (or Update button) to update data
                updateData(userId);
            }
        });

        // Set an OnItemLongClickListener for the ListView to handle deletions when a list row is long-pressed.
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> user = userList.get(position);
                int userId = Integer.parseInt(user.get("id"));
                // Handle long-press on the list item to delete data
                deleteData(userId);
                return true; // Return true to indicate that the long-press has been handled.
            }
        });
    }

    private void refreshListView() {
        DbHandler db = new DbHandler(this);
        userList = db.GetUsers();
        String[] from = {"name", "designation", "location"}; // Use the correct keys from the HashMap
        int[] to = {R.id.name, R.id.designation, R.id.location}; // Use the correct TextView IDs from list_row.xml
        ListAdapter adapter = new SimpleAdapter(DetailsActivity.this, userList, R.layout.list_row, from, to);
        lv.setAdapter(adapter);
    }

    private void updateData(int userId) {
        // TODO: Implement the logic to update user data in the database
        // You can create a dialog or another activity to get new values and then update the data
        Toast.makeText(getApplicationContext(), "Update User with ID: " + userId, Toast.LENGTH_SHORT).show();
    }

    private void deleteData(int userId) {
        DbHandler dbHandler = new DbHandler(this);
        int rowsDeleted = dbHandler.deleteUser(userId);

        if (rowsDeleted > 0) {
            Toast.makeText(getApplicationContext(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
            refreshListView(); // Refresh the ListView to reflect the changes.
        } else {
            Toast.makeText(getApplicationContext(), "Deletion Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
