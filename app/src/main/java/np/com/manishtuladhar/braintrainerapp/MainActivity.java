package np.com.manishtuladhar.braintrainerapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

//widgets
    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    Button button0,button1,button2,button3;
    TextView timerTextView;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;

//answers
    ArrayList<Integer> answers = new ArrayList<Integer>();

//location of correct answer
    int locationOfCorrectAnswer;

//score variable
    int score = 0;

//questions
    int numberOfQuestions = 0;

/*
    ------------------- Game Starting Period ---------------
 */
    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);
        //sending view since it needs one
        playAgain(findViewById(R.id.playAgainButton));

    }

    /*
    ------------------- Generating Question ----------------------
 */
    public void generateQuestion()
    {
        //generating random number
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a)+ "+" + Integer.toString(b));

        // setting up the location of the answers

        locationOfCorrectAnswer = rand.nextInt(4);

        //since our answers are stacking up we clear it
        answers.clear();

        int incorrectAnswer;

        for(int i = 0; i<4; i++)
        {
            if(i == locationOfCorrectAnswer){
                answers.add(a+b);
            }
            else{
                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == a + b)
                {
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

/*
      -------------------- the button tapped -----------------
 */

    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct!");
        }
        else{
            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

/*
   ------------------- Game Starting Period ---------------
*/
    public void playAgain(View view)
    {
        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000 + "s"));
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your score is " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);

        button0 = (Button)findViewById(R.id.button);
        button1 = (Button)findViewById(R.id.button2);
        button2 = (Button)findViewById(R.id.button3);
        button3 = (Button)findViewById(R.id.button4);

        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);

        gameRelativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);
    }
}
