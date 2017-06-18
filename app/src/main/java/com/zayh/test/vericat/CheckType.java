package com.zayh.test.vericat;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.zayh.test.vericat.validator.AbstractValidator;
import com.zayh.test.vericat.validator.TypeValidator;

import java.util.Map;

/**
 * Created by guozhk on 2017/6/18.
 */

class CheckType {
    private Map<String, AbstractValidator> mValidators;
    private String mType;
    private View mView;

    CheckType(Map<String, AbstractValidator> mValidators, String mType, View mView) {
        this.mValidators = mValidators;
        this.mType = mType;
        this.mView = mView;
    }

    /**
     * 判断是否允许跳过该验证规则
     *
     * @return
     */
    boolean canSkip() {
        // 如果不存在必需和接受规则，则值为空，则允许跳过
        if (!mValidators.containsKey(Rule.REQUIRED) && !mValidators.containsKey(Rule.ACCEPTED)) {
            Object value = value(mType);
            if (value == null) {
                return true;
            } else if (value instanceof String && ((String) value).length() == 0) {
                return true;
            }
        }
        return false;
    }

    private Object value(String mType) {
        switch (mType) {
            case TypeValidator.BOOLEAN:
                return booleanValue();
            case TypeValidator.DOUBLE:
                return doubleValue();
            case TypeValidator.FLOAT:
                return floatValue();
            case TypeValidator.INTEGER:
                return intValue();
            case TypeValidator.LONG:
                return longValue();
            case TypeValidator.SHORT:
                return shortValue();
            case TypeValidator.STRING:
            default:
                return stringValue();
        }
    }


    private Boolean booleanValue() {
        if (mView instanceof CompoundButton) {
            return ((CompoundButton) mView).isChecked();
        }
        return null;
    }

    private Double doubleValue() {

        try {
            return Double.parseDouble(stringValue());
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private Float floatValue() {
        try {
            return Float.parseFloat(stringValue());
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private Integer intValue() {
        try {
            return Integer.parseInt(stringValue());
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private Long longValue() {
        try {
            return Long.parseLong(stringValue());
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private Short shortValue() {
        try {
            return Short.parseShort(stringValue());
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private String stringValue() {
        if (mView instanceof EditText) {
            return String.valueOf(((EditText) mView).getText());
        }
        return null;
    }
}
