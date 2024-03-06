package com.example.ssc_voting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class view_partylist extends AppCompatActivity {

    CardView risecardview;
    ImageView backBTN;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView;
    List<PartylistData> dataList;
    AdapterPartylist adapter;
    public view_partylist() {
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_partylist);

        backBTN = findViewById(R.id.backbtn);
        this.recyclerView = (RecyclerView)this.findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        this.recyclerView.setLayoutManager(gridLayoutManager);
        AlertDialog.Builder builder = new AlertDialog.Builder(view_partylist.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        this.dataList = new ArrayList();
        this.adapter = new AdapterPartylist(this, this.dataList);
        this.recyclerView.setAdapter(this.adapter);
        this.databaseReference = FirebaseDatabase.getInstance().getReference("partylist");
        this.eventListener = this.databaseReference.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                view_partylist.this.dataList.clear();
                Iterator var2 = snapshot.getChildren().iterator();

                while(var2.hasNext()) {
                    DataSnapshot itemSnapshot = (DataSnapshot)var2.next();
                    PartylistData dataClass = (PartylistData)itemSnapshot.getValue(PartylistData.class);
                    view_partylist.this.dataList.add(dataClass);
                }

                view_partylist.this.adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}