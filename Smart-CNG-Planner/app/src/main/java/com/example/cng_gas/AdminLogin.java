package com.example.cng_gas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    EditText ename,eaccesscode;
    String adminname,adminaccesscode;
    SharedPreferences aminaccesscodesp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        ename=findViewById(R.id.adminuser);
        eaccesscode=findViewById(R.id.admincode);
        aminaccesscodesp = this.getSharedPreferences("com.example.cng_gas", Context.MODE_PRIVATE);


    }

    public void login(View view) {
        adminname=ename.getText().toString();
        adminaccesscode=eaccesscode.getText().toString();
        if(adminname.equals("manager1001") && adminaccesscode.equals("1001"))
        {
            aminaccesscodesp.edit().putString("AccessCodeManager",adminaccesscode).apply();
            Intent intent1 = new Intent(getApplicationContext(),AdminPanel.class);
            startActivity(intent1);

        }
        else if(adminname.equals("manager1002") && adminaccesscode.equals("1002"))
        {
            aminaccesscodesp.edit().putString("AccessCodeAdmin",adminaccesscode).apply();
            Intent intent1 = new Intent(getApplicationContext(),AdminPanel.class);
            startActivity(intent1);

        }
        else if(adminname.equals("") && adminaccesscode.equals("")){
            Toast.makeText(this, "Please Enter Manager Username and Password", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Invalid Username and Password", Toast.LENGTH_SHORT).show();

        }


    }

    public void viewareacode(View view) {

        Intent intent4 = new Intent(getApplicationContext(),ViewArea.class);
        startActivity(intent4);

    }

    public void back(View view) {
        finish();
    }
}