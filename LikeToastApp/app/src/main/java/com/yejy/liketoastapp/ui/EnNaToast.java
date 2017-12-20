package com.yejy.liketoastapp.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.yejy.liketoastapp.R;

/**
 * 类描述：用PopWindow模拟系统Toast
 * 创建人：yejinyun
 * 创建时间：2017/12/19 9:12
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class EnNaToast {

    private PopupWindow popupWindow;
    private int duration;
    private Activity activity;
    private Handler handler = new Handler();

    private EnNaToast(Activity activity, CharSequence text, int duration) {
        popupWindow = new PopupWindow(activity);
        popupWindow.setAnimationStyle(R.style.AnimationToast);
        popupWindow.setBackgroundDrawable(null);
        View view = LayoutInflater.from(activity).inflate(R.layout.view_toast, null);
        TextView showTextView = view.findViewById(R.id.text);
        showTextView.setText(text);
        popupWindow.setContentView(view);
        if (duration == Toast.LENGTH_LONG) {
            this.duration = 3000;
        } else if (duration == Toast.LENGTH_SHORT){
            this.duration = 1000;
        } else {
            this.duration = duration;
        }
        this.activity = activity;
    }

    public static EnNaToast makeText(Activity activity, CharSequence text, int duration) {
        return new EnNaToast(activity, text, duration);
    }

    public void show() {
        popupWindow.showAtLocation(getActivityRoot(activity), Gravity.CENTER, 0, getDisplayMetrics().heightPixels / 4);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
            }
        }, duration);
    }

    public void cancel() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    /**
     * 获取activity的根view
     */
    private View getActivityRoot(Activity activity) {
        return ((ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT)).getChildAt(0);
    }

    /**
     * 获取 DisplayMetrics
     */
    private DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) activity.getApplicationContext().getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
}
