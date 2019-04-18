package com.example.taha.pitchin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class id extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;
    ImageView imageView;
    EditText rollNo;
    Button contID;
    Uri uriProfileImage; // Uri object
    ProgressBar progressBar;
    String profileImageUrl;
    private StorageReference mStorageReference;
    private static final int choosePicture = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id);
        mAuth = FirebaseAuth.getInstance();

        rollNo = findViewById(R.id.rollNo);

        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(this);

        contID = findViewById(R.id.contID);
        contID.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageView:
                showImageChooser();
                break;
            case R.id.contID:
                saveUserInformation();
                break;
        }

    }

    private void saveUserInformation() {
        String RollNumber = rollNo.getText().toString();
        if(RollNumber.isEmpty()){
            rollNo.setError("Roll Number is required to register");
            rollNo.requestFocus();
            return;
        }

        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null && profileImageUrl != null){
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder().setDisplayName(RollNumber).setPhotoUri(Uri.parse(profileImageUrl)).build();
            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(id.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    startActivity(new Intent(this,name.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == choosePicture && resultCode == RESULT_OK && data != null && data.getData() != null){
            uriProfileImage = data.getData(); //Returns a uri to the image
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriProfileImage);
                imageView.setImageBitmap(bitmap);
                uploadImageToFirebaseStoage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImageToFirebaseStoage() {
        StorageReference profilePictureRef = FirebaseStorage.getInstance().getReference("ProfilePics/"+System.currentTimeMillis() + ".jpg");
        if (uriProfileImage != null){
            progressBar.setVisibility(View.VISIBLE);
            profilePictureRef.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressBar.setVisibility(View.GONE);
                    profileImageUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(id.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showImageChooser(){
        Intent image = new Intent();
        image.setType("image/*");
        image.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(image,"Select Profile Image"), choosePicture);
    }
}
