package sinaflashmoney.credit.sina.com.mylibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by liuc on 2017/4/13.
 */

public class ToastUtil {

    private static View view;

    /**
     * 之前显示的内容
     */
    private static String oldMsg;
    /**
     * Toast对象
     */
    private static Toast mToast = null;
    /**
     * 第一次时间
     */
    private static long oneTime = 0;
    /**
     * 第二次时间
     */
    private static long twoTime = 0;

    //压制警告
    @SuppressLint("InflateParams")
    public static void showToast(Context context,String message) {
        //设置弹出显示的时间
        view = LayoutInflater.from(context).inflate(R.layout.view_custom_no_image_toast,null);
        TextView tvToastTips = (TextView) view.findViewById(R.id.tv_toast_tips);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(32, 32, 32, 32);
        tvToastTips.setLayoutParams(layoutParams);
        tvToastTips.setText(message);

        if (mToast == null) {
            mToast = new Toast(context);
            initToast();
            //最后一步，show出来
            mToast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (message.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    initToast();
                    mToast.show();
                }
            } else {
                oldMsg = message;
                initToast();
                mToast.show();
            }
        }
        oneTime = twoTime;
    }

    private static void initToast() {
        mToast.setDuration(Toast.LENGTH_SHORT);
        //设置toast显示的位置
        mToast.setGravity(Gravity.CENTER, 0, 0);
        //设置布局
        mToast.setView(view);
    }
    /**
     * 使toast不在显示
     */
    public static void cancleMyToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }


}
