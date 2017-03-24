package nk.peekimageview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by YuHua on 2017/3/24 12:06
 * Copyright (c) 2017, www.saidian.com All Rights Reserved.
 * 描述：
 */

public class PopupWindowFactory {

    private static final class Holder {
        private static final PopupWindowFactory FACTORY = new PopupWindowFactory();
    }

    private PopupWindowFactory() {
    }

    public static PopupWindowFactory getInstance() {
        return Holder.FACTORY;
    }

    public PopupWindow create(Context context, final OnPopWindowClick onClick) {
        final PopupWindow popupWindow = new PopupWindow();
        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_window, null);
        initPhoneWindow(popupWindow, contentView);
        TextView tvPhone = (TextView) contentView.findViewById(R.id.tv_phone);
        TextView tvLocal = (TextView) contentView.findViewById(R.id.tv_local);
        tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick != null) {
                    onClick.onPhone();
                }
                popupWindow.dismiss();
            }
        });
        tvLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick != null) {
                    onClick.onLocal();
                }
                popupWindow.dismiss();
            }
        });
        return popupWindow;
    }

    private void initPhoneWindow(PopupWindow popupWindow, View contentView) {
        popupWindow.setContentView(contentView);
        popupWindow.setWidth(LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
    }

    public interface OnPopWindowClick {
        void onPhone();

        void onLocal();
    }

}
