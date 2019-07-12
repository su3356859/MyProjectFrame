package com.demo.android.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 软键盘Utils
 * @author lh
 */
public class SoftKeybordUtils {
    /**
     * 关闭软键盘
     * @param activity
     */
    public static void hide(Activity activity) {
        if (activity == null) return;
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    /**
     * 强制显示软键盘
     * @param activity
     */
    public static void show(Activity activity) {
        if (activity == null) return;
        View view = activity.getWindow().peekDecorView();

        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            boolean isOpen=inputmanger.isActive();//isOpen若返回true，则表示输入法打开
            if (!isOpen){
                inputmanger.showSoftInput(view,InputMethodManager.SHOW_FORCED);
            }
        }
    }

    /**
     * 输入法在窗口上已经显示，则隐藏，反之则显示
     * @param activity
     */
    public static void toggle(Activity activity) {
        if (activity == null) return;
        View view = activity.getWindow().peekDecorView();

        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }










    //隐藏虚拟键盘
    public static void HideKeyboard(View v)
    {
        InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );
        if ( imm.isActive( ) ) {
            imm.hideSoftInputFromWindow( v.getApplicationWindowToken( ) , 0 );

        }
    }

    //显示虚拟键盘
    public static void ShowKeyboard(View v)
    {
        InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );

        imm.showSoftInput(v,InputMethodManager.SHOW_FORCED);

    }

    //强制显示或者关闭系统键盘
    public static void KeyBoard(final EditText txtSearchKey,final String status)
    {

        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run()
            {
                InputMethodManager m = (InputMethodManager)
                        txtSearchKey.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if(status.equals("open"))
                {
                    m.showSoftInput(txtSearchKey,InputMethodManager.SHOW_FORCED);
                }
                else
                {
                    m.hideSoftInputFromWindow(txtSearchKey.getWindowToken(), 0);
                }
            }
        }, 300);
    }

    //通过定时器强制隐藏虚拟键盘
    public static void TimerHideKeyboard(final View v)
    {
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run()
            {
                InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );
                if ( imm.isActive( ) )
                {
                    imm.hideSoftInputFromWindow( v.getApplicationWindowToken( ) , 0 );
                }
            }
        }, 10);
    }
    //输入法是否显示着
    public static boolean KeyBoard(EditText edittext)
    {
        boolean bool = false;
        InputMethodManager imm = ( InputMethodManager ) edittext.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );
        if (imm.isActive( ))
        {
            bool = true;
        }
        return bool;

    }










}
