package nk.peekimageview;

import android.content.Context;

import java.util.List;

/**
 * Created by YuHua on 2017/3/24 14:23
 * Copyright (c) 2017, www.saidian.com All Rights Reserved.
 * 描述：
 */

public class ImgResult {
    private List<Item> results;
    private String imgRootPath;
    private String imgOriginalPath;
    private String imgCropPath;

    public ImgResult(Context context) {
        imgRootPath = FileUtil.getRootFile(context).getPath();
        imgOriginalPath = imgRootPath + "/" + FileUtil.PATH_SUFFIX_ORIGINAL;
        imgCropPath = imgRootPath + "/" + FileUtil.PATH_SUFFIX_CROP;
    }

    public List<Item> getResults() {
        return results;
    }

    public void setResults(List<Item> results) {
        this.results = results;
    }

    public String getImgRootPath() {
        return imgRootPath;
    }

    public String getImgOriginalPath() {
        return imgOriginalPath;
    }

    public String getImgCropPath() {
        return imgCropPath;
    }

    public static class Item {
        private boolean hasCrop;
        private String md5;

        public Item(boolean hasCrop, String md5) {
            this.hasCrop = hasCrop;
            this.md5 = md5;
        }

        public boolean isHasCrop() {
            return hasCrop;
        }

        public void setHasCrop(boolean hasCrop) {
            this.hasCrop = hasCrop;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Item)) return false;

            Item item = (Item) o;

            if (hasCrop != item.hasCrop) return false;
            return md5 != null ? md5.equals(item.md5) : item.md5 == null;

        }

        @Override
        public int hashCode() {
            return md5 != null ? md5.hashCode() : 0;
        }
    }
}
