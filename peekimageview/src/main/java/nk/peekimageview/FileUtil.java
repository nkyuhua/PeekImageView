package nk.peekimageview;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by YuHua on 2017/3/24 14:13
 * Copyright (c) 2017, www.saidian.com All Rights Reserved.
 * 描述：
 */

public class FileUtil {
    public static final String PATH_ROOT = "/peekimg";
    public static final String PATH_SUFFIX_ORIGINAL = "/original";
    public static final String PATH_SUFFIX_CROP = "/crop";
    public static final String IMG_SUFFIX = ".jpg";


    /**
     * @param context
     * @return /peekimg
     */
    public static File getRootFile(Context context) {
        File parentDir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            parentDir = context.getExternalFilesDir(null);
        } else
            parentDir = context.getCacheDir();
        File rootFile = createFile(parentDir.getPath(), PATH_ROOT);
        return rootFile;
    }

    public static File createImgFile(String rootPath) {
        Long curTime = System.currentTimeMillis();
        File originalFile = new File(rootPath + PATH_SUFFIX_ORIGINAL + "/" + curTime + IMG_SUFFIX);
        if (!originalFile.exists())
            originalFile.mkdirs();
        return originalFile;
    }

    public static File createImgCropFile(String rootPath) {
        Long curTime = System.currentTimeMillis();
        File cropFile = new File(rootPath + PATH_SUFFIX_CROP + "/" + curTime + IMG_SUFFIX);
        if (cropFile != null && !cropFile.exists())
            cropFile.mkdirs();
        return cropFile;
    }

    public static File createFile(String parentPath, String childPath) {
        File file = new File(parentPath, childPath);
        if (!file.exists())
            file.mkdirs();
        return file;
    }

}
