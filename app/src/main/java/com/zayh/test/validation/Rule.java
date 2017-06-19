package com.zayh.test.validation;

import android.content.res.Resources;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.TextView;

import com.zayh.test.R;
import com.zayh.test.validation.validator.RegularStrContant;
import com.zayh.test.validation.validator.AbstractValidator;
import com.zayh.test.validation.validator.RegexValidator;
import com.zayh.test.validation.validator.RequiredValidator;

import java.util.ArrayList;
import java.util.List;

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

    private Resources mResources;

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

    public static Rule with(View view, String name) {
        return new Rule(view, name);
    }

    public Rule(View mView, String mName) {
        if (!(mView instanceof TextView)) {
            throw new ValidationException("只支持 TextView 及其派生控件。");
        }
        this.mView = mView;
        this.mName = mName;
        this.mResources = mView.getResources();
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
                (getValue() == null || getValue().equals(""))) {
            return true;
        }
        return false;
    }

    public CharSequence getValue() {
        return ((TextView) mView).getText();
    }


    /**
     * 开始验证
     */
    public void validation() {
        boolean val = true;
        ValidationError error = null;
        for (AbstractValidator validator : mAbstractValidators) {
            if (!validator.isValid(getValue())) {
                val = false;
                error = new ValidationError(mView, validator.getmValidationMsg());
                break;
            }
        }
        notifyHandler(val, error, false);
    }

    public void notifyHandler(boolean val, ValidationError validationError, boolean changbg) {
        if (mErrorHandler != null) {
            if (val) {
                mErrorHandler.onValid();
            } else {
                mErrorHandler.onInValid(validationError, changbg);
            }
        }
    }

    public View getView() {
        return mView;
    }

    private ErrorHandler mErrorHandler;

    public void setValidationListener(ErrorHandler mErrorHandler) {
        this.mErrorHandler = mErrorHandler;
    }

    public List<AbstractValidator> getAbstractValidators() {
        return mAbstractValidators;
    }

    public Rule required() {
        mValidationType = VALIDATION_TYPE_REQUIRED;
        String errorMessage = mResources.getString(R.string.validation_error_message_required, mName);
        addValidation(new RequiredValidator(errorMessage));
        return this;
    }

    public Rule noRequired() {
        mValidationType = VALIDATION_TYPE_REQUIRED_NO;
        return this;
    }

    public Rule email() {
        String errorMessage = mResources.getString(R.string.validation_error_message_email, mName);
        addValidation(new RegexValidator(errorMessage, RegularStrContant.EMAIL_ADDRESS));
        return this;
    }
}
