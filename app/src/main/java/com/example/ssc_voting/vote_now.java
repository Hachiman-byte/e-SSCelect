package com.example.ssc_voting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class vote_now extends AppCompatActivity {
    TextView courseView;
    RecyclerView recyclerViewPresident,recyclerViewVPInternal,recyclerViewVPExternal,recyclerSecretary,recyclerViewTreasurer,recyclerViewAuditor,recyclerViewPIO,recyclerViewPm,recyclerViewSenators;
    List<CandidatesData> dataList1,dataList2,dataList3,dataList4,dataList5,dataList6,dataList7,dataList8,dataList9;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    Button nextButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_now);
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
        nextButton = findViewById(R.id.nextBTN);

        PresidentRecylerView();
        VPInternalRecyclerView();
        VPExternalRecyclerView();
        SecretaryRecyclerView();
        TreasurerRecyclerView();
        AuditorRecyclerView();
        PIORecyclerView();
        PMRecyclerView();
        SenatorsRecyclerView();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(vote_now.this);
                builder.setCancelable(false);
                builder.setView(R.layout.progress_layout);
                final android.app.AlertDialog dialog = builder.create();
                dialog.show();

                Intent intentt = getIntent();

                String firstname = intentt.getStringExtra("firstname");
                String lastname = intentt.getStringExtra("lastname");
                String Image = intentt.getStringExtra("profilePic");
                String student_id = intentt.getStringExtra("studentID");
                String course_section = intentt.getStringExtra("course_section");
                String password = intentt.getStringExtra("password");
                String email = intentt.getStringExtra("email");


                CandidatesData president = PresidentAdapter.getSelectedCandidate();
                CandidatesData vpinternal = VPInternalAdapter.getSelectedCandidate();
                CandidatesData vpexternal = VPExternalAdapter.getSelectedCandidate();
                CandidatesData secretary = SecretaryAdapter.getSelectedCandidate();
                CandidatesData treasurer = TreasurerAdapter.getSelectedCandidate();
                CandidatesData auditor = AuditorAdapter.getSelectedCandidate();
                CandidatesData pio = PIOAdapter.getSelectedCandidate();
                CandidatesData projectmanager = ProjectManagerAdapter.getSelectedCandidate();
                CandidatesData senator = SenatorAdapter.getSelectedCandidate();

                Intent intent = new Intent(vote_now.this, confirm_vote.class);

                if (president != null) {
                    intent.putExtra("president_fullname", president.getLastname() + ", " + president.getFirstname());
                    intent.putExtra("president_firstname", president.getFirstname());
                } else {
                    intent.putExtra("president_fullname", "null");
                    intent.putExtra("president_firstname", "null");
                }

                if (vpinternal != null) {
                    intent.putExtra("vpinternal_fullname", vpinternal.getLastname() + ", " + vpinternal.getFirstname());
                    intent.putExtra("vpinternal_firstname", vpinternal.getFirstname());
                } else {
                    intent.putExtra("vpinternal_fullname", "null");
                    intent.putExtra("vpinternal_firstname", "null");
                }

                if (vpexternal != null) {
                    intent.putExtra("vpexternal_fullname", vpexternal.getLastname()+", "+vpexternal.getFirstname());
                    intent.putExtra("vpexternal_firstname", vpexternal.getFirstname());
                } else {
                    intent.putExtra("vpexternal_fullname", "null");
                    intent.putExtra("vpexternal_firstname", "null");
                }

                if (secretary != null) {
                    intent.putExtra("secretary_fullname", secretary.getLastname()+", "+secretary.getFirstname());
                    intent.putExtra("secretary_firstname", secretary.getFirstname());
                } else {
                    intent.putExtra("secretary_fullname", "null");
                    intent.putExtra("secretary_firstname", "null");
                }

                if (treasurer != null) {
                    intent.putExtra("treasurer_fullname", treasurer.getLastname()+", "+treasurer.getFirstname());
                    intent.putExtra("treasurer_firstname", treasurer.getFirstname());
                } else {
                    intent.putExtra("treasurer_fullname", "null");
                    intent.putExtra("treasurer_firstname", "null");
                }

                if (auditor != null) {
                    intent.putExtra("auditor_fullname", auditor.getLastname()+", "+auditor.getFirstname());
                    intent.putExtra("auditor_firstname", auditor.getFirstname());
                } else {
                    intent.putExtra("auditor_fullname", "null");
                    intent.putExtra("auditor_firstname", "null");
                }

                if (pio != null) {
                    intent.putExtra("pio_fullname", pio.getLastname()+", "+pio.getFirstname());
                    intent.putExtra("pio_firstname", pio.getFirstname());
                } else {
                    intent.putExtra("pio_fullname", "null");
                    intent.putExtra("pio_firstname", "null");
                }

                if (projectmanager != null) {
                    intent.putExtra("projectmanager_fullname", projectmanager.getLastname()+", "+projectmanager.getFirstname());
                    intent.putExtra("projectmanager_firstname", projectmanager.getFirstname());
                } else {
                    intent.putExtra("projectmanager_fullname", "null");
                    intent.putExtra("projectmanager_firstname", "null");
                }

                if (senator != null) {
                    intent.putExtra("senator_fullname", senator.getLastname()+", "+senator.getFirstname());
                    intent.putExtra("senator_firstname", senator.getFirstname());
                } else {
                    intent.putExtra("senator_fullname", "null");
                    intent.putExtra("senator_firstname", "null");
                }


                intent.putExtra("firstname", firstname);
                intent.putExtra("lastname", lastname);
                intent.putExtra("studentID", student_id);
                intent.putExtra("course_section", course_section);
                intent.putExtra("password", password);
                intent.putExtra("email", email);
                intent.putExtra("profilePic", Image);
                dialog.dismiss();
                vote_now.this.startActivity(intent);

            }
        });
    }
    
    public void PresidentRecylerView(){
        AlertDialog.Builder builder = new AlertDialog.Builder(vote_now.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(vote_now.this, 1);
        recyclerViewPresident.setLayoutManager(gridLayoutManager);

        dataList1 = new ArrayList<>();
        PresidentAdapter adapter1 = new PresidentAdapter(vote_now.this,dataList1);
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(vote_now.this, 1);
        recyclerViewVPInternal.setLayoutManager(gridLayoutManager);


        dataList2 = new ArrayList<>();
        VPInternalAdapter adapter2 = new VPInternalAdapter(vote_now.this,dataList2);
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(vote_now.this, 1);
        recyclerViewVPExternal.setLayoutManager(gridLayoutManager);


        dataList3 = new ArrayList<>();
        VPExternalAdapter adapter3 = new VPExternalAdapter(vote_now.this,dataList3);
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(vote_now.this, 1);
        recyclerSecretary.setLayoutManager(gridLayoutManager);

        dataList4 = new ArrayList<>();
        SecretaryAdapter adapter4 = new SecretaryAdapter(vote_now.this,dataList4);
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(vote_now.this, 1);
        recyclerViewTreasurer.setLayoutManager(gridLayoutManager);



        dataList5 = new ArrayList<>();
        TreasurerAdapter adapter5 = new TreasurerAdapter(vote_now.this,dataList5);
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(vote_now.this, 1);
        recyclerViewAuditor.setLayoutManager(gridLayoutManager);

        dataList6 = new ArrayList<>();
        AuditorAdapter adapter6 = new AuditorAdapter(vote_now.this,dataList6);
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(vote_now.this, 1);
        recyclerViewPIO.setLayoutManager(gridLayoutManager);

        dataList7 = new ArrayList<>();
        PIOAdapter adapter7 = new PIOAdapter(vote_now.this,dataList7);
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(vote_now.this, 1);
        recyclerViewPm.setLayoutManager(gridLayoutManager);

        dataList8 = new ArrayList<>();
        ProjectManagerAdapter adapter8 = new ProjectManagerAdapter(vote_now.this,dataList8);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(vote_now.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();

        Intent intentt = getIntent();
        String course = intentt.getStringExtra("course_section");

        courseView.setText("("+course+")");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(vote_now.this, 1);
        recyclerViewSenators.setLayoutManager(gridLayoutManager);

        dataList9 = new ArrayList<>();
        SenatorAdapter adapter9 = new SenatorAdapter(vote_now.this,dataList9);
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