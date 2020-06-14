package com.muy.muysamples.popupwindow;

import java.io.Serializable;

/**
 * Created by James on 2020-03-27.
 * Desc: The version update data
 */
public class UpdateData implements Serializable {

    private static final long serialVersionUID = 1L;

    public String updateLog;
    public String versionName;
    public int versionCode;
    public String path;
    public long apkSize;
    public String apkHash;
    public long diffSize;
}
