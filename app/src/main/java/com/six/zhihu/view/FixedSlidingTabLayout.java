package com.six.zhihu.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.six.zhihu.R;

public class FixedSlidingTabLayout extends SlidingTabLayout {
    public FixedSlidingTabLayout(Context context) {
        super(context);
    }

    public FixedSlidingTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedSlidingTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        setTextsize(17);
        TextView tv_tab_title = this.getTitleView(getCurrentTab());
        if (tv_tab_title != null) {
            tv_tab_title.setTextSize(23);
        }
    }
    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
        setTextsize(17);
        TextView tv_tab_title = this.getTitleView(getCurrentTab());
        if (tv_tab_title != null) {
            tv_tab_title.setTextSize(23);
        }
    }

}
