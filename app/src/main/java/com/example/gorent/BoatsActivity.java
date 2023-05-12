package com.example.gorent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class BoatsActivity extends AppCompatActivity {

    ImageView homeicon;
    ImageView offersicon;
    ImageView addicon;
    ImageView basketicon;
    ImageView logouticon;


    RecyclerView recyclerView;

    ArrayList<String> model,type,rent;

    DBHelperr DB;

    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boats);

        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("userEmail");

        homeicon= (ImageView) findViewById(R.id.homeicon);
        homeicon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(BoatsActivity.this, HomeAvtivity.class);
                i1.putExtra("userEmail",userEmail);
                startActivity(i1);
            }
        });

        offersicon= (ImageView) findViewById(R.id.listicon);
        offersicon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(BoatsActivity.this, OffersActivity.class);
                i2.putExtra("userEmail",userEmail);
                startActivity(i2);
            }
        });

        addicon= (ImageView) findViewById(R.id.addicon);
        addicon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(BoatsActivity.this, AddActivity.class);
                i3.putExtra("userEmail",userEmail);
                startActivity(i3);;
            }
        });

        basketicon= (ImageView) findViewById(R.id.basketicon);
        basketicon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i4 = new Intent(BoatsActivity.this, RentedActivity.class);
                i4.putExtra("userEmail",userEmail);
                startActivity(i4);
            }
        });



        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Log out");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent =  new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No",null);


        logouticon= (ImageView) findViewById(R.id.logouticon);
        logouticon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                builder.show();

            }
        });


        DB = new DBHelperr(this);
        model=new ArrayList<>();
        type=new ArrayList<>();
        rent=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerview);
        adapter=new MyAdapter(this,model,rent);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();




    }

    private void displaydata() {
        Cursor cursor = DB.getdata();
        if(cursor.getCount()==0){
            Toast.makeText(this, "No Boats for rent", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            while(cursor.moveToNext()){
                if (cursor.getString(3).equals("Boat")) {
                    model.add(cursor.getString(2));
                    rent.add(cursor.getString(6));
                }
            }
        }


    }
}