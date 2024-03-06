package com.example.ssc_voting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class confirm_account extends AppCompatActivity {

    TextView loginTextview;
    EditText studID;
    EditText email;
    Button confirmBtn;

    public confirm_account() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_account);
        this.loginTextview = (TextView)this.findViewById(R.id.logintxtview);
        this.studID = (EditText)this.findViewById(R.id.confirm_acc_studid);
        this.email = (EditText)this.findViewById(R.id.confirm_acc_email);
        this.confirmBtn = (Button)this.findViewById(R.id.confirmbtn);
        this.loginTextview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(confirm_account.this, MainActivity.class);
                confirm_account.this.startActivity(intent);
                confirm_account.this.finish();
            }
        });
        this.confirmBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!(!confirm_account.this.validateStudID() | !confirm_account.this.validatePassword())) {
                    confirm_account.this.checkUser();
                }

            }
        });

    }

    public Boolean validateStudID() {
        String val = this.studID.getText().toString();
        if (val.isEmpty()) {
            this.studID.setError("Student ID cannot be empty");
            return false;
        } else {
            this.studID.setError((CharSequence)null);
            return true;
        }
    }

    public Boolean validatePassword() {
        String val = this.email.getText().toString();
        if (val.isEmpty()) {
            this.email.setError("Email cannot be empty");
            return false;
        } else {
            this.email.setError((CharSequence)null);
            return true;
        }
    }

    public void checkUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        final AlertDialog dialog = builder.create();
        dialog.show();
        final String userStudID = this.studID.getText().toString().trim();
        final String userEmail = this.email.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("studentID").equalTo(userStudID);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    confirm_account.this.studID.setError((CharSequence)null);
                    String emailFromDB = (String)snapshot.child(userStudID).child("email").getValue(String.class);
                    String account_statusFromDB = (String)snapshot.child(userStudID).child("account_status").getValue(String.class);
                    if (account_statusFromDB.equals("0")) {
                        if (emailFromDB.equals(userEmail)) {
                            confirm_account.this.studID.setError((CharSequence)null);
                            String studentIDFromDB = (String)snapshot.child(userStudID).child("studentID").getValue(String.class);
                            Intent intentx = new Intent(confirm_account.this, activate_account.class);
                            intentx.putExtra("studentID", studentIDFromDB);
                            intentx.putExtra("email", emailFromDB);
                            dialog.dismiss();
                            confirm_account.this.startActivity(intentx);
                        } else {
                            dialog.dismiss();
                            confirm_account.this.email.setError("Invalid Email");
                            confirm_account.this.email.requestFocus();
                        }
                    } else {
                        dialog.dismiss();
                        Intent intent = new Intent(confirm_account.this, MainActivity.class);
                        confirm_account.this.startActivity(intent);
                        Toast.makeText(confirm_account.this, "Account already activated", 0).show();
                    }
                } else {
                    dialog.dismiss();
                    confirm_account.this.studID.setError("Student ID does not exist");
                    confirm_account.this.studID.requestFocus();
                }

            }

            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}