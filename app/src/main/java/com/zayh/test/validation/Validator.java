package com.zayh.test.validation;

import com.zayh.test.validation.validator.AbstractValidator;

import org.xml.sax.ErrorHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guozhk on 2017/6/18.
 */

public class Validator {
    private static final String TAG = Validator.class.getSimpleName();
    private List<Rule> mRules;
    private ValidationListener mValidationListener;

    public void setmRules(List<Rule> mRules) {
        this.mRules = mRules;
    }

    public void setmValidationListener(ValidationListener mValidationListener) {
        this.mValidationListener = mValidationListener;
    }

    public void valide() {
        if (mRules == null) {
            return;
        }
        if (mRules.size() == 0) {
            return;
        }
        List<ValidationError> errors = new ArrayList<>();
        for (Rule rule : mRules) {
            if (rule.canSkip()) {
                continue;
            }
            List<AbstractValidator> validators = rule.getAbstractValidators();
            if (validators != null && validators.size() > 0) {
                for (AbstractValidator valide : validators) {
                    if (!valide.isValid(rule.getValue())) {
                        ValidationError err = new ValidationError(rule.getView(), valide.getmValidationMsg());
                        errors.add(err);
                        break;
                    }
                }

            } else {
                continue;
            }
        }
        if (mValidationListener != null) {
            if (errors.size() == 0) {
                mValidationListener.onValid();
            } else {
                mValidationListener.onInValid(errors);
            }
        }

    }
}
