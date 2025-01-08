package com.example.cng_gas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminPanel extends AppCompatActivity {

    String AccessCodeAdmin;
    DatabaseReference s1,s2,s3,s4,s5,s6,ss1,ss2,ss3,ss4,ss5,ss6,date,price;
    EditText e1,e2,e3,e4,e5,e6,dateE,priceE;
    String r1,r2,r3,r4,r5,r6;

    TextView head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);


        Date date1 = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        //date.setValue(formatter.format(date1));

        e1=findViewById(R.id.t1);
        e2=findViewById(R.id.t2);
        e3=findViewById(R.id.t3);
        e4=findViewById(R.id.t4);
        e5=findViewById(R.id.t5);
        e6=findViewById(R.id.t6);

        head=findViewById(R.id.head);

        dateE=findViewById(R.id.date);
        priceE=findViewById(R.id.price);
        dateE.setText(formatter.format(date1));


        SharedPreferences prefs = this.getSharedPreferences("com.example.cng_gas", Context.MODE_PRIVATE);
        AccessCodeAdmin= prefs.getString("AccessCodeAdmin", "");

        //database values get
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        date = database.getReference("date");
        price = database.getReference("price");

        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        s1 = database2.getReference("s1");
        s2 = database.getReference("s2");
        s3 = database.getReference("s3");
        s4 = database.getReference("s4");
        s5 = database.getReference("s5");
        s6 = database.getReference("s6");

        ss1 = database.getReference("ss1");
        ss2 = database.getReference("ss2");
        ss3 = database.getReference("ss3");
        ss4 = database.getReference("ss4");
        ss5 = database.getReference("ss5");
        ss6 = database.getReference("ss6");

        if(AccessCodeAdmin.equals("1001")){
            head.setText("Alephata (1001)");
        }else {
            head.setText("Narayangaon (1002)");
        }




    }

    public void Save(View view) {

        if(AccessCodeAdmin.equals("1001")){


            r1=e1.getText().toString();
            r2=e2.getText().toString();
            r3=e3.getText().toString();
            r4=e4.getText().toString();
            r5=e5.getText().toString();
            r6=e6.getText().toString();

            s1.setValue(Integer.parseInt(r1));
            s2.setValue(Integer.parseInt(r2));
            s3.setValue(Integer.parseInt(r3));
            s4.setValue(Integer.parseInt(r4));
            s5.setValue(Integer.parseInt(r5));
            s6.setValue(Integer.parseInt(r6));
            date.setValue(dateE.getText().toString());
            price.setValue(priceE.getText().toString());
            Toast.makeText(this, "Data Saved ! You Can Logout", Toast.LENGTH_SHORT).show();

        }else {
            r1=e1.getText().toString();
            r2=e2.getText().toString();
            r3=e3.getText().toString();
            r4=e4.getText().toString();
            r5=e5.getText().toString();
            r6=e6.getText().toString();

            ss1.setValue(Integer.parseInt(r1));
            ss2.setValue(Integer.parseInt(r2));
            ss3.setValue(Integer.parseInt(r3));
            ss4.setValue(Integer.parseInt(r4));
            ss5.setValue(Integer.parseInt(r5));
            ss6.setValue(Integer.parseInt(r6));
            date.setValue(dateE.getText().toString());
            price.setValue(priceE.getText().toString());
            Toast.makeText(this, "Data Saved ! you can Logout", Toast.LENGTH_SHORT).show();

        }



    }

    public void logout(View view) {
        finish();
    }

    public void open(View view) {
        Intent intent4 = new Intent(getApplicationContext(),view_user.class);
        startActivity(intent4);

    }
}