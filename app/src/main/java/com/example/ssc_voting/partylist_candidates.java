package com.example.ssc_voting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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

public class partylist_candidates extends AppCompatActivity {

    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView;
    List<CandidatesData> dataList;
    ImageView backBTN;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partylist_candidates);
        backBTN = findViewById(R.id.backbtn);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        viewCandidates();
    }
    public void viewCandidates(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(partylist_candidates.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(partylist_candidates.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();
        MyAdapter adapter = new MyAdapter(partylist_candidates.this,dataList);
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        String partylistName = intent.getStringExtra("partylistname");

        databaseReference = FirebaseDatabase.getInstance().getReference("candidates").child(partylistName);
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                Iterator var2 = snapshot.getChildren().iterator();

                while(var2.hasNext()) {
                    DataSnapshot itemSnapshot = (DataSnapshot)var2.next();
                    CandidatesData dataClass = (CandidatesData) itemSnapshot.getValue(CandidatesData.class);

                    dataList.add(dataClass);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });
    }
}