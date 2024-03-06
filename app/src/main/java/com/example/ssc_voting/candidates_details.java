package com.example.ssc_voting;

import static com.example.ssc_voting.R.id.candidate_photo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class candidates_details extends AppCompatActivity {

    ImageView candidatesPhoto,backBTN;
    TextView lastname,course,gendeR,firstname,posiTion;
    public candidates_details() {
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidates_details);

        backBTN = findViewById(R.id.backbtn);

        this.candidatesPhoto = (ImageView)this.findViewById(candidate_photo);
        this.firstname = (TextView)this.findViewById(R.id.firstname);
        this.lastname = (TextView)this.findViewById(R.id.lastname);
        this.gendeR = (TextView)this.findViewById(R.id.gender);
        this.course = (TextView)this.findViewById(R.id.course_section);
        this.posiTion = (TextView)this.findViewById(R.id.position);
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            this.firstname.setText(bundle.getString("firstname"));
            this.lastname.setText(bundle.getString("lastname"));
            this.gendeR.setText(bundle.getString("gender"));
            this.course.setText(bundle.getString("course_section"));
            this.posiTion.setText(bundle.getString("position"));
            Glide.with(this).load(bundle.getString("profileImage")).into(this.candidatesPhoto);
        }

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}