package com.example.cng_gas;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    //declare start
    EditText EName,EPhoneNumber,ECity,Eemail,Ecngno,Epass;
    String Name,PhoneNumber,City,Email,Customerid,Cngno,password;
    FirebaseDatabase databaseCus;
    DatabaseReference Customer;
    SharedPreferences sp;
    SharedPreferences prefs;
    String cusname,cusphone,cuscity,cusemail,cuscngno,cuspass;

    //declare end

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 123);

        } else {

        }


        //start data get
        EName=findViewById(R.id.username);
        EPhoneNumber=findViewById(R.id.phonenumber);
        ECity=findViewById(R.id.address);
        Eemail=findViewById(R.id.email);
        Ecngno=findViewById(R.id.cngno);
        Epass=findViewById(R.id.pass);

        prefs = this.getSharedPreferences("com.example.cng_gas", Context.MODE_PRIVATE);
        sp=getSharedPreferences("logged",MODE_PRIVATE);
        //end data get

        //get the sp data
        SharedPreferences prefs = this.getSharedPreferences("com.example.cng_gas", Context.MODE_PRIVATE);
        cusname= prefs.getString("CustomerName", "");
        cusphone= prefs.getString("CustomerPhoneNumber", "");
        cuscity= prefs.getString("CustomerCity", "");
        cusemail= prefs.getString("CustomerEmail", "");
        cuscngno= prefs.getString("CustomerCngno", "");
        cuspass=prefs.getString("Customerpass","");

//        EName.setText(cusname);
//        EPhoneNumber.setText(cusphone);
//        ECity.setText(cuscity);
//        Epass.setText(cuspass);
//        Eemail.setText(cusemail);
//        Ecngno.setText(cuscngno);

        //end sp data

//        //check if Login
//        if(sp.getBoolean("logged", true)){
//            Intent intent = new Intent(getApplicationContext(),login.class);
//            startActivity(intent);
//            finish();
//        }
        //check if Login end

    }


    //customer login start
    public void Register(View view) {
        //get all data
        Name=EName.getText().toString();
        PhoneNumber=EPhoneNumber.getText().toString();
        City=ECity.getText().toString();
        Email=Eemail.getText().toString();
        Cngno=Ecngno.getText().toString();
        password=Epass.getText().toString();
        //get all data end
        if(password.isEmpty() || password.length() < 8){
            Epass.setError("Please Enter Password containing atleast 8 digit");
            Epass.requestFocus();
        }
        else if(!Name.equals("") && !PhoneNumber.equals("") && !City.equals("") && !Email.equals("")){
            //firebase start

            Customerid= password;
            databaseCus = FirebaseDatabase.getInstance();
            Customer = databaseCus.getReference("Users").child(Customerid);

            Customer.child("Name").setValue(Name);
            Customer.child("Phone Number").setValue(PhoneNumber);
            Customer.child("Password").setValue(password);
            Customer.child("City").setValue(City);
            Customer.child("Email").setValue(Email);
            Customer.child("Cng Number").setValue(Cngno);

            //firebase end

            //shared p
            sp.edit().putBoolean("logged",true).apply();
            prefs.edit().putString("CustomerName",Name).apply();
            prefs.edit().putString("CustomerPhoneNumber",PhoneNumber).apply();
            prefs.edit().putString("CustomerCity",City).apply();
            prefs.edit().putString("CustomerEmail",Email).apply();
            prefs.edit().putString("Customerpass",password).apply();
            prefs.edit().putString("CustomerCngno",Cngno).apply();
            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
            Intent intent3 = new Intent(getApplicationContext(),login.class);
            startActivity(intent3);


            // shared o end
        }
        else {
            Toast.makeText(this, "Enter all fields to Register", Toast.LENGTH_LONG).show();
        }

    }
    //customer login end

    //admin login start
    public void adminlogin(View view) {
        Intent admin = new Intent(getApplicationContext(),AdminLogin.class);
        startActivity(admin);

    }
    //admin login end

    //admin login start
    public void viewareacode(View view) {
        Intent viewarea = new Intent(getApplicationContext(),ViewArea.class);
        startActivity(viewarea);

    }

    public void Login(View view) {
        Intent login1 = new Intent(getApplicationContext(),login.class);
        startActivity(login1);

    }

    //admin login end
}