package com.tracotech.customui;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tracotech.tracoshop.R;

/**
 * Created by vishalm on 04/07/18.
 */
public class CustomTextView extends AppCompatTextView {
    public CustomTextView(Context context) {
        super(context);
        init(null);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        if(attrs != null){
            TypedArray attributes = getContext().obtainStyledAttributes(attrs,
                    R.styleable.font_style, 0, 0);
            String fontStyle = attributes.getString(R.styleable.font_style_font_style);
            if(fontStyle != null && fontStyle.equalsIgnoreCase("medium"))
                setTypeface(ResourcesCompat.getFont(getContext(), R.font.roboto_medium));
            else if(fontStyle != null && fontStyle.equalsIgnoreCase("light"))
            setTypeface(ResourcesCompat.getFont(getContext(), R.font.roboto_light));
            else if(fontStyle != null && fontStyle.equalsIgnoreCase("bold"))
                setTypeface(ResourcesCompat.getFont(getContext(), R.font.roboto_bold));
            else setTypeface(ResourcesCompat.getFont(getContext(), R.font.roboto_medium));
        }
         else
             setTypeface(ResourcesCompat.getFont(getContext(), R.font.roboto_medium));
    }
}
