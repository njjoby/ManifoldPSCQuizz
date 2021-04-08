package com.jobyProjects.manifoldpscquizz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void signUpClicked(View view) {
        EditText nameEditText=findViewById(R.id.name);
        EditText phoneEditText=findViewById(R.id.mobile_no);
        EditText emailEditText=findViewById(R.id.username);
        EditText passwordEditText=findViewById(R.id.password);
        ProgressBar loadingProgress=findViewById(R.id.progres);
        loadingProgress.setVisibility(view.VISIBLE);
        Log.d("Test", "Value is: " + nameEditText.getText().toString()+ emailEditText.getText().toString()+phoneEditText.getText().toString() + passwordEditText.getText());
        User newUser =new User(nameEditText.getText().toString(),emailEditText.getText().toString(),phoneEditText.getText().toString() ,passwordEditText.getText().toString(),"guest");
       FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users/"+nameEditText.getText().toString()+" "+ phoneEditText.getText().toString());

       myRef.setValue(newUser);
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
}