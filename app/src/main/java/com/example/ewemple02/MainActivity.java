package com.example.ewemple02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText name, email, phone, address;
    Button insert;
    FirebaseDatabase database;
    DatabaseReference myRef;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("FireBaseExemple");
        myRef.push().setValue("Hello, World!");
 */
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.addr);
        insert = findViewById(R.id.insert);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("FireBaseExemple");
        user = new User();
    }
    private void getValues(){
        user.setName(name.getText().toString());
        user.setEmail(email.getText().toString());
        user.setAddress(address.getText().toString());
        user.setPhone(phone.getText().toString());
    }

    public void btninsert(View view) {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getValues();
                myRef.child("user02").setValue(user);
                Toast.makeText(MainActivity.this, "data inserted",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "data COULD NOT be inserted",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void btnaff(View view) {
        opensecondactivity();
    }

    private void opensecondactivity() {
        Intent intent = new Intent(this, Activity_aff.class);
        startActivity(intent);
    }
}
