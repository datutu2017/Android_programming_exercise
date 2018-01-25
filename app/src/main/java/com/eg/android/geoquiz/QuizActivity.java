package com.eg.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private Button mPREV;
    private TextView mQuestionTextView;
    private int sum = 0;
    private int count = 0;
    private boolean mIsCheater;

    private Question[] mQuestionsBank = new Question[]{
            new Question(R.string.q1, true),
            new Question(R.string.q2, true),
            new Question(R.string.q3, false),
            new Question(R.string.q4, false),
            new Question(R.string.q5, false),
            new Question(R.string.q6, false),

    };
    private int mCurrentIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        if (savedInstanceState != null) {
            //0为默认值
            mCurrentIndex = savedInstanceState.getInt("index", 0);
        }
        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mQuestionTextView = findViewById(R.id.question_text_view);
        mNextButton = findViewById(R.id.next_button);
        mPREV = findViewById(R.id.PREV);
        mCheatButton = findViewById(R.id.cheat_button);


        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
                //这里调用是更新数据
                update();

            }
        });
        //这里调用 是初始化
        update();
//        /**
//         *获取屏幕宽高
//         */
//        Resources resources = this.getResources();
//        DisplayMetrics dm = resources.getDisplayMetrics();
//        float density = dm.density;
//        width = dm.widthPixels;
//        height = dm.heightPixels;

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast toast = Toast.makeText(QuizActivity.this, R.string.pafu, Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
//                toast.show();
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
                //这里调用是更新数据
                update();
            }
        });

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean answer = mQuestionsBank[mCurrentIndex].isAnswer();
                Intent intent = CheatActivity.newIntent(QuizActivity.this, answer);
//                startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });

        mPREV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //多加一周在后退
                mCurrentIndex = (mCurrentIndex + mQuestionsBank.length - 1) % mQuestionsBank.length;
                //这里调用是更新数据
                update();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == 1) {
            if (data == null) {
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    private void update() {
        int question = mQuestionsBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        ifques();
    }

    private void checkAnswer(boolean userPressed) {
        mQuestionsBank[mCurrentIndex].setAnswerend(true);
        boolean answer = mQuestionsBank[mCurrentIndex].isAnswer();
        int messageResId = 0;
        if (mIsCheater) {
            messageResId=R.string.judgment_toast;}
            else {
            if (userPressed == answer) {
                messageResId = R.string.true_button;
                sum++;
            } else {
                messageResId = R.string.false_button;
            }
        }
        //此处的方法调用要在SUM++后统计,否则丢失一次答案
        ifques();

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "start");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "pause");
    }

    //    覆盖onSaveInstanceState(Bundle) 方法， 以刚才新增的常量值作为键， 将
//            mCurrentIndex变量值保存到bundle
    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putInt("index", mCurrentIndex);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "stop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "destroy");
    }

    private void ifques() {
        if (mQuestionsBank[mCurrentIndex].isAnswerend() == true) {
            //如果题目被回答，则按键设置不可按下
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
            //答完题即统计正确率
            count++;
            if (count == mQuestionsBank.length) {
                Double correctMark;
                correctMark = (double) sum / count;
                //保留后两位
                correctMark = (double) ((int) (correctMark * 10000) / 100.0);
                Toast.makeText(this, "正确率" + correctMark + "%", Toast.LENGTH_SHORT).show();
            }
        } else {
            //如果题目没有被回答，则按键设置可按下
            mTrueButton.setEnabled(true);
            mFalseButton.setEnabled(true);
        }
    }
}
