package com.zayh.test.ui;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.zayh.test.R;
import com.zayh.test.widget.CustomTipTextLayout;

public class VerificationEditTextActivity extends Activity {
    private CustomTipTextLayout itemTest;
    private Button btnVirifi;

    private ScrollView sRoot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_edit_text);

        initView();
    }

    private void initView() {
        sRoot = (ScrollView) findViewById(R.id.root);
        itemTest = (CustomTipTextLayout) findViewById(R.id.item_test);
        btnVirifi = (Button) findViewById(R.id.btn_virification);

        btnVirifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //et1.setError("测试提示信息");
                // et3.setError("测试提示信息");
                itemTest.setErrorMsg("afdsf");
                int d = (int) (itemTest.getY() - itemTest.getHeight() + 0.5);
                sRoot.scrollTo(0, d);
            }
        });


    }
}
