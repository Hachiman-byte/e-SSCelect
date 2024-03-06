package com.example.ssc_voting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.audiofx.DynamicsProcessing;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class home extends AppCompatActivity {

    LinearLayout viewPartylist,vote_now,view_profile,resulT;
    CardView profile_PIC,profileContainer,sinagpartylist,risepartylist;
    TextView fullNAME,studid,logOUT;
    ImageView profilePic;
    LinearLayout linEar;
    RecyclerView partylist;
    List<PartylistData> dataList;
    AdapterHomePartylist adapter;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPartylist = findViewById(R.id.view_partylist);
        profilePic = findViewById(R.id.profilepic);
        fullNAME = findViewById(R.id.fullname);
        vote_now = findViewById(R.id.voteNow);
        profile_PIC = findViewById(R.id.profile_pic);
        studid = findViewById(R.id.studID);
        view_profile = findViewById(R.id.viewprofile);
        profileContainer = findViewById(R.id.profile_container);
        linEar = findViewById(R.id.linear);
        logOUT = findViewById(R.id.logout);
        risepartylist = findViewById(R.id.risePartylist);
        sinagpartylist = findViewById(R.id.sinagPartylist);
        resulT = findViewById(R.id.result);

        resulT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickresult();
            }
        });
        viewPartylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this, view_partylist.class);
                home.this.startActivity(intent);
            }
        });
        vote_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickvotenow();
            }
        });
        profile_PIC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickprofile();
            }
        });

        Animation anirotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        profile_PIC.startAnimation(anirotate);

        fullNAME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickprofile();
            }
        });
        view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickprofile();
            }
        });
        profileContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickprofile();
            }
        });
        logOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmationDialog();
            }
        });
        risepartylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this, partylist_candidates.class);
                intent.putExtra("partylistname", "rise");
                home.this.startActivity(intent);
            }
        });
        sinagpartylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this, partylist_candidates.class);
                intent.putExtra("partylistname", "sinag");
                home.this.startActivity(intent);
            }
        });
        showuserdata();
    }
    public void showuserdata(){

        Intent intent = getIntent();

        String firstname = intent.getStringExtra("firstname");
        String lastname = intent.getStringExtra("lastname");
        String fullname = firstname+" "+lastname;
        String Image = intent.getStringExtra("profilePic");
        String student_ID = intent.getStringExtra("studentID");

        studid.setText(student_ID);
        this.fullNAME.setText(fullname);
        Glide.with(this).load(Image).into(this.profilePic);
    }
    public void clickprofile(){
        Intent intentt = getIntent();

        String firstname = intentt.getStringExtra("firstname");
        String lastname = intentt.getStringExtra("lastname");
        String Image = intentt.getStringExtra("profilePic");
        String student_id = intentt.getStringExtra("studentID");
        String course_section = intentt.getStringExtra("course_section");
        String password = intentt.getStringExtra("password");
        String email = intentt.getStringExtra("email");

        Intent intent = new Intent(home.this, profile.class);

        intent.putExtra("firstname", firstname);
        intent.putExtra("lastname", lastname);
        intent.putExtra("studentID", student_id);
        intent.putExtra("course_section", course_section);
        intent.putExtra("password", password);
        intent.putExtra("email", email);
        intent.putExtra("profilePic", Image);
        home.this.startActivity(intent);
    }
    public void clickvotenow(){
        Intent intentt = getIntent();
        String voting_status = intentt.getStringExtra("voting_status");
        String firstname = intentt.getStringExtra("firstname");
        String lastname = intentt.getStringExtra("lastname");
        String Image = intentt.getStringExtra("profilePic");
        String student_id = intentt.getStringExtra("studentID");
        String course_section = intentt.getStringExtra("course_section");
        String password = intentt.getStringExtra("password");
        String email = intentt.getStringExtra("email");


        if (voting_status.equals("1")){
            Toast.makeText(home.this, "Account has already been voted", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent = new Intent(home.this, vote_now.class);
            intent.putExtra("firstname", firstname);
            intent.putExtra("lastname", lastname);
            intent.putExtra("studentID", student_id);
            intent.putExtra("course_section", course_section);
            intent.putExtra("password", password);
            intent.putExtra("email", email);
            intent.putExtra("profilePic", Image);
            home.this.startActivity(intent);
        }
    }
    public void clickresult(){
        Intent intentt = getIntent();
        String course_section = intentt.getStringExtra("course_section");
        String voting_status = intentt.getStringExtra("voting_status");

        if (voting_status.equals("1")){
            Intent intent = new Intent(home.this, result.class);
            intent.putExtra("course_section", course_section);
            home.this.startActivity(intent);
        }
        else{
            Toast.makeText(home.this, "Account haven't voted yet", Toast.LENGTH_SHORT).show();
        }
    }
    public home() {
    }
    public void onBackPressed() {

    }
    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Log Out");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout(home.this);

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
    public void logout(Context context) {
        // Clear all recent activities
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        // Finish the current activity
        ((Activity) context).finish();
    }
    private static void clearApplicationData(Context context) {
        context.getCacheDir().delete();

    }

}