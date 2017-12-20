package com.yejy.liketoastapp.ui;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.yejy.liketoastapp.R;

/**
 * 类描述：仿系统Toast
 * 创建人：yejinyun
 * 创建时间：2017/12/19 9:12
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CloneToast {

    private final Handler mHandler = new Handler();
    private View mView;
    private WindowManager mWM;
    private WindowManager.LayoutParams mParams;
    private int duration;

    private CloneToast(Context context, CharSequence message, int duration) {
        mWM = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mParams = new WindowManager.LayoutParams();
        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.format = PixelFormat.TRANSLUCENT;
        mParams.windowAnimations = R.style.AnimationToast;
        mParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        mParams.setTitle("Toast");
        mParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        mParams.verticalWeight = 1.0f;
        mParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        mParams.y = context.getResources().getDimensionPixelSize(R.dimen.margin_64);
        LayoutInflater inflate = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflate.inflate(R.layout.view_toast, null);
        TextView showTextView = (TextView) mView.findViewById(R.id.text);
        showTextView.setText(message);
        if (duration == Toast.LENGTH_LONG) {
            this.duration = 3000;
        } else if (duration == Toast.LENGTH_SHORT) {
            this.duration = 1000;
        } else {
            this.duration = duration;
        }
    }

    public static CloneToast makeText(Context context, CharSequence message, int duration) {
        return new CloneToast(context, message, duration);
    }

    public void show() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mView.getParent() != null) {
                    mWM.removeView(mView);
                }
                mWM.addView(mView, mParams);
            }
        });
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hide();
            }
        }, duration);
    }

    public synchronized void hide() {
        if (mView != null) {
            if (mView.getParent() != null) {
                mWM.removeView(mView);
            }
            mView = null;
        }
    }
}
