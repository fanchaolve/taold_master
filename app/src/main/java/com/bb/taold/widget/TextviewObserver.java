package com.bb.taold.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.bb.taold.R;

import java.util.Observable;

/**
 * Created by Administrator on 2017/2/24.
 */

public class TextviewObserver extends TextView implements java.util.Observer {


    private Context mContext;

    private int resourceId;

    private int index;


    public TextviewObserver(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public TextviewObserver(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.TextviewObserver, defStyleAttr, 0);
        resourceId = a.getResourceId(R.styleable.TextviewObserver_format_string, R.string.howPrice);
        index = a.getInteger(R.styleable.TextviewObserver_getnumber, 0);
        a.recycle();
    }

    public TextviewObserver(Context context) {
        this(context, null, 0);

    }

    @Override
    public void update(Observable o, Object arg) {

        String[] updates = (String[]) arg;

        setText(mContext.getString(resourceId, updates[index]));
    }
}
