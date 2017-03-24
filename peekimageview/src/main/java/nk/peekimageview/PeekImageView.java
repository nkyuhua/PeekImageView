package nk.peekimageview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import java.io.File;

import static android.app.Activity.RESULT_OK;

/**
 * Created by YuHua on 2017/3/24 10:14
 * Copyright (c) 2017, www.saidian.com All Rights Reserved.
 * 描述：
 */

public class PeekImageView extends ImageView implements PopupWindowFactory.OnPopWindowClick {
    public static final int REQUEST_CODE = 0x1;
    private ImgResult result;
    private PopupWindow mPopupWindow;
    private boolean needCrop;

    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupWindow();
        }
    };

    public PeekImageView(Context context) {
        this(context, null);
    }

    public PeekImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PeekImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, 0, 0);
    }

    @TargetApi(21)
    public PeekImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }


    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        initAttrs(context, attrs, defStyleAttr, defStyleRes);
        initBackGround();
        initListener();
        initPopupWindow();
        initRootFile(context);
    }

    private void initRootFile(Context context) {
        result = new ImgResult(context);

    }

    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PeekImageView,
                defStyleAttr, defStyleRes);
        needCrop = typedArray.getBoolean(R.styleable.PeekImageView_needCrop, false);
        typedArray.recycle();
    }

    private void initBackGround() {
        setBackgroundResource(R.drawable.def_img_bg);
    }

    private void initListener() {
        setOnClickListener(clickListener);
    }

    private void initPopupWindow() {
        PopupWindowFactory factory = PopupWindowFactory.getInstance();
        mPopupWindow = factory.create(getContext(), this);
    }

    private void showPopupWindow() {
        mPopupWindow.showAsDropDown(this);
    }

    @Override
    public void onPhone() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File mPhoneFile = FileUtil.createImgFile(result.getImgRootPath());
        Uri uri = FileProvider.getUriForFile(getContext(), "com.app.provider_camera.camera",
                mPhoneFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        ((Activity) getContext()).startActivityForResult(intent, REQUEST_CODE);
    }

    //// TODO: 2017/3/24 优化 getContext==Activity
    @Override
    public void onLocal() {
        Intent local = new Intent(Intent.ACTION_PICK);
        local.setType("image/*");
        local.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ((Activity) getContext()).startActivityForResult(local, REQUEST_CODE);
    }

    public void dispatchActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PeekImageView.REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                String realPath = BitmapUtil.getPath(getContext(), data.getData());
                Bitmap bitmap = BitmapUtil.compress(realPath);
                setImageBitmap(bitmap);
            }
        }
    }

}
