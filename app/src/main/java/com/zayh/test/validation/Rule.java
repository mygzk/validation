package com.zayh.test.validation;

import android.support.annotation.StringRes;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zayh.test.validation.validator.RegularStrContant;
import com.zayh.test.vericat.*;
import com.zayh.test.validation.validator.AbstractValidator;
import com.zayh.test.validation.validator.RegexValidator;
import com.zayh.test.validation.validator.RequiredValidator;
import com.zayh.test.vericat.ValidationException;
import com.zayh.test.vericat.validator.TypeValidator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guozhk on 2017/6/18.
 */

public class Rule {
    //非空 必须验证
    private final int VALIDATION_TYPE_REQUIRED = 0;
    //在不为空下验证
    private final int VALIDATION_TYPE_REQUIRED_NO = 1;
    //控件
    private View mView;
    //字段名称
    private String mName;

    private List<AbstractValidator> mAbstractValidators;

    private int mValidationType = VALIDATION_TYPE_REQUIRED;

    private Rule() {
    }

    public static Rule with(View view) {
        return new Rule(view, null);
    }

    public static Rule with(View view, @StringRes int nameResId) {
        String name = view.getResources().getString(nameResId);
        return new Rule(view, name);
    }

    public Rule(View mView, String mName) {
        if (!(mView instanceof TextView)) {
            throw new ValidationException("只支持 TextView 及其派生控件。");
        }
        this.mView = mView;
        this.mName = mName;
        mAbstractValidators = new ArrayList<>();
    }

    //添加验证
    private Rule addValidation(AbstractValidator validator) {
        mAbstractValidators.add(validator);
        return this;
    }

    /***
     * 检验是不是必须验证
     * @return boolean
     */
    public boolean canSkip() {
        if ((mValidationType == VALIDATION_TYPE_REQUIRED_NO) &&
                getValue() != null) {
            return true;
        }
        return false;
    }

    public CharSequence getValue() {
        return ((TextView) mView).getText();
    }

    public Rule required() {
        mValidationType = VALIDATION_TYPE_REQUIRED;
        addValidation(new RequiredValidator("字段不能为空"));
        return this;
    }

    public Rule noRequired() {
        mValidationType = VALIDATION_TYPE_REQUIRED_NO;
        return this;
    }

    public Rule email() {
        addValidation(new RegexValidator("字段邮件格式不正确", RegularStrContant.EMAIL_ADDRESS));
        return this;
    }

    public void validation() {
        for (AbstractValidator validator : mAbstractValidators) {
            if (!validator.isValid(getValue())) {
                break;
            }
        }
    }


    public boolean validate() {
        return true;
    }


}
