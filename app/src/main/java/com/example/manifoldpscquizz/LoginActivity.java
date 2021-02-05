package com.example.manifoldpscquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginButtonClicked(final View view) {
        final EditText userNameEditText=findViewById(R.id.username);
        final EditText passwordEditText=findViewById(R.id.password);
        final ProgressBar loadingProgressBar=findViewById(R.id.loading);
        final LogedInUser logedInUser=new LogedInUser();

        loadingProgressBar.setVisibility(view.VISIBLE);

        final Intent intent=new Intent(this, QuestionDisplayActivity.class);
        //startActivity(intent);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // DatabaseReference myRef = database.getReference("Users").orderByChild("email").equalTo("jobynj@gecskp.ac.in").limitToFirst(1);
        Query QueryRef= database.getReference("Users").orderByChild("email").equalTo(userNameEditText.getText().toString());
// Read from the database
        //Query a= database.r
        QueryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User u1;
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                // User u1 = dataSnapshot.getChildren().
                // dataSnapshot.getChildren().
                //  Log.d("joby", "Value is: " + u1.name);
                if(dataSnapshot.getChildrenCount()>0){
                    for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                        // TODO: handle the post

                        u1=userSnapshot.getValue(User.class);
                        Log.d("joby", "Value is: " + u1.name);
                        Log.d("joby", "Value is: " + u1.password);
                        if(u1.password.equals(passwordEditText.getText().toString())){
                            logedInUser.setUser(u1);
                            logedInUser.setStatus("SUCCESS");
                            Log.d("Test", "Value is: " + "++++++++++++++++++++++++++");
                            intent.putExtra("currentUserLogedIn",logedInUser);
                           startActivity(intent);
                            break;
                        }else{
                            loadingProgressBar.setVisibility(view.INVISIBLE);
                            Context context = getApplicationContext();
                            CharSequence text = "Invalid Userid or Password" ;
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }

                    }

                }
                else{
                    loadingProgressBar.setVisibility(view.INVISIBLE);
                }
                loadingProgressBar.setVisibility(view.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
    public void onTextViewClick(View view) {
//        Intent intent = new Intent(this, NewActivity.class);
//        startActivity(intent);
        Intent intent=new Intent(this,SignUp.class);
        startActivity(intent);
    }
}