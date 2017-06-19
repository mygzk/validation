package com.zayh.test.validation.validator;

import java.util.regex.Pattern;

/**
 * Created by guozhk on 2017/6/18.
 */

public class RegularStrContant {
    public static final Pattern EMAIL_ADDRESS =  Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
    public static final Pattern IP_ADDRESS = android.util.Patterns.IP_ADDRESS;
    public static final Pattern WEB_URL = android.util.Patterns.WEB_URL;
    public static final Pattern DOMAIN_NAME = android.util.Patterns.DOMAIN_NAME;
    public static final Pattern ALPHA = Pattern.compile("^[A-Za-z]+$");
    public static final Pattern ALPHA_DASH = Pattern.compile("^\\w+$");
    public static final Pattern ALPHA_NUM = Pattern.compile("^[A-Za-z0-9]+$");
}
