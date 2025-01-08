package com.example.cng_gas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    //declare start
    EditText EName,EPhoneNumber;
    SharedPreferences sp;
    DatabaseReference reference;
    SharedPreferences prefs;
    String cusname,cusphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        //start data get
        EName=findViewById(R.id.username);
        EPhoneNumber=findViewById(R.id.phonenumber);


//        prefs = this.getSharedPreferences("com.example.cng_gas", Context.MODE_PRIVATE);
//        sp=getSharedPreferences("logged",MODE_PRIVATE);
        //end data get

        //get the sp data
//        SharedPreferences prefs = this.getSharedPreferences("com.example.cng_gas", Context.MODE_PRIVATE);
//        cusname= prefs.getString("CustomerName", "");
//        cusphone= prefs.getString("Customerpass", "");


        //EName.setText(cusname);
        //EPhoneNumber.setText(cusphone);

        //end sp data

    }

    public void Login(View view) {
        reference=FirebaseDatabase.getInstance().getReference("Users");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String n=EName.getText().toString();
                String p=EPhoneNumber.getText().toString();
                if(n.isEmpty() && p.isEmpty()) {
                    Toast.makeText(login.this, "Enter Empty Fields", Toast.LENGTH_SHORT).show();
                }
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    String name = snapshot.child("Name").getValue(String.class);
                    String pass = snapshot.child("Password").getValue(String.class);


                    if (name.equals(n) && pass.equals(p)) {
//                            sp.edit().putBoolean("logged",true).apply();
                        Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent4 = new Intent(getApplicationContext(), MainActivity2.class);
                        startActivity(intent4);
                    }
//                    else if(!name.equals(n) && !pass.equals(p)){
//                        EName.setError("Please Enter Valid Username ");
//                        EPhoneNumber.setError("Please Enter Valid Password");
//
//                    }

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {



            }
        });

    }



    public void viewareacode(View view) {

        Intent intent4 = new Intent(getApplicationContext(),ViewArea.class);
        startActivity(intent4);

    }

    public void Register(View view) {
        Intent intent3 = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent3);

    }

    public void admin(View view) {
        Intent admin = new Intent(getApplicationContext(),AdminLogin.class);
        startActivity(admin);
    }
}