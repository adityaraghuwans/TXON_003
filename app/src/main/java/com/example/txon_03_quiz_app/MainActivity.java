package com.example.txon_03_quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView totalquestions;
    Button ansA,ansB,ansC,ansD;
    Button NEXT;
    Button RESET;
    Button EXIT;
    int score=0;
    int totalquestion = QuestionAnswer.question.length;
    int currentQuestionIndex=0;
    String selectAnswer=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalquestions = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        NEXT = findViewById(R.id.button6);
        RESET = findViewById(R.id.button7);
        EXIT = findViewById(R.id.button8);
        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        NEXT.setOnClickListener(this);
        RESET.setOnClickListener(this);
        EXIT.setOnClickListener(this);
        totalquestions.setText("Total Questions: "+totalquestion);

        loadNewQuestion();
    }


    @Override
    public void onClick(View v) {
        ansA.setBackgroundColor(Color.GREEN);
        ansB.setBackgroundColor(Color.GREEN);
        ansC.setBackgroundColor(Color.GREEN);
        ansD.setBackgroundColor(Color.GREEN);
        Button clickedButton = (Button) v ;
        if (clickedButton.getId()==R.id.button6){
            if (selectAnswer.equals((QuestionAnswer.correctAnswers[currentQuestionIndex]))){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();
        }
        else{
            selectAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
        if(clickedButton.getId()==R.id.button7){
            restartQuiz();
        }
        if(clickedButton.getId()==R.id.button8){
            exitQuiz();

        }

    }
    void loadNewQuestion(){
        if (currentQuestionIndex == totalquestion){
            finishQuiz();
            return;
        }
        totalquestions.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

    }

    void finishQuiz(){
        String passStatus = " ";
        if(score>totalquestion*0.50){
            passStatus="PASS";
        }
        else {
            passStatus="FAILED";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+score+" out of" +totalquestion)
                .setPositiveButton("Restart",((dialogInterface, i) ->restartQuiz() ))
                .setCancelable(false)
                .show();

    }
    void restartQuiz(){
        score=0;
        currentQuestionIndex=0;
        loadNewQuestion();
    }
    void exitQuiz(){
        score=0;
        currentQuestionIndex=0;


    }
}