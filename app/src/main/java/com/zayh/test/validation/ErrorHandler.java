package com.zayh.test.validation;

import java.util.List;

/**
 * Created by guozhk on 2017/6/19.
 */

public interface ErrorHandler {

    void onValid();

    void onInValid(ValidationError error,boolean changebg);
}
