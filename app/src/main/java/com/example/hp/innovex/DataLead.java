package com.example.hp.innovex;

import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by HP on 23-06-2018.
 */
@SuppressWarnings("serial")
public class DataLead implements Serializable {

    public String qsn;
    public String detail;
    public String video;
    public String uploadPhoto;
    public boolean enable;

    public DataLead(Boolean enable) {
        this.enable = enable;
    }

    public String getQsn() {
        return "QSN:5214236";
    }

    public String getDetail() {
        return "General Electrical work light fitting, celing, fan, installation, " +
                "MCB repair, TV Installation";
    }

    public String getVideo() {
        return "2 Videos,6 Photos";
    }

    public String getUploadPhoto() {
        return "20Nov,2017";
    }


    public void setQsn(String qsn) {
        this.qsn = "QSN:5214236";
    }

    public void setDetail(String detail) {
        this.detail = "General Electrical work light fitting, celing, fan, installation, MCB repair, TV Installation";
    }

    public void setVideo(String video) {
        this.video = "2 Videos,6 Photos";
    }

    public void setUploadPhoto(String uploadPhoto) {
        this.uploadPhoto = "20Nov,2017";
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
