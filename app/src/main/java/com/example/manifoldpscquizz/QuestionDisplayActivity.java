package com.example.manifoldpscquizz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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
    public static    int choiceArray[];
    public  static Integer questionPointer;
    public String currentModulePath;
    public LogedInUser liu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_display);

        // list view trial

        Intent i = getIntent();
         liu = (LogedInUser)i.getSerializableExtra("currentUserLogedIn");
        Log.d("TAG", "Value is qwerty : " + liu.user.email);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
 if (qList.size()==0){
     FirebaseDatabase database = FirebaseDatabase.getInstance();
     DatabaseReference myRef = database.getReference("UserProgression/"+liu.user.name+liu.user.phone+"/CurrentModule");

// Read from the database
     myRef.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             // This method is called once with the initial value and again
             // whenever data at this location is updated.
             String modulePath = dataSnapshot.getValue(String.class);
             Log.d("TAG", "Value is: " + modulePath);
             if(modulePath==null){
                 modulePath="PSC/HSST_2016/M1";
                 Log.d("TAG", "New Value is: " + modulePath);
             }
             loadModuleAndDisplay(modulePath);
         }

         @Override
         public void onCancelled(DatabaseError error) {
             // Failed to read value
             Log.w("TAG", "Failed to read value.", error.toException());
         }
     });


 }else

 {

     Log.w("TAG1",  "questionPointer "+ questionPointer);
     Log.w("TAG1",  "choiceArray[questionPointer] "+ choiceArray[questionPointer]);

     Question firstQ=qList.get(questionPointer);
            displayQuestionFunction(firstQ);
        }



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

        RadioButton rBa=findViewById(R.id.radio_a) ;
        RadioButton rBb=findViewById(R.id.radio_b) ;
        RadioButton rBc=findViewById(R.id.radio_c) ;
        RadioButton rBd=findViewById(R.id.radio_d) ;

       // rBa.setChecked(false);rBb.setChecked(false);rBc.setChecked(false);rBd.setChecked(false);

        switch(choiceArray[questionPointer]){
            case 1:rBa.setChecked(true);break;
            case 2:rBb.setChecked(true);break;
            case 3:rBc.setChecked(true);break;
            case 4:rBd.setChecked(true);break;

        }
        Log.d("displayQuestionFunction", "Insided isplayQuestionFunction " + questionPointer  + " " + choiceArray[questionPointer] );

        mathViewDisp.setText("\\( \\mathbf {" +(questionPointer+1) + " .}  \\)" + firstQ.question);
        mathViewOptionA.setText("\\( \\mathbb {A .}  \\)"+ firstQ.option_a);
        mathViewOptionB.setText("\\( \\mathbb {B .}  \\)"+ firstQ.option_b);
        mathViewOptionC.setText("\\( \\mathbb {C .}  \\)"+ firstQ.option_c);
        mathViewOptionD.setText("\\( \\mathbb {D .}  \\)"+ firstQ.option_d);

        TextView textViewStatus = findViewById(R.id.textViewStatus);
        TextView textViewDescription = findViewById(R.id.textViewQuestionDescription);
        TextView textViewBelongs = findViewById(R.id.textViewQuestionBelongs);
        textViewDescription.setText(firstQ.descriptionIfAny);
        textViewStatus.setText(" "+(questionPointer+1) + " / "+ qList.size());
    }

    void loadModuleAndDisplay( String testPath ){

        if (qList.size()==0){
            currentModulePath=testPath;
           // Intent i = getIntent();
            // testPath = (String)i.getSerializableExtra("TestPath");

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("UserProgression/"+liu.user.name+liu.user.phone+"/CurrentModule");
            myRef.setValue(currentModulePath);

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
                    choiceArray=new int[qList.size()] ;
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
        Log.d("Inside Next clicked ", "Inside Next clicked before " + questionPointer  );
        RadioGroup radioGroup=findViewById(R.id.radio_group);
        if(questionPointer<qList.size()-1){

            RadioButton rBa=findViewById(R.id.radio_a) ;
            RadioButton rBb=findViewById(R.id.radio_b) ;
            RadioButton rBc=findViewById(R.id.radio_c) ;
            RadioButton rBd=findViewById(R.id.radio_d) ;


            ++questionPointer;
            Question firstQ=qList.get(questionPointer);
            radioGroup.clearCheck();
            displayQuestionFunction(firstQ);

        }
        Log.d("Inside Next clicked ", "Inside Next clicked before " + questionPointer  + " " + choiceArray[questionPointer] );
    }
    public void finishClicked(View view) {
        //
        if (qList.size()==0){
            return;
        }


        int moduleScore=0;
for(int i=0;i<qList.size();i++)
{
    int ai=0;
    Question qi=qList.get(i);
    switch (qi.answer){
        case " A":ai=1;break;
        case " B":ai=2;break;
        case " C":ai=3;break;
        case " D":ai=4;break;
    }
if(ai==choiceArray[i]){
    moduleScore++;
}




}



int currentScore=moduleScore*100/qList.size();
Context context = getApplicationContext();
        CharSequence text = "Your Score In this Module : "+ ( currentScore) ;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
//remove questions
        qList.clear();
        qKeyList.clear();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("UserProgression/"+liu.user.name+liu.user.phone+"/CompletedModules/"+currentModulePath);
        myRef.setValue(currentScore);

    }
    public void backClicked(View view) {
        RadioGroup radioGroup=findViewById(R.id.radio_group);
        // questionPointer=questionPointer+1;
        Log.d("Inside Back clicked ", "Inside back clicked before " + questionPointer  );
        if(questionPointer>0){

            RadioButton rBa=findViewById(R.id.radio_a) ;
            RadioButton rBb=findViewById(R.id.radio_b) ;
            RadioButton rBc=findViewById(R.id.radio_c) ;
            RadioButton rBd=findViewById(R.id.radio_d) ;



            --questionPointer;
            Question firstQ=qList.get(questionPointer);
            radioGroup.clearCheck();
            displayQuestionFunction(firstQ);
        }
        Log.d("Inside Back clicked ", "Inside back clicked  After " + questionPointer + " " + choiceArray[questionPointer]);
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_a:
                if (checked)
                    // a
                    choiceArray[questionPointer]=1;
                    break;
            case R.id.radio_b:
                if (checked)
                    // b
                    choiceArray[questionPointer]=2;
                    break;
            case R.id.radio_c:
                if (checked)
                    // c
                    choiceArray[questionPointer]=3;
                    break;
            case R.id.radio_d:
                if (checked)
                    // d
                    choiceArray[questionPointer]=4;
                    break;
        }

        Log.d("Radio Clicked","--> "+ choiceArray[questionPointer] );
    }
}