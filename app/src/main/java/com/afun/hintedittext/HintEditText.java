package com.afun.hintedittext;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by qingxi.wang on 2016/2/2.
 */
public class HintEditText extends LinearLayout {
    private Context mContext;
    private EditText mEditText;


    public HintEditText(Context context) {
        super(context);
        mContext = context;
    }

    public HintEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public HintEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }


    private EditText findEditTextChild() {
        if (getChildCount() > 0 && getChildAt(0) instanceof EditText) {
            return (EditText) getChildAt(0);
        }
        return null;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mEditText = findEditTextChild();
        if (mEditText == null)
            return;

        setView();
    }

    public void setView() {
        setOrientation(VERTICAL);

        final TextView warning = new TextView(mContext);
        LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        warning.setLayoutParams(textViewLp);
        warning.setText("不能为空");
        warning.setTextColor(mContext.getResources().getColor(R.color.red));
        int textSize = DensityUtil.sp2px(mContext, (float) 4.0);
        warning.setTextSize(textSize);
        warning.setVisibility(View.INVISIBLE);
        addView(warning);

        mEditText.setSingleLine();

        mEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false) {
                    if (TextUtils.isEmpty(mEditText.getText().toString())) {
                        warning.setVisibility(View.VISIBLE);
                    } else {
                        warning.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }

}
