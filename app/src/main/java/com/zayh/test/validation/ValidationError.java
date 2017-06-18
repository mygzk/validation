package com.zayh.test.validation;

import android.view.View;

import java.util.List;
import java.util.Map;

/**
 * Created by guozhk on 2017/6/18.
 */

public class ValidationError {
    private String mName;
    private View mView;
    private List<String> mErrorMessages;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public View getmView() {
        return mView;
    }

    public void setmView(View mView) {
        this.mView = mView;
    }

    public List<String> getmErrorMessages() {
        return mErrorMessages;
    }

    public void setmErrorMessages(List<String> mErrorMessages) {
        this.mErrorMessages = mErrorMessages;
    }
}
