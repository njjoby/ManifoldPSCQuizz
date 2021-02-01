package com.example.manifoldpscquizz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.android.gms.common.internal.service.Common;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import io.github.kexanie.library.MathView;

public class QuestionDisplayActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    public static ArrayList<Question> qList = new ArrayList<Question>();
    public static ArrayList<String> qKeyList = new ArrayList<String>();
    public static  String testPath ;
    public  static Integer questionPointer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_display);

        // list view trial

        Intent i = getIntent();
        LogedInUser liu = (LogedInUser)i.getSerializableExtra("currentUserLogedIn");
        Log.d("TAG", "Value is qwerty : " + liu.user.email);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.question_display, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

      if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public boolean HSST2016ModuleItemClicked(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.hsst2016m1: {
                //do somthing
                loadModuleAndDisplay("PSC/HSST_2016/M1");
                break;
            }
            case R.id.hsst2016m2: {
                //do somthing
                loadModuleAndDisplay("PSC/HSST_2016/M2");
                break;
            }
            case R.id.hsst2016m3: {
                //do somthing
                loadModuleAndDisplay("PSC/HSST_2016/M3");
                break;
            }
            case R.id.hsst2016m4: {
                //do somthing
                loadModuleAndDisplay("PSC/HSST_2016/M4");
                break;
            }
            case R.id.hsst2016m5: {
                //do somthing
                loadModuleAndDisplay("PSC/HSST_2016/M5");
                break;
            }
        }
        //close navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
    public boolean LecturerIT2013ModuleItemClicked(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.LecturerIT2013m1: {
                //do somthing

                break;
            }
        }
// Udaip
        Context context = getApplicationContext();
        CharSequence text = "Please Complete HSST 2016";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        //close navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    void displayQuestionFunction(Question firstQ){
        MathView mathViewDisp = findViewById(R.id.mathViewDisp);
        MathView mathViewOptionA = findViewById(R.id.mathViewOptionA);
        MathView mathViewOptionB = findViewById(R.id.mathViewOptionB);
        MathView mathViewOptionC = findViewById(R.id.mathViewOptionC);
        MathView mathViewOptionD = findViewById(R.id.mathViewOptionD);
        mathViewDisp.setText("\\( \\mathbf {" +(questionPointer+1) + " .}  \\)" + firstQ.question);
        mathViewOptionA.setText("\\( \\mathbb {A .}  \\)"+ firstQ.option_a);
        mathViewOptionB.setText("\\( \\mathbb {B .}  \\)"+ firstQ.option_b);
        mathViewOptionC.setText("\\( \\mathbb {C .}  \\)"+ firstQ.option_c);
        mathViewOptionD.setText("\\( \\mathbb {D .}  \\)"+ firstQ.option_d);

    }

    void loadModuleAndDisplay( String testPath ){
        if (qList.size()==0){
           // Intent i = getIntent();
            // testPath = (String)i.getSerializableExtra("TestPath");
            DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child(testPath);

            ref.addValueEventListener(new ValueEventListener(){

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Question q1;
                    Log.d("Inside for loop ", "Value is: " + "qq.getKey()");
                    for(DataSnapshot qq: dataSnapshot.getChildren()){
                        q1=qq.getValue(Question.class);
                        qKeyList.add(qq.getKey());
                        qList.add(q1);
                        // Log.d("Inside for loop ", "Value is: " + qq.getKey());

                        //so on
                    }

                    questionPointer=0;

                    Question firstQ=qList.get(questionPointer);
                    displayQuestionFunction(firstQ);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


            });
        }
        else{

            Question firstQ=qList.get(questionPointer);
            displayQuestionFunction(firstQ);
        }



    }
    public void nextClicked(View view) {


        // questionPointer=questionPointer+1;
        Log.d("Inside next clicked ", "Inside next clicked " + qList.size() );

        if(questionPointer<qList.size()-1){
            ++questionPointer;
            Question firstQ=qList.get(questionPointer);
            displayQuestionFunction(firstQ);
        }

    }
    public void backClicked(View view) {

        // questionPointer=questionPointer+1;
        Log.d("Inside Back clicked ", "Inside next clicked " + qList.size() );
        if(questionPointer>0){
            Question firstQ=qList.get(--questionPointer);
            displayQuestionFunction(firstQ);
        }

    }
}