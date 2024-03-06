package com.example.ssc_voting;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class profile extends AppCompatActivity {

     ImageView backBTN,profilePic;
     TextView first_name,last_name,emaIL,courseSection,studid;
     Button changepasswordBTN;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        backBTN = findViewById(R.id.backbtn);
        profilePic = findViewById(R.id.profile_PIC);
        first_name = findViewById(R.id.firstname);
        last_name = findViewById(R.id.lastname);
        emaIL = findViewById(R.id.emaiL);
        courseSection = findViewById(R.id.course);
        studid = findViewById(R.id.studID);
        changepasswordBTN = findViewById(R.id.changepassword);

        changepasswordBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changepassword();
            }
        });
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        showuserdata();
    }

    public void showuserdata(){

        Intent intent = getIntent();

        String firstname = intent.getStringExtra("firstname");
        String lastname = intent.getStringExtra("lastname");
        String studentid = intent.getStringExtra("studentID");
        String email = intent.getStringExtra("email");
        String course_section = intent.getStringExtra("course_section");
        String Image = intent.getStringExtra("profilePic");
        String password = intent.getStringExtra("password");

        first_name.setText(firstname);
        last_name.setText(lastname);
        emaIL.setText(email);
        courseSection.setText(course_section);
        studid.setText(studentid);
        Glide.with(this).load(Image).into(this.profilePic);
    }
    public void changepassword(){
        Intent intentt = getIntent();

        String firstname = intentt.getStringExtra("firstname");
        String lastname = intentt.getStringExtra("lastname");
        String Image = intentt.getStringExtra("profilePic");
        String student_id = intentt.getStringExtra("studentID");
        String course_section = intentt.getStringExtra("course_section");
        String password = intentt.getStringExtra("password");
        String email = intentt.getStringExtra("email");

        Intent intent = new Intent(profile.this, change_password.class);

        intent.putExtra("firstname", firstname);
        intent.putExtra("lastname", lastname);
        intent.putExtra("studentID", student_id);
        intent.putExtra("course_section", course_section);
        intent.putExtra("password", password);
        intent.putExtra("email", email);
        intent.putExtra("profilePic", Image);
        profile.this.startActivity(intent);
    }
}