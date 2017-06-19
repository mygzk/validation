package com.zayh.test.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.zayh.test.R;
import com.zayh.test.validation.ValidationError;
import com.zayh.test.validation.ValidationListener;
import com.zayh.test.validation.Validator;
import com.zayh.test.widget.CustomTipTextLayout;

import java.util.List;

public class VerificationEditTextActivity extends Activity {
    private CustomTipTextLayout itemTest;
    private CustomTipTextLayout itemTest1;
    private CustomTipTextLayout itemTest2;
    private CustomTipTextLayout itemTest3;
    private CustomTipTextLayout itemTest4;
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
        itemTest1 = (CustomTipTextLayout) findViewById(R.id.item_test1);
        itemTest2 = (CustomTipTextLayout) findViewById(R.id.item_test2);
        itemTest3 = (CustomTipTextLayout) findViewById(R.id.item_test3);
        itemTest4 = (CustomTipTextLayout) findViewById(R.id.item_test4);
        final Validator<CustomTipTextLayout> validator = new Validator();
        validator.addRule(itemTest, itemTest.getRule());
        validator.addRule(itemTest1, itemTest1.getRule());
        validator.addRule(itemTest2, itemTest2.getRule());
        validator.addRule(itemTest3, itemTest3.getRule());
        validator.addRule(itemTest4, itemTest4.getRule());
        validator.setValidationListener(new ValidationListener<CustomTipTextLayout>() {
            @Override
            public void onValid() {
                Log.e("validtion", "btn onValid is fail:");
            }

            @Override
            public void onInValid(List<ValidationError> errors) {
                Log.e("validtion", "onValid is fail:" + errors.size());
            }

            @Override
            public void onInValidFirstView(CustomTipTextLayout view) {
                if (view != null) {
                    int d = (int) (view.getY() - view.getHeight() + 0.5);
                    sRoot.scrollTo(0, d);
                }
            }
        });
        btnVirifi = (Button) findViewById(R.id.btn_virification);

        btnVirifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*//et1.setError("测试提示信息");
                // et3.setError("测试提示信息");
                itemTest.setErrorMsg("afdsf");
                int d = (int) (itemTest.getY() - itemTest.getHeight() + 0.5);
                sRoot.scrollTo(0, d);*/
                validator.valide();
            }
        });


    }
}
