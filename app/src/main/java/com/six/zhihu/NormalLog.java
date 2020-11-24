package com.six.zhihu;

import android.util.Log;

public class NormalLog {
    public static void log(Class clazz, Integer level, String method, Integer type, Object ...args){
        String log = method + " ";
        switch (type){
            case 0:log = log+"start ";break;
            case 1: log = log+"end ";break;
            case 3: log = log+"return ";break;
        }
        for (Object arg: args){
            log = log + String.valueOf(arg)+" ";
        }
        switch (level){
            case 0: Log.v(clazz.getName(), log);break;
            case 1: Log.d(clazz.getName(), log);break;
            case 2: Log.i(clazz.getName(), log);break;
            case 3:Log.w(clazz.getName(), log); break;
            case 4:Log.e(clazz.getName(),log);break;
        }
    }
}
