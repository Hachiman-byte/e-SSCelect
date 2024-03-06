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
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class change_password extends AppCompatActivity {
    Button changePassword;
    EditText newpass,retypepass,currentpass;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        changePassword = findViewById(R.id.changebtn);
        newpass = findViewById(R.id.edittextNewpass);
        retypepass = findViewById(R.id.edittextRetypepass);
        currentpass = findViewById(R.id.edittextCurrentpass);

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmationDialog();
            }
        });


    }


    public Boolean validatenewpassword() {
        String newpassword = newpass.getText().toString();
        if (newpassword.isEmpty()) {
            this.newpass.setError("This field cannot be empty");
            return false;
        } else {
            this.newpass.setError((CharSequence)null);
            return true;
        }
    }
    public Boolean validateretypepassword() {
        String newpassword = newpass.getText().toString();
        String retypepassword = retypepass.getText().toString();
        if (retypepassword.isEmpty()) {
            this.retypepass.setError("This field cannot be empty");
            return false;
        }else {
            if (!retypepassword.equals(newpassword)){
                retypepass.setError("Unmatch password");
                return false;
            }
            else{
                this.retypepass.setError((CharSequence)null);
                return true;
            }

        }
    }
    public Boolean validatecurrentpassword() {
        String currentpassword = currentpass.getText().toString();
        Intent intent = getIntent();
        String current_password = intent.getStringExtra("password");

        if (currentpassword.isEmpty()) {
            this.currentpass.setError("This field cannot be empty");
            return false;
        }
        else {
            if(!currentpassword.equals(current_password)){
                currentpass.setError("Incorrect password");
                return false;
            }
            else{
                this.retypepass.setError((CharSequence)null);
                return true;
            }

        }
    }
    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Change Password");
        builder.setMessage("Are you sure you want to change your password?");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AlertDialog.Builder builder = new AlertDialog.Builder(change_password.this);
                builder.setCancelable(false);
                builder.setView(R.layout.progress_layout);
                AlertDialog dialog1 = builder.create();
                dialog1.show();

                if (change_password.this.validatenewpassword() && change_password.this.validateretypepassword() && change_password.this.validatecurrentpassword()) {
                    Intent intentt = getIntent();
                    String studentID = intentt.getStringExtra("studentID");
                    String newpassword = newpass.getText().toString();
                    changePassword(newpassword,studentID);


                }
                dialog1.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked No
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void changePassword(String newpassword, String studentID) {
        DatabaseReference positionRef = databaseReference.child("users").child(studentID);
        positionRef.child("password").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    try {
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(change_password.this);
                        builder.setCancelable(false);
                        builder.setView(R.layout.progress_layout);
                        final android.app.AlertDialog dialog = builder.create();
                        dialog.show();

                        positionRef.child("password").setValue(newpassword);
                        Toast.makeText(change_password.this, "New Password Applied", Toast.LENGTH_SHORT).show();
                        navigateToProfileActivity();
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
    private void navigateToProfileActivity() {
        Intent intentt = getIntent();
        String userStudID = intentt.getStringExtra("studentID");

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
                    String passwordFromDB = (String)snapshot.child(userStudID).child("password").getValue(String.class);
                    Intent intent = new Intent(change_password.this, home.class);
                    intent.putExtra("password", passwordFromDB);
                    intent.putExtra("firstname", firstnameFromDB);
                    intent.putExtra("lastname", lastnameFromDB);
                    intent.putExtra("studentID", studentIDFromDB);
                    intent.putExtra("course_section", course_sectionFromDB);
                    intent.putExtra("email", emailFromDB);
                    intent.putExtra("profilePic", profilepicFromDB);
                    intent.putExtra("voting_status", votingstatusFromDB);
                    dialog.dismiss();
                    change_password.this.startActivity(intent);

                } else {
                    dialog.dismiss();
                }

            }

            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}