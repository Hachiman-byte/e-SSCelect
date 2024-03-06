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
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class result extends AppCompatActivity {
    RecyclerView recyclerViewPresident,recyclerViewVPInternal,recyclerViewVPExternal,recyclerSecretary,recyclerViewTreasurer,recyclerViewAuditor,recyclerViewPIO,recyclerViewPm,recyclerViewSenators;
    List<CandidatesData> dataList1,dataList2,dataList3,dataList4,dataList5,dataList6,dataList7,dataList8,dataList9;
    TextView courseView;
    ImageView backBTN;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        backBTN = findViewById(R.id.backbtn);
        recyclerViewPresident = findViewById(R.id.recyclerViewPresident);
        recyclerViewVPInternal = findViewById(R.id.recyclerViewVPinternal);
        recyclerViewVPExternal = findViewById(R.id.recyclerViewVPExternal);
        recyclerSecretary = findViewById(R.id.recyclerSecretary);
        recyclerViewTreasurer = findViewById(R.id.recyclerTreasurer);
        recyclerViewAuditor = findViewById(R.id.recyclerAuditor);
        recyclerViewPIO = findViewById(R.id.recyclerPIO);
        recyclerViewPm = findViewById(R.id.recyclerPM);
        recyclerViewSenators = findViewById(R.id.recyclerSenators);
        courseView = findViewById(R.id.senatorcourse);

        PresidentRecylerView();
        VPInternalRecyclerView();
        VPExternalRecyclerView();
        SecretaryRecyclerView();
        TreasurerRecyclerView();
        AuditorRecyclerView();
        PIORecyclerView();
        PMRecyclerView();
        SenatorsRecyclerView();

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    public void PresidentRecylerView(){
        AlertDialog.Builder builder = new AlertDialog.Builder(result.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(result.this, 1);
        recyclerViewPresident.setLayoutManager(gridLayoutManager);

        dataList1 = new ArrayList<>();
        resultPresidentAdapter adapter1 = new resultPresidentAdapter(result.this,dataList1);
        recyclerViewPresident.setAdapter(adapter1);
        databaseReference = FirebaseDatabase.getInstance().getReference("voting").child("president");

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList1.clear();
                Iterator var2 = snapshot.getChildren().iterator();

                while(var2.hasNext()) {
                    DataSnapshot itemSnapshot = (DataSnapshot)var2.next();
                    CandidatesData dataClass = (CandidatesData) itemSnapshot.getValue(CandidatesData.class);

                    dataList1.add(dataClass);
                }
                adapter1.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });
    }
    public void VPInternalRecyclerView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(result.this, 1);
        recyclerViewVPInternal.setLayoutManager(gridLayoutManager);


        dataList2 = new ArrayList<>();
        resultVPInternalAdapter adapter2 = new resultVPInternalAdapter(result.this,dataList2);
        recyclerViewVPInternal.setAdapter(adapter2);
        databaseReference = FirebaseDatabase.getInstance().getReference("voting").child("internal_vice_president");

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList2.clear();
                Iterator var2 = snapshot.getChildren().iterator();

                while(var2.hasNext()) {
                    DataSnapshot itemSnapshot = (DataSnapshot)var2.next();
                    CandidatesData dataClass = (CandidatesData) itemSnapshot.getValue(CandidatesData.class);

                    dataList2.add(dataClass);
                }
                adapter2.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void VPExternalRecyclerView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(result.this, 1);
        recyclerViewVPExternal.setLayoutManager(gridLayoutManager);


        dataList3 = new ArrayList<>();
        resultVPExternalAdapter adapter3 = new resultVPExternalAdapter(result.this,dataList3);
        recyclerViewVPExternal.setAdapter(adapter3);
        databaseReference = FirebaseDatabase.getInstance().getReference("voting").child("external_vice_president");

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList3.clear();
                Iterator var2 = snapshot.getChildren().iterator();

                while(var2.hasNext()) {
                    DataSnapshot itemSnapshot = (DataSnapshot)var2.next();
                    CandidatesData dataClass = (CandidatesData) itemSnapshot.getValue(CandidatesData.class);

                    dataList3.add(dataClass);
                }
                adapter3.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void SecretaryRecyclerView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(result.this, 1);
        recyclerSecretary.setLayoutManager(gridLayoutManager);

        dataList4 = new ArrayList<>();
        resultSecretaryAdapter adapter4 = new resultSecretaryAdapter(result.this,dataList4);
        recyclerSecretary.setAdapter(adapter4);
        databaseReference = FirebaseDatabase.getInstance().getReference("voting").child("secretary");

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList4.clear();
                Iterator var2 = snapshot.getChildren().iterator();

                while(var2.hasNext()) {
                    DataSnapshot itemSnapshot = (DataSnapshot)var2.next();
                    CandidatesData dataClass = (CandidatesData) itemSnapshot.getValue(CandidatesData.class);

                    dataList4.add(dataClass);
                }
                adapter4.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void TreasurerRecyclerView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(result.this, 1);
        recyclerViewTreasurer.setLayoutManager(gridLayoutManager);



        dataList5 = new ArrayList<>();
        resultTreasurerAdapter adapter5 = new resultTreasurerAdapter(result.this,dataList5);
        recyclerViewTreasurer.setAdapter(adapter5);
        databaseReference = FirebaseDatabase.getInstance().getReference("voting").child("treasurer");

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList5.clear();
                Iterator var2 = snapshot.getChildren().iterator();

                while(var2.hasNext()) {
                    DataSnapshot itemSnapshot = (DataSnapshot)var2.next();
                    CandidatesData dataClass = (CandidatesData) itemSnapshot.getValue(CandidatesData.class);

                    dataList5.add(dataClass);
                }
                adapter5.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void AuditorRecyclerView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(result.this, 1);
        recyclerViewAuditor.setLayoutManager(gridLayoutManager);

        dataList6 = new ArrayList<>();
        resultAuditorAdapter adapter6 = new resultAuditorAdapter(result.this,dataList6);
        recyclerViewAuditor.setAdapter(adapter6);
        databaseReference = FirebaseDatabase.getInstance().getReference("voting").child("auditor");

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList6.clear();
                Iterator var2 = snapshot.getChildren().iterator();

                while(var2.hasNext()) {
                    DataSnapshot itemSnapshot = (DataSnapshot)var2.next();
                    CandidatesData dataClass = (CandidatesData) itemSnapshot.getValue(CandidatesData.class);

                    dataList6.add(dataClass);
                }
                adapter6.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void PIORecyclerView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(result.this, 1);
        recyclerViewPIO.setLayoutManager(gridLayoutManager);

        dataList7 = new ArrayList<>();
        resultPIOAdapter adapter7 = new resultPIOAdapter(result.this,dataList7);
        recyclerViewPIO.setAdapter(adapter7);
        databaseReference = FirebaseDatabase.getInstance().getReference("voting").child("pio");

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList7.clear();
                Iterator var2 = snapshot.getChildren().iterator();

                while(var2.hasNext()) {
                    DataSnapshot itemSnapshot = (DataSnapshot)var2.next();
                    CandidatesData dataClass = (CandidatesData) itemSnapshot.getValue(CandidatesData.class);

                    dataList7.add(dataClass);
                }
                adapter7.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void PMRecyclerView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(result.this, 1);
        recyclerViewPm.setLayoutManager(gridLayoutManager);

        dataList8 = new ArrayList<>();
        resultPMAdapter adapter8 = new resultPMAdapter(result.this,dataList8);
        recyclerViewPm.setAdapter(adapter8);
        databaseReference = FirebaseDatabase.getInstance().getReference("voting").child("project_manager");

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList8.clear();
                Iterator var2 = snapshot.getChildren().iterator();

                while(var2.hasNext()) {
                    DataSnapshot itemSnapshot = (DataSnapshot)var2.next();
                    CandidatesData dataClass = (CandidatesData) itemSnapshot.getValue(CandidatesData.class);

                    dataList8.add(dataClass);
                }
                adapter8.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void SenatorsRecyclerView(){

        AlertDialog.Builder builder = new AlertDialog.Builder(result.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();

        Intent intentt = getIntent();
        String course = intentt.getStringExtra("course_section");

        courseView.setText("("+course+")");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(result.this, 1);
        recyclerViewSenators.setLayoutManager(gridLayoutManager);

        dataList9 = new ArrayList<>();
        resultSenatorsAdapter adapter9 = new resultSenatorsAdapter(result.this,dataList9);
        recyclerViewSenators.setAdapter(adapter9);
        databaseReference = FirebaseDatabase.getInstance().getReference("voting").child("senator").child(course);

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList9.clear();
                Iterator var2 = snapshot.getChildren().iterator();

                while(var2.hasNext()) {
                    DataSnapshot itemSnapshot = (DataSnapshot)var2.next();
                    CandidatesData dataClass = (CandidatesData) itemSnapshot.getValue(CandidatesData.class);

                    dataList9.add(dataClass);
                }
                adapter9.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });
    }
}