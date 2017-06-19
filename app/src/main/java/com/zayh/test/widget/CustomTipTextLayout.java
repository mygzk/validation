package com.zayh.test.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zayh.test.R;
import com.zayh.test.validation.ErrorHandler;
import com.zayh.test.validation.Rule;
import com.zayh.test.validation.ValidationError;

/**
 * Created by guozhk on 2017/6/18.
 */

public class CustomTipTextLayout extends FrameLayout {
    private final int TYPE_EDIT = 0;
    private final int TYPE_TV = 1;
    private TextView tvtip;
    private TextView tvErrortip;
    private EditText etInput;
    private TextView tvContent;
    private ImageView imgArrowBelow;
    private FrameLayout flContent;

    private int mType = TYPE_EDIT;
    private boolean mTipShow = false;
    private int mTipTvColor;
    private int mErrorTipTvColor;
    private boolean mValidator = false;
    private String mTipField;
    private Rule mRule;


    public CustomTipTextLayout(@NonNull Context context) {
        this(context, null);
    }

    public CustomTipTextLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTipTextLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray attributesArray = context.obtainStyledAttributes(attrs,
                R.styleable.form_item, defStyleAttr, 0);
        mType = attributesArray.getInt(R.styleable.form_item_type, TYPE_EDIT);
        mTipShow = attributesArray.getBoolean(R.styleable.form_item_tip_show, false);
        mTipTvColor = attributesArray.getColor(R.styleable.form_item_tip_color, 0);
        mErrorTipTvColor = attributesArray.getColor(R.styleable.form_item_tip_error_color, 0);
        mValidator = attributesArray.getBoolean(R.styleable.form_item_auto_validator, false);
        mTipField = attributesArray.getString(R.styleable.form_item_tip_filed);
        attributesArray.recycle();
        iniView();
    }

    private void iniView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.custom_item_form, this, true);
        flContent = (FrameLayout) view.findViewById(R.id.form_item_content_layout);
        tvtip = (TextView) view.findViewById(R.id.form_item_tv_tip);
        tvErrortip = (TextView) view.findViewById(R.id.form_item_tv_error_tip);
        etInput = (EditText) view.findViewById(R.id.form_item_content_et);
        tvContent = (TextView) view.findViewById(R.id.form_item_content_tv);
        imgArrowBelow = (ImageView) view.findViewById(R.id.form_item_content_img_below);


        if (mTipShow) {
            tvtip.setVisibility(VISIBLE);
        }
        if (mType == TYPE_EDIT) {
            etInput.setVisibility(VISIBLE);
            tvContent.setVisibility(GONE);
            imgArrowBelow.setVisibility(GONE);
            etInput.addTextChangedListener(watcher);
            etInput.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        flContent.setBackgroundResource(R.drawable.bg_blue_stroke);
                        etInput.setTextColor(getResources().getColor(R.color.form_font_corlor1));
                    } else {
                        flContent.setBackgroundResource(R.drawable.bg_gray);
                    }
                }
            });
        } else {
            etInput.setVisibility(GONE);
            tvContent.setVisibility(VISIBLE);
            imgArrowBelow.setVisibility(VISIBLE);
            tvContent.addTextChangedListener(watcher);
        }
        if (mTipTvColor != 0) {
            tvtip.setTextColor(mTipTvColor);
        }
        if (mErrorTipTvColor != 0) {
            tvErrortip.setTextColor(mErrorTipTvColor);
        }


        etInput.setOnTouchListener(mTouchListener);
        tvContent.setOnTouchListener(mTouchListener);
        initValidtion();
    }


    public void checkErr(String msg, boolean changebg) {
        tvErrortip.setVisibility(VISIBLE);
        tvErrortip.setText(msg + "");
        if (changebg) {
            flContent.setBackgroundResource(R.drawable.bg_red);
            etInput.setTextColor(getResources().getColor(R.color.white));
            tvContent.setTextColor(getResources().getColor(R.color.white));
        }
    }

    public void checkSucc() {
        flContent.setBackgroundResource(R.drawable.bg_gray);
        tvErrortip.setVisibility(GONE);
    }


    private TextWatcher watcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (etInput.getVisibility() == VISIBLE &&
                    etInput.hasFocus()) {
                flContent.setBackgroundResource(R.drawable.bg_blue_stroke);
            } else {
                flContent.setBackgroundResource(R.drawable.bg_gray);
            }
            tvErrortip.setVisibility(GONE);

            if (mValidator) {
                mRule.validation();
            }

            String searChTv = s.toString();
            if (TextUtils.isEmpty(searChTv)) {
                tvtip.setVisibility(GONE);
            } else {
                tvtip.setVisibility(VISIBLE);
            }
        }
    };

    private void initValidtion() {
        mRule = Rule.with(etInput, mTipField).required().email();
        mRule.setValidationListener(mErrorHandler);
    }

    private ErrorHandler mErrorHandler = new ErrorHandler() {
        @Override
        public void onValid() {
            checkSucc();
        }

        @Override
        public void onInValid(ValidationError error, boolean changebg) {
            checkErr(error.getmErrorMessages(), changebg);
        }
    };

    private OnTouchListener mTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (etInput.getVisibility() == VISIBLE) {
                if (etInput.hasFocus()) {
                    etInput.setTextColor(getResources().getColor(R.color.form_font_corlor1));
                    flContent.setBackgroundResource(R.drawable.bg_blue_stroke);
                }
            }

            tvContent.setTextColor(getResources().getColor(R.color.form_font_corlor1));

            return false;
        }
    };

    public Rule getRule() {
        return mRule;
    }
}
