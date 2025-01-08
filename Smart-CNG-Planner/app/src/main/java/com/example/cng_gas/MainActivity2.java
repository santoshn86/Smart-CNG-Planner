package com.example.cng_gas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {

    TextView tdate, tprice, upino, upiid, pay, stock1, stock2;

    String[] timesolt = {"06:00 AM - 09:00 AM", "09:00 AM - 12:00 PM", "12:00 PM - 03:00 PM",
            "03:00 PM - 06:00 PM", "06:00 PM - 09:00 PM", "09:00 PM - 12:00 AM"};

    String[] areas = {"Please select Area", "Alephata", "Narayangaon"};

    String[] paymethod = {"CASH", "UPI"};

    String cngtime, cngarea, cngpay, cngkg, cusname, cusphone;
    String price_cng;
    EditText ekg;
    SharedPreferences sp;

    DatabaseReference s1, s2, s3, s4, s5, s6, ss1, ss2, ss3, ss4, ss5, ss6, st1, st2;

    int r1, r2, r3, r4, r5, r6, rr1, rr2, rr3, rr4, rr5, rr6;

    int tempos;
    int temparea,stt1,stt2;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //textview data
        tdate = findViewById(R.id.date);
        tprice = findViewById(R.id.price);
        upino = findViewById(R.id.upino);
        upiid = findViewById(R.id.upiid);
        stock1 = findViewById(R.id.stock1);
        stock2 = findViewById(R.id.stock2);
        upiid.setText("UPI ID - ");
        upino.setText("UPI NUMBER - ");

        ekg = findViewById(R.id.kg);
        sp = getSharedPreferences("logged", MODE_PRIVATE);
        //
        //database values get
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference date = database.getReference("date");
        DatabaseReference price = database.getReference("price");
        st1 = database.getReference("Stock1");
        st2 = database.getReference("Stock2");

        Date date1 = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        date.setValue(formatter.format(date1));

        //get the sp data
        SharedPreferences prefs = this.getSharedPreferences("com.example.cng_gas", Context.MODE_PRIVATE);

        cusname = prefs.getString("CustomerName", "");
        cusphone = prefs.getString("CustomerPhoneNumber", "");
        //end sp data

        //spinenr start
        Spinner Timeslots = findViewById(R.id.slots);
        Spinner Pumps = findViewById(R.id.areas);
        Spinner PayMethod = findViewById(R.id.pays);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, timesolt);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Timeslots.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, areas);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Pumps.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, paymethod);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PayMethod.setAdapter(adapter3);

        Timeslots.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                cngtime = timesolt[position];
                tempos = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        Pumps.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                cngarea = areas[position];
                temparea = position;
                if (temparea == 1) {
                    upiid.setText("UPI ID - @ypi.satelil.obin");
                    upino.setText("UPI NUMBER - 4545454545");
                }
                if (temparea == 2) {
                    upiid.setText("UPI ID - @ypi.nateli.sbin");
                    upino.setText("UPI NUMBER - 3232233223");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        PayMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                cngpay = paymethod[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        //spinner end



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
                tdate.setText(value);

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
                tprice.setText("Today CNG price - " + value + "/-");
                price_cng = value;

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        st1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                stock1.setText("Alephata CNG STOCK -" + value);
                stt1=value;
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
                stock2.setText("Narayangaon CNG STOCK -" + value);
                stt2=value;
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
                r1 = value;

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
                r2 = value;

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
                r3 = value;

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
                r4 = value;

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
                r5 = value;

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
                r6 = value;

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
                rr1 = value;

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
                rr2 = value;

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
                rr3 = value;

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
                rr4 = value;

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
                rr5 = value;

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
                rr6 = value;

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });


        //database value end

    }

    public void Confirm(View view) {
        cngkg = ekg.getText().toString();

        if (!cngkg.equals("")) {
            int cn=Integer.parseInt(cngkg);
            if(cn<=12){
                //start
                if (temparea == 0) {
                    Toast.makeText(this, "Please Select Area and Slot", Toast.LENGTH_SHORT).show();
                }
                if (temparea == 1) {

                    if (tempos == 0) {
                        r1 = r1 - 1;
                        s1.setValue(r1);
                    }
                    if (tempos == 1) {
                        r2 = r2 - 1;
                        s2.setValue(r2);
                    }
                    if (tempos == 2) {
                        r3 = r3 - 1;
                        s3.setValue(r3);
                    }
                    if (tempos == 3) {
                        r4 = r4 - 1;
                        s4.setValue(r4);
                    }
                    if (tempos == 4) {
                        r5 = r5 - 1;
                        s5.setValue(r5);
                    }
                    if (tempos == 5) {
                        r6 = r6 - 1;
                        s6.setValue(r6);
                    }
                    int d1=stt1-cn;
                    st1.setValue(d1);
                }
                if (temparea == 2) {

                    if (tempos == 0) {
                        rr1 = rr1 - 1;
                        ss1.setValue(rr1);
                    }
                    if (tempos == 1) {
                        rr2 = rr2 - 1;
                        ss2.setValue(rr2);
                    }
                    if (tempos == 2) {
                        rr3 = rr3 - 1;
                        ss3.setValue(rr3);
                    }
                    if (tempos == 3) {
                        rr4 = rr4 - 1;
                        ss4.setValue(rr4);
                    }
                    if (tempos == 4) {
                        rr5 = rr5 - 1;
                        ss5.setValue(rr5);
                    }
                    if (tempos == 5) {
                        rr6 = rr6 - 1;
                        ss6.setValue(rr6);
                    }
                    int d2=stt2-cn;
                    st2.setValue(d2);
                }
                //end
                //Getting intent and PendingIntent instance

                sendSMSMessage();
            }
            else
            {
                Toast.makeText(this, "Limit Exceed Enter Value till 12 kg", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "Please Enter Kg of CNG", Toast.LENGTH_LONG).show();
        }
    }

    public void viewareacode(View view) {
        Intent intent4 = new Intent(getApplicationContext(), ViewArea.class);
        startActivity(intent4);
    }

    public void Logout(View view) {
        sp.edit().putBoolean("logged",false).apply();
        Intent intent = new Intent(getApplicationContext(), login.class);
        startActivity(intent);

    }


    protected void sendSMSMessage() {

        float p1 = Float.parseFloat(price_cng);
        float p2 = Float.parseFloat(cngkg);
        float finalprice = p1 * p2;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity2.this);
        alertDialogBuilder.setMessage("Slot Booked Successfully ! Check Your INBOX " + " Pay - " + finalprice + " cash or using UPI (Please wait for  2 to 3 Min before you press Exit)");

        alertDialogBuilder.setPositiveButton("EXIT",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //Getting intent and PendingIntent instance
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(cusphone, null, "Slot Booked Successfully", null, null);
                        Intent intent = new Intent(getApplicationContext(), login.class);
                        startActivity(intent);
                    }
                });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }


}