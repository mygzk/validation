package com.zayh.test.validation.validator;

import java.util.regex.Pattern;

/**
 * Created by guozhk on 2017/6/18.
 */

public class RequiredValidator extends AbstractValidator<CharSequence> {

    public RequiredValidator(String mValidationMsg) {
        super(mValidationMsg);
    }

    @Override
    public boolean isValid(CharSequence charSequence) {
        return !(charSequence == null || charSequence.length() == 0);
    }
}
