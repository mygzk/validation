package com.zayh.test.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zayh.test.R;
import com.zayh.test.adapter.MainAdapter;
import com.zayh.test.bean.ActivityBrean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lvTest;
    MainAdapter adapter;
    List<ActivityBrean> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("MainActivity");
        initView();
    }

    private void initView() {
        lvTest = (ListView) findViewById(R.id.test_list);
        initData();
        adapter = new MainAdapter(this, mDatas);
        lvTest.setAdapter(adapter);
        lvTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.toSkip(position);
            }
        });
    }

    private void initData() {
        mDatas.add(new ActivityBrean(VerificationEditTextActivity.class, "VerificationEditTextActivity"));
        mDatas.add(new ActivityBrean(VerificationEditTextActivity.class, "VerificationEditTextActivity"));
        mDatas.add(new ActivityBrean(VerificationEditTextActivity.class, "VerificationEditTextActivity"));
        mDatas.add(new ActivityBrean(VerificationEditTextActivity.class, "VerificationEditTextActivity"));
        mDatas.add(new ActivityBrean(VerificationEditTextActivity.class, "VerificationEditTextActivity"));
    }
}
