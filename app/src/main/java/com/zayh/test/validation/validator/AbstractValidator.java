package com.zayh.test.validation.validator;

/**
 * Created by guozhk on 2017/6/18.
 */

public abstract class AbstractValidator<Value> {
    private String mValidationMsg;

    public AbstractValidator(String mValidationMsg) {
        this.mValidationMsg = mValidationMsg;
    }

    public abstract boolean isValid(Value value);

    public String getmValidationMsg() {
        return mValidationMsg;
    }

    public void setmValidationMsg(String mValidationMsg) {
        this.mValidationMsg = mValidationMsg;
    }
}
