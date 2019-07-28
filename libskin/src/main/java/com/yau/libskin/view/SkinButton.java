package com.yau.libskin.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.yau.libskin.R;
import com.yau.libskin.base.ISkinView;
import com.yau.libskin.bean.AttrBean;

/**
 * author: yau
 * time: 2019/07/27 12:45
 * desc:
 */
public class SkinButton extends AppCompatButton implements ISkinView {

    private AttrBean mAttrBean;

    public SkinButton(Context context) {
        this(context, null);
    }

    public SkinButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray =
                context.obtainStyledAttributes(attrs, R.styleable.SkinButton, defStyleAttr, 0);
        mAttrBean = new AttrBean(typedArray, R.styleable.SkinButton);
        typedArray.recycle();
    }

    @Override
    public void onSkinChange() {
        int textColorResId = mAttrBean.getResById(
                R.styleable.SkinButton[R.styleable.SkinButton_android_textColor]);
        if (textColorResId > 0) {
            ColorStateList textColorStateList =
                    ContextCompat.getColorStateList(getContext(), textColorResId);
            setTextColor(textColorStateList);
        }

        int backgroundResId =
                mAttrBean.getResById(R.styleable.SkinButton[R.styleable.SkinButton_android_background]);
        if (backgroundResId > 0) {
            Drawable drawable = ContextCompat.getDrawable(getContext(), backgroundResId);
            setBackgroundDrawable(drawable);
        }

        int typefaceResId =
                mAttrBean.getResById(R.styleable.SkinButton[R.styleable.SkinButton_customTypeFace]);
        if (typefaceResId > 0) {
            String typefacePath = getContext().getString(typefaceResId);
            if (TextUtils.isEmpty(typefacePath)) {
                setTypeface(Typeface.DEFAULT);
            } else {
                Typeface typeface = Typeface.createFromAsset(
                        getContext().getAssets(),
                        typefacePath);
                setTypeface(typeface);
            }
        }
    }
}
