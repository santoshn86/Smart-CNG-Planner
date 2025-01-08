package com.example.cng_gas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class view_user extends AppCompatActivity {
    ListView listv;
    DatabaseReference reference;
    ArrayList<String> myArraylist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        ArrayAdapter<String> myArrayAdapter= new ArrayAdapter<String>(view_user.this, android.R.layout.simple_expandable_list_item_1,myArraylist);

        listv=findViewById(R.id.listv);
        listv.setAdapter(myArrayAdapter);

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        reference = FirebaseDatabase.getInstance().getReference("Users");



//        DatabaseReference ref1= FirebaseDatabase.getInstance().getReference();
//        DatabaseReference ref2;
//        ref2 = ref1.child("Users");
        reference=FirebaseDatabase.getInstance().getReference("Users");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {


                    String name= snapshot.child("Name").getValue(String.class);
                    String city=snapshot.child("City").getValue(String.class);
                    String cng= snapshot.child("Cng Number").getValue(String.class);
                    String phone=snapshot.child("Phone Number").getValue(String.class);
                   // Toast.makeText(view_user.this, String.valueOf(dsp.getValue()), Toast.LENGTH_SHORT).show();
                    String value="Name: "+name+"\nMobile No: "+phone+"\nCity: "
                            +city+"      Cng Number: "+cng+"\n ______________________________________________________________ ";
                    myArrayAdapter.add(value);

                    myArrayAdapter.notifyDataSetChanged();
                }
//                display();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
//void display(){
//    LinearLayout linearLayout=findViewById(R.id.l);
//    //setContentView(linearLayout);
//    //linearLayout.setOrientation(LinearLayout.VERTICAL);
//    for (int counter = 0; counter < Userlist.size(); counter++) {
//        TextView textView = new TextView(this);
//        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT));
//        textView.setText(Userlist.get(counter));
//        textView.setTextSize(20);
////        textView.setBackgroundColor(blu); // hex color 0xAARRGGBB
//        textView.setPadding(20, 20, 20, 20);
//        linearLayout.addView(textView);
//
//        //Toast.makeText(this, Userlist., Toast.LENGTH_SHORT).show();
//    }
//}


    public void back(View view) {
        finish();
    }
}
