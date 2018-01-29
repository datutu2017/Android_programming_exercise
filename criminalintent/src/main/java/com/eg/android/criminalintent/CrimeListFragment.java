package com.eg.android.criminalintent;

import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * @author Datuu
 * @date on 2018/1/27.
 * @email yiyun0331@163.com
 */

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list,
                container, false);
        mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        /**
         *  RecyclerView 类不会亲自摆放屏幕上的列表项。实际上，摆放的任务被委托给了
         LayoutManager。除了在屏幕上摆放列表项，LayoutManager还负责定义屏幕滚动行为
         GridLayoutManager类，以网格形式展示列表项
         */
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private void updateUI() {
        //数据表对象
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        //数据适配
        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);

    }

    //viewholder主要负责一个条目中 初始化控件
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Crime mCrime;
        private ImageView mImageView;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            itemView.setOnClickListener(this);
            mTitleTextView = itemView.findViewById(R.id.crime_title);
            mDateTextView = itemView.findViewById(R.id.crime_date);
            mImageView = itemView.findViewById(R.id.crime_solved);
        }

        //区别构建一个新的holder类
        public CrimeHolder(LayoutInflater inflater, ViewGroup parent, int type) {

            super(inflater.inflate(R.layout.list_item_crimepo, parent, false));
            itemView.setOnClickListener(this);
            mTitleTextView = itemView.findViewById(R.id.crime_title);
            mDateTextView = itemView.findViewById(R.id.crime_date);
            mImageView = itemView.findViewById(R.id.buttonpo);
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("tag", ">>>>>>>>>>>>>>mag");
                }
            });
        }

        public void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
//            //日期格式化 封装一下
            FormatDates date =new  FormatDates(mCrime.getDate());
            mDateTextView.setText(date.getDates());
            mImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);

        }

        @Override
        public void onClick(View view) {
//            Toast.makeText(getActivity(), mCrime.getTitle() + "c",
//                    Toast.LENGTH_SHORT).show();
//fragment 跳转到CrimeActivity
//            Intent intent = new Intent(getActivity(), CrimeActivity.class);
            Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(intent);

        }
    }

    //条目管理
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;
        private static final int TYPE_ONE = 0;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            //这里获取crime对象里的值 对type  viewtype  mRequiresPolice 对应相关的不同对象进行区分返回holder
            int type = getItemViewType(viewType);
            Log.d("tag", ">>>>>>>a>>>>>:" + type + ">>>>>>" + viewType);
            if (type == TYPE_ONE) {
                return new CrimeHolder(layoutInflater, parent);
            } else {
                return new CrimeHolder(layoutInflater, parent, 1);
            }


        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime);

        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

        @Override
        public int getItemViewType(int position) {
            //这里关联viewtype  mRequiresPolice
            return mCrimes.get(position).getRequiresPolice();
        }
    }
}
