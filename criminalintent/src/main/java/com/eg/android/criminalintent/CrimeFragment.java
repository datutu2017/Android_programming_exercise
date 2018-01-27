package com.eg.android.criminalintent;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

/**
 * @author Datuu
 * @date on 2018/1/27.
 * @email yiyun0331@163.com
 */

public class CrimeFragment extends Fragment {
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedChechkBox;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
    }

    /**
     * 该方法实例化fragment 视图的布局， 然后将实例化的View 返回给托管activity 。
     * LayoutInflater及ViewGroup是实例化布局的必要参数。Bundle用来存储恢复数据，可供该方
     * 法从保存状态下重建视图。
     * 在onCreateView(...)方法中，fragment的视图是直接通过
     * 调用LayoutInflater.inflate(...)
     * 方法并传入布局的资源ID生成的。第二个参数是视图的父视图，我们通常需要父视图来正确配置
     * 组件。第三个参数告诉布局生成器是否将生成的视图添加给父视图。这里，传入了false参数，
     * 因为我们将以代码的方式添加视图。
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     * @Nullable和NotNul 用来标注方法是否能传入null值，如果可以传入NUll值，则标记为nullbale，如果不可以则标注为Nonnull.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        mTitleField = v.findViewById(R.id.crime_title);

        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            /**
             * 在onTextChanged(...)方法中，调用CharSequence（代表用户输入）的toString()方法。
             该方法最后返回用来设置Crime标题的字符串。
             * @param charSequence
             * @param i
             * @param i1
             * @param i2
             */
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCrime.setTitle(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDateButton = v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDate().toString());
        //设置按钮不能点击
        mDateButton.setEnabled(false);

        mSolvedChechkBox = v.findViewById(R.id.crime_solved);
        mSolvedChechkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mCrime.setSolved(b);

            }
        });

        return v;
    }
}
