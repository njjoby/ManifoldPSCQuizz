package com.jobyProjects.manifoldpscquizz;
import java.util.ArrayList;

public class Question {


    public String question;
    public String option_a;
    public String option_b;
    public String option_c;
    public String option_d;

    public String answer;
    public ArrayList<String> Belongs;
    public String descriptionIfAny;
    public String examName ;
    public String postName;
    public String year;

    public Question(String question, String option_a, String option_b, String option_c, String option_d, String answer, ArrayList<String> belongs, String descriptionIfAny, String examName, String postName, String year) {
        this.question = question;
        this.option_a = option_a;
        this.option_b = option_b;
        this.option_c = option_c;
        this.option_d = option_d;
        this.answer = answer;
        Belongs = belongs;
        this.descriptionIfAny = descriptionIfAny;
        this.examName = examName;
        this.postName = postName;
        this.year = year;
    }

    public Question() {

    }
}


