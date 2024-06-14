package com.example.kioskita2;
import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


// Import necessary Firebase libraries
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

// MainActivity class
public class MainActivity extends AppCompatActivity {

    // DatabaseReference to communicate with Firebase Realtime Database
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Get a reference to the "events" node in the database
        mDatabase = database.getReference("events");

        // Write a new event to the database
        writeNewEvent("1", "Membangun Tim yang Berkinerja Tinggi", "Organisasi", R.drawable.gambarsatu);

        // Read events from the database
        readEvents();
    }

    // Method to write a new event to the database
    private void writeNewEvent(String eventId, String title, String category, int imageResource) {
        itemEvent event = new itemEvent(title, category, imageResource);
        mDatabase.child(eventId).setValue(event);
    }

    // Method to read events from the database
    private void readEvents() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get ItemEvent objects and use the values to update the UI
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    itemEvent event = postSnapshot.getValue(itemEvent.class);
                    // Update UI with event data
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting ItemEvent failed, log a message
                Log.w(TAG, "loadEvent:onCancelled", databaseError.toException());
            }
        });
    }
}







//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Mengganti MainActivity dengan MainFragment
//        replaceFragment(new MainFragment());
//    }
//
////     Metode untuk mengganti Fragment
//    private void replaceFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }
//}




