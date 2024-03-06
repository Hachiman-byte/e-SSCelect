package com.example.ssc_voting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextInputEditText studId;
    TextInputEditText passWord;
    Button login;
    Button setupBTN;

    public MainActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        studId = (TextInputEditText)this.findViewById(R.id.studid);
        passWord = (TextInputEditText)this.findViewById(R.id.password);
        login = (Button)this.findViewById(R.id.loginbtn);
        setupBTN = (Button)this.findViewById(R.id.setupbtn);
        setupBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, confirm_account.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        });
        this.login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!(!MainActivity.this.validateStudID() | !MainActivity.this.validatePassword())) {
                    MainActivity.this.checkUser();
                }

            }
        });
    }

    public Boolean validateStudID() {
        String val = this.studId.getText().toString();
        if (val.isEmpty()) {
            this.studId.setError("Student ID cannot be empty");
            return false;
        } else {
            this.studId.setError((CharSequence)null);
            return true;
        }
    }

    public Boolean validatePassword() {
        String val = this.passWord.getText().toString();
        if (val.isEmpty()) {
            this.passWord.setError("Password cannot be empty");
            return false;
        } else {
            this.passWord.setError((CharSequence)null);
            return true;
        }
    }
    public void onBackPressed() {

    }
    public void checkUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        final AlertDialog dialog = builder.create();
        dialog.show();
        final String userStudID = this.studId.getText().toString().trim();
        final String userPassword = this.passWord.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("studentID").equalTo(userStudID);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    MainActivity.this.studId.setError((CharSequence)null);
                    String passwordFromDB = (String)snapshot.child(userStudID).child("password").getValue(String.class);
                    String account_statusFromDB = (String)snapshot.child(userStudID).child("account_status").getValue(String.class);
                    if (account_statusFromDB.equals("1")) {
                        if (passwordFromDB.equals(userPassword)) {
                            MainActivity.this.studId.setError((CharSequence)null);
                            String firstnameFromDB = (String)snapshot.child(userStudID).child("firstname").getValue(String.class);
                            String lastnameFromDB = (String)snapshot.child(userStudID).child("lastname").getValue(String.class);
                            String studentIDFromDB = (String)snapshot.child(userStudID).child("studentID").getValue(String.class);
                            String course_sectionFromDB = (String)snapshot.child(userStudID).child("course_section").getValue(String.class);
                            String emailFromDB = (String)snapshot.child(userStudID).child("email").getValue(String.class);
                            String profilepicFromDB = (String)snapshot.child(userStudID).child("profileImage").getValue(String.class);
                            String votingstatusFromDB = (String)snapshot.child(userStudID).child("voting_status").getValue(String.class);
                            Intent intent = new Intent(MainActivity.this, home.class);
                            intent.putExtra("firstname", firstnameFromDB);
                            intent.putExtra("lastname", lastnameFromDB);
                            intent.putExtra("studentID", studentIDFromDB);
                            intent.putExtra("course_section", course_sectionFromDB);
                            intent.putExtra("password", passwordFromDB);
                            intent.putExtra("email", emailFromDB);
                            intent.putExtra("profilePic", profilepicFromDB);
                            intent.putExtra("voting_status", votingstatusFromDB);
                            dialog.dismiss();
                            MainActivity.this.startActivity(intent);
                            finish();
                        } else {
                            dialog.dismiss();
                            MainActivity.this.passWord.setError("Invalid Credentials");
                            MainActivity.this.passWord.requestFocus();
                        }
                    } else {
                        dialog.dismiss();
                        Intent intent = new Intent(MainActivity.this, confirm_account.class);
                        MainActivity.this.startActivity(intent);
                        Toast.makeText(MainActivity.this, "Account isn't activated yet", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    dialog.dismiss();
                    MainActivity.this.studId.setError("User does not exist");
                    MainActivity.this.studId.requestFocus();
                }

            }

            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}