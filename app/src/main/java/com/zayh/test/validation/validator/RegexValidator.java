package com.zayh.test.validation.validator;

import java.util.regex.Pattern;

/**
 * Created by guozhk on 2017/6/18.
 */

public class RegexValidator extends AbstractValidator<CharSequence> {
    private Pattern pattern;

    public RegexValidator(String mValidationMsg, Pattern pattern) {
        super(mValidationMsg);
        this.pattern = pattern;
    }

    public RegexValidator(String mValidationMsg,String pattern) {
        super(mValidationMsg);
        this.pattern = Pattern.compile(pattern);
    }

    @Override
    public boolean isValid(CharSequence charSequence) {
        return false;
    }


    public static class Patterns {
        public static final Pattern EMAIL_ADDRESS = android.util.Patterns.EMAIL_ADDRESS;
        public static final Pattern IP_ADDRESS = android.util.Patterns.IP_ADDRESS;
        public static final Pattern WEB_URL = android.util.Patterns.WEB_URL;
        public static final Pattern DOMAIN_NAME = android.util.Patterns.DOMAIN_NAME;
        public static final Pattern ALPHA = Pattern.compile("^[A-Za-z]+$");
        public static final Pattern ALPHA_DASH = Pattern.compile("^\\w+$");
        public static final Pattern ALPHA_NUM = Pattern.compile("^[A-Za-z0-9]+$");

    }
}
