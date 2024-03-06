package com.example.ssc_voting;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class activate_account extends AppCompatActivity {

    Spinner course_section;
    ImageView uploadProfile;
    TextInputEditText firstNAME;
    TextInputEditText lastNAME;
//    TextInputEditText course_section;
    TextInputEditText passWORD,retypepassWORD;
    Button submitBTN;
    String imageURL;
    Uri uri;

    public activate_account() {
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate_account);
        this.uploadProfile = (ImageView)this.findViewById(R.id.uploadprofile);
        this.firstNAME = (TextInputEditText)this.findViewById(R.id.firstname);
        this.lastNAME = (TextInputEditText)this.findViewById(R.id.lastname);
//        this.course_section = (TextInputEditText)this.findViewById(R.id.course_and_section);
        this.passWORD = (TextInputEditText)this.findViewById(R.id.password);
        this.submitBTN = (Button)this.findViewById(R.id.submitbtn);
        this.retypepassWORD = (TextInputEditText)this.findViewById(R.id.retypepassword);
        course_section = findViewById(R.id.courseDropdown);

        course_section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item =adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("BSIT");
        arrayList.add("BSBA");
        arrayList.add("BSHM");
        arrayList.add("BSCrim");
        arrayList.add("BSEduc");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        course_section.setAdapter(adapter);


        final ActivityResultLauncher<Intent> activityResultLauncher = this.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == -1) {
                    Intent data = result.getData();
                    activate_account.this.uri = data.getData();
                    if (activate_account.this.uri != null) {
                        activate_account.this.uploadProfile.setImageURI(activate_account.this.uri);
                    } else {
                        activate_account.this.uploadProfile.setImageResource(R.drawable.default_profile);
                    }
                } else {
                    Toast.makeText(activate_account.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                }

            }
        });
        this.uploadProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent photoPicker = new Intent("android.intent.action.PICK");
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        this.submitBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                activate_account.this.saveData();
            }
        });
    }

    public void saveData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        final AlertDialog dialog = builder.create();
        dialog.show();
        if (this.uri == null) {
            imageURL = "https://firebasestorage.googleapis.com/v0/b/sscvoting-9b667.appspot.com/o/Default%20Profile%2Fdefault_profile.png?alt=media&token=6739d0ce-a8a5-4684-93c6-78e90f67b809";
            uploadData();
            dialog.dismiss();
            return;
        }
        else{
            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Profile Images").child(this.uri.getLastPathSegment());
            storageReference.putFile(this.uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();

                    while(!uriTask.isComplete()) {
                    }

                    Uri urlImage = (Uri)uriTask.getResult();
                    activate_account.this.imageURL = urlImage.toString();
                    activate_account.this.uploadData();
                    dialog.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                public void onFailure(@NonNull Exception e) {

                }
            });
        }
    }

    public void uploadData() {
        Intent intent = this.getIntent();
        String studID = intent.getStringExtra("studentID");
        String eMail = intent.getStringExtra("email");
        String firstname = this.firstNAME.getText().toString().trim();
        String lastname = this.lastNAME.getText().toString().trim();
        String courseandsection = this.course_section.getSelectedItem().toString();
        String password = this.passWORD.getText().toString().trim();
        String retypepassword = retypepassWORD.getText().toString().trim();
        String status = "1";
        String vstatus = "0";
        if (retypepassword.equals(password)){
            UserData userData = new UserData(firstname, lastname, courseandsection, password, studID, eMail, this.imageURL, status,vstatus);
            FirebaseDatabase.getInstance().getReference("users").child(studID).setValue(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(activate_account.this, "Saved", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activate_account.this, MainActivity.class);
                        activate_account.this.startActivity(intent);
                        activate_account.this.finish();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(activate_account.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            retypepassWORD.setError("Unmatch Password");
            Toast.makeText(activate_account.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }
    }

}