package com.example.cng_gas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewArea extends AppCompatActivity {

    DatabaseReference s1,s2,s3,s4,s5,s6,ss1,ss2,ss3,ss4,ss5,ss6;
    TextView et1,et2,et3,et4,et5,et6,ett1,ett2,ett3,ett4,ett5,ett6,datee,pricee,sw,sw2;
    String[] areas = {"Please Select Area","Alephata" ,"Narayangaon"};

    TableLayout t1,t2;

    DatabaseReference st1, st2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_area);

        et1=findViewById(R.id.t1);
        et2=findViewById(R.id.t2);
        et3=findViewById(R.id.t3);
        et4=findViewById(R.id.t4);
        et5=findViewById(R.id.t5);
        et6=findViewById(R.id.t6);

        ett1=findViewById(R.id.tt1);
        ett2=findViewById(R.id.tt2);
        ett3=findViewById(R.id.tt3);
        ett4=findViewById(R.id.tt4);
        ett5=findViewById(R.id.tt5);
        ett6=findViewById(R.id.tt6);

        sw=findViewById(R.id.a);
        sw2=findViewById(R.id.a2);

        datee=findViewById(R.id.date);
        pricee=findViewById(R.id.price);


        t1=findViewById(R.id.l1);
        t2=findViewById(R.id.l2);

        t1.setVisibility(View.GONE);
        t2.setVisibility(View.GONE);

        //database values get
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference date = database.getReference("date");
        DatabaseReference price = database.getReference("price");
        st1 = database.getReference("Stock1");
        st2 = database.getReference("Stock2");
        st1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                sw.setText("Alephata CNG STOCK - " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        st2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                sw2.setText("Narayangaon CNG STOCK - " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        //spinenr start
        Spinner Pumps= findViewById(R.id.area);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,areas);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Pumps.setAdapter(adapter2);

        Pumps.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if(position ==1){
                    t2.setVisibility(View.GONE);
                    t1.setVisibility(View.VISIBLE);
                }
                if(position ==2){
                    t1.setVisibility(View.GONE);
                    t2.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        // end



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

        date.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                datee.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        price.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pricee.setText(value+"/-");

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        s1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                String v=Integer.toString(value);
                et1.setText(v);

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        s2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                String v=Integer.toString(value);
                et2.setText(v);

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        s3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                String v=Integer.toString(value);
                et3.setText(v);

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        s4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                String v=Integer.toString(value);
                et4.setText(v);

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        s5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                String v=Integer.toString(value);
                et5.setText(v);

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        s6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                String v=Integer.toString(value);
                et6.setText(v);

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });


        ss1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                String v=Integer.toString(value);
                ett1.setText(v);

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        ss2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                String v=Integer.toString(value);
                ett2.setText(v);

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        ss3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                String v=Integer.toString(value);
                ett3.setText(v);

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        ss4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                String v=Integer.toString(value);
                ett4.setText(v);

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        ss5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                String v=Integer.toString(value);
                ett5.setText(v);

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        ss6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                String v=Integer.toString(value);
                ett6.setText(v);

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });


        //database value end


    }

    public void BACK(View view) {
        finish();
    }
}