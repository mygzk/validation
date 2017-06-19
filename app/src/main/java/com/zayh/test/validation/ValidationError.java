package com.zayh.test.validation;

import android.view.View;

import java.util.List;
import java.util.Map;

/**
 * Created by guozhk on 2017/6/18.
 */

public class ValidationError {
    private View mView;
    private String mErrorMessages;

    public ValidationError() {
    }

    public ValidationError( View mView, String mErrorMessages) {
        this.mView = mView;
        this.mErrorMessages = mErrorMessages;
    }

    public View getmView() {
        return mView;
    }

    public void setmView(View mView) {
        this.mView = mView;
    }

    public String getmErrorMessages() {
        return mErrorMessages;
    }

    public void setmErrorMessages(String mErrorMessages) {
        this.mErrorMessages = mErrorMessages;
    }
}
