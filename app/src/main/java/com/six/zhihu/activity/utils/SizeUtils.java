package com.six.zhihu.activity.utils;

import android.content.Context;

public class SizeUtils {
    public static Integer dp2px(Context context, Float dpValue) {
        return (int)(context.getResources().getDisplayMetrics().density * dpValue + 0.5f);
    }

    public static Integer px2dp(Context context, Float pxValues) {
        return (int)(pxValues / context.getResources().getDisplayMetrics().density + 0.5f);
    }
}
