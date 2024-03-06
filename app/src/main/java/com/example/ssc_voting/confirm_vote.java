package com.example.ssc_voting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class confirm_vote extends AppCompatActivity {

    TextView dateTime,presidentDisplay,internalVPDisplay,externalVPDisplay,secretaryDisplay,treasurerDisplay,auditorDisplay,pioDisplay,projectmanagerDisplay,senatorDisplay;
    Button cancelButton,submitButton;
    DatabaseReference databaseReference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_vote);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        dateTime = findViewById(R.id.dateandtime);
        presidentDisplay = findViewById(R.id.presidentVote);
        internalVPDisplay = findViewById(R.id.InternalVPVote);
        externalVPDisplay = findViewById(R.id.ExternalVPVote);
        secretaryDisplay = findViewById(R.id.SecretaryVote);
        treasurerDisplay = findViewById(R.id.TreasurerVote);
        auditorDisplay = findViewById(R.id.AuditorVote);
        pioDisplay = findViewById(R.id.PIOVote);
        projectmanagerDisplay = findViewById(R.id.ProjectManagerVote);
        senatorDisplay = findViewById(R.id.SenatorsVote);
        cancelButton = findViewById(R.id.cancelbtn);
        submitButton = findViewById(R.id.submitbtn);

        Intent intentt = getIntent();
        String president_fullname = intentt.getStringExtra("president_fullname");

        String vpinternal_fullname = intentt.getStringExtra("vpinternal_fullname");

        String vpexternal_fullname = intentt.getStringExtra("vpexternal_fullname");


        String secretary_fullname = intentt.getStringExtra("secretary_fullname");

        String treasurer_fullname = intentt.getStringExtra("treasurer_fullname");

        String auditor_fullname = intentt.getStringExtra("auditor_fullname");

        String pio_fullname = intentt.getStringExtra("pio_fullname");

        String projectmanager_fullname = intentt.getStringExtra("projectmanager_fullname");
        String senator_fullname = intentt.getStringExtra("senator_fullname");

        if(!president_fullname.equals("null")){
            presidentDisplay.setText(president_fullname);
        }else{
            presidentDisplay.setText("--------------------");
        }

        if(!vpinternal_fullname.equals("null")){
            internalVPDisplay.setText(vpinternal_fullname);
        }else{
            internalVPDisplay.setText("--------------------");
        }

        if(!vpexternal_fullname.equals("null")){
            externalVPDisplay.setText(vpexternal_fullname);
        }else{
            externalVPDisplay.setText("--------------------");
        }

        if(!secretary_fullname.equals("null")){
            secretaryDisplay.setText(secretary_fullname);
        }else{
            secretaryDisplay.setText("--------------------");
        }

        if(!treasurer_fullname.equals("null")){
            treasurerDisplay.setText(treasurer_fullname);
        }else{
            treasurerDisplay.setText("--------------------");
        }

        if(!auditor_fullname.equals("null")){
            auditorDisplay.setText(auditor_fullname);
        }else{
            auditorDisplay.setText("--------------------");
        }

        if(!pio_fullname.equals("null")){
            pioDisplay.setText(pio_fullname);
        }else{
            pioDisplay.setText("--------------------");
        }

        if(!projectmanager_fullname.equals("null")){
            projectmanagerDisplay.setText(projectmanager_fullname);
        }else{
            projectmanagerDisplay.setText("--------------------");
        }

        if(!senator_fullname.equals("null")){
            senatorDisplay.setText(senator_fullname);
        }else{
            senatorDisplay.setText("--------------------");
        }
        displayDateTime();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmationDialog();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
    public void displayDateTime(){
        LocalDateTime currentDateTime = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentDateTime = LocalDateTime.now();
        }

        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        }

        String formattedDateTime = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formattedDateTime = currentDateTime.format(formatter);
        }
        dateTime.setText(formattedDateTime);
    }
    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Vote Submission");
        builder.setMessage("Are you sure you want to submit your vote?");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AlertDialog.Builder builder = new AlertDialog.Builder(confirm_vote.this);
                builder.setCancelable(false);
                builder.setView(R.layout.progress_layout);
                AlertDialog dialog1 = builder.create();
                dialog1.show();

                Intent intentt = getIntent();

                String president_firstname = intentt.getStringExtra("president_firstname");
                String vpinternal_firstname = intentt.getStringExtra("vpinternal_firstname");
                String vpexternal_firstname = intentt.getStringExtra("vpexternal_firstname");
                String secretary_firstname = intentt.getStringExtra("secretary_firstname");
                String treasurer_firstname = intentt.getStringExtra("treasurer_firstname");
                String auditor_firstname = intentt.getStringExtra("auditor_firstname");
                String pio_firstname = intentt.getStringExtra("pio_firstname");
                String projectmanager_firstname = intentt.getStringExtra("projectmanager_firstname");
                String senator_firstname = intentt.getStringExtra("senator_firstname");
                String course_section = intentt.getStringExtra("course_section");

                if (!president_firstname.equals("null")){
                    updatePositionVoteCount("president", president_firstname);
                }
                if (!vpinternal_firstname.equals("null")){
                    updatePositionVoteCount("internal_vice_president", vpinternal_firstname);
                }
                if (!vpexternal_firstname.equals("null")){
                    updatePositionVoteCount("external_vice_president", vpexternal_firstname);
                }
                if (!secretary_firstname.equals("null")){
                    updatePositionVoteCount("secretary", secretary_firstname);
                }
                if (!treasurer_firstname.equals("null")){
                    updatePositionVoteCount("treasurer", treasurer_firstname);
                }
                if (!auditor_firstname.equals("null")){
                    updatePositionVoteCount("auditor", auditor_firstname);
                }
                if (!pio_firstname.equals("null")){
                    updatePositionVoteCount("pio", pio_firstname);
                }
                if (!projectmanager_firstname.equals("null")){
                    updatePositionVoteCount("project_manager", projectmanager_firstname);
                }
                if (!senator_firstname.equals("null")){
                    updateSenatorVoteCount(course_section,"senator",senator_firstname);
                }
                dialog1.dismiss();
                showSubmissionSuccessDialog();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showSubmissionSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Vote Submitted");
        builder.setMessage("Thank you for submitting your vote!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateVotingStatus();
                navigateToHomeActivity();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void updatePositionVoteCount(String position, String firstName) {
        DatabaseReference positionRef = databaseReference.child("voting").child(position).child(firstName);
        positionRef.child("voteCounts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    try {
                        long currentVoteCount = snapshot.getValue(Long.class);

                        long newVoteCount = currentVoteCount + 1;


                        positionRef.child("voteCounts").setValue(newVoteCount);
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void updateSenatorVoteCount(String course,String position, String firstName) {
        DatabaseReference positionRef = databaseReference.child("voting").child(position).child(course).child(firstName);
        positionRef.child("voteCounts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    try {
                        long currentVoteCount = snapshot.getValue(Long.class);

                        long newVoteCount = currentVoteCount + 1;


                        positionRef.child("voteCounts").setValue(newVoteCount);
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void navigateToHomeActivity() {
        Intent intentt = getIntent();
        String firstname = intentt.getStringExtra("firstname");
        String lastname = intentt.getStringExtra("lastname");
        String Image = intentt.getStringExtra("profilePic");
        String userStudID = intentt.getStringExtra("studentID");
        String course_section = intentt.getStringExtra("course_section");
        String password = intentt.getStringExtra("password");
        String email = intentt.getStringExtra("email");


        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        final android.app.AlertDialog dialog = builder.create();
        dialog.show();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("studentID").equalTo(userStudID);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    String firstnameFromDB = (String)snapshot.child(userStudID).child("firstname").getValue(String.class);
                    String lastnameFromDB = (String)snapshot.child(userStudID).child("lastname").getValue(String.class);
                    String studentIDFromDB = (String)snapshot.child(userStudID).child("studentID").getValue(String.class);
                    String course_sectionFromDB = (String)snapshot.child(userStudID).child("course_section").getValue(String.class);
                    String emailFromDB = (String)snapshot.child(userStudID).child("email").getValue(String.class);
                    String profilepicFromDB = (String)snapshot.child(userStudID).child("profileImage").getValue(String.class);
                    String votingstatusFromDB = (String)snapshot.child(userStudID).child("voting_status").getValue(String.class);
                    Intent intent = new Intent(confirm_vote.this, home.class);
                    intent.putExtra("firstname", firstnameFromDB);
                    intent.putExtra("lastname", lastnameFromDB);
                    intent.putExtra("studentID", studentIDFromDB);
                    intent.putExtra("course_section", course_sectionFromDB);
                    intent.putExtra("email", emailFromDB);
                    intent.putExtra("profilePic", profilepicFromDB);
                    intent.putExtra("voting_status", votingstatusFromDB);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    dialog.dismiss();
                    confirm_vote.this.startActivity(intent);
                    finish();

                } else {
                    dialog.dismiss();
                }

            }

            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public void updateVotingStatus() {

        Intent intentt = getIntent();
        String student_id = intentt.getStringExtra("studentID");
        DatabaseReference votingStatusReference = databaseReference.child("users").child(student_id).child("voting_status");


        votingStatusReference.setValue("1");
    }

}
