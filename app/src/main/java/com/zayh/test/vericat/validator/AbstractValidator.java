package com.zayh.test.vericat.validator;

/**
 * Created by guozhk on 2017/6/18.
 */

public abstract class AbstractValidator<Value> {

    public abstract boolean isValid(Value value);

}
