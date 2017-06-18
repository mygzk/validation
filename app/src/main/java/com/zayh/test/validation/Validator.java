package com.zayh.test.validation;

import org.xml.sax.ErrorHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guozhk on 2017/6/18.
 */

public class Validator {
    private static final String TAG = Validator.class.getSimpleName();
    private List<Rule> mRules;

    public Validator() {
        this(null);
    }

    public Validator(ErrorHandler errorHandler) {
        this.mRules = new ArrayList<>();
    }
}
