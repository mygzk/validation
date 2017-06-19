package com.zayh.test.validation;

import android.view.View;

import com.zayh.test.validation.validator.AbstractValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guozhk on 2017/6/18.
 */

public class Validator<T extends View> {
    private static final String TAG = Validator.class.getSimpleName();
    private List<Rule> mRules;
    private List<T> mViews;
    private T view;
    private ValidationListener mValidationListener;

    public void setmRules(List<Rule> mRules) {
        this.mRules = mRules;
    }

    public void setValidationListener(ValidationListener mValidationListener) {
        this.mValidationListener = mValidationListener;
    }

    public void valide() {
        if (mRules == null) {
            if (mValidationListener != null) {
                mValidationListener.onValid();
            }
            return;
        }
        if (mRules.size() == 0) {
            if (mValidationListener != null) {
                mValidationListener.onValid();
            }
            return;
        }
        view = null;
        List<ValidationError> errors = new ArrayList<>();
        for (int i = 0; i < mRules.size(); i++) {
            Rule rule = mRules.get(i);
            if (rule.canSkip()) {
                continue;
            }
            List<AbstractValidator> validators = rule.getAbstractValidators();
            if (validators != null && validators.size() > 0) {
                for (AbstractValidator valide : validators) {
                    if (!valide.isValid(rule.getValue())) {
                        ValidationError err = new ValidationError(rule.getView(), valide.getmValidationMsg());
                        errors.add(err);
                        ValidationError errorMSg = new ValidationError(rule.getView(), valide.getmValidationMsg());
                        rule.notifyHandler(false, errorMSg, true);
                        if (mViews != null && i < mViews.size()) {
                            if (view == null) {
                                view = mViews.get(i);
                            }
                        }
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
                if(view!=null){
                    mValidationListener.onInValidFirstView(view);
                }
            }
        }

    }

    public void addRule(Rule rule) {
        if (mRules == null) {
            mRules = new ArrayList<>();
        }
        if (!mRules.contains(rule) && rule != null) {
            mRules.add(rule);
        }
    }

    public void addRule(T view, Rule rule) {
        if (mViews == null) {
            mViews = new ArrayList<>();
        }
        if (!mViews.contains(view) && view != null) {
            mViews.add(view);
        }
        addRule(rule);
    }

    public T getView() {
        return view;
    }
}
